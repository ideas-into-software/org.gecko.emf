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


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.EMFNamespaces;
import org.geckoprojects.emf.core.EPackageConfigurator;
import org.geckoprojects.emf.core.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.ResourceSetFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import org.geckoprojects.emf.example.model.basic.model.BasicPackage;

/**
 * Integration test for the {@link ResourceSetFactory}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class IsolatedResourceSetFactoryIntegrationTest  {


	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testResourceSetFactoryExists() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceChecker<?> testSC = createTrackedChecker("(rsf.name=test)", false);
		testSC.assertCreations(0, false).assertRemovals(0, false);

		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("rsf.name", "test");
		properties.put("rsf.model.target.filter", "(" + EMFNamespaces.EMF_MODEL_NAME + "=*)");
		Configuration c = createConfigForCleanup(EMFNamespaces.ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME, "?", properties);

		testSC.assertCreations(4, true).assertRemovals(0, false);

		Set<ServiceReference<?>> references = testSC.getAllServiceReferences().stream().filter(o->o instanceof ServiceReference<?>).map(o->(ServiceReference<?>)o).collect(Collectors.toSet());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(ResourceSetFactory.class.getName())).
				findFirst().
				isPresent());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(Resource.Factory.Registry.class.getName())).
				findFirst().
				isPresent());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(EPackage.Registry.class.getName())).
				findFirst().
				isPresent());

		testSC.assertCreations(4, false).assertRemovals(0, false);
		deleteConfigurationAndRemoveFromCleanup(c);
		testSC.assertCreations(4, false).assertRemovals(4, true);
	}

	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testResourceSetExists() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceChecker<?> testSC = createTrackedChecker("(rsf.name=test)", false);
		testSC.assertCreations(0, false).assertRemovals(0, false);

		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("rsf.name", "test");
		properties.put("rsf.model.target.filter", "(" + EMFNamespaces.EMF_MODEL_NAME + "=*)");
		Configuration c = createConfigForCleanup(EMFNamespaces.ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME, "?", properties);

		testSC.assertCreations(4, true).assertRemovals(0, false);

		Set<ServiceReference<?>> references = testSC.getAllServiceReferences().stream().filter(o->o instanceof ServiceReference<?>).map(o->(ServiceReference<?>)o).collect(Collectors.toSet());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(ResourceSet.class.getName())).
				findFirst().
				isPresent());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(Resource.Factory.Registry.class.getName())).
				findFirst().
				isPresent());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(EPackage.Registry.class.getName())).
				findFirst().
				isPresent());

		testSC.assertCreations(4, false).assertRemovals(0, false);
		deleteConfigurationAndRemoveFromCleanup(c);
		testSC.assertCreations(4, false).assertRemovals(4, true);
	}

	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testResourceSetFactoryRegister() throws IOException, InterruptedException, InvalidSyntaxException {
		ServiceChecker<?> testSC = createTrackedChecker("(rsf.name=test)", false);
		testSC.assertCreations(0, false).assertRemovals(0, false);

		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("rsf.name", "test");
		properties.put("rsf.model.target.filter", "(" + EMFNamespaces.EMF_MODEL_NAME + "=test)");
		Configuration c = createConfigForCleanup(EMFNamespaces.ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME, "?", properties);

		testSC.assertCreations(4, true).assertRemovals(0, false);

		Set<ServiceReference<?>> references = testSC.getAllServiceReferences().stream().filter(o->o instanceof ServiceReference<?>).map(o->(ServiceReference<?>)o).collect(Collectors.toSet());
		Optional<ServiceReference<?>> rsRefOpt = references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(ResourceSet.class.getName())).
				findFirst();
		assertTrue(rsRefOpt.isPresent());
		Optional<ServiceReference<?>> rsfRefOpt = references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(ResourceSetFactory.class.getName())).
				findFirst();
		assertTrue(rsfRefOpt.isPresent());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(Resource.Factory.Registry.class.getName())).
				findFirst().
				isPresent());
		assertTrue(references.stream().
				filter(sr->((String[])sr.getProperty("objectClass"))[0].equals(EPackage.Registry.class.getName())).
				findFirst().
				isPresent());

		ServiceReference<?> rsfRef = rsfRefOpt.get();
		Object modelNames = rsfRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		ServiceReference<?> rsRef = rsRefOpt.get();
		modelNames = rsRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		ServiceChecker<ResourceFactoryConfigurator> rfcSC = createTrackedChecker(ResourceFactoryConfigurator.class, false).run();
		Dictionary<String, Object> epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, TestPackage.eNAME);
		TestPackageConfigurator configurator1 = new TestPackageConfigurator();
		rfcSC.assertCreations(1, false).assertRemovals(0, false);
		registerServiceForCleanup(configurator1, epackageProperties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		rfcSC.assertCreations(2, true).assertRemovals(0, false);

		rsfRef = rsfRefOpt.get();
		modelNames = rsfRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));
		
		rsRef = rsRefOpt.get();
		modelNames = rsRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, BasicPackage.eNAME + "2");
		/*
		 * No change expected because target filter does not match
		 */
		rfcSC.assertCreations(2, false).assertRemovals(0, false);
		TestPackageConfigurator configurator2 = new TestPackageConfigurator();
		registerServiceForCleanup(configurator2, epackageProperties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		rfcSC.assertCreations(3, true).assertRemovals(0, false);

		rsfRef = rsfRefOpt.get();
		modelNames = rsfRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		rsRef = rsRefOpt.get();
		modelNames = rsRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		rfcSC.assertCreations(3, false).assertRemovals(0, false);
		unregisterService(configurator1);
		rfcSC.assertCreations(3, false).assertRemovals(1, true);
		unregisterService(configurator2);
		rfcSC.assertCreations(3, false).assertRemovals(2, true);

		testSC.assertCreations(4, false).assertRemovals(0, false);
		deleteConfigurationAndRemoveFromCleanup(c);
		testSC.assertCreations(4, false).assertRemovals(4, true);

	}

}