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
package org.geckoprojects.emf.mongo.handlers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.gecko.emf.osgi.ResourceSetConfigurator;

/**
 * {@link ResourceSetConfigurator} just handling the mongo stuff
 * @author Mark Hoffmann
 * @since 18.04.2018
 */
public class MongoResourceSetConfigurator implements ResourceSetConfigurator {
	
	private final MongoURIHandlerProvider provider;

	/**
	 * Creates a new instance.
	 */
	public MongoResourceSetConfigurator(MongoURIHandlerProvider provider) {
		this.provider = provider;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.ResourceSetConfigurator#configureResourceSet(org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	@Override
	public void configureResourceSet(ResourceSet resourceSet) {
		URIConverter uriConverter = resourceSet.getURIConverter();
		EList<URIHandler> uriHandlers = uriConverter.getURIHandlers();
		uriHandlers.add(0, provider.getURIHandler());
	}

}
