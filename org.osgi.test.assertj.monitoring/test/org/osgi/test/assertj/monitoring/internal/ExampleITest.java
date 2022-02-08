package org.osgi.test.assertj.monitoring.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.assertj.core.api.Condition;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.assertj.NotPartOfPR.Conditions.ServiceEventConditions;
import org.osgi.test.assertj.monitoring.MonitoringAssertion;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class ExampleITest {

	@InjectBundleContext
	BundleContext bc;

	@Test
	public void example1() {
		MonitoringAssertion.executeAndObserve(() -> {

			for (int i = 0; i < 5; i++) {
				bc.registerService(Serializable.class, "" + i, null);
				System.out.println("reg" + i);
				Thread.sleep(100);
			}

		}).untilNoMoreServiceEventWithin(10l)// main stop-condition criteria
				.assertWithTimeoutThat(3000)// timeout to granite a stop
				.isNotTimedOut()// check that NOT timed-out
				.hasAtLeastOneServiceEventRegisteredWith(Serializable.class);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void example2() throws InvalidSyntaxException {
		MonitoringAssertion.executeAndObserve(() -> {

			ServiceRegistration<Serializable> sr = bc.registerService(Serializable.class, new Serializable() {
			}, Dictionaries.dictionaryOf("k", "v0"));

			sr.setProperties(Dictionaries.dictionaryOf("k", "v1"));
			sr.setProperties(Dictionaries.dictionaryOf("k", "v2"));
			sr.unregister();

		}).untilNoMoreServiceEventWithin(100l)// main stop-condition criteria
				.assertWithTimeoutThat(3000)// timeout to granite a stop
				.hasNoThrowable()// not exception thrown while executed
				.isNotTimedOut()// check that NOT timed-out
				.hasServiceEventsInOrder(//
						Arrays.asList(new Condition[]{ServiceEventConditions.matches(ServiceEvent.REGISTERED, "(k=v0)"), //
								ServiceEventConditions.typeModifiedAndObjectClass(Serializable.class), //
								ServiceEventConditions.typeUnregisteringAndObjectClass(Serializable.class)}
						))//
				.hasServiceEventsInExactOrder(//
						Arrays.asList(new Condition[]{ServiceEventConditions.typeRegisteredWith(Serializable.class, Collections.singletonMap("k", "v0")), //
								ServiceEventConditions.typeModifiedAndObjectClass(Serializable.class), //
								ServiceEventConditions.typeModifiedAndObjectClass(Serializable.class), //
								ServiceEventConditions.matches(ServiceEvent.UNREGISTERING, Serializable.class,
										Collections.singletonMap("k", "v2"))}
						))//
				.hasAtLeastNServiceEventModifiedWith(2, Serializable.class)//
				.hasAtMostNServiceEventModifiedWith(2, Serializable.class)//
				.hasExactlyNServiceEventModifiedWith(2, Serializable.class)//
				.hasExactlyOneServiceEventRegisteredWith(Serializable.class)//
				.hasAtLeastOneServiceEventModifiedWith(Serializable.class)//
				.hasAtLeastOneServiceEventUnregisteringWith(Serializable.class)//
				.hasAtLeastOneServiceEventWith(ServiceEvent.REGISTERED, "(k=v0)")//
				.hasAtLeastOneServiceEventRegisteredWith(Serializable.class, Collections.singletonMap("k", "v0"))//
				.hasAtLeastOneServiceEventModifiedWith(Serializable.class, Collections.singletonMap("k", "v1"))//
				.hasAtLeastOneServiceEventUnregisteringWith(Serializable.class, Collections.singletonMap("k", "v2"))//
				.hasAtLeastOneServiceEventWith(ServiceEvent.REGISTERED, Serializable.class)

				.hasNoServiceEventWith(ServiceEvent.REGISTERED, "(k=v4)");
		// hasServiceEvents;
	}

}