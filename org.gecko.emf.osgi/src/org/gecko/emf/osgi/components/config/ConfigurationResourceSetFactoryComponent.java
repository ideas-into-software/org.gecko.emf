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
package org.gecko.emf.osgi.components.config;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.gecko.emf.osgi.DefaultResourceSetFactory;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.ResourceSetConfigurator;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.annotation.bundle.Capability;
import org.osgi.annotation.bundle.Requirement;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

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
@Component(service= {}, configurationPid=EMFNamespaces.RESOURCE_SET_FACTORY_CONFIG_NAME, configurationPolicy=ConfigurationPolicy.REQUIRE)
@Capability(
		namespace = EMFNamespaces.EMF_NAMESPACE,
		name = ResourceSetFactory.EMF_CAPABILITY_NAME,
		version = ResourceSetFactory.GECKOPROJECTS_EMF_VERSION
		)

@Requirement(namespace = EMFNamespaces.EMF_CONFIGURATOR_NAMESPACE, //
	name = EPackageConfigurator.EMF_CONFIGURATOR_NAME, filter="(name=ecore)")
public class ConfigurationResourceSetFactoryComponent extends DefaultResourceSetFactory {

	private Dictionary<String, Object> properties;

	/**
	 * Inject the {@link EPackage.Registry}
	 * @param registry the registry to inject
	 */
	@Reference(name="ePackageRegistry", policy=ReferencePolicy.STATIC, cardinality=ReferenceCardinality.MANDATORY, unbind="unsetRegistry")
	public void setRegistry(EPackage.Registry registry) {
		super.setEPackageRegistry(registry);
	}

	/**
	 * Remove the registry on shutdown
	 * @param registry the registry to be removed
	 */
	public void unsetRegistry(EPackage.Registry registry) {
		super.unsetEPackageRegistry(registry);
	}

