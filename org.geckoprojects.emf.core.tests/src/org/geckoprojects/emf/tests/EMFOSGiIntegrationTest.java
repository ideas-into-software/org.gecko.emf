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
package org.geckoprojects.emf.tests;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.core.tests.ServiceChecker;
import org.geckoprojects.emf.core.api.EMFNamespaces;
import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.geckoprojects.emf.core.api.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.api.ResourceSetConfigurator;
import org.geckoprojects.emf.core.api.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.model.BasicFactory;
import org.geckoprojects.emf.example.model.basic.model.Person;
import org.geckoprojects.emf.osgi.model.test.TestPackage;
import org.geckoprojects.emf.osgi.model.test.configurator.TestPackageConfigurator;
import org.geckoprojects.emf.tests.configurator.TestConfigurator;
import org.geckoprojects.emf.tests.configurator.TestResourceSetConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * Tests the EMF OSGi integration
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class EMFOSGiIntegrationTest {
@InjectBundleContext BundleContext bc;
	
	/**
	 * Trying to load an instance with a non registered {@link EPackage}
	 * @throws IOException 
	 */
	@Test(expected=IOWrappedException.class)
	public void testLoadResourceFailNoEPackage_Factory(@InjectService ServiceAware<ResourceSetFactory> sa) throws IOException {
		
		ServiceReference<ResourceSetFactory> reference = sa.getServiceReference();
		assertNotNull(reference);

		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));

		ResourceSetFactory factory = sa.getService();
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
	}

	/**
	 * Trying to load an instance with a non registered {@link EPackage}
	 * @throws IOException 
	 */
	@Test(expected=IOWrappedException.class)
	public void testLoadResourceFailNoEPackage(@InjectService ServiceAware<ResourceSet> sa) throws IOException {
		
		ServiceReference<ResourceSet> reference = sa.getServiceReference();
			assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertEquals(0, ((String[])configNames).length);

		ResourceSet rs = sa.getService();
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
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackage_Factory(@InjectService ServiceAware<ResourceSetFactory> sa) throws IOException, InvalidSyntaxException {

		
		bc.registerService(new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()}, new TestPackageConfigurator(), new Hashtable<String, Object>());



		
		ServiceReference<ResourceSetFactory> reference =sa.getServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertEquals(0, ((String[])configNames).length);
		
		assertNotNull(reference);
		ResourceSetFactory factory = sa.getService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
	}
	
	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackage_FactoryWithModelName() throws IOException, InvalidSyntaxException {
		
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "test");
		registerServiceForCleanup(new TestPackageConfigurator(), properties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		
		ServiceChecker<Object> rsfSC = createTrackedChecker("(&(objectClass=org.geckoprojects.emf.core.ResourceSetFactory)(emf.model.name=test))", true).run();
		rsfSC.assertCreations(1, true);
		ServiceReference<Object> reference = rsfSC.getTrackedServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertEquals(0, ((String[])configNames).length);
		
		assertNotNull(reference);
		assertTrue(rsfSC.getTrackedService() instanceof ResourceSetFactory);
		ResourceSetFactory factory = (ResourceSetFactory) rsfSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
	}
	
	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testLoadResourceRegisteredConfigurator_FactoryWithModelName() throws IOException, InvalidSyntaxException {
		
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "test");
		registerServiceForCleanup(new TestPackageConfigurator(), properties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		
		
		ServiceChecker<Object> rsfSC = createTrackedChecker("(&(objectClass=org.geckoprojects.emf.core.ResourceSetFactory)(emf.configurator.name=myConfig))", true).run();
		rsfSC.assertCreations(0, false);
		
		properties = new Hashtable<String, Object>();
		properties.put("emf.configurator.name", "myConfig");
		registerServiceForCleanup(new TestResourceSetConfiguration(), properties, new String[] {ResourceSetConfigurator.class.getName()});
		rsfSC.assertCreations(1, true);
		
		ServiceReference<Object> reference = rsfSC.getTrackedServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		
		assertNotNull(reference);
		assertTrue(rsfSC.getTrackedService() instanceof ResourceSetFactory);
		ResourceSetFactory factory = (ResourceSetFactory) rsfSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
	}
	
	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testLoadResourceRegisteredConfiguratorManyConfigName_FactoryWithModelName() throws IOException, InvalidSyntaxException {
		
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "test");
		registerServiceForCleanup(new TestPackageConfigurator(), properties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		
		
		ServiceChecker<Object> rsfSC = createTrackedChecker("(&(objectClass=org.geckoprojects.emf.core.ResourceSetFactory)(emf.configurator.name=myConfig)(emf.configurator.name=mySecConfig)(emf.configurator.name=myThirdConfig))", true).run();
		rsfSC.assertCreations(0, false);
		
		properties = new Hashtable<String, Object>();
		String[] configs = new String[] {"myConfig", "mySecConfig"};
		properties.put("emf.configurator.name", configs);
		registerServiceForCleanup(new TestResourceSetConfiguration(), properties, new String[] {ResourceSetConfigurator.class.getName()});
		rsfSC.assertCreations(0, true);
		
		properties = new Hashtable<String, Object>();
		properties.put("emf.configurator.name", "myThirdConfig");
		registerServiceForCleanup(new TestResourceSetConfiguration(), properties, new String[] {ResourceSetConfigurator.class.getName()});
		rsfSC.assertCreations(1, true);
		
		ServiceReference<Object> reference = rsfSC.getTrackedServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));
		
		assertNotNull(reference);
		assertTrue(rsfSC.getTrackedService() instanceof ResourceSetFactory);
		ResourceSetFactory factory = (ResourceSetFactory) rsfSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
	}
	
	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testLoadResourceRegisteredManyChangeConfigName_FactoryWithModelName() throws IOException, InvalidSyntaxException {
		
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "test");
		EPackageConfigurator testConfig = new TestPackageConfigurator();
		registerServiceForCleanup(testConfig, properties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		
		
		ServiceChecker<Object> rsfSC = createTrackedChecker("(&(objectClass=org.geckoprojects.emf.core.ResourceSetFactory)(emf.configurator.name=myConfig)(emf.configurator.name=mySecConfig)(emf.configurator.name=myThirdConfig))", true).run();
		rsfSC.assertCreations(0, false);
		
		properties = new Hashtable<String, Object>();
		String[] configs = new String[] {"myConfig", "mySecConfig"};
		properties.put("emf.configurator.name", configs);
		ResourceSetConfigurator rsConfig = new TestResourceSetConfiguration();
		registerServiceForCleanup(rsConfig, properties, new String[] {ResourceSetConfigurator.class.getName()});
		rsfSC.assertCreations(0, true);
		
		properties = new Hashtable<String, Object>();
		properties.put("emf.configurator.name", "myThirdConfig");
		registerServiceForCleanup(new TestResourceSetConfiguration(), properties, new String[] {ResourceSetConfigurator.class.getName()});
		rsfSC.assertCreations(1, true);
		
		ServiceReference<Object> reference = rsfSC.getTrackedServiceReference();
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));
		
		assertNotNull(reference);
		assertTrue(rsfSC.getTrackedService() instanceof ResourceSetFactory);
		ResourceSetFactory factory = (ResourceSetFactory) rsfSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
		
		properties = new Hashtable<String, Object>();
		properties.put("emf.model.name", "test2");
		rsfSC.assertModifications(0, false);
		updateServiceRegistration(testConfig, properties);
		rsfSC.assertModifications(1, true);
		
		reference = rsfSC.getTrackedServiceReference();
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test2"));
		configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		configNameList = Arrays.asList((String[]) configNames);
		assertTrue(configNameList.contains("myConfig"));
		assertTrue(configNameList.contains("mySecConfig"));
		assertTrue(configNameList.contains("myThirdConfig"));
		
		configs = new String[] {"myConfig", "mySecConfig", "hallo"};
		properties.put("emf.configurator.name", configs);
		rsfSC.assertModifications(1, false);
		updateServiceRegistration(rsConfig, properties);
		rsfSC.assertModifications(2, true);
		
		reference = rsfSC.getTrackedServiceReference();
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test2"));
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
	 * @throws IOException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackage() throws IOException {

		registerServiceForCleanup(new TestPackageConfigurator(), new Hashtable<String, Object>(), new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSet> rsSC = createStaticTrackedChecker(ResourceSet.class).run();
		ServiceReference<ResourceSet> reference = rsSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSet rs = rsSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
		
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackageResourceSet_Factory() throws IOException {

		registerServiceForCleanup(new TestPackageConfigurator(), new Hashtable<String, Object>(), new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSetFactory> rsfSC = createStaticTrackedChecker(ResourceSetFactory.class).run();
		ServiceReference<ResourceSetFactory> reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSetFactory factory = rsfSC.getTrackedService();
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}
	 * @throws IOException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackageResourceSet() throws IOException {

		registerServiceForCleanup(new TestPackageConfigurator(), new Hashtable<String, Object>(), new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSet> rsSC = createStaticTrackedChecker(ResourceSet.class).run();
		ServiceReference<ResourceSet> reference = rsSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSet rs = rsSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the EPackage and have an exception
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test(expected=IOWrappedException.class)
	public void testLoadResourceRegisteredEPackageAndUnregister_Factory() throws IOException, InterruptedException {

		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup(configurator, new Hashtable<String, Object>(), new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSetFactory> rsfSC = createStaticTrackedChecker(ResourceSetFactory.class);
		rsfSC.assertCreations(1, true).assertModifications(0, false);
		
		ServiceReference<ResourceSetFactory> reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSetFactory factory = rsfSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());

		rsfSC.assertCreations(1, false).assertModifications(0, false).assertRemovals(0, false);
		unregisterService(configurator);
		rsfSC.assertCreations(1, false).assertModifications(0, false).assertRemovals(0, false);

		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());
		testLoadResource2.load(bais, null);
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the EPackage and have an exception
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test(expected=IOWrappedException.class)
	public void testLoadResourceRegisteredEPackageAndUnregister() throws IOException, InterruptedException {
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(EMFNamespaces.EMF_MODEL_NAME, TestPackage.eNAME);
		props.put(EMFNamespaces.EMF_MODEL_NSURI, TestPackage.eNS_URI);
		registerServiceForCleanup(configurator, props, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		ServiceChecker<ResourceSet> rsSC = createStaticTrackedChecker(ResourceSet.class);
		rsSC.setCreateTimeout(1000);
		rsSC.setModifyTimeout(1000);
		rsSC.setRemovalTimeout(1000);
		rsSC.assertCreations(1, false);
		ServiceReference<ResourceSet> reference = rsSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSet rs = rsSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());

		rsSC.assertCreations(1, false).assertModifications(0, false).assertRemovals(0, false);
		unregisterService(configurator);
		rsSC.assertCreations(1, false).assertModifications(1, true).assertRemovals(0, false);
		Thread.sleep(1000l);
		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());
		testLoadResource2.load(bais, null);
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the EPackage and have an exception
	 * @throws IOException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackageAndUnregisterProperties_Factory() throws IOException {
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, TestPackage.eNAME);
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup(configurator, properties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSetFactory> rsfSC = createStaticTrackedChecker(ResourceSetFactory.class).run();
		rsfSC.assertCreations(1, true).assertModifications(0, false);

		ServiceReference<ResourceSetFactory> reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
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
		Person p = BasicFactory.eINSTANCE.createPerson();
		p.setFirstName("Emil");
		p.setLastName("Tester");
		testSaveResource.getContents().add(p);
		testSaveResource.save(baos, null);

		byte[] content = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(content);

		Resource testLoadResource = rs.createResource(uri);
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());

		rsfSC.assertCreations(1, false).assertModifications(0, false);
		unregisterService(configurator);
		rsfSC.assertCreations(1, false).assertModifications(1, true);

		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());
		try {
			testLoadResource2.load(bais, null);
			fail("IOWrappedException exptected");
		} catch (IOWrappedException e) {
			assertNotNull(e);
		}
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the EPackage and have an exception
	 * @throws IOException 
	 */
	@Test
	public void testLoadResourceRegisteredEPackageAndUnregisterProperties() throws IOException {
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(EMFNamespaces.EMF_MODEL_NAME, TestPackage.eNAME);
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup(configurator, properties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSet> rsSC = createStaticTrackedChecker(ResourceSet.class).run();
		ServiceReference<ResourceSet> reference = rsSC.getTrackedServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));

		ResourceSet rs = rsSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());

		rsSC.assertCreations(1, false).assertModifications(0, false);
		unregisterService(configurator);
		rsSC.assertCreations(1, false).assertModifications(1, true);

		Resource testLoadResource2 = rs.createResource(uri);
		assertNotEquals(testLoadResource, testLoadResource2);
		assertEquals(0, testLoadResource2.getContents().size());
		try {
			testLoadResource2.load(bais, null);
			fail("IOWrappedException exptected");
		} catch (IOWrappedException e) {
			assertNotNull(e);
		}
		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the EPackage and have an exception
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testEPackageAndConfiguratorProperties_Factory() throws IOException, InterruptedException {
		Dictionary<String, Object> epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, TestPackage.eNAME);
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup(configurator, epackageProperties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSetFactory> rsfSC = createStaticTrackedChecker(ResourceSetFactory.class).run();
		rsfSC.assertCreations(1, true).assertModifications(0, false);

		ServiceReference<ResourceSetFactory> reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertEquals(0, configNameList.size());

		Dictionary<String, Object> configProperties = new Hashtable<String, Object>();
		configProperties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, "testConfigurator");
		configProperties.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME, "testResourceConfigurator");
		
		TestConfigurator configurator2 = new TestConfigurator();
		rsfSC.assertCreations(1, false).assertModifications(0, false);
		registerServiceForCleanup(configurator2, configProperties, new String[] {ResourceSetConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		rsfSC.assertCreations(1, false).assertModifications(2, true);
		reference = rsfSC.getTrackedServiceReference();
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
		assertEquals(1, configNameList.size());
		assertTrue(configNameList.contains("testResourceConfigurator"));

		ResourceSetFactory factory = rsfSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());
		
		rsfSC.assertCreations(1, false).assertModifications(2, false);
		unregisterService(configurator);
		rsfSC.assertCreations(1, false).assertModifications(3, true);

		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));

		rsfSC.assertCreations(1, false).assertModifications(3, false);
		unregisterService(configurator2);
		rsfSC.assertCreations(1, false).assertModifications(5, true);

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
		assertEquals(0, configNameList.size());
		assertFalse(configNameList.contains("testResourceConfigurator"));
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage} than unload the EPackage and have an exception
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testEPackageAndConfiguratorProperties() throws IOException, InterruptedException {
		Dictionary<String, Object> epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, TestPackage.eNAME);
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup(configurator, epackageProperties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});

		ServiceChecker<ResourceSet> rsSC = createStaticTrackedChecker(ResourceSet.class).run();
		rsSC.assertCreations(1, true).assertModifications(0, false);

		ServiceReference<ResourceSet> reference = rsSC.getTrackedServiceReference();
		assertNotNull(reference);
		Object modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		Object configNames = reference.getProperty(EMFNamespaces.EMF_CONFIGURATOR_NAME);
		assertNotNull(configNames);
		assertTrue(configNames instanceof String[]);
		List<String> configNameList = Arrays.asList((String[]) configNames);
		assertEquals(0, configNameList.size());

		Dictionary<String, Object> configProperties = new Hashtable<String, Object>();
		configProperties.put(EMFNamespaces.EMF_CONFIGURATOR_NAME, "testConfigurator");
		configProperties.put(EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_NAME, "testResourceConfigurator");
		
		TestConfigurator configurator2 = new TestConfigurator();
		rsSC.assertCreations(1, false).assertModifications(0, false);
		registerServiceForCleanup(configurator2, configProperties, new String[] {ResourceSetConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		rsSC.assertCreations(1, false).assertModifications(2, true);
		reference = rsSC.getTrackedServiceReference();
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
		assertEquals(1, configNameList.size());
		assertTrue(configNameList.contains("testResourceConfigurator"));

		ResourceSet rs = rsSC.getTrackedService();
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
		assertEquals(0, testLoadResource.getContents().size());
		testLoadResource.load(bais, null);
		assertEquals(1, testLoadResource.getContents().size());
		Person result = (Person) testLoadResource.getContents().get(0);
		assertNotNull(result);
		assertEquals("Emil", result.getFirstName());
		assertEquals("Tester", result.getLastName());

		rsSC.assertCreations(1, false).assertModifications(2, false);
		unregisterService(configurator);
		rsSC.assertCreations(1, false).assertModifications(3, true);

		modelNames = reference.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));

		rsSC.assertCreations(1, false).assertModifications(3, false);
		unregisterService(configurator2);
		rsSC.assertCreations(1, false).assertModifications(5, true);

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
		assertEquals(0, configNameList.size());
		assertFalse(configNameList.contains("testResourceConfigurator"));
	}

}