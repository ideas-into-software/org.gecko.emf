/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.core.api;

import org.geckoprojects.emf.core.api.annotation.EMFModel;
import org.geckoprojects.emf.core.api.annotation.EMFResourceFactoryConfigurator;
import org.geckoprojects.emf.core.api.annotation.EMFResourceSetConfigurator;

/**
 * 
 * @author Juergen Albert
 * @since 9 Feb 2018
 */
public class EMFNamespaces {

	public static final String EMF_NAMESPACE = "emf.core";
	public static final String EMF_CONFIGURATOR_NAMESPACE = "emf.configurator";
	
	/**
	 * Attribute name constants for {@link EMFResourceFactoryConfigurator} annotation
	 */

	// Attribute PREFIX for the ResourceSetFactory configurator name
	public static final String EMF_RESOURCE_CONFIGURATOR_PREFIX = "emf.resource.configurator.";
	// Attribute name for the ResourceSetFactory configurator name
	public static final String EMF_RESOURCE_CONFIGURATOR_NAME = EMF_RESOURCE_CONFIGURATOR_PREFIX+"name";
	// Attribute name for the ResourceSetFactory configurator content type
	public static final String EMF_RESOURCE_CONFIGURATOR_CONTENT_TYPE = EMF_RESOURCE_CONFIGURATOR_PREFIX+"contentType";
	// Attribute name for the ResourceSetFactory configurator file extension
	public static final String EMF_RESOURCE_CONFIGURATOR_FILE_EXT = EMF_RESOURCE_CONFIGURATOR_PREFIX+"fileExtension";
	// Attribute name for the ResourceSetFactory configurator feature
	public static final String EMF_RESOURCE_CONFIGURATOR_FEATURE = EMF_RESOURCE_CONFIGURATOR_PREFIX+"feature";

	/**
	 * Attribute name constants for {@link EMFResourceSetConfigurator} annotation
	 */
	
	// Attribute PREFIX for the ResourceSetFactory configurator 
	public static final String EMF_CONFIGURATOR_PREFIX= "emf.configurator.";
	// Attribute name for the ResourceSetFactory configurator name
	public static final String EMF_CONFIGURATOR_NAME = EMF_CONFIGURATOR_PREFIX+"name";
	// Attribute name for the ResourceSetFactory configurator content type
	public static final String EMF_CONFIGURATOR_CONTENT_TYPE = EMF_CONFIGURATOR_PREFIX+"contentType";
	// Attribute name for the ResourceSetFactory configurator file extension
	public static final String EMF_CONFIGURATOR_FILE_EXT = EMF_CONFIGURATOR_PREFIX+"fileExtension";
	// Attribute name for the ResourceSetFactory configurator file extension
	public static final String EMF_CONFIGURATOR_VERSION = EMF_CONFIGURATOR_PREFIX+"version";
	// Attribute name for the ResourceSetFactory configurator feature
	public static final String EMF_CONFIGURATOR_FEATURE = EMF_CONFIGURATOR_PREFIX+"feature";
	
	/**
	 * Attribute name constants for {@link EMFModel} annotation
	 */
	// Attribute PREFIX for the EMF model
	public static final String EMF_MODEL_PREFIX = "emf.model.";
	// Attribute name for the EMF model name
	public static final String EMF_MODEL_NAME = EMF_MODEL_PREFIX+"name";
	// Attribute name for the EMF model namespace
	public static final String EMF_MODEL_NSURI = EMF_MODEL_PREFIX+"nsURI";
	// Attribute name for the EMF model content type
	public static final String EMF_MODEL_CONTENT_TYPE = EMF_MODEL_PREFIX+"contentType";
	// Attribute name for the EMF model file extension
	public static final String EMF_MODEL_FILE_EXT = EMF_MODEL_PREFIX+"fileExtension";
	// Attribute name for the EMF model version
	public static final String EMF_MODEL_VERSION = EMF_MODEL_PREFIX+"version";
	
	/**
	 * Constants for the isolated configurable resource set factory
	 */
	// property name for the resource set factory name
	public static final String PROP_RESOURCE_SET_FACTORY_NAME = "rsf.name";
	// property name for the resource set factory model target filter
	public static final String PROP_MODEL_TARGET_FILTER = "rsf.model.target.filter";
	// property name for the dynmaic model configurator ecore path '<bsn>:(<version>)/(<path>)/<file>.ecore'
	public static final String PROP_DYNAMIC_CONFIG_ECORE_PATH = "dynamic.ecore.path";
	// property name for the dynmaic model configurator content type
	public static final String PROP_DYNAMIC_CONFIG_CONTENT_TYPE = "dynamic.ecore.contentType";
	// property name for the dynmaic model configurator file extension
	public static final String PROP_DYNAMIC_CONFIG_FILE_EXTENSION = "dynamic.ecore.fileExtension";
	
	
	// Configuration pid for the EPackage registry 
	public static final String EPACKAGE_REGISTRY_CONFIG_NAME = "EPackageRegistry";
	// Configuration pid for the ResourceFactory registry 
	public static final String RESOURCE_FACTORY_CONFIG_NAME = "ResourceFactoryRegistry";
	// Configuration pid for the ResourceSetFactory registry 
	public static final String RESOURCE_SET_FACTORY_CONFIG_NAME = "ResourceSetFactory";
	// Configuration pid for the isolated ResourceSetFactrory configuration component 
	public static final String ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME = "IsolatedResourceSetFactory";
	// Configuration pid for the dynamic EPackage and ResourceFactory configurator registry 
	public static final String DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME = "DynamicModelConfigurator";
	// Configuration target filter property for the EPackage registry 
	public static final String EPACKAGE_REGISTRY_TARGET = "ePackageRegistry.target";
	// Configuration target filter property for the ResourceFactory registry 
	public static final String RESOURCE_FACTORY_REGISTRY_TARGET = "resourceFactoryRegistry.target";
	// Configuration target filter property for the EPackage configurators 
	public static final String EPACKAGE_TARGET = "ePackageConfigurator.target";
	// Configuration target filter property for the ResourceFactory configurators 
	public static final String RESOURCE_FACTORY_TARGET = "resourceFactoryConfigurator.target";
}
