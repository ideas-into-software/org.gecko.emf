package org.geckoprojects.osgitest.events;

import java.time.Instant;


/**
 * The Class TimedEvent.
 *
 * @param <T> the generic type
 */
public class TimedEvent<T> {
	
	/** The event. */
	private T event;

	/** The instant. */
	private Instant instant = Instant.now();

	/**
	 * Instantiates a new timed event.
	 *
	 * @param event the event
	 */
	public TimedEvent(T event) {
		this.event = event;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public T getEvent() {
		return event;
	}

	/**
	 * Gets the instant.
	 *
	 * @return the instant
	 */
	public Instant getInstant() {
		return instant;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TimedEvent [event=" + event + ", instant=" + instant + "]";
	}

}