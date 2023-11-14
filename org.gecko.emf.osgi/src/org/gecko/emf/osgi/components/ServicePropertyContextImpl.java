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
package org.gecko.emf.osgi.components;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.gecko.emf.osgi.helper.ServicePropertiesHelper.getStringPlusValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.helper.ServicePropertiesHelper;
import org.gecko.emf.osgi.helper.ServicePropertyContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.annotations.Component;

/**
 * 
 * @author Mark Hoffmann
 * @since 04.11.2023
 */
//@Component(property = "name=.default")
public class ServicePropertyContextImpl implements ServicePropertyContext {
	
	private final Map<Long, Set<String>> resourceFactoryNameMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> configuratorNameMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> modelNameMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> fileExtensionMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> contentTypeMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> protocolMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> versionMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> featureMap = new ConcurrentHashMap<>();
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#updateResourceFactoryProperties(java.util.Map, boolean)
	 */
	@Override
	public void updateResourceFactoryProperties(Map<String, Object> serviceProperties, boolean add) {
		updateConfiguratorProperties(serviceProperties, add, resourceFactoryNameMap);
		updateModelProperties(serviceProperties, add);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#updateModelProperties(java.util.Map, boolean)
	 */
	@Override
	public void updateModelProperties(Map<String, Object> serviceProperties, boolean add) {
		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_NAME, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_FEATURE, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_VERSION, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_FILE_EXT, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_PROTOCOL, serviceProperties, add);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#updateConfiguratorProperties(java.util.Map, boolean, java.util.Map)
	 */
	@Override
	public void updateConfiguratorProperties(Map<String, Object> serviceProperties, boolean add, Map<Long, Set<String>> targetConfiguratorMap) {
		Set<String> nameSet = getStringPlusValue(serviceProperties, EMFNamespaces.EMF_CONFIGURATOR_NAME);
		Long serviceId = (Long) serviceProperties.get(Constants.SERVICE_ID);
		if (nonNull(nameSet)) {
			if (!add) {
				nameSet = Collections.emptySet();
			} else {
				nameSet = new HashSet<String>(nameSet);
			}
			ServicePropertiesHelper.updateNameMap(targetConfiguratorMap, nameSet, serviceId);
		}
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#updateProperties(java.lang.String, java.util.Map, boolean)
	 */
	@Override
	public void updateProperties(String type, Map<String, Object> serviceProperties, boolean add) {
		requireNonNull(type);
		requireNonNull(serviceProperties);
		
		Set<String> nameSet = getStringPlusValue(serviceProperties, type);
		Long serviceId = (Long) serviceProperties.get(Constants.SERVICE_ID);
		if (nonNull(nameSet)) {
			if (!add) {
				nameSet = Collections.emptySet();
			} else {
				nameSet = new HashSet<String>(nameSet);
			}
			switch (type) {
			case EMFNamespaces.EMF_CONFIGURATOR_NAME:
				ServicePropertiesHelper.updateNameMap(configuratorNameMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_MODEL_NAME:
				ServicePropertiesHelper.updateNameMap(modelNameMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_MODEL_FEATURE:
				ServicePropertiesHelper.updateNameMap(featureMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_MODEL_VERSION:
				ServicePropertiesHelper.updateNameMap(versionMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_MODEL_CONTENT_TYPE:
				ServicePropertiesHelper.updateNameMap(contentTypeMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_MODEL_FILE_EXT:
				ServicePropertiesHelper.updateNameMap(fileExtensionMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_MODEL_PROTOCOL:
				ServicePropertiesHelper.updateNameMap(protocolMap, nameSet, serviceId);
				break;
			default:
				break;
			}
		}
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#getDictionary(boolean)
	 */
	@Override
	public Dictionary<String, Object> getDictionary(boolean includeResoureFactory) {
		Dictionary<String, Object> properties = new Hashtable<>();
		appendToDictionary(EMFNamespaces.EMF_MODEL_NAME, modelNameMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_FEATURE, featureMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_VERSION, versionMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_PROTOCOL, protocolMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_FILE_EXT, fileExtensionMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, contentTypeMap, properties);
		String[] configurations = ServicePropertiesHelper.getNamesArray(configuratorNameMap);
		String[] resourceFactoryNames = ServicePropertiesHelper.getNamesArray(resourceFactoryNameMap);
		if (includeResoureFactory && 
				nonNull(resourceFactoryNames) && 
				resourceFactoryNames.length > 0) {
			properties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, resourceFactoryNames);
		} else {
			properties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, configurations);
		}
		
		return properties;
	}
	
	private Dictionary<String, Object> appendToDictionary(String key, Map<Long, Set<String>> sourceMap, Dictionary<String, Object> dictionary) {
		requireNonNull(dictionary);
		requireNonNull(sourceMap);
		requireNonNull(key);
		String[] stringArrayValues = ServicePropertiesHelper.getNamesArray(sourceMap);
		if (nonNull(stringArrayValues) && stringArrayValues.length > 0) {
			dictionary.put(key, stringArrayValues);
		}
		return dictionary;
	}

}
