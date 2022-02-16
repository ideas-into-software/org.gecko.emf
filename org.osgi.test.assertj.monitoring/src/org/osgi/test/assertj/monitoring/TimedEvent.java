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

import java.time.Instant;

public interface TimedEvent<T> {

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	T getEvent();

	/**
	 * Gets the instant. Time where the event is fired.
	 *
	 * @return the instant
	 */
	Instant getInstant();

}