package org.apache.felix.configadmin.plugin.complexjson;

import org.osgi.service.component.annotations.ComponentPropertyType;

@ComponentPropertyType
public @interface RequireComplexJsonConfigurationPlugin {
	public static final String PREFIX_ = Constants.PLUGIN_ID;
	
	boolean enable() default true;
}