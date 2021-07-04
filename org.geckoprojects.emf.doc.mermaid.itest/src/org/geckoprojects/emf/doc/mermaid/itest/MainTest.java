package org.geckoprojects.emf.doc.mermaid.itest;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.geckoprojects.emf.doc.mermaid.MermaidService;
import org.geckoprojects.osgitest.conditions.Conditions;
import org.geckoprojects.osgitest.events.RuntimeMonitoringAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class MainTest {

	@InjectBundleContext
	BundleContext bc;

//	@Test
	public void mai() {
		RuntimeMonitoringAssert.executeAndObserve(() -> {

			for (int i = 0; i < 5; i++) {
				bc.registerService(Serializable.class, "" + i, null);
				System.out.println("reg" + i);
				Thread.sleep(100);
			}

		}).untilNoMoreServiceEventOfClassWithin(10l, Serializable.class).assertThat(3000).isNotTimedOut()
				.hasAtLeastOneServiceEventRegisteredWith(Serializable.class);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void mai2() throws InvalidSyntaxException {
		
	
		RuntimeMonitoringAssert.executeAndObserve(() -> {

			ServiceRegistration<Serializable> sr = bc.registerService(Serializable.class, new Serializable() {},
					Dictionaries.dictionaryOf("k", "v0"));
			
			
			sr.setProperties(Dictionaries.dictionaryOf("k", "v1"));
			sr.setProperties(Dictionaries.dictionaryOf("k", "v2"));
			sr.unregister();

		}).untilNoMoreServiceEventWithin(100l).assertThat(3000)//
		.hasNoThrowable()//
				.isNotTimedOut().hasServiceEventsInOrder(//
						List.of(
						Conditions.ServiceEventConditions.matches(ServiceEvent.REGISTERED,"(k=v0)"), //
						Conditions.ServiceEventConditions.typeModifiedAndObjectClass(Serializable.class), //
						Conditions.ServiceEventConditions.typeUnregisteringAndObjectClass(Serializable.class)))//
				.hasServiceEventsInExactOrder(//
						List.of(
						Conditions.ServiceEventConditions.typeRegisteredWith(Serializable.class,Map.of("k", "v0")), //
						Conditions.ServiceEventConditions.typeModifiedAndObjectClass(Serializable.class), //
						Conditions.ServiceEventConditions.typeModifiedAndObjectClass(Serializable.class), //
						Conditions.ServiceEventConditions.matches(ServiceEvent.UNREGISTERING,Serializable.class,Map.of("k", "v2"))))//
				.hasAtLeastNServiceEventModifiedWith(2,Serializable.class)//
				.hasAtMostNServiceEventModifiedWith(2,Serializable.class)//
				.hasExactlyNServiceEventModifiedWith(2,Serializable.class)//
				.hasExactlyOneServiceEventRegisteredWith(Serializable.class)//
				.hasAtLeastOneServiceEventModifiedWith(Serializable.class)//
				.hasAtLeastOneServiceEventUnregisteringWith(Serializable.class)//
				.hasAtLeastOneServiceEventWith(ServiceEvent.REGISTERED,"(k=v0)")//
				.hasAtLeastOneServiceEventRegisteredWith(Serializable.class, Map.of("k", "v0"))//
				.hasAtLeastOneServiceEventModifiedWith(Serializable.class, Map.of("k", "v1"))//
				.hasAtLeastOneServiceEventUnregisteringWith(Serializable.class, Map.of("k", "v2"))//
				.hasAtLeastOneServiceEventWith(ServiceEvent.REGISTERED, Serializable.class)

				.hasNoServiceEventWith(ServiceEvent.REGISTERED, "(k=v4)");
				//hasServiceEvents;
	}

//	@Test
	public void main(@InjectService MermaidService mermaidService) throws Exception {

		String ecorePath = "./model/bsm.ecore";
//		Writer consoleWriter = new OutputStreamWriter(System.out);
		Writer fileWriter = new FileWriter(new File("output.md"));

		mermaidService.generate(ecorePath, fileWriter);

	}
}