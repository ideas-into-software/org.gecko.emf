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
package org.gecko.emf.osgi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.core.tests.AbstractOSGiTest;
import org.gecko.core.tests.ServiceChecker;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.gecko.emf.osgi.model.test.Person;
import org.gecko.emf.osgi.model.test.TestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;

/**
 * Tests the EMF OSGi integration
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class DynamicModelConfiguratorTest extends AbstractOSGiTest {

	/**
	 * Creates a new instance.
	 */
	public DynamicModelConfiguratorTest() {
		super(FrameworkUtil.getBundle(DynamicModelConfiguratorTest.class).getBundleContext());
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doBefore()
	 */
	@Override
	public void doBefore() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doAfter()
	 */
	@Override
	public void doAfter() {
		// TODO Auto-generated method stub

	}

	/**
	 * Trying to load an instance with a registered dynamic {@link EPackage}
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testCreateDynamicModel() throws IOException, InterruptedException {

		ServiceReference<ResourceSetFactory> reference = getServiceReference(ResourceSetFactory.class, false);
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));

		ServiceChecker<ResourceSetFactory> rsfSC = createTrackedChecker(ResourceSetFactory.class, true).assertCreations(1, true).assertModifications(0, false);

		Dictionary<String,Object> properties =  new Hashtable<>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, "test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_CONTENT_TYPE,"test#1.0");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_FILE_EXTENSION,"test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_ECORE_PATH,"org.gecko.emf.osgi.test.model/model/test.ecore");
		createConfigForCleanup(EMFNamespaces.DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME, "?", properties);

		rsfSC.assertModifications(1, true);
		reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));

		ResourceSetFactory factory = rsfSC.getTrackedService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("person.test");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Person p = TestFactory.eINSTANCE.createPerson();
		p.setFirstName("Emil");
		p.setLastName("Tester");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		testLoadResource.load(bais, null);
		assertFalse(testLoadResource.getContents().isEmpty());
		EObject eo = testLoadResource.getContents().get(0);
		assertFalse(eo instanceof Person);
		EStructuralFeature firstName = eo.eClass().getEStructuralFeature("firstName");
		assertNotNull(firstName);
		EStructuralFeature lastName = eo.eClass().getEStructuralFeature("lastName");
		assertNotNull(lastName);
		assertEquals("Emil", eo.eGet(firstName));
		assertEquals("Tester", eo.eGet(lastName));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}. Unregister the dynamic package and try to load the resource again, 
	 * which may fail
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test(expected=IOWrappedException.class)
	public void testCreateDynamicModelUnregister() throws IOException, InterruptedException {
		ServiceReference<ResourceSetFactory> reference = getServiceReference(ResourceSetFactory.class, false);
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));

		ServiceChecker<ResourceSetFactory> rsfSC = createTrackedChecker(ResourceSetFactory.class, true).assertCreations(1, true).assertModifications(0, false);

		Dictionary<String,Object> properties =  new Hashtable<>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, "test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_CONTENT_TYPE,"test#1.0");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_FILE_EXTENSION,"test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_ECORE_PATH,"org.gecko.emf.osgi.test.model/model/test.ecore");
		Configuration dynamicConfig = createConfigForCleanup(EMFNamespaces.DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME, "?", properties);

		rsfSC.assertModifications(1, true);
		reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));

		ResourceSetFactory factory = rsfSC.getTrackedService();
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("person.test");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Person p = TestFactory.eINSTANCE.createPerson();
		p.setFirstName("Emil");
		p.setLastName("Tester");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		testLoadResource.load(bais, null);
		assertFalse(testLoadResource.getContents().isEmpty());
		EObject eo = testLoadResource.getContents().get(0);
		assertFalse(eo instanceof Person);
		EStructuralFeature firstName = eo.eClass().getEStructuralFeature("firstName");
		assertNotNull(firstName);
		EStructuralFeature lastName = eo.eClass().getEStructuralFeature("lastName");
		assertNotNull(lastName);
		assertEquals("Emil", eo.eGet(firstName));
		assertEquals("Tester", eo.eGet(lastName));
		
		rsfSC.assertModifications(1, false);
		deleteConfigurationAndRemoveFromCleanup(dynamicConfig);
		rsfSC.assertModifications(2, true);
		
		testLoadResource = rs.createResource(uri);
		testLoadResource.load(bais, null);
		
	}

}