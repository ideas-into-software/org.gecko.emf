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
package org.gecko.emf.osgi.ecore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.gecko.emf.osgi.EMFNamespaces;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;

/**
 * A {@link XMLResourceFactory} with some reasonable defaults.
 * 
 * @author Juergen Albert
 * @since 22 Feb 2022
 */
public class GeckoXMLResourceFactory extends XMLResourceFactoryImpl implements Resource.Factory{

	public static final Map<String, Object> PROPERTIES = GeckoXMLResourceFactory.getProperties();
	
	private static Map<String, Object> getProperties(){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME,"GeckoXMLResourceFactory"); 
		result.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_CONTENT_TYPE, Arrays.asList( new String[] {
				"application/xml"})); 
		result.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_FILE_EXT, Arrays.asList( new String[] {
				"xml"})); 
		result.put(EMFNamespaces.EMF_CONFIGURATOR_VERSION, "1.0.0");
		result.put(Constants.SERVICE_ID, new Random().nextLong());
		return result;
	}
	
	private org.eclipse.emf.ecore.EPackage.Registry registry;

	/**
	 * Creates a new instance.
	 */
	@Activate
	public GeckoXMLResourceFactory(EPackage.Registry registry) {
		this.registry = registry;
	}
	
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resource createResource(URI uri) {
		XMLResource result = new XMLResourceImpl(uri);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

		ExtendedMetaData extendedMetaData =
				new BasicExtendedMetaData(registry)
				{

				@Override
				protected boolean isFeatureNamespaceMatchingLax()
				{
				return true;
				}
				};

		result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		
		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
		return result;
	}
}