	/**
	 * Inject a {@link Registry} for resource factories
	 * @param resourceFactoryRegistry the resource factory to be injected
	 */
	@Override
	@Reference(name="resourceFactoryRegistry", policy=ReferencePolicy.STATIC, unbind="unsetResourceFactoryRegistry", updated = "modifiedResourceFactoryRegistry")
	public void setResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry, Map<String, Object> properties) {
		super.setResourceFactoryRegistry(resourceFactoryRegistry, properties);
	}

	public void modifiedResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry, Map<String, Object> properties) {
		super.modifiedResourceFactoryRegistry(resourceFactoryRegistry, properties);
	}
	
	/**
	 * Removed the registry on shutdown
	 * @param resourceFactoryRegistry the registry to be removed
	 */
	@Override
	public void unsetResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry) {
		super.unsetResourceFactoryRegistry(resourceFactoryRegistry);
	}

	/**
	 * Injects {@link EPackageConfigurator}, to register the Ecore Package
	 * @param configurator the {@link EPackageConfigurator} to be registered
	 * @param properties the service properties
	 */
	@Override
	@Reference(unbind ="removeEPackageConfigurator", policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.AT_LEAST_ONE, target="(emf.model.name=ecore)")
	public void addEcoreConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		super.addEcoreConfigurator(configurator, properties);
	}

	/**
	 * Injects {@link EPackageConfigurator}, to register a new {@link EPackage}
	 * @param configurator the {@link EPackageConfigurator} to be registered
	 * @param properties the service properties
	 */
	@Override
	@Reference(name="ePackageConfigurator", policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.MULTIPLE, target="(!(emf.model.name=ecore))", updated = "modifyEPackageConfigurator", unbind = "removeEPackageConfigurator")
	public void addEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		super.addEPackageConfigurator(configurator, properties);
	}
	
	/**
	 * Injects {@link EPackageConfigurator}, to update a new {@link EPackage}
	 * @param configurator the {@link EPackageConfigurator} to be updated
	 * @param properties the service properties
	 */
	public void modifyEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		super.addEPackageConfigurator(configurator, properties);
	}

	/**
	 * Removes a {@link EPackageConfigurator} from the registry and unconfigures it
	 * @param configurator the configurator to be removed
	 * @param properties the service properties
	 */
	@Override
	public void removeEPackageConfigurator(EPackageConfigurator configurator, Map<String, Object> properties) {
		super.removeEPackageConfigurator(configurator, properties);
	}

	/**
	 * Adds a resource factory configurator for the basic Ecore Package
	 * @param configurator the resource factory configurator to be registered
	 * @param properties the service properties
	 */
	@Override
	@Reference(unbind = "removeResourceFactoryConfigurator", policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.AT_LEAST_ONE, target="(emf.model.name=ecore)")
	public void addEcoreResourceFactoryConfigurator(ResourceFactoryConfigurator configurator, Map<String, Object> properties) {
		super.addEcoreResourceFactoryConfigurator(configurator, properties);
	}

	/**
	 * Adds a resource factory configurator to the registry 
	 * @param configurator the resource factory configurator to be registered
	 * @param properties the service properties
	 */
	@Override
	@Reference(name="resourceFactoryConfigurator", policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.MULTIPLE, target="(!(emf.model.name=ecore))", updated = "modifyResourceFactoryConfigurator")
	public void addResourceFactoryConfigurator(ResourceFactoryConfigurator configurator, Map<String, Object> properties) {
		super.addResourceFactoryConfigurator(configurator, properties);
	}
	
	/**
	 * Modifies a resource factory configurator to the registry 
	 * @param configurator the resource factory configurator to be updated
	 * @param properties the service properties
	 */
	public void modifyResourceFactoryConfigurator(ResourceFactoryConfigurator configurator, Map<String, Object> properties) {
		super.addResourceFactoryConfigurator(configurator, properties);
	}

	/**
	 * Removes a resource factory configurator from the registry
	 * @param configurator the resource factory configurator to be removed
	 * @param properties the service properties
	 */
	@Override
	public void removeResourceFactoryConfigurator(ResourceFactoryConfigurator configurator, Map<String, Object> properties) {
		super.removeResourceFactoryConfigurator(configurator, properties);
	}

	/**
	 * Adds new {@link ResourceSetConfigurator} to this factory
	 * @param resourceSetConfigurator the new configurator to be added
	 * @param properties the service properties
	 */
	@Override
	@Reference(policy=ReferencePolicy.DYNAMIC, cardinality=ReferenceCardinality.MULTIPLE, updated = "modifyResourceSetConfigurator")
	public void addResourceSetConfigurator(ResourceSetConfigurator resourceSetConfigurator, Map<String, Object> properties) {
		super.addResourceSetConfigurator(resourceSetConfigurator, properties);
	}
	
	/**
	 * Modifies new {@link ResourceSetConfigurator} to this factory
	 * @param resourceSetConfigurator the new configurator to be modified
	 * @param properties the service properties
	 */
	public void modifyResourceSetConfigurator(ResourceSetConfigurator resourceSetConfigurator, Map<String, Object> properties) {
		super.addResourceSetConfigurator(resourceSetConfigurator, properties);
	}

	/**
	 * Removes a {@link ResourceSetConfigurator} from the list for this factory
	 * @param resourceSetConfigurator
	 * @param properties the service properties
	 */
	@Override
	public void removeResourceSetConfigurator(ResourceSetConfigurator resourceSetConfigurator, Map<String, Object> properties) {
		super.removeResourceSetConfigurator(resourceSetConfigurator, properties);
	}

	/**
	 * Called on component activation
	 * @param ctx the component context
	 */
	@Override
	@Activate
	public void activate(ComponentContext ctx) {
		properties = ctx.getProperties();
		registerServices(ctx);
	}
	
	/**
	 * Called on component deactivation
	 */
	@Override
	@Deactivate
	public void deactivate() {
		super.deactivate();
	}
	

	@Override
	protected Dictionary<String, Object> getDictionary() {
		Dictionary<String, Object> props = super.getDictionary();
		Enumeration<String> keys = properties.keys();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			props.put(key, properties.get(key));
		}
		return props;
	}
	

}
