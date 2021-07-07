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
package org.geckoprojects.emf.core.components;

import org.eclipse.emf.ecore.resource.URIHandler;
import org.geckoprojects.emf.core.api.UriHandlerProvider;
import org.geckoprojects.emf.core.urihandler.RestfulURIHandlerImpl;
import org.osgi.service.component.annotations.Component;

/**
 * Provider for the rest-ful URI Handler, that can use BasicAuthentication
 * @author Mark Hoffmann
 * @since 27.07.2017
 */
@Component(name="RestUriHandlerProvider", immediate=true, service=UriHandlerProvider.class)
public class RestUriHandlerProvider implements UriHandlerProvider {


	@Override
	public URIHandler getURIHandler() {
		return new RestfulURIHandlerImpl();
	}

}
