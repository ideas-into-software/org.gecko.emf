package org.geckoprojects.osgitest.events;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.ClassBasedNavigableListAssert;
import org.assertj.core.api.Condition;
import org.assertj.core.api.DurationAssert;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ThrowableAssert;
import org.geckoprojects.osgitest.events.impl.EventRecording.TimedEvent;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.test.assertj.bundleevent.BundleEventAssert;
import org.osgi.test.assertj.frameworkevent.FrameworkEventAssert;
import org.osgi.test.assertj.serviceevent.ServiceEventAssert;

public interface RuntimeMonitoringResultAssert {

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventUnregisteringWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventUnregisteringWith(int n, Class<?> objectClass,
			Map<String, Object> map);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventModifiedWith(final Class<?> objectClass);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventModifiedWith(final Class<?> objectClass,
			Map<String, Object> map);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventRegisteredWith(final Class<?> objectClass);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventRegisteredWith(final Class<?> objectClass,
			Map<String, Object> map);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventUnregisteringWith(final Class<?> objectClass);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventUnregisteringWith(final Class<?> objectClass,
			Map<String, Object> map);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventWith(int eventTypeMask, final Class<?> objectClass);

	public RuntimeMonitoringResultAssert hasAtLeastOneServiceEventWith(int eventTypeMask, final Class<?> objectClass,
			Map<String, Object> map);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventUnregisteringWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventUnregisteringWith(int n, Class<?> objectClass,
			Map<String, Object> map);

	public ClassBasedNavigableListAssert<?, List<? extends BundleEvent>, BundleEvent, BundleEventAssert> hasBundleEventsThat();

	public DurationAssert hasDurationBetweenThat(int firstElementIndex, int secondElementIndex);

	public ListAssert<?> hasEventsThat();

	public <T> ListAssert<T> hasEventsThat(Class<T> clazz);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventUnregisteringWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventUnregisteringWith(int n, Class<?> objectClass,
			Map<String, Object> map);

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventUnregisteringWith(Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventUnregisteringWith(Class<?> objectClass,
			Map<String, Object> map);

	public RuntimeMonitoringResultAssert hasExactlyOneServiceEventWith(int eventTypeMask, final Class<?> objectClass);

	public RuntimeMonitoringResultAssert hasExactlyOneServiceEventWith(int eventTypeMask, final Class<?> objectClass,
			Map<String, Object> map);

	public ClassBasedNavigableListAssert<?, List<? extends FrameworkEvent>, FrameworkEvent, FrameworkEventAssert> hasFrameworkEventsThat();

	public RuntimeMonitoringResultAssert hasNoBundleEvent();

	public RuntimeMonitoringResultAssert hasNoFrameworkEvent();

	public RuntimeMonitoringResultAssert hasNoServiceEvent();

	public RuntimeMonitoringResultAssert hasNoServiceEventWith(int eventTypeMask, final Class<?> objectClass,
			Map<String, Object> map);

	public RuntimeMonitoringResultAssert hasNoThrowable();


	public RuntimeMonitoringResultAssert hasServiceEventsInExactOrder(List<Condition<ServiceEvent>> conditions);


	public RuntimeMonitoringResultAssert hasServiceEventsInOrder(List<Condition<ServiceEvent>> conditions);

	public ClassBasedNavigableListAssert<?, List<? extends ServiceEvent>, ServiceEvent, ServiceEventAssert> hasServiceEventsThat();

	public ThrowableAssert hasThrowableThat();

	public ListAssert<TimedEvent<BundleEvent>> hasTimedBundleEventsThat();

	public ListAssert<TimedEvent<Object>> hasTimedEventsThat();

	public <T> ListAssert<TimedEvent<T>> hasTimedEventsThat(Class<T> clazz);

	public ListAssert<TimedEvent<FrameworkEvent>> hasTimedFrameworkEventsThat();

	public ListAssert<TimedEvent<ServiceEvent>> hasTimedServiceEventsThat();

	public RuntimeMonitoringResultAssert isNotTimedOut();

	public RuntimeMonitoringResultAssert isTimedOut();

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventWith(int n, int eventTypeMask, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventWith(int n, int eventTypeMask, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventWith(int n, int eventTypeMask, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventWith(int n, int eventTypeMask, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventRegisteredWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventRegisteredWith(int n, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventRegisteredWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventRegisteredWith(int n, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventRegisteredWith(Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventRegisteredWith(Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventRegisteredWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventRegisteredWith(int n, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventModifiedWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventModifiedWith(int n, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventModifiedWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventModifiedWith(int n, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventModifiedWith(Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventModifiedWith(Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventModifiedWith(int n, Class<?> objectClass);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventModifiedWith(int n, Class<?> objectClass, Map<String, Object> map);

	RuntimeMonitoringResultAssert hasNoServiceEventWith(int eventTypeMask, String filter) throws InvalidSyntaxException;

	RuntimeMonitoringResultAssert hasExactlyNServiceEventWith(int n, int eventTypeMask, String filter) throws InvalidSyntaxException;

	RuntimeMonitoringResultAssert hasExactlyOneServiceEventWith(int eventTypeMask, String filter) throws InvalidSyntaxException;

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventWith(int n, int eventTypeMask, String filter) throws InvalidSyntaxException;

	RuntimeMonitoringResultAssert hasAtLeastOneServiceEventWith(int eventTypeMask, String filter) throws InvalidSyntaxException;

	RuntimeMonitoringResultAssert hasAtLeastNServiceEventWithCondition(int n, Condition<ServiceEvent> condition);

	RuntimeMonitoringResultAssert hasAtMostNServiceEventWithCondition(int n, Condition<ServiceEvent> condition);

	RuntimeMonitoringResultAssert hasExactlyNServiceEventWithCondition(int n, Condition<ServiceEvent> condition);

	
}
