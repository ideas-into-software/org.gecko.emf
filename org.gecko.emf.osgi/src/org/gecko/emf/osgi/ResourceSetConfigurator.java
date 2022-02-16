/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Bryan Hunt - initial API
 *      Data In Motion 
 */
package org.gecko.emf.osgi;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * This OSGi service configures a resource set during construction. It is
 * expected that a system may define more than one configurator and that the
 * ResourceSet factory will apply all configurators.
 * 
 * @author bhunt, Mark Hoffmann
 */
public interface ResourceSetConfigurator {
	
	public static final String EMF_CONFIGURATOR_NAME = "resourceSet";
	
	/**
	 * Configure a newly constructed {@link ResourceSet}. 
	 * @param resourceSet the {@link ResourceSet} to configure
	 */
	void configureResourceSet(ResourceSet resourceSet);
}
