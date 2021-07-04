package org.geckoprojects.osgitest.events.impl;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.function.Predicate;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.geckoprojects.osgitest.events.RuntimeMonitoringAssert;
import org.geckoprojects.osgitest.events.RuntimeMonitoringAssertTimeoutStep;
import org.geckoprojects.osgitest.events.RuntimeMonitoringResultAssert;
import org.geckoprojects.osgitest.predicates.EventPredicates;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceEvent;

public class RuntimeMonitoringAssertImpl implements RuntimeMonitoringAssertTimeoutStep, RuntimeMonitoringAssert {

	private ThrowingCallable execute;

	private Predicate<?> innerPredicate = (se) -> false;

	public RuntimeMonitoringAssertImpl(ThrowingCallable execute) {
		this.execute = execute;

	}

	/**
	 * @param execute   - the action that will be invoked.
	 * @param predicate - Predicate that would be tested against the Events
	 * @param timeout   - the time in that the matches must happened
	 */
	private static EventRecordingAssertImpl call(ThrowingCallable execute, Predicate<?> predicate, int timeout) {
		BundleContext bc = FrameworkUtil.getBundle(EventRecordingAssertImpl.class).getBundleContext();

		EventRecording eventRecording = EventRecording.record(bc, execute, predicate, timeout);
		return new EventRecordingAssertImpl(eventRecording, EventRecordingAssertImpl.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventRecordingAssertImpl assertThat(int timeout) {
		return call(execute, innerPredicate, timeout);
	}
	//todo: niht wen until verwendet
		@Override
		public EventRecordingAssertImpl assertThat() {
			return assertThat(0);
		}




	@SuppressWarnings("unchecked")
	private static <T> Predicate<Object> hasTypeAnd(Class<T> clazz, Predicate<T> predicate) {
		return e -> clazz.isAssignableFrom(e.getClass()) && predicate.test((T) e);
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEvent() {

		innerPredicate = hasTypeAnd(ServiceEvent.class, (se)->true);
		return this;
	}
	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEvent(Predicate<ServiceEvent> predicate) {
		innerPredicate = hasTypeAnd(ServiceEvent.class, predicate);
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventOfClassWithin(long millis, Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class, EventPredicates.ServiceEvents.hasObjectClass(clazz))
				.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventOfClassWithin(long millis, Class<?> clazz,
			Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class, EventPredicates.ServiceEvents.matches(clazz, map))
				.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventRegisters(Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.REGISTERED, clazz));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventRegistersWithin(long millis, Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.REGISTERED, clazz))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventRegisters(Class<?> clazz, Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.REGISTERED, clazz, map));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventRegistersWithin(long millis, Class<?> clazz,
			Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.REGISTERED, clazz, map))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventUnregisters(Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.UNREGISTERING, clazz));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventUnregistersWithin(long millis, Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.UNREGISTERING, clazz))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventUnregisters(Class<?> clazz, Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.UNREGISTERING, clazz, map));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventUnregistersWithin(long millis, Class<?> clazz,
			Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.UNREGISTERING, clazz, map))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModified(Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED, clazz));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedWithin(long millis, Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED, clazz))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModified(Class<?> clazz, Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED, clazz, map));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedWithin(long millis, Class<?> clazz,
			Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED, clazz, map))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModifiedEndmatch(Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED_ENDMATCH, clazz));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedEndmatchWithin(long millis, Class<?> clazz) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED_ENDMATCH, clazz))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilServiceEventModifiedEndmatch(Class<?> clazz,
			Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED_ENDMATCH, clazz, map));
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventModifiedEndmatchWithin(long millis, Class<?> clazz,
			Map<String, Object> map) {
		innerPredicate = hasTypeAnd(ServiceEvent.class,
				EventPredicates.ServiceEvents.matches(ServiceEvent.MODIFIED_ENDMATCH, clazz, map))
						.and(new NoEventWithinPredicate(millis));
		return this;
	}

	/**
	 * @param predicate - Predicate that would be tested against the BundleEvents
	 * 
	 */
	@Override
	public RuntimeMonitoringAssertTimeoutStep untilBundleEvent(Predicate<BundleEvent> predicate) {
		innerPredicate = hasTypeAnd(BundleEvent.class, predicate);
		return this;
	}

	/**
	 * @param predicate - Predicate that would be tested against the FrameworkEvents
	 * 
	 */
	@Override
	public RuntimeMonitoringAssertTimeoutStep untilFrameworkEvent(Predicate<FrameworkEvent> predicate) {
		innerPredicate = hasTypeAnd(FrameworkEvent.class, predicate);
		return this;
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventWithin(long timeMs) {

		return untilNoMoreServiceEventWithin(timeMs, (se) -> true);
	}

	@Override
	public RuntimeMonitoringAssertTimeoutStep untilNoMoreServiceEventWithin(long timeMs,
			Predicate<ServiceEvent> predicate) {

		innerPredicate = hasTypeAnd(ServiceEvent.class, predicate).and(new NoEventWithinPredicate(timeMs));
		return this;
	}

	private class NoEventWithinPredicate implements Predicate<Object> {

		private Runner runner;

		public NoEventWithinPredicate(long timeMs) {

			runner = new Runner(timeMs);
		}

		@Override
		public boolean test(Object t) {

		
			try {
				return runner.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}

		}
	};

	private class Runner {
		AtomicMarkableReference<CountDownLatch> atomic = new AtomicMarkableReference<CountDownLatch>(null, false);

		private long timeout;

		Runner(long timeout) {
			this.timeout = timeout;
		}

		public boolean await() throws InterruptedException {

			if (atomic.getReference() != null) {
				atomic.getReference().countDown();
			}

			CountDownLatch latch = new CountDownLatch(1);
			atomic.set(latch, true);

			boolean b = !latch.await(timeout, TimeUnit.MILLISECONDS);
			return b&&latch.getCount()>0;
		}
	}

}
