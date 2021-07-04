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
package org.geckoprojects.emf.core.factory;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.ResourceSetFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.PrototypeServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * {@link PrototypeServiceFactory} for creating {@link ResourceSet}
 * @author Mark Hoffmann
 * @since 18.10.2018
 */
public class ResourceSetPrototypeFactory implements PrototypeServiceFactory<ResourceSet> {

	private final ResourceSetFactory resourceSetFactory;

	/**
	 * Creates a new instance.
	 */
	public ResourceSetPrototypeFactory(ResourceSetFactory resourceSetFactory) {
		this.resourceSetFactory = resourceSetFactory;
	}
	/* 
	 * (non-Javadoc)
	 * @see org.osgi.framework.PrototypeServiceFactory#getService(org.osgi.framework.Bundle, org.osgi.framework.ServiceRegistration)
	 */
	@Override
	public ResourceSet getService(Bundle bundle, ServiceRegistration<ResourceSet> registration) {
		return resourceSetFactory.createResourceSet();
	}

	/* 
	 * (non-Javadoc)
	 * @see org.osgi.framework.PrototypeServiceFactory#ungetService(org.osgi.framework.Bundle, org.osgi.framework.ServiceRegistration, java.lang.Object)
	 */
	@Override
	public void ungetService(Bundle bundle, ServiceRegistration<ResourceSet> registration, ResourceSet service) {
		synchronized (service) {
			service.getResources().forEach((r)->r.getContents().clear());
			service.getResources().clear();
		}
	}

}
