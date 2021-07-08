package org.geckoprojects.osgitest.events;

public interface RuntimeMonitoringAssertTimeoutStep {

	/**
	 * Assert with timeout that.
	 *
	 * @param timeout - the maximal time in that actions will be observed
	 * @return RuntimeMonitoringResultAssert - to do the assertions
	 */
	public RuntimeMonitoringResultAssert assertWithTimeoutThat(int timeout) ;
	

}