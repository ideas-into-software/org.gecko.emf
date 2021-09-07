package org.apache.felix.configadmin.plugin.complexjson;

import java.io.IOException;
import java.io.StringReader;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import org.apache.felix.cm.json.ConfigurationReader;
import org.apache.felix.cm.json.Configurations;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;

public class ComplexJsonConfigurationPlugin implements org.osgi.service.cm.ConfigurationPlugin {

	@Override
	public void modifyConfiguration(ServiceReference<?> reference, Dictionary<String, Object> properties) {

		if (Boolean.FALSE.equals(reference.getProperty(Constants.PLUGIN_ID + ".enable"))) {
			return;
		}
		try {
			process(properties);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println(properties);

	}

	private Logger getLog() {
		return Activator.LOG;
	}

	private void process(Dictionary<String, Object> properties) throws IOException {
		Object pid = properties.get(org.osgi.framework.Constants.SERVICE_PID);
		if (pid == null) {
			pid = "nested";

		}
		for (Enumeration<String> keys = properties.keys(); keys.hasMoreElements();) {
			String key = keys.nextElement();
			Object val = properties.get(key);
			if (val instanceof String) {
				Object newVal = getNewValue(key, (String) val);
				if (newVal != null && !newVal.equals(val)) {
					properties.put(key, newVal);
					getLog().info("Replaced value of configuration property '{}' for PID {}", key, pid);
				}
			} else if (val instanceof String[]) {
				String[] array = (String[]) val;
				String[] newArray = null;
				for (int i = 0; i < array.length; i++) {
					Object newVal = getNewValue(key, array[i]);
					if (newVal != null && !newVal.equals(array[i])) {
						if (newArray == null) {
							newArray = new String[array.length];
							System.arraycopy(array, 0, newArray, 0, array.length);
						}
						newArray[i] = newVal.toString();
					}
				}
				if (newArray != null) {
					properties.put(key, newArray);
					getLog().info("Replaced value of configuration property '{}' for PID {}", key, pid);
				}
			}
		}
	}

	private Object getNewValue(final String key, final String value) throws IOException {
		final Object result = jsonStringToMap(key, value);
		if (value.equals(result)) {
			return null;
		}

		return result;
	}

	private Dictionary<String, Object> jsonStringToMap(String key, String value) throws IOException {

		if (!value.startsWith("{") && !value.endsWith("}")) {
			return null;

		}

		Map<String, Object> map = Configurations.buildReader()//
				.withIdentifier(UUID.randomUUID().toString())//
				.verifyAsBundleResource(true)//
				.build(new StringReader(value))//
				.readConfigurationResource()//
				.getProperties();

		Dictionary<String, Object> dict = new Hashtable<String, Object>(map);
		process(dict);
		return dict;

	}
}
