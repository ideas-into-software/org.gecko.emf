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

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.gecko.emf.osgi.helper.ServicePropertiesHelper.appendToDictionary;
import static org.gecko.emf.osgi.helper.ServicePropertiesHelper.getStringPlusValue;

import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.helper.ServicePropertiesHelper;
import org.gecko.emf.osgi.helper.ServicePropertyContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

/**
 * {@link ServicePropertyContext} implementation. Hold a set of property maps for Gecko EMF
 * @author Mark Hoffmann
 * @since 04.11.2023
 */
public class ServicePropertyContextImpl implements ServicePropertyContext {
	
	private final Map<Long, Set<String>> resourceFactoryNameMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> configuratorNameMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> modelNameMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> fileExtensionMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> contentTypeMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> protocolMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> versionMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<String>> featureMap = new ConcurrentHashMap<>();
	private final Map<Long, ServicePropertyContext> subContextMap = new ConcurrentHashMap<>();
	
	
	/**
	 * Creates a new instance.
	 * @param contextProperties
	 */
	public ServicePropertyContextImpl(Map<String, Object> contextProperties) {
		updateServiceProperties(contextProperties, true);
	}
	
	/**
	 * Creates a new instance.
	 */
	public ServicePropertyContextImpl() {
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#updateServiceProperties(java.util.Map, boolean)
	 */
	@Override
	public void updateServiceProperties(Map<String, Object> serviceProperties, boolean add) {
		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_RESOURCE_FACTORY_CONFIGURATOR_NAME, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_NAME, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_FEATURE, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_VERSION, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_FILE_EXT, serviceProperties, add);
		updateProperties(EMFNamespaces.EMF_MODEL_PROTOCOL, serviceProperties, add);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#getDictionary(boolean)
	 */
	@Override
	public Dictionary<String, Object> getDictionary(boolean merged) {
		Dictionary<String, Object> properties = new Hashtable<>();
		appendToDictionary(EMFNamespaces.EMF_MODEL_NAME, modelNameMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_FEATURE, featureMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_VERSION, versionMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_PROTOCOL, protocolMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_FILE_EXT, fileExtensionMap, properties);
		appendToDictionary(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, contentTypeMap, properties);
		appendToDictionary(EMFNamespaces.EMF_RESOURCE_FACTORY_CONFIGURATOR_NAME, resourceFactoryNameMap, properties);
		appendToDictionary(EMFNamespaces.EMF_CONFIGURATOR_NAME, configuratorNameMap, properties);
		if (!merged) {
			return properties;
		} else {
			ServicePropertyContext[] subContexts = new ServicePropertyContext[0];
			synchronized (subContextMap) {
				subContexts = subContextMap.values().toArray(new ServicePropertyContext[subContextMap.size()]);
			}
			return merge(subContexts).getDictionary(false);
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#getProperties(boolean)
	 */
	@Override
	public Map<String, Object> getProperties(boolean merged) {
		return FrameworkUtil.asMap(getDictionary(merged));
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#addSubContext(java.util.Map)
	 */
	@Override
	public ServicePropertyContext addSubContext(Map<String, Object> subContextProperties) {
		if (isNull(subContextProperties)) {
			return null;
		}
		return ServicePropertiesHelper.
				getServiceId(subContextProperties).
				map(id->addSubContext(id, subContextProperties)).
				orElse(null);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#removeSubContext(java.util.Map)
	 */
	@Override
	public ServicePropertyContext removeSubContext(Map<String, Object> subContextProperties) {
		if (isNull(subContextProperties)) {
			return null;
		}
		return ServicePropertiesHelper.
				getServiceId(subContextProperties).
				map(this::removeSubContext).
				orElse(null);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.emf.osgi.helper.ServicePropertyContext#merge(org.gecko.emf.osgi.helper.ServicePropertyContext)
	 */
	@Override
	public ServicePropertyContext merge(ServicePropertyContext ...toMerge) {
		if (isNull(toMerge)) {
			return this;
		}
		ServicePropertyContext merged = ServicePropertyContext.create(getProperties(false));
		for (ServicePropertyContext mergeCtx : toMerge) {
			merged.updateServiceProperties(mergeCtx.getProperties(false), true);
		}
		return merged;
	}

	/**
	 * Updates all the needed service properties 
	 * @param key the property key
	 * @param serviceProperties the service properties to look into
	 * @param add set to <code>true</code>, to store the information
	 */
	protected void updateProperties(String key, Map<String, Object> serviceProperties, boolean add) {
		requireNonNull(key);
		requireNonNull(serviceProperties);
		
		Set<String> nameSet = getStringPlusValue(serviceProperties, key);
		Long serviceId = (Long) serviceProperties.get(Constants.SERVICE_ID);
		if (nonNull(nameSet)) {
			if (!add) {
				nameSet = Collections.emptySet();
			} else {
				nameSet = new HashSet<String>(nameSet);
			}
			switch (key) {
			case EMFNamespaces.EMF_CONFIGURATOR_NAME:
				ServicePropertiesHelper.updateNameMap(configuratorNameMap, nameSet, serviceId);
				break;
			case EMFNamespaces.EMF_RESOURCE_FACTORY_CONFIGURATOR_NAME:
				ServicePropertiesHelper.updateNameMap(resourceFactoryNameMap, nameSet, serviceId);
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

	/**
	 * Add the given service properties as sub-context for the given service id and returns the corresponding instance
	 * @param serviceId the service id to register the sub-context
	 * @param subContextProperties the service properties from the sub-context
	 * @return the {@link ServicePropertyContext} instance
	 */
	protected ServicePropertyContext addSubContext(long serviceId, Map<String, Object> subContextProperties) {
		ServicePropertyContext subContext = nonNull(subContextProperties) ? 
				ServicePropertyContext.create(subContextProperties) : 
					new ServicePropertyContextImpl() ;
		synchronized (subContextMap) {
			subContextMap.put(serviceId, subContext);
		}
		return subContext;
	}

	/**
	 * Removes the {@link ServicePropertyContext} for the given service id and returns it or <code>null</code> 
	 * @param serviceId the service id to remove the {@link ServicePropertyContext} for
	 * @return the {@link ServicePropertyContext} to be removed or <code>null</code> 
	 */
	protected ServicePropertyContext removeSubContext(long serviceId) {
		ServicePropertyContext subContext = null;
		synchronized (subContextMap) {
			subContext = subContextMap.remove(serviceId);
		}
		return isNull(subContext) ? null : subContext;
	}
	
}
