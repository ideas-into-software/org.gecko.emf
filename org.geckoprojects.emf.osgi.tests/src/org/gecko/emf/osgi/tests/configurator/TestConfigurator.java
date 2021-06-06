/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.tests.configurator;

import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.ResourceSetConfigurator;

/**
 * 
 * @author mark
 * @since 03.03.2018
 */
public class TestConfigurator implements ResourceSetConfigurator, ResourceFactoryConfigurator {

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.ResourceSetConfigurator#configureResourceSet(org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	@Override
	public void configureResourceSet(ResourceSet resourceSet) {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 */
	@Override
	public void configureResourceFactory(Registry registry) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)
	 */
	@Override
	public void unconfigureResourceFactory(Registry registry) {
		// TODO Auto-generated method stub
		
	}

}
