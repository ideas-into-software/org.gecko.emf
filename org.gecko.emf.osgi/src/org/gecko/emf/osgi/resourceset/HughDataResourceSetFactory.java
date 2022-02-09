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

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.api.DefaultResourceSetFactory;
import org.gecko.emf.osgi.api.HughDataResourceSet;
import org.gecko.emf.osgi.api.ResourceSetFactory;

/**
 * {@link ResourceSetFactory} that creates a {@link HughDataResourceSet} and configures it to
 * use the {@link ResourceLocator}
 * @author Mark Hoffmann
 * @since 27.09.2019
 */
public class HughDataResourceSetFactory extends DefaultResourceSetFactory {
	

	@Override
	protected ResourceSet internalCreateResourceSet() {
		HughDataResourceSet resourceSet = new HughDataResourceSetImpl();
		resourceSet.setUseResourceLocator(true);
		return resourceSet;
	}

}
