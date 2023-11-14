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
package org.gecko.emf.osgi.helper;

import static java.util.Objects.requireNonNull;

import java.util.Dictionary;
import java.util.Map;

import org.gecko.emf.osgi.components.ServicePropertyContextImpl;

/**
 * Class to handle the different service properties. It allows to have  
 * @author Mark Hoffmann
 * @since 09.11.2023
 */
public interface ServicePropertyContext {

	/**
	 * Updates the service properties for a resource factory, configurator or service. 
	 * @param serviceProperties the service properties
	 * @param add <code>true</code>, to add this configuration
	 */
	void updateServiceProperties(Map<String, Object> serviceProperties, boolean add);

	/**
	 * Adds a sub-context for a given service id contained in the sub context properties
	 * @param subContextProperties the service property map of the sub-context
	 * @return the {@link ServicePropertyContext} instance
	 */
	ServicePropertyContext addSubContext(Map<String, Object> subContextProperties);
	
	/**
	 * Removes the sub-context for the given service properties and returns the removed value or <code>null</code>. 
	 * @param subContextProperties the service properties of the sub-context to be removed
	 * @return the {@link ServicePropertyContext} instance, that has been removed or <code>null</code>
	 */
	ServicePropertyContext removeSubContext(Map<String, Object> subContextProperties);
	
	/**
	 * Creates a {@link Map} for the stored properties
	 * @param merged, set to <code>true</code>, to get the merged properties of this context and all registered sub-contexts
	 * @return a {@link Map} for the stored properties
	 */
	Map<String, Object> getProperties(boolean merged);

	/**
	 * Creates a {@link Dictionary} for the stored properties
	 * @param merged, set to <code>true</code>, to get the merged properties of this context and all registered sub-contexts
	 * @return a {@link Dictionary} for the stored properties
	 */
	Dictionary<String, Object> getDictionary(boolean merged);

	/**
	 * Creates a {@link ServicePropertyContext} out of a property map
	 * @param contextProperties the property map, must not be <code>null</code>
	 * @return {@link ServicePropertyContext} instance
	 */
	static ServicePropertyContext create(Map<String, Object> contextProperties) {
		requireNonNull(contextProperties);
		return new ServicePropertyContextImpl(contextProperties);
	}
	
	/**
	 * Merge the given {@link ServicePropertyContext} with this instance
	 * @param toMerge the contexts to be merged
	 * @return a new merged instance
	 */
	ServicePropertyContext merge(ServicePropertyContext ...toMerge);

}