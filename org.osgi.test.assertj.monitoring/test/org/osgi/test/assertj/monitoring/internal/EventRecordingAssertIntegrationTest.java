/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.osgi.test.assertj.monitoring.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.assertj.monitoring.MonitoringAssertion;
import org.osgi.test.assertj.monitoring.MonitoringAssertionResult;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.junit5.context.BundleContextExtension;

@Disabled
@ExtendWith(BundleContextExtension.class)
public class EventRecordingAssertIntegrationTest {

	private BundleContext bc;
	String k1 = "key1";
	String v1 = "value1";

	String k2 = "key2";
	String v2 = "value2";
	
	@BeforeEach
	public void before(@InjectBundleContext BundleContext ctx) {
		this.bc = ctx;
	}

	@Test
	void exampleIntegrationTest() throws Exception {

		// Setup assert

		MonitoringAssertionResult assertionResult = MonitoringAssertion.executeAndObserve(() -> {
			ServiceRegistration<A> reg = null;
			reg = bc.registerService(A.class, new A() {
			}, Dictionaries.dictionaryOf(k1, v1));
			reg.setProperties(Dictionaries.dictionaryOf(k1, v1, k2, v2));
			reg.unregister();
		}).untilServiceEvent((e) -> e.getType() == ServiceEvent.UNREGISTERING).assertWithTimeoutThat(1000);

		// check whether the Predicate matches or the timeout
		assertionResult.isNotTimedOut();

		// get ListAsserts and check them
		assertionResult.hasEventsThat().isNotEmpty();

		assertionResult.hasFrameworkEventsThat().isEmpty();

		assertionResult.hasBundleEventsThat().isEmpty();

		// ListAsserts in combination with Conditions
		assertionResult.hasServiceEventsThat().isNotEmpty().hasSize(3);

		assertionResult.hasServiceEventsThat().first().isOfType(ServiceEvent.REGISTERED);

		assertionResult.hasServiceEventsThat().element(1).isOfType(ServiceEvent.MODIFIED);

		assertionResult.hasServiceEventsThat().element(2).isOfType(ServiceEvent.UNREGISTERING);
	}

	class A {
	}
}