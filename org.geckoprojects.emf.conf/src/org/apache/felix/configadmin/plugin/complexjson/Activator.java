package org.apache.felix.configadmin.plugin.complexjson;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.annotation.bundle.Header;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Header(name=Constants.BUNDLE_ACTIVATOR, value="${@class}")
public class Activator implements BundleActivator {

   

    static final int PLUGIN_RANKING = 500;

    static final Logger LOG = LoggerFactory.getLogger(ComplexJsonConfigurationPlugin.class);

    @Override
    public void start(BundleContext context) throws Exception {


        ConfigurationPlugin plugin = new ComplexJsonConfigurationPlugin();
        Dictionary<String, Object> props = new Hashtable<>();
        props.put(ConfigurationPlugin.CM_RANKING, PLUGIN_RANKING);
        props.put(org.apache.felix.configadmin.plugin.complexjson.Constants.KEY_CONFIG_PLUGIN_ID, org.apache.felix.configadmin.plugin.complexjson.Constants.PLUGIN_ID);

        context.registerService(ConfigurationPlugin.class, plugin, props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Service is automatically unregistered when bundle is stopped.
    }
}