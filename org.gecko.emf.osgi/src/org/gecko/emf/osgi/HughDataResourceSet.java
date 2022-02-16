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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A special {@link ResourceSet}, that can deal with large data sets.
 * For that a {@link ResourceLocator} is attached to the {@link ResourceSet}.
 * 
 * Usually when adding/removing {@link Resource} from a {@link ResourceSet}, there are
 * {@link Notification}'s about that. This causes a lot of load, when e.g. calling 'clear' on 
 * {@link ResourceSet#getResources()}. 
 * 
 * @author Mark Hoffmann
 * @since 27.09.2019
 */
public interface HughDataResourceSet extends ResourceSet {
	
	/**
	 * Set to <code>true</code>, when using a special resource locator, to cache
	 * URI and {@link Resource}
	 * 
	 * @param useLocator set to <code>true</code>, to enable the {@link ResourceLocator}
	 */
	public void setUseResourceLocator(boolean useLocator);
	
	/**
	 * Returns <code>true</code>, if the {@link ResourceLocator} is used
	 * @return <code>true</code>, if the {@link ResourceLocator} is used
	 */
	public boolean isUseResourceLocator();
	
	/**
	 * Set to <code>true</code>, to de-activate the notifications in the resource set in general. 
	 * @param suppressNotification the parameter
	 */
	public void setSuppressNotification(boolean suppressNotification);
	
	/**
	 * Returns <code>true</code>, if notifications are de-activated
	 * @return <code>true</code>, if notifications are de-activated
	 */
	public boolean isSuppressNotification();

}
