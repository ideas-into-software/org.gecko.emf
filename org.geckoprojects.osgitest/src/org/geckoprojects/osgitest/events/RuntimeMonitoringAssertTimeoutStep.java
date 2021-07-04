package org.geckoprojects.osgitest.events;

public interface RuntimeMonitoringAssertTimeoutStep {

	/**
	 * @param timeout - the maximal time in that actions will be observed
	 */
	public RuntimeMonitoringResultAssert assertThat(int timeout) ;
	

}