/**
 * Copyright (c) 2012 - 2019 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.resourceset;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl.MappedResourceLocator;

/**
 * An implementation of the {@link MappedResourceLocator}, with some methods,
 * to manually clear the cache.
 * @author Mark Hoffmann
 * @since 27.09.2019
 */
public class HughDataResourceLocator extends MappedResourceLocator {

	/**
	 * Creates a new instance.
	 * @param resourceSet
	 */
	public HughDataResourceLocator(ResourceSetImpl resourceSet) {
		super(resourceSet);
	}
	
	/**
	 * Clears the {@link Resource} cache
	 */
	public void clear() {
		normalizationMap.clear();
		resourceMap.clear();
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl.ResourceLocator#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		clear();
	}

}
