package org.geckoprojects.osgitest.events;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.geckoprojects.osgitest.events.impl.RuntimeMonitoringAssertImpl;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.ServiceEvent;

public interface RuntimeMonitoringAssert {
	/*
	 * @param execute - the action that will be invoked.
	 */
	public static RuntimeMonitoringAssert executeAndObserve(ThrowingCallable execute) {
		Objects.requireNonNull(execute, "Runnable must exist");
		return new RuntimeMonitoringAssertImpl(execute);
	}
	public RuntimeMonitoringAssertTimeoutStep untilServiceEvent();
	public RuntimeMonitoringAssertTimeoutStep untilServiceEvent(Predicate<ServiceEvent> predicate);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventRegisters(Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventRegisters(Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventUnregisters(Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventUnregisters(Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModified(Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModified(Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModifiedEndmatch(Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModifiedEndmatch(Class<?> clazz,
			Map<String, Object> map);
	
	
	
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventWithin(long timeMs);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventWithin(long timeMs, Predicate<ServiceEvent> predicate);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventOfClassWithin(long millis,Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventOfClassWithin(long millis,Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventRegistersWithin(long millis,Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventRegistersWithin(long millis,Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventUnregistersWithin(long millis,Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventUnregistersWithin(long millis,Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedWithin(long millis,Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedWithin(long millis,Class<?> clazz, Map<String, Object> map);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedEndmatchWithin(long millis,Class<?> clazz);
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedEndmatchWithin(long millis,Class<?> clazz,
			Map<String, Object> map);



	public RuntimeMonitoringAssertTimeoutStep untilBundleEvent(Predicate<BundleEvent> predicate);
	public RuntimeMonitoringAssertTimeoutStep untilFrameworkEvent(Predicate<FrameworkEvent> predicate);
	
	/**
	 * will stop observation of the events direct after executing the action.
	 */
	public RuntimeMonitoringResultAssert assertThat() ;
	
	/**
	 * @param timeout - the maximal time in that actions will be observed. Could be lower if an other `until`-Condition is set.
	 */
	public RuntimeMonitoringResultAssert assertWithTimeoutThat(int timeout) ;
	

}
