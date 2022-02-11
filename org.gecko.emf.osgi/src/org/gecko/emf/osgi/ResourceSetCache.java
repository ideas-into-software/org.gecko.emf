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
 *      Data In Motion - implementation
 */
package org.gecko.emf.osgi;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * The ResourceSet from this service is not thread safe. This service
 * is intended to be used on the UI thread.
 * 
 * @author bhunt
 * @author Mark Hoffmann
 */
public interface ResourceSetCache {
	
	/** Service property name to define a name for the instance */
	public static final String RESOURCE_SET_CACHE_NAME = "rs.cache.name"; 
	
	/**
	 * Returns the cached instance. If no instance exists, it will be created initially
	 * @return the {@link ResourceSet} instance
	 */
	public ResourceSet getResourceSet();
}