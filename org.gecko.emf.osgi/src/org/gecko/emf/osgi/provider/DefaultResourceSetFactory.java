/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.ResourceSetConfigurator;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.gecko.emf.osgi.components.ServicePropertyContextImpl;
import org.gecko.emf.osgi.factory.ResourceSetPrototypeFactory;
import org.gecko.emf.osgi.helper.ServicePropertyContext;
import org.osgi.framework.Constants;
import org.osgi.framework.PrototypeServiceFactory;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.condition.Condition;

/**
 * Implementation of a {@link ResourceSetFactory}. It hold the {@link EPackage} registry as well as the {@link Factory} registry.
 * {@link EPackage} are dynamically injected as {@link EPackageProvider} instance. 
 * {@link Factory} instance are injected dynamically as {@link ServiceReference} instance. So they can be registered using
 * their properties for contentTyp or fileExtension.
 * Third additional {@link ResourceSetConfigurator} instance can be injected to customize the {@link ResourceSet} for
 * further extension like custom serialization. 
 * @author Mark Hoffmann
 * @since 28.06.2017
 */
public class DefaultResourceSetFactory implements ResourceSetFactory {

	private final Set<ResourceSetConfigurator> resourceSetConfigurators = new CopyOnWriteArraySet<>();
	private final Set<EPackageConfigurator> ePackageConfigurators = new CopyOnWriteArraySet<>();
	private final Set<ResourceFactoryConfigurator> resourceFactoryConfigurators = new CopyOnWriteArraySet<>();
	private final ServicePropertyContext propertyContext = new ServicePropertyContextImpl();
//	private final Map<Long, Set<String>> configuratorNameMap = new ConcurrentHashMap<>();
//	private final Map<Long, Set<String>> modelNameMap = new ConcurrentHashMap<>();
	protected EPackage.Registry packageRegistry;
	protected Resource.Factory.Registry resourceFactoryRegistry;
	protected ServiceRegistration<ResourceSetFactory> rsfRegistration = null;
	protected ServiceRegistration<ResourceSet> rsRegistration = null;
	protected ServiceRegistration<Condition> conditionRegistration = null;
	private long serviceChangeCount = 0;
	
	/**
	 * Returns the propertyContext.
	 * @return the propertyContext
	 */
	public ServicePropertyContext getPropertyContext() {
		return propertyContext;
	}
	
	/**
	 * Set the {@link EPackage.Registry}
	 * @param registry the {@link EPackage} registry to set
	 */
	protected void setEPackageRegistry(EPackage.Registry registry) {
		this.packageRegistry = registry;
		updatePackageRegistry();
	}
	
	/**
	 * Un-set the {@link EPackage} registry
	 * @param registry the {@link EPackage} registry to be removed
	 */
	protected void unsetEPackageRegistry(EPackage.Registry registry) {
		this.packageRegistry.clear();
		this.packageRegistry = null;
	}

	/**
	 * Set a {@link Registry} for resource factories
	 * @param resourceFactoryRegistry the resource factory to be injected
	 */
	protected void setResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry, Map<String, Object> properties) {
		this.resourceFactoryRegistry = resourceFactoryRegistry;
		updateResourceFactoryRegistry();
		getPropertyContext().updateModelProperties(properties, true);
//		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, properties, true);
	}

	/**
	 * Set a {@link Registry} for resource factories
	 * @param resourceFactoryRegistry the resource factory to be injected
	 */
	protected void modifiedResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry, Map<String, Object> properties) {
		if(this.resourceFactoryRegistry == resourceFactoryRegistry) {
			getPropertyContext().updateModelProperties(properties, true);
//			updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, properties, true);
		}
	}

	/**
	 * Removes the {@link Resource.Factory.Registry}registry
	 * @param resourceFactoryRegistry the registry to be removed
	 */
	protected void unsetResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry) {
		this.resourceFactoryRegistry = null;
	}

	/**
	 * Adds {@link EPackageConfigurator}, to register a new {@link EPackage}
	 * @param configurator the {@link EPackageConfigurator} to be registered
	 * @param properties the service properties
	 */
	protected void addEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		ePackageConfigurators.add(configurator);
		if (packageRegistry != null) {
			configurator.configureEPackage(packageRegistry);
		}
		getPropertyContext().updateModelProperties(properties, true);
//		updateProperties(EMFNamespaces.EMF_MODEL_NAME, properties, true);
	}

	/**
	 * Removes a {@link EPackageConfigurator} from the registry and unconfigures it
	 * @param configurator the configurator to be removed
	 * @param modelInfo the model information
	 * @param properties the service properties
	 */
	protected void removeEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		ePackageConfigurators.remove(configurator);
		getPropertyContext().updateModelProperties(properties, false);
//		updateProperties(EMFNamespaces.EMF_MODEL_NAME, properties, false);
		Object nsUris = properties.get(EMFNamespaces.EMF_MODEL_NSURI);
		if (packageRegistry != null) {
			configurator.unconfigureEPackage(packageRegistry);
			if (nsUris != null && (nsUris instanceof String || nsUris instanceof String[])) {
				List<String> nsUriList = nsUris instanceof String ? 
						Collections.singletonList((String)nsUris) : Arrays.asList((String[])nsUris);
				for (String nsUri : nsUriList) {
					EPackage.Registry.INSTANCE.remove(nsUri);
				}
			}
		}
	}

	/**
	 * Adds a resource factory configurator to the registry 
	 * @param configurator the resource factory configurator to be registered
	 * @param properties the service properties
	 */
	protected void addResourceFactoryConfigurator(ResourceFactoryConfigurator configurator, Map<String, Object> properties) {
		resourceFactoryConfigurators.add(configurator);
		if (resourceFactoryRegistry != null) {
			configurator.configureResourceFactory(resourceFactoryRegistry);
		}
		getPropertyContext().updateModelProperties(properties, true);
//		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, properties, true);
	}

	/**
	 * Removes a resource factory configurator from the registry
	 * @param configurator the resource factory configurator to be removed
	 * @param properties the service properties
	 */
	protected void removeResourceFactoryConfigurator(ResourceFactoryConfigurator configurator, Map<String, Object> properties) {
		resourceFactoryConfigurators.remove(configurator);
		getPropertyContext().updateModelProperties(properties, false);
//		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, properties, false);
		if (resourceFactoryRegistry != null) {
			configurator.unconfigureResourceFactory(resourceFactoryRegistry);
		}
	}

	/**
	 * Adds new {@link ResourceSetConfigurator} to this factory
	 * @param resourceSetConfigurator the new configurator to be added
	 * @param properties the service properties
	 */
	protected void addResourceSetConfigurator(ResourceSetConfigurator resourceSetConfigurator, Map<String, Object> properties) {
		resourceSetConfigurators.add(resourceSetConfigurator);
		getPropertyContext().updateModelProperties(properties, true);
//		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, properties, true);
	}
	
	/**
	 * Removes a {@link ResourceSetConfigurator} from the list for this factory
	 * @param resourceSetConfigurator
	 * @param properties the service properties
	 */
	protected void removeResourceSetConfigurator(ResourceSetConfigurator resourceSetConfigurator, Map<String, Object> properties) {
		resourceSetConfigurators.remove(resourceSetConfigurator);
		getPropertyContext().updateModelProperties(properties, false);
//		updateProperties(EMFNamespaces.EMF_CONFIGURATOR_NAME, properties, false);
	}

	/**
	 * Called on component activation
	 * @param ctx the component context
	 */
	protected void activate(ComponentContext ctx) {
		
		packageRegistry.putAll(EPackage.Registry.INSTANCE);
		resourceFactoryRegistry.getExtensionToFactoryMap().putAll(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap());
		resourceFactoryRegistry.getContentTypeToFactoryMap().putAll(Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap());
		resourceFactoryRegistry.getProtocolToFactoryMap().putAll(Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap());
		registerServices(ctx);
	}

	/**
	 * Registers the {@link ResourceSet} and {@link ResourceSetFactory}
	 * @param ctx the component context
	 */
	protected void registerServices(ComponentContext ctx) {
		Dictionary<String, Object> props = getDictionary();
		rsfRegistration = ctx.getBundleContext().registerService(ResourceSetFactory.class, this, copyDictionary(props));
		PrototypeServiceFactory<ResourceSet> protoFactory = new ResourceSetPrototypeFactory(this);
		rsRegistration = ctx.getBundleContext().registerService(ResourceSet.class, protoFactory, copyDictionary(props));
		conditionRegistration = ctx.getBundleContext().registerService(Condition.class, Condition.INSTANCE, copyDictionaryForCondition(props));
	}
	
	/**
	 * De-registers the {@link ResourceSet} and {@link ResourceSetFactory}
	 */
	protected void unregisterServices() {
		if (rsfRegistration != null) {
			rsfRegistration.unregister();
			rsfRegistration = null;
		}
		if (rsRegistration != null) {
			rsRegistration.unregister();
			rsRegistration = null;
		}
		if (conditionRegistration != null) {
			conditionRegistration.unregister();
			conditionRegistration = null;
		}
	}
	
	/**
	 * Called on component deactivation
	 */
	protected void deactivate() {
		unregisterServices();
	}
	
	/**
	 * Internally creates a {@link ResourceSet} instance
	 * @return the {@link ResourceSet} instance
	 */
	protected ResourceSet internalCreateResourceSet() {
		return new ResourceSetImpl();
	}


	@Override
	public ResourceSet createResourceSet() {
		ResourceSet resourceSet = internalCreateResourceSet();
		resourceSet.setPackageRegistry(new EPackageRegistryImpl(packageRegistry));
		resourceSet.setResourceFactoryRegistry(new DelegatingResourceFactoryRegistry(resourceFactoryRegistry));
		resourceSetConfigurators.forEach((c)->c.configureResourceSet(resourceSet));
		return resourceSet;
	}

	@Override
	public Collection<ResourceSetConfigurator> getResourceSetConfigurators() {
		return Collections.unmodifiableCollection(resourceSetConfigurators);
	}

	/**
	 * Updates the package registry
	 */
	protected void updatePackageRegistry() {
		List<EPackageConfigurator> providers = new ArrayList<>(ePackageConfigurators);
		providers.forEach((p)->p.configureEPackage(packageRegistry));
	}
	
	/**
	 * Updates the resource factory registry
	 * Register the XML resource factory to handle XML files 
	 */
	protected void updateResourceFactoryRegistry() {
		List<ResourceFactoryConfigurator> providers = new ArrayList<>(resourceFactoryConfigurators);
		providers.forEach((p)->p.configureResourceFactory(resourceFactoryRegistry));
	}
	
	/**
	 * Updates the properties of the service, depending on changes on injected services
	 * @param type the type of the property to publish 
	 * @param serviceProperties the service properties from the injected service
	 * @param add <code>true</code>, if the service was add, <code>false</code> in case of an remove
	 */
//	protected void updateProperties(String type, Map<String, Object> serviceProperties, boolean add) {
//		Object name = serviceProperties.get(type);
//		Long serviceId = (Long) serviceProperties.get(Constants.SERVICE_ID);
//		if (name != null && (name instanceof String || name instanceof String[])) {
//			Set<String> nameSet;
//			if (!add) {
//				nameSet = Collections.emptySet();
//			} else {
//				if (name instanceof String) {
//					nameSet = Collections.singleton(name.toString());
//				} else {
//					nameSet = new HashSet<String>(Arrays.asList((String[])name));
//				}
//			}
//			switch (type) {
//			case EMFNamespaces.EMF_CONFIGURATOR_NAME:
//				ServicePropertiesHelper.updateNameMap(configuratorNameMap, nameSet, serviceId);
//				break;
//			case EMFNamespaces.EMF_MODEL_NAME:
//				ServicePropertiesHelper.updateNameMap(modelNameMap, nameSet, serviceId);
//				break;
//			default:
//				break;
//			}
//			updateRegistrationProperties();
//		}
//	}
	
	/**
	 * Updates the service registration properties
	 */
	protected void updateRegistrationProperties() {
		if(rsfRegistration != null || rsRegistration != null || conditionRegistration != null) {
			Dictionary<String, Object> dictionary = getDictionary();
			if (rsfRegistration != null) {
				rsfRegistration.setProperties(copyDictionary(dictionary));
			}
			if (rsRegistration != null) {
				rsRegistration.setProperties(copyDictionary(dictionary));
			}
			if (conditionRegistration != null) {
				conditionRegistration.setProperties(copyDictionaryForCondition(dictionary));
			}
		}
	}

	
	/**
	 * @param dictionary the dictionary to copy
	 * @return a copy of the original Dictionary
	 */
	private Dictionary<String, Object> copyDictionaryForCondition(Dictionary<String, Object> dictionary) {
		Dictionary<String, Object> copy = copyDictionary(dictionary);
		copy.put(Condition.CONDITION_ID, CONDITION_ID);
		return copy;
	}
	
	/**
	 * @param dictionary the dictionary to copy
	 * @return a copy of the original Dictionary
	 */
	private Dictionary<String, Object> copyDictionary(Dictionary<String, Object> dictionary) {
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		Enumeration<String> enumeration = dictionary.keys();
		while (enumeration.hasMoreElements()) {
			String string = (String) enumeration.nextElement();
			props.put(string, dictionary.get(string));
		}
		return props;
	}

	/**
	 * Creates a dictionary for the stored properties
	 * @return a dictionary for the stored properties
	 */
	protected Dictionary<String, Object> getDictionary() {
		Dictionary<String, Object> properties = propertyContext.getDictionary(true);
//		Dictionary<String, Object> properties = new Hashtable<>();
//		String[] configNames = ServicePropertiesHelper.getNamesArray(configuratorNameMap);
//		String[] modelNames = ServicePropertiesHelper.getNamesArray(modelNameMap);
		properties.put(ComponentConstants.COMPONENT_NAME, "DefaultResourcesetFactory");
		properties.put(Constants.SERVICE_CHANGECOUNT, serviceChangeCount++);
		return properties;
	}

}
