package org.geckoprojects.emf.conf;

import java.util.Map;

import org.apache.felix.configadmin.plugin.complexjson.RequireComplexJsonConfigurationPlugin;
import org.geckoprojects.emf.conf.My.FU;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.configurator.annotations.RequireConfigurator;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Designate(ocd = FU.class)
@Component(configurationPid = "my.pid", configurationPolicy = ConfigurationPolicy.REQUIRE)
@RequireConfigurator
@RequireConfigurationAdmin
@RequireComplexJsonConfigurationPlugin
public class My {

//	@Reference(target = Constants.CONDITION_TARGET)
//	Object condition;

	@ObjectClassDefinition
	@interface FU {
		@AttributeDefinition()
		CK ck();
	}

	@ObjectClassDefinition
	@interface CK {
		@AttributeDefinition
		String d();
	}

	@Activate
	public void activate(Map<String, Object> map) {
		System.out.println(map);

		System.out.println(map);
		System.out.println(map);
	}
}
