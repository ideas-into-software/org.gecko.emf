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
package org.gecko.emf.osgi.itest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.ResourceSetConfigurator;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.gecko.emf.osgi.example.model.manual.Foo;
import org.gecko.emf.osgi.example.model.manual.ManualFactory;
import org.gecko.emf.osgi.example.model.manual.ManualPackage;
import org.gecko.emf.osgi.example.model.manual.configuration.ManualPackageConfigurator;
import org.gecko.emf.osgi.itest.configurator.TestConfigurator;
import org.gecko.emf.osgi.itest.configurator.TestResourceSetConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.assertj.monitoring.MonitoringAssertion;
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
public class EMFOSGiIntegrationTest {

	@InjectBundleContext
	BundleContext bc;

	/**
	 * Trying to load an instance with a non registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceFailNoEPackage_Factory(@InjectService ServiceAware<ResourceSetFactory> sa)
			throws IOException {

		ServiceReference<ResourceSetFactory> reference = sa.getServiceReference();
		assertNotNull(reference);

		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));

		ResourceSetFactory factory = sa.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		Assertions.assertThatThrownBy(() -> {

			testLoadResource.load(bais, null);
		}).isInstanceOf(IOWrappedException.class);
	}

	/**
	 * Trying to load an instance with a non registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceFailNoEPackage(@InjectService ServiceAware<ResourceSet> sa) throws IOException {

		ServiceReference<ResourceSet> reference = sa.getServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertEquals(0, ((String[]) configNames).length);

		ResourceSet rs = sa.getService();
		assertNotNull(rs);
		URI uri = URI.createURI("foox.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);

		Assertions.assertThatThrownBy(() -> {

			testLoadResource.load(bais, null);
		}).isInstanceOf(IOWrappedException.class);

	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void testLoadResourceRegisteredEPackage_Factory(@InjectService ServiceAware<ResourceSetFactory> sa)
			throws IOException, InvalidSyntaxException {

		bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				new ManualPackageConfigurator(), new Hashtable<String, Object>());

		ServiceReference<ResourceSetFactory> reference = sa.getServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertEquals(0, ((String[]) configNames).length);

		assertNotNull(reference);
		ResourceSetFactory factory = sa.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("fooxx.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void testLoadResourceRegisteredEPackage_FactoryWithModelName(
			@InjectService(filter = "(emf.model.name=manual)", cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF)
			throws IOException, InvalidSyntaxException {

		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "manual");

		MonitoringAssertion.executeAndObserve(() -> {
			bc.registerService(
					new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
					new ManualPackageConfigurator(), properties);

		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000)
				.hasExactlyOneServiceEventRegisteredWith(EPackageConfigurator.class)
				.hasExactlyOneServiceEventRegisteredWith(ResourceFactoryConfigurator.class);

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertEquals(0, ((String[]) configNames).length);

		assertNotNull(reference);
		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("fooo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void testLoadResourceRegisteredConfigurator_FactoryWithModelName(
			@InjectService(filter = "(emf.configurator.name=myConfig)", cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF)
			throws IOException, InvalidSyntaxException {

		MonitoringAssertion.executeAndObserve(() -> {
			Dictionary<String, Object> properties = new Hashtable<String, Object>();
			properties.put("emf.model.name", "manual");
			bc.registerService(
					new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
					new ManualPackageConfigurator(), properties);

		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000)
				.hasExactlyOneServiceEventRegisteredWith(EPackageConfigurator.class)
				.hasExactlyOneServiceEventRegisteredWith(ResourceFactoryConfigurator.class);

		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.configurator.name", "myConfig");
		bc.registerService(new String[] { ResourceSetConfigurator.class.getName() }, new TestResourceSetConfiguration(),
				properties);

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));

		assertNotNull(reference);
		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("fff.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void testLoadResourceRegisteredConfiguratorManyConfigName_FactoryWithModelName(
			@InjectService(filter = "(&(emf.configurator.name=myConfig)(emf.configurator.name=mySecConfig)(emf.configurator.name=myThirdConfig))", cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF)
			throws IOException, InvalidSyntaxException {

		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "manual");

		bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				new ManualPackageConfigurator(), properties);

		properties = new Hashtable<String, Object>();
		String[] configs = new String[] { "myConfig", "mySecConfig" };
		properties.put("emf.configurator.name", configs);

		bc.registerService(new String[] { ResourceSetConfigurator.class.getName() }, new TestResourceSetConfiguration(),
				properties);

		properties = new Hashtable<String, Object>();
		properties.put("emf.configurator.name", "myThirdConfig");
		bc.registerService(new String[] { ResourceSetConfigurator.class.getName() }, new TestResourceSetConfiguration(),
				properties);

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));

		assertNotNull(reference);
		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("fffg.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void testLoadResourceRegisteredManyChangeConfigName_FactoryWithModelName(
			@InjectService(filter = "(&(emf.configurator.name=myConfig)(emf.configurator.name=mySecConfig)(emf.configurator.name=myThirdConfig))", cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF)
			throws IOException, InvalidSyntaxException {

		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "manual");
		EPackageConfigurator testConfig = new ManualPackageConfigurator();
		ServiceRegistration<?> reg1 = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				testConfig, properties);

		properties = new Hashtable<String, Object>();
		String[] configs = new String[] { "myConfig", "mySecConfig" };
		properties.put("emf.configurator.name", configs);
		ResourceSetConfigurator rsConfig = new TestResourceSetConfiguration();
		ServiceRegistration<?> reg2 = bc.registerService(new String[] { ResourceSetConfigurator.class.getName() },
				rsConfig, properties);

		properties = new Hashtable<String, Object>();
		properties.put("emf.configurator.name", "myThirdConfig");
		bc.registerService(new String[] { ResourceSetConfigurator.class.getName() }, new TestResourceSetConfiguration(),
				properties);

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));

		assertNotNull(reference);
		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("fofo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "manual2");
		reg1.setProperties(properties);

		reference = serviceAwareRSF.getServiceReference();
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual2"));
		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));

		configs = new String[] { "myConfig", "mySecConfig", "hallo" };
		properties.put("emf.configurator.name", configs);
		reg2.setProperties(properties);

		reference = serviceAwareRSF.getServiceReference();
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual2"));
		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));
		assertTrue(configNameList.contains("hallo"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceRegisteredEPackage(
			@InjectService(cardinality = 0) ServiceAware<ResourceSet> serviceAwareRS) throws IOException {

		bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				new ManualPackageConfigurator(), new Hashtable<String, Object>());

		ServiceReference<ResourceSet> reference = serviceAwareRS.getServiceReference();
		assertNotNull(reference);
		ResourceSet rs = serviceAwareRS.getService();
		assertNotNull(rs);
		URI uri = URI.createURI("person.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceRegisteredEPackageResourceSet_Factory(
			@InjectService(cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF) throws IOException {
		bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				new ManualPackageConfigurator(), new Hashtable<String, Object>());

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		assertNotNull(reference);
		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		URI uri = URI.createURI("person.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();

		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceRegisteredEPackageResourceSet(
			@InjectService(cardinality = 0) ServiceAware<ResourceSet> serviceAwareRS) throws IOException {

		bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				new ManualPackageConfigurator(), new Hashtable<String, Object>());

		ServiceReference<ResourceSet> reference = serviceAwareRS.getServiceReference();
		assertNotNull(reference);
		ResourceSet rs = serviceAwareRS.getService();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the
	 * EPackage and have an exception
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testLoadResourceRegisteredEPackageAndUnregister_Factory(
			@InjectService(cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF)
			throws IOException, InterruptedException {

		ManualPackageConfigurator configurator = new ManualPackageConfigurator();

		ServiceRegistration<?> reg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, new Hashtable<String, Object>());

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		assertNotNull(reference);
		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");

		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		reg.unregister();

		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());

		Assertions.assertThatThrownBy(() -> {

			testLoadResource2.load(bais, null);
		}).isInstanceOf(IOWrappedException.class);
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the
	 * EPackage and have an exception
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testLoadResourceRegisteredEPackageAndUnregister(
			@InjectService(cardinality = 0) ServiceAware<ResourceSet> serviceAwareRS)
			throws IOException, InterruptedException {
		ManualPackageConfigurator configurator = new ManualPackageConfigurator();
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(EMFNamespaces.EMF_MODEL_NAME, ManualPackage.eNAME);
		props.put(EMFNamespaces.EMF_MODEL_NSURI, ManualPackage.eNS_URI);

		ServiceRegistration<?> reg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, props);

		ServiceReference<ResourceSet> reference = serviceAwareRS.getServiceReference();
		assertNotNull(reference);
		ResourceSet rs = serviceAwareRS.getService();
		assertNotNull(rs);

		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");

		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		reg.unregister();
		Thread.sleep(1000l);
		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());

		Assertions.assertThatThrownBy(() -> {
			testLoadResource2.load(bais, null);
		}).isInstanceOf(IOWrappedException.class);
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the
	 * EPackage and have an exception
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceRegisteredEPackageAndUnregisterProperties_Factory(
			@InjectService(cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF) throws IOException {
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, ManualPackage.eNAME);
		ManualPackageConfigurator configurator = new ManualPackageConfigurator();

		ServiceRegistration<?> reg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, properties);

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));

		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");

		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		reg.unregister();

		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());

		Assertions.assertThatThrownBy(() -> {
			testLoadResource2.load(bais, null);
		}).isInstanceOf(IOWrappedException.class);

		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the
	 * EPackage and have an exception
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadResourceRegisteredEPackageAndUnregisterProperties(
			@InjectService(cardinality = 0) ServiceAware<ResourceSet> serviceAwareRS) throws IOException {
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, ManualPackage.eNAME);
		ManualPackageConfigurator configurator = new ManualPackageConfigurator();

		ServiceRegistration<?> reg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, properties);

		ServiceReference<ResourceSet> reference = serviceAwareRS.getServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));

		ResourceSet rs = serviceAwareRS.getService();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		reg.unregister();

		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());

		Assertions.assertThatThrownBy(() -> {
			testLoadResource2.load(bais, null);
		}).isInstanceOf(IOWrappedException.class);

		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the
	 * EPackage and have an exception
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testEPackageAndConfiguratorProperties_Factory(
			@InjectService(cardinality = 0) ServiceAware<ResourceSetFactory> serviceAwareRSF)
			throws IOException, InterruptedException {
		Dictionary<String, Object> epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, ManualPackage.eNAME);
		ManualPackageConfigurator configurator = new ManualPackageConfigurator();

		ServiceRegistration<?> sreg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, epackageProperties);

		ServiceReference<ResourceSetFactory> reference = serviceAwareRSF.getServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertEquals(0, configNameList.size());

		Dictionary<String, Object> configProperties = new Hashtable<String, Object>();
		configProperties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, "testConfigurator");
		configProperties.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME, "testResourceConfigurator");

		TestConfigurator configurator2 = new TestConfigurator();

		ServiceRegistration<?> sreg2 = bc.registerService(
				new String[] { ResourceSetConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator2, configProperties);
		reference = serviceAwareRSF.getServiceReference();
		assertNotNull(reference);

		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertEquals(1, configNameList.size());
		assertTrue(configNameList.contains("testConfigurator"));

		configNames = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("testResourceConfigurator"));

		ResourceSetFactory factory = serviceAwareRSF.getService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		sreg.unregister();
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));

		sreg2.unregister();

		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertEquals(0, configNameList.size());
		assertFalse(configNameList.contains("testConfigurator"));

		configNames = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertFalse(configNameList.contains("testResourceConfigurator"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the
	 * EPackage and have an exception
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testEPackageAndConfiguratorProperties(
			@InjectService(cardinality = 0) ServiceAware<ResourceSet> serviceAwareRS)
			throws IOException, InterruptedException {
		Dictionary<String, Object> epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, ManualPackage.eNAME);
		ManualPackageConfigurator configurator = new ManualPackageConfigurator();

		ServiceRegistration<?> reg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, epackageProperties);

		ServiceReference<ResourceSet> reference = serviceAwareRS.getServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("manual"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertEquals(0, configNameList.size());

		Dictionary<String, Object> configProperties = new Hashtable<String, Object>();
		configProperties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, "testConfigurator");
		configProperties.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME, "testResourceConfigurator");

		TestConfigurator configurator2 = new TestConfigurator();

		ServiceRegistration<?> reg2 = bc.registerService(
				new String[] { ResourceSetConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator2, configProperties);
		reference = serviceAwareRS.getServiceReference();
		assertNotNull(reference);

		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertEquals(1, configNameList.size());
		assertTrue(configNameList.contains("testConfigurator"));

		configNames = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("testResourceConfigurator"));

		ResourceSet rs = serviceAwareRS.getService();
		assertNotNull(rs);
		URI uri = URI.createURI("foo.manual");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo p = ManualFactory.eINSTANCE.createFoo();
		p.setValue("Emil");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Foo result = (Foo) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getValue());

		reg.unregister();

		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("manual"));

		reg2.unregister();

		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertEquals(0, configNameList.size());
		assertFalse(configNameList.contains("testConfigurator"));

		configNames = reference.getProperty(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		configNameList = Arrays.asList((String[]) configNames);
		assertFalse(configNameList.contains("testResourceConfigurator"));
	}

}