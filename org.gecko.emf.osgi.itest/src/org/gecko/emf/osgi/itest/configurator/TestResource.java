/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.itest.configurator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

/**
 * 
 * @author ungei
 * @since 6 Apr 2023
 */
public class TestResource extends ResourceImpl {

	/**
	 * Creates a new instance.
	 */
	public TestResource(URI uri) {
		super(uri);
	}
	
}
