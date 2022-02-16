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
package org.osgi.test.assertj.monitoring;

public interface MonitoringAssertionTimeoutStep {

	/**
	 * Assert with timeout that.
	 *
	 * @param timeout - the maximal time in that actions will be observed
	 * @return RuntimeMonitoringResultAssert - to do the assertions
	 */
	public MonitoringAssertionResult assertWithTimeoutThat(int timeout) ;
	

}