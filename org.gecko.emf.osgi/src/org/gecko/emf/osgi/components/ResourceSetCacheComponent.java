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
package org.gecko.emf.osgi.components;

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.api.ResourceSetCache;
import org.gecko.emf.osgi.api.ResourceSetFactory;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * A cache for a {@link ResourceSet}. Whereas the {@link ResourceSetFactory} only creates new instances of
 * {@link ResourceSet}'s, this component enables a cache for a certain resource set.
 * @author bhunt
 * @author Mark Hoffmann
 */
@Component(name="ResourceSetCache", service=ResourceSetCache.class, configurationPolicy=ConfigurationPolicy.REQUIRE)
@RequireConfigurationAdmin
public class ResourceSetCacheComponent implements ResourceSetCache {
	
	private AtomicReference<ResourceSetFactory> resourceSetFactoryReference = new AtomicReference<ResourceSetFactory>();
	private volatile ResourceSet resourceSet;


	@Override
	public synchronized ResourceSet getResourceSet() {
		if (resourceSet == null) {
			ResourceSetFactory resourceSetFactory = resourceSetFactoryReference.get();

			if (resourceSetFactory != null) {
				resourceSet = resourceSetFactory.createResourceSet();
			}
		}
		return resourceSet;
	}

	/**
	 * Binds the {@link ResourceSetFactory} to this component
	 * @param resourceSetFactory
	 */
	@Reference(cardinality=ReferenceCardinality.MANDATORY, policy=ReferencePolicy.STATIC)
	public void bindResourceSetFactory(ResourceSetFactory resourceSetFactory) {
		resourceSetFactoryReference.set(resourceSetFactory);
	}

	/**
	 * Unbind the resource set factory instance on shutdown
	 * @param resourceSetFactory the resource set factory to be removed
	 */
	public void unbindResourceSetFactory(ResourceSetFactory resourceSetFactory) {
		resourceSetFactoryReference.compareAndSet(resourceSetFactory, null);
	}
}
