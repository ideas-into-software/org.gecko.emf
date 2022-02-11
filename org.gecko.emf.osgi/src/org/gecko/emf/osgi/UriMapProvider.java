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

import java.util.Map;

import org.eclipse.emf.common.util.URI;

/**
 * Provider to convert Uri's
 * @author bhunt
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
public interface UriMapProvider {
	
	public static final String URI_MAP_SOURE = "uri.map.src"; 
	public static final String URI_MAP_DESTINATION = "uri.map.dest"; 
	
	/**
	 * Return the Uri map
	 * @return the map instance
	 */
	Map<URI, URI> getUriMap();
}
