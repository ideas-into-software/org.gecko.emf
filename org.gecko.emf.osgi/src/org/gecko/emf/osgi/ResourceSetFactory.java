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
	public static final String GECKOPROKECTS_EMF_VERSION = "2.0";
	
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
