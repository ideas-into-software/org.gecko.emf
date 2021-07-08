package org.geckoprojects.osgitest.events.impl;

import org.geckoprojects.osgitest.events.RuntimeMonitoringResultAssert;

public class RuntimeMonitoringResultAssertImpl extends AbstractRuntimeMonitoringAssert<RuntimeMonitoringResultAssertImpl, EventRecording> implements RuntimeMonitoringResultAssert{



	public RuntimeMonitoringResultAssertImpl(EventRecording actual, Class<RuntimeMonitoringResultAssertImpl> selfType) {
		super(actual, selfType);
	}

	public static RuntimeMonitoringResultAssertImpl assertThat(EventRecording actual) {
		return new RuntimeMonitoringResultAssertImpl(actual, RuntimeMonitoringResultAssertImpl.class);
	}


}
