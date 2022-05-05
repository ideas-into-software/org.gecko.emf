/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A Helper to update the given Service Properties
 * @author Juergen Albert
 * @since 22 Feb 2022
 */
public class ServicePropertiesHelper {

	/**
	 * Updates the name set for a given service id
	 * @param nameMap the map to update
	 * @param newNames the new properties
	 * @param serviceId the service id the name set belongs to
	 */
	public static void updateNameMap(Map<Long, Set<String>> nameMap, Set<String> newNames, Long serviceId) {
		Set<String> nameSet = nameMap.get(serviceId);
		if (nameSet == null) {
			nameSet = new HashSet<String>();
			nameMap.put(serviceId, nameSet);
		}
		nameSet.clear();
		nameSet.addAll(newNames);
	}

	/**
	 * Return all values of all map entries as array
	 * @param nameMap the map to return values from
	 * @return a string array
	 */
	public static String[] getNamesArray(Map<Long, Set<String>> nameMap) {
		return nameMap.entrySet()
				.stream()
				.map(Entry::getValue)
				.flatMap(Collection::stream)
				.collect(Collectors.toList())
				.toArray(new String[0]);
	}
	
}
