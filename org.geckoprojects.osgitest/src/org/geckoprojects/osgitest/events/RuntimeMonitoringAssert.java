package org.geckoprojects.osgitest.events;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.geckoprojects.osgitest.events.impl.RuntimeMonitoringAssertImpl;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.ServiceEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface RuntimeMonitoringAssert.
 */
public interface RuntimeMonitoringAssert {

	/**
	 * Creates a RuntimeMonitoringAssert.
	 *
	 * @param execute - the action that will be invoked.
	 * @return the runtime monitoring assert
	 */
	public static RuntimeMonitoringAssert executeAndObserve(ThrowingCallable execute) {
		Objects.requireNonNull(execute, "Runnable must exist");
		return new RuntimeMonitoringAssertImpl(execute);
	}

	/**
	 * Until any service event is fired.
	 *
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilAnyServiceEvent();

	/**
	 * Until a service event that matches the given predicate is fired.
	 *
	 * @param predicate the predicate that must match
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEvent(Predicate<ServiceEvent> predicate);

	/**
	 * Until a service event registered with the given class is fired.
	 *
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventRegistered(Class<?> objectClass);

	/**
	 * Until a service event registered with the given class and properties is
	 * fired.
	 *
	 * @param objectClass the objectClass
	 * @param map         the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventRegistered(Class<?> objectClass,
			Map<String, Object> properties);

	/**
	 * Until a service event unregistered with the given class is fired.
	 *
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventUnregistered(Class<?> objectClass);

	/**
	 * /** Until a service event unregistered with the given class and properties is
	 * fired.
	 *
	 * @param objectClass the objectClass
	 * @param map         the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventUnregistered(Class<?> objectClass,
			Map<String, Object> properties);

	/**
	 * Until a service event modified with the given class is fired.
	 *
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModified(Class<?> objectClass);

	/**
	 * Until a service event modified with the given class and properties is fired.
	 *
	 * @param objectClass the objectClass
	 * @param map         the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModified(Class<?> objectClass,
			Map<String, Object> properties);

	/**
	 * Until a service event modified-endmatch with the given class is fired.
	 *
	 * @param clazz the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModifiedEndmatch(Class<?> objectClass);

	/**
	 * Until a service modified-endmatch with the given class and properties is
	 * fired.
	 *
	 * @param objectClass the objectClass
	 * @param properties  the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModifiedEndmatch(Class<?> objectClass,
			Map<String, Object> map);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param timeMs the time ms
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventWithin(long timeMs);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param timeMs    the time ms
	 * @param predicate the predicate
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventWithin(long timeMs,
			Predicate<ServiceEvent> predicate);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventOfClassWithin(long millis, Class<?> objectClass);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @param properties  the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventOfClassWithin(long millis, Class<?> objectClass,
			Map<String, Object> properties);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventRegisteredWithin(long millis,
			Class<?> objectClass);

	/**
	 * Until no more service event within given time is fired. *
	 * 
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @param properties  the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventRegistersWithin(long millis, Class<?> objectClass,
			Map<String, Object> properties);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventUnregistersWithin(long millis,
			Class<?> objectClass);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @param properties  the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventUnregistersWithin(long millis,
			Class<?> objectClass, Map<String, Object> properties);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedWithin(long millis, Class<?> objectClass);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @param properties  the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedWithin(long millis, Class<?> objectClass,
			Map<String, Object> properties);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedEndmatchWithin(long millis,
			Class<?> objectClass);

	/**
	 * Until no more service event within given time is fired.
	 *
	 * @param millis      the millis
	 * @param objectClass the objectClass
	 * @param properties  the properties
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedEndmatchWithin(long millis,
			Class<?> objectClass, Map<String, Object> map);

	/**
	 * Until a bundle event matching the given predicate is fired.
	 *
	 * @param predicate the predicate
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilBundleEvent(Predicate<BundleEvent> predicate);

	/**
	 * Until a framework event matching the given predicate is fired.
	 *
	 * @param predicate the predicate
	 * @return RuntimeMonitoringAssertTimeoutStep - to define the
	 *         observation-timeout.
	 */
	public RuntimeMonitoringAssertTimeoutStep untilFrameworkEvent(Predicate<FrameworkEvent> predicate);

	/**
	 * will stop observation of the events direct after executing the action.
	 *
	 * @return the runtime monitoring result assert
	 */
	public RuntimeMonitoringResultAssert assertThat();

	/**
	 * will stop observation of the events at least after the given timeout.
	 *
	 * @param timeout - the maximal time in that actions will be observed. Could be
	 *                lower if an other `until`-Condition is set.
	 * @return the runtime monitoring result assert
	 */
	public RuntimeMonitoringResultAssert assertWithTimeoutThat(int timeout);

}
