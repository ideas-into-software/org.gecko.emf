/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.components.dynamic;

import static org.gecko.emf.osgi.EMFNamespaces.DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME;
import static org.gecko.emf.osgi.EMFNamespaces.EMF_MODEL_NAME;
import static org.gecko.emf.osgi.EMFNamespaces.PROP_DYNAMIC_CONFIG_CONTENT_TYPE;
import static org.gecko.emf.osgi.EMFNamespaces.PROP_DYNAMIC_CONFIG_ECORE_PATH;
import static org.gecko.emf.osgi.EMFNamespaces.PROP_DYNAMIC_CONFIG_FILE_EXTENSION;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * {@link EPackageConfigurator} and {@link ResourceFactoryConfigurator} that are configurable.
 * It loads the ecore file and registers the package depending on the configuration
 * @author Mark Hoffmann
 * @since 15.10.2018
 */
@Component(configurationPid=DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME, configurationPolicy=ConfigurationPolicy.REQUIRE, service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})
public class DynamicConfigurator implements EPackageConfigurator, ResourceFactoryConfigurator {
	
	private static final Logger logger = Logger.getLogger(DynamicConfigurator.class.getName());
	private String modelName = null;
	private String fileExtension = null;
	private String contentTypeIdentifier = null;
	private String ecorePath = null;
	private EPackage dynamicPackage = null;
	private ComponentContext ctx;

	/**
	 * Called on components activation
	 * @param ctx the component context
	 * @param properties the component properties
	 * @throws ConfigurationException
	 */
	@Activate
	public void activate(ComponentContext ctx, Map<String, Object> properties) throws ConfigurationException {
		this.ctx = ctx;
		modelName = (String) properties.get(EMF_MODEL_NAME);
		if (modelName == null) {
			throw new ConfigurationException(EMF_MODEL_NAME, "The EMF model name property is missing in the configuration"); //$NON-NLS-1$
		}
		ecorePath = (String) properties.get(PROP_DYNAMIC_CONFIG_ECORE_PATH);
		if (ecorePath == null) {
			throw new ConfigurationException(PROP_DYNAMIC_CONFIG_ECORE_PATH, "The EMF model ecore file path is missing in the configuration"); //$NON-NLS-1$
		}
		try {
			loadModel();
		} catch (Exception e) {
			throw new ConfigurationException(PROP_DYNAMIC_CONFIG_ECORE_PATH, "The EMF model ecore file path is invalid please use: '<bsn>:(<version>)/(<path>)/<file>.ecore': " + e.getMessage()); //$NON-NLS-1$
		}
		fileExtension = (String) properties.get(PROP_DYNAMIC_CONFIG_FILE_EXTENSION);
		contentTypeIdentifier = (String) properties.get(PROP_DYNAMIC_CONFIG_CONTENT_TYPE);
		
	}
	
	/**
	 * Loads the model from the given path
	 */
	private void loadModel() {
		String[] segments = ecorePath.split("/");
		if (segments.length < 2) {
			throw new IllegalStateException("There are at least two segments expected in the ecore path");
		}
		Bundle bundle = getBundle(segments[0]);
		if (bundle == null) {
			throw new IllegalStateException("No bundle can be found for segment " + segments[0]);
		}
		String path = ecorePath.replace(segments[0], "");
		String file = segments[segments.length - 1];
		URL url = bundle.getResource(path);
		if (url == null) {
			throw new IllegalStateException("There was nothing found at '" + segments[0] + path + "'");
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		try {
			Resource resource = resourceSet.createResource(URI.createURI(file));
			InputStream inputStream = url.openStream();
			resource.load(inputStream, null);
			if (resource.getContents().isEmpty()) {
				throw new IllegalStateException("Loaded ecore file with no content '" + segments[0] + path + "'");
			}
			dynamicPackage = (EPackage) resource.getContents().get(0);
			resource.getContents().clear();
			resourceSet.getResources().clear();
		} catch (IOException e) {
			throw new IllegalStateException("Error loading ecore file at '" + segments[0] + path + "'", e);
		}
	}

	/**
	 * Returns the bundle from the given bsn version string.
	 * This parameter is expected in the format:
	 * <bsn>:<version>, where the version part is optional.
	 * @param bsnVersionString the {@link String} in the format from above
	 */
	private Bundle getBundle(String bsnVersionString) {
		String[] bsnVersion = bsnVersionString.split(":");
		String bsn = bsnVersion[0];
		Version version = null;
		if (bsnVersion.length == 2) {
			version = Version.parseVersion(bsnVersion[1]);
		}
		Set<Bundle> candidates = new TreeSet<>((o1, o2) -> o1.getVersion().compareTo(o2.getVersion()));
		for (Bundle b : ctx.getBundleContext().getBundles()) {
			if (bsn.equalsIgnoreCase(b.getSymbolicName())) {
				if (version == null) {
					candidates.add(b);
				} else {
					if (b.getVersion().compareTo(version) == 0) {
						return b;
					}
				}
			}
		}
		if (candidates.isEmpty()) {
			throw new IllegalStateException("There is no bundle with this bsn and version '" + bsn + ":" + version + "'");
		} else {
			return candidates.stream().findFirst().orElse(null);
		}
	}


	@Override
	public void configureResourceFactory(Registry registry) {
		ResourceFactoryImpl factory = new XMIResourceFactoryImpl();
		if (contentTypeIdentifier != null) {
			if (contentTypeIdentifier.equalsIgnoreCase("XML")) {
				factory = new XMLResourceFactoryImpl();
			}
			registry.getContentTypeToFactoryMap().put(contentTypeIdentifier, factory);
		}
		if (fileExtension != null) {
			registry.getExtensionToFactoryMap().put(fileExtension, factory);
		}
	}


	@Override
	public void unconfigureResourceFactory(Registry registry) {
		if (contentTypeIdentifier != null) {
			registry.getExtensionToFactoryMap().remove(contentTypeIdentifier);
		}
		if (fileExtension != null) {
			registry.getExtensionToFactoryMap().remove(fileExtension);
		}
	}


	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		if (dynamicPackage != null) {
			registry.put(dynamicPackage.getNsURI(), dynamicPackage);
		} else {
			logger.log(Level.SEVERE, "[{0}][{1}] Wanted to register a package that was null, that should never happen", new Object[] {modelName, ecorePath});
		}
	}


	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		if (dynamicPackage != null) {
			registry.remove(dynamicPackage.getNsURI());
		} else {
			logger.log(Level.SEVERE, "[{0}][{1}] Wanted to un-register a package that was null, that should never happen", new Object[] {modelName, ecorePath});
		}
	}

}
