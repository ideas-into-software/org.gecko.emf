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

import java.util.Dictionary;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author mark
 * @since 09.11.2023
 */
public interface ServicePropertyContext {

	/**
	 * Updates the properties for a resource factory configurator or service
	 * @param serviceProperties the service properties
	 * @param add <code>true</code>, to add this configuration
	 */
	void updateResourceFactoryProperties(Map<String, Object> serviceProperties, boolean add);

	void updateModelProperties(Map<String, Object> serviceProperties, boolean add);
	
	void updateConfiguratorProperties(Map<String, Object> serviceProperties, boolean add,
			Map<Long, Set<String>> targetConfiguratorMap);


	/**
	 * Updates the properties of the service, depending on changes on injected services
	 * @param type the type of the property to publish 
	 * @param serviceProperties the service properties from the injected service
	 * @param add <code>true</code>, if the service was add, <code>false</code> in case of an remove
	 */
	void updateProperties(String type, Map<String, Object> serviceProperties, boolean add);

	/**
	 * Creates a dictionary for the stored properties
	 * @return a dictionary for the stored properties
	 */
	Dictionary<String, Object> getDictionary(boolean includeResoureFactory);

}