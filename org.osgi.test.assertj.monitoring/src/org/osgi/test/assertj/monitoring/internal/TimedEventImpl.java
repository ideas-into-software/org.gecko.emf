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

import java.time.Instant;

import org.osgi.test.assertj.monitoring.TimedEvent;


/**
 * The Class TimedEvent.
 *
 * @param <T> the generic type of the event
 */
 class TimedEventImpl<T> implements TimedEvent<T> {
	
	/** The event. */
	private T event;

	/** The instant. */
	private Instant instant = Instant.now();

	/**
	 * Instantiates a new timed event.
	 *
	 * @param event the event
	 */
	public TimedEventImpl(T event) {
		this.event = event;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	@Override
	public T getEvent() {
		return event;
	}

	/**
	 * Gets the instant. Time where the event is fired.
	 *
	 * @return the instant
	 */
	@Override
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