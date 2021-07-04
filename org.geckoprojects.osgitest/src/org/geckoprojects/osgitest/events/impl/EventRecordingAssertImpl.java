package org.geckoprojects.osgitest.events.impl;

import org.geckoprojects.osgitest.events.RuntimeMonitoringResultAssert;

public class EventRecordingAssertImpl extends AbstractEventRecordingAssert<EventRecordingAssertImpl, EventRecording> implements RuntimeMonitoringResultAssert{



	public EventRecordingAssertImpl(EventRecording actual, Class<EventRecordingAssertImpl> selfType) {
		super(actual, selfType);
	}

	public static EventRecordingAssertImpl assertThat(EventRecording actual) {
		return new EventRecordingAssertImpl(actual, EventRecordingAssertImpl.class);
	}


}
