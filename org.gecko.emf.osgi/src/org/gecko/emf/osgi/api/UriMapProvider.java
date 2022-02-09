/*******************************************************************************
 * Copyright (c) 2012 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/
package org.gecko.emf.osgi.api;

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
