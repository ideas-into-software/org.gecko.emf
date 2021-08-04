/*******************************************************************************
 * Copyright (c) 2012 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *    Data In Motion Consulting GmbH
 *******************************************************************************/

package org.geckoprojects.emf.core.api;

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