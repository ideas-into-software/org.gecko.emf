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

import org.eclipse.emf.ecore.resource.URIHandler;

/**
 * This OSGi service provides a URI handler. It is intended to be used with the
 * IResourceSetConfigurator for configuring a ResourceSet.
 * @author bhunt
 * 
 */
public interface UriHandlerProvider {
	
	/**
	 * Returns the URI handler instance
	 * @return the URI handler instance
	 */
	URIHandler getURIHandler();
}
