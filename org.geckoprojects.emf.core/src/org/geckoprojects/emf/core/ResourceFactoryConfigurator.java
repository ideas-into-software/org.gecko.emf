/**
 * Copyright (c) 2012 - 2017 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.core;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;

/**
 * Configurator for the {@link Registry} registry
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
public interface ResourceFactoryConfigurator {
	
	/**
	 * Configures the {@link Registry} registry
	 * @param registry the registry to be configured
	 */
	public void configureResourceFactory(Resource.Factory.Registry registry);
	
	/**
	 * Unconfigure {@link Registry} registry
	 * @param registry the registry to unconfigure
	 */
	public void unconfigureResourceFactory(Resource.Factory.Registry registry);

}
