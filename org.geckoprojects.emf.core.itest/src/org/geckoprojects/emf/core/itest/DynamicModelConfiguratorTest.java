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
package org.geckoprojects.emf.core.itest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.api.EMFNamespaces;
import org.geckoprojects.emf.core.api.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.BasicFactory;
import org.geckoprojects.emf.example.model.basic.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.test.assertj.dictionary.DictionaryAssert;
import org.osgi.test.assertj.monitoring.MonitoringAssertion;
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
public class DynamicModelConfiguratorTest {

	@InjectService
	ConfigurationAdmin ca;

	/**
	 * Trying to load an instance with a registered dynamic {@link EPackage}
	 * 
	 * @throws Exception
	 */
	@Test

	public void testCreateDynamicModel(
			@InjectService(cardinality = 1) ServiceAware<ResourceSetFactory> resourceSetFactory) throws Exception {

		assertThat(resourceSetFactory.getServices()).hasSize(1);
		ServiceReference<ResourceSetFactory> reference = resourceSetFactory.getServiceReference();
		assertThat(reference).isNotNull();

		DictionaryAssert.assertThat(reference.getProperties()).containsKey(EMFNamespaces.EMF_MODEL_NAME)
				.extractingByKey(EMFNamespaces.EMF_MODEL_NAME).isNotNull()
				.isInstanceOfSatisfying(String[].class, arr -> {
					assertThat(arr).contains("ecore");
					assertThat(arr).doesNotContain("test");
				});

		reference = resourceSetFactory.getServiceReference();
		assertThat(reference).isNotNull();

		Dictionary<String, Object> properties = new Hashtable<>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, "test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_CONTENT_TYPE, "test#1.0");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_FILE_EXTENSION, "test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_ECORE_PATH,
				"org.geckoprojects.emf.example.model.basic/model/basic.ecore");

		AtomicReference<Configuration> refConfig = new AtomicReference<>();

		MonitoringAssertion.executeAndObserve(() -> {

			Configuration c = ca.getConfiguration(EMFNamespaces.DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME, "?");
			c.update(properties);
			refConfig.set(c);

			
		}).untilNoMoreServiceEventModifiedWithin(100, ResourceSetFactory.class)
		  .assertWithTimeoutThat(10000)
		  .hasAtLeastOneServiceEventModifiedWith(ResourceSetFactory.class);

		reference = resourceSetFactory.getServiceReference();
		assertThat(reference).isNotNull();

		DictionaryAssert.assertThat(reference.getProperties()).containsKey(EMFNamespaces.EMF_MODEL_NAME)
				.extractingByKey(EMFNamespaces.EMF_MODEL_NAME).isNotNull()
				.isInstanceOfSatisfying(String[].class, arr -> {
					assertThat(arr).contains("ecore", "test");
				});

		ResourceSetFactory factory = resourceSetFactory.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("person.test");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Person p = BasicFactory.eINSTANCE.createPerson();
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
		refConfig.get().delete();
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}. Unregister the
	 * dynamic package and try to load the resource again, which may fail
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testCreateDynamicModelUnregister(
			@InjectService(cardinality = 1) ServiceAware<ResourceSetFactory> resourceSetFactory)
			throws IOException, InterruptedException {

		assertThat(resourceSetFactory.getServices()).hasSize(1);
		ServiceReference<ResourceSetFactory> reference = resourceSetFactory.getServiceReference();
		assertThat(reference).isNotNull();

		DictionaryAssert.assertThat(reference.getProperties()).containsKey(EMFNamespaces.EMF_MODEL_NAME)
				.extractingByKey(EMFNamespaces.EMF_MODEL_NAME).isNotNull()
				.isInstanceOfSatisfying(String[].class, arr -> {
					assertThat(arr).contains("ecore");
					assertThat(arr).doesNotContain("test");
				});

		Dictionary<String, Object> properties = new Hashtable<>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, "test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_CONTENT_TYPE, "test#1.0");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_FILE_EXTENSION, "test");
		properties.put(EMFNamespaces.PROP_DYNAMIC_CONFIG_ECORE_PATH,
				"org.geckoprojects.emf.example.model.basic/model/basic.ecore");

		AtomicReference<Configuration> refConfig = new AtomicReference<>();
		MonitoringAssertion.executeAndObserve(() -> {

			Configuration c = ca.getConfiguration(EMFNamespaces.DYNAMIC_MODEL_CONFIGURATOR_CONFIG_NAME, "?");
			c.update(properties);
			refConfig.set(c);

		}).untilNoMoreServiceEventModifiedWithin(100, ResourceSetFactory.class).assertWithTimeoutThat(1000)
				.isNotTimedOut().hasAtLeastOneServiceEventModifiedWith(ResourceSetFactory.class);

		reference = resourceSetFactory.getServiceReference();
		assertThat(reference).isNotNull();

		DictionaryAssert.assertThat(reference.getProperties()).containsKey(EMFNamespaces.EMF_MODEL_NAME)
				.extractingByKey(EMFNamespaces.EMF_MODEL_NAME).isNotNull()
				.isInstanceOfSatisfying(String[].class, arr -> {
					assertThat(arr).contains("ecore", "test");
				});

		ResourceSetFactory factory = resourceSetFactory.getService();
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("person.test");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Person p = BasicFactory.eINSTANCE.createPerson();
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

		refConfig.get().delete();

		Resource testLoadResource2 = rs.createResource(uri);

		Assertions.assertThrows(IOWrappedException.class, () -> {
			testLoadResource2.load(bais, null);
		});

	}
}
