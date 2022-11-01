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
 *       Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.itest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * Tests the EMF OSGi integration
 * 
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class EMFWhiteboardTest {

	@InjectBundleContext
	BundleContext bc;

	//TODO More tests for dynamic registration and unregistration.
	
	/**
	 * Trying to load an instance with a non registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGeckoXMLResourceFactoryRegistered(@InjectService ServiceAware<ResourceSetFactory> sa)
			throws IOException {

		ServiceReference<ResourceSetFactory> reference = sa.getServiceReference();
		assertNotNull(reference);

		System.err.println(reference.getProperties());
		
		Object extensions = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME);
		assertNotNull(extensions);
		assertTrue(extensions instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) extensions);
		assertTrue(modelNameList.contains("GeckoXMLResourceFactory"));
	}

	/**
	 * Trying to load an instance with a non registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGeckoXMLResourceFactoryRegisteredInRegistry(@InjectService ServiceAware<Factory> factory)
			throws IOException {
		
		ServiceReference<Factory> reference = factory.getServiceReference();
		assertNotNull(reference);
		
		Object extensions = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_FILE_EXT);
		assertNotNull(extensions);
		assertTrue(extensions instanceof String[]);
		List<String> extensionNameList = Arrays.asList((String[]) extensions);
		assertTrue(extensionNameList.contains("xml"));
		
		Object contentType = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_CONTENT_TYPE);
		assertNotNull(contentType);
		assertTrue(contentType instanceof String[]);
		List<String> contentTypeNameList = Arrays.asList((String[]) contentType);
		assertTrue(contentTypeNameList.contains("application/xml"));
	}
}