/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface to detach EObjects from a resource or/and resource sets
 * @author Mark Hoffmann
 * @since 23.11.2017
 */
public interface Detachable {
	
	/**
	 * Detaches the object just from the resource and leaves the resource in the resource set
	 * @param object the object to detach
	 */
	public void detachFromResource(EObject object);
	
	/**
	 * Detaches the object from the resource and detaches the resource from the resource set as well
	 * @param object the object to detach 
	 */
	public void detachFromAll(EObject object);

}
