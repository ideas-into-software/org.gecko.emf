/**
 * Copyright (c) 2012 - 2017 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.ecore;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.EMOFResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.geckoprojects.emf.core.api.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.api.annotation.EMFModel;
import org.geckoprojects.emf.core.api.annotation.provide.ProvideEMFModel;
import org.geckoprojects.emf.core.api.annotation.provide.ProvideEMFResourceConfigurator;
import org.geckoprojects.emf.core.api.annotation.require.RequireEMF;
import org.osgi.service.component.annotations.Component;

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
@Component(name="EcoreConfigurator", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
@EMFModel(emf_model_name="ecore", emf_model_nsURI={XMLTypePackage.eNS_URI,
		XMLNamespacePackage.eNS_URI,
		EcorePackage.eNS_URI}, emf_model_version="1.0")
@RequireEMF
@ProvideEMFModel(name = "ecore", nsURI = {
			XMLTypePackage.eNS_URI,
			XMLNamespacePackage.eNS_URI,
			EcorePackage.eNS_URI
		},
		version = "1.0"
		)
@ProvideEMFResourceConfigurator( name = "ecore", 
	contentType = { 
			"org.eclipse.emf.ecore", 
			"org.eclipse.emf.emof", 
			"application/xmi", 
			"application/xml", 
			"application/octet-stream"
			}, 
	fileExtension = { 
			"*", 
			"xml", 
			"xmi", 
			"ecore", 
			"emof", 
			"bin"
			},
	version = "1.0"
)
public class EcoreConfigurator implements EPackageConfigurator, ResourceFactoryConfigurator {

	private static Resource.Factory BINARY_FACTORY = new ResourceFactoryImpl(){


		@Override
		public Resource createResource(URI uri) {
			return new BinaryResourceImpl(uri);
		}
	};
	

	@Override
	public void configureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("emof", new EMOFResourceFactoryImpl());
		registry.getExtensionToFactoryMap().put("bin", BINARY_FACTORY);
		registry.getContentTypeToFactoryMap().put("org.eclipse.emf.ecore", new EcoreResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("org.eclipse.emf.emof", new EcoreResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("application/xmi", new XMIResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("application/xml", new XMLResourceFactoryImpl());
		registry.getContentTypeToFactoryMap().put("application/octet-stream", BINARY_FACTORY);
	}


	@Override
	public void unconfigureResourceFactory(Registry registry) {
		registry.getExtensionToFactoryMap().remove("*");
		registry.getExtensionToFactoryMap().remove("xmi");
		registry.getExtensionToFactoryMap().remove("xml");
		registry.getExtensionToFactoryMap().remove("ecore");
		registry.getExtensionToFactoryMap().remove("emof");
		registry.getContentTypeToFactoryMap().remove("org.eclipse.emf.ecore");
		registry.getContentTypeToFactoryMap().remove("org.eclipse.emf.emof");
		registry.getContentTypeToFactoryMap().remove("application/xmi");
		registry.getContentTypeToFactoryMap().remove("application/xml");
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
