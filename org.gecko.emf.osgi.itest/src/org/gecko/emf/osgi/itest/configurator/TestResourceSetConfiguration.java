/**
 * Copyright (c) 2012 - 2020 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.itest.configurator;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.ResourceSetConfigurator;

/**
 * 
 * @author mark
 * @since 07.03.2020
 */
public class TestResourceSetConfiguration implements ResourceSetConfigurator {

	private AtomicInteger cnt = new AtomicInteger();

	@Override
	public void configureResourceSet(ResourceSet resourceSet) {
		cnt.incrementAndGet();
	}

	public int getCount() {
		return cnt.get();
	}

}
