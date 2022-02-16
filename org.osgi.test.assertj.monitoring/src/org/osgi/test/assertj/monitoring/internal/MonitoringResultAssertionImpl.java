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

import org.osgi.test.assertj.monitoring.MonitoringAssertionResult;

class MonitoringResultAssertionImpl extends AbstractMonitoringAssertion<MonitoringResultAssertionImpl, EventRecording>
		implements MonitoringAssertionResult {

	public MonitoringResultAssertionImpl(EventRecording actual, Class<MonitoringResultAssertionImpl> selfType) {
		super(actual, selfType);
	}

	public static MonitoringResultAssertionImpl assertThat(EventRecording actual) {
		return new MonitoringResultAssertionImpl(actual, MonitoringResultAssertionImpl.class);
	}

}
