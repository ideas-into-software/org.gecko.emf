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
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.EMOFResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.annotation.EMFModel;
import org.gecko.emf.osgi.annotation.provide.ProvideEMFResourceConfigurator;
import org.osgi.framework.Constants;

/**
 * Configurator for for the {@link EcorePackage}, which is a {@link EPackageConfigurator} and {@link ResourceFactoryConfigurator}
 * As default it registers resource factories for:
 * <li>XMI</li> 
 * <li>EMOF</li>
 * <li>ecore</li>
 * <li>binary</li>
 * 
 * </br>Also the following mime-types are registered as content type identifiers:
 * <li>application/xmi XMI</li>
 * <li>application/xml XML</li>
 * <li>application/octet-stream Binary</li>
 * 
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@EMFModel(name="ecore", nsURI={XMLTypePackage.eNS_URI,
		XMLNamespacePackage.eNS_URI,
		EcorePackage.eNS_URI}, version="1.0.0")
@ProvideEMFResourceConfigurator( name = "ecore", 
	contentType = { 
			"org.eclipse.emf.ecore", 
			"org.eclipse.emf.emof", 
			"application/xmi", 
			"application/octet-stream"
			}, 
	fileExtension = { 
			"*", 
			"xmi", 
			"ecore", 
			"emof", 
			"bin"
			},
	version = "1.0"
)
public class EcoreConfigurator implements EPackageConfigurator, ResourceFactoryConfigurator {

	public static final Map<String, Object> PROPERTIES = EcoreConfigurator.getProperties();
	
	private static Map<String, Object> getProperties(){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(EMFNamespaces.EMF_CONFIGURATOR_NAME,"ecore"); 
		result.put(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, Arrays.asList( new String[] {
				"org.eclipse.emf.ecore", 
				"org.eclipse.emf.emof", 
				"application/xmi", 
				"application/octet-stream"})); 
		result.put(EMFNamespaces.EMF_MODEL_FILE_EXT, Arrays.asList( new String[] {
				"*", 
				"xmi", 
				"ecore", 
				"emof", 
				"bin"})); 
		result.put(EMFNamespaces.EMF_MODEL_VERSION, "1.0.0");
		result.put(EMFNamespaces.EMF_MODEL_NAME, "ecore");
		result.put(EMFNamespaces.EMF_MODEL_NSURI, Arrays.asList( new String[] {
				XMLTypePackage.eNS_URI,
				XMLNamespacePackage.eNS_URI,
				EcorePackage.eNS_URI}));
		result.put(Constants.SERVICE_ID, new Random().nextLong());
		return result;
	}
			
	
	private static Resource.Factory BINARY_FACTORY = new ResourceFactoryImpl(){
		@Override
		public Resource createResource(URI uri) {
			return new BinaryResourceImpl(uri);
		}
	};
	
	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("emof", new EMOFResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("bin", BINARY_FACTORY);
		registry.getContentTypeToFactoryMap().put("org.eclipse.emf.ecore", new EcoreResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("org.eclipse.emf.emof", new EcoreResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("application/xmi", new XMIResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("application/octet-stream", BINARY_FACTORY);
	}

	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("*");
		registry.getExtensionToFactoryMap().remove("xmi");
		registry.getExtensionToFactoryMap().remove("ecore");
		registry.getExtensionToFactoryMap().remove("emof");
		registry.getContentTypeToFactoryMap().remove("org.eclipse.emf.ecore");
		registry.getContentTypeToFactoryMap().remove("org.eclipse.emf.emof");
		registry.getContentTypeToFactoryMap().remove("application/xmi");
		registry.getContentTypeToFactoryMap().remove("application/octet-stream");
	}

	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(XMLTypePackage.eNS_URI, XMLTypePackage.eINSTANCE);
		registry.put(XMLNamespacePackage.eNS_URI, XMLNamespacePackage.eINSTANCE);
		registry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
	}

	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(XMLTypePackage.eNS_URI);
		registry.remove(XMLNamespacePackage.eNS_URI);
		registry.remove(EcorePackage.eNS_URI);
	}

}
