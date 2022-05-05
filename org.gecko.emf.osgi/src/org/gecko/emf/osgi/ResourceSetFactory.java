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

import java.util.Collection;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * This OSGi service builds an EMF ResourceSet.
 * @author bhunt
 * @author Mark Hoffmann
 */
public interface ResourceSetFactory
{
	public static final String EMF_CAPABILITY_NAME = "osgi";
	public static final String GECKOPROKECTS_EMF_VERSION = "4.0";
	public static final String CONDITION_ID = "org.gecko.emf.osgi.ResourceSetFactory";
	
	/**
	 * Returns a new instance of a resource set
	 * @return the newly created ResourceSet
	 */
	ResourceSet createResourceSet();

	/**
	 * Returns a lsit of all resource set configurators or an empty list
	 * @return the collection of resource set configurators currently bound to the factory
	 */
	Collection<ResourceSetConfigurator> getResourceSetConfigurators();
}
