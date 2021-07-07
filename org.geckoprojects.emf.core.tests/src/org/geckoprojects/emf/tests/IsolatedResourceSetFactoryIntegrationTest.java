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
import java.util.concurrent.atomic.AtomicReference;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.api.EMFNamespaces;
import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.geckoprojects.emf.core.api.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.api.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.osgitest.events.RuntimeMonitoringAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * Integration test for the {@link ResourceSetFactory}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class IsolatedResourceSetFactoryIntegrationTest  {


	@InjectService ConfigurationAdmin ca;
	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testResourceSetFactoryExists(@InjectService(filter = "(rsf.name=test)",cardinality =  0) ServiceAware<?> serviceAware) throws IOException, InterruptedException, InvalidSyntaxException {

		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("rsf.name", "test");
		properties.put("rsf.model.target.filter", "(" + EMFNamespaces.EMF_MODEL_NAME + "=*)");
		
		
		AtomicReference<Configuration> cr=new AtomicReference<>();
		RuntimeMonitoringAssert.executeAndObserve(()->{
		Configuration configuration=	ca.getConfiguration(EMFNamespaces.ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME, "?");
			configuration.update(properties);
			
			cr.set(configuration);
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasExactlyOneServiceEventRegisteredWith(ResourceSetFactory.class)
		.hasExactlyOneServiceEventRegisteredWith(Resource.Factory.Registry.class)
		.hasExactlyOneServiceEventRegisteredWith(EPackage.Registry.class);
		
		Assertions.assertThat(serviceAware.getServiceReferences()).hasSize(4);
cr.get().delete();
	}

	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testResourceSetExists(@InjectService(filter = "(rsf.name=test)",cardinality =  0) ServiceAware<?> serviceAware) throws IOException, InterruptedException, InvalidSyntaxException {

		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("rsf.name", "test");
		properties.put("rsf.model.target.filter", "(" + EMFNamespaces.EMF_MODEL_NAME + "=*)");
		
		
		AtomicReference<Configuration> cr=new AtomicReference<>();
		RuntimeMonitoringAssert.executeAndObserve(()->{
		Configuration configuration=	ca.getConfiguration(EMFNamespaces.ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME, "?");
			configuration.update(properties);
			
			cr.set(configuration);
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasExactlyOneServiceEventRegisteredWith(ResourceSet.class)
		.hasExactlyOneServiceEventRegisteredWith(Resource.Factory.Registry.class)
		.hasExactlyOneServiceEventRegisteredWith(EPackage.Registry.class);
		
		Assertions.assertThat(serviceAware.getServiceReferences()).hasSize(4);
cr.get().delete();





	}

	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidSyntaxException 
	 */
	@Test
	public void testResourceSetFactoryRegister(@InjectService(filter = "(rsf.name=test)",cardinality =  0) ServiceAware<ResourceSet> serviceAware,@InjectService(filter = "(rsf.name=test)",cardinality =  0) ServiceAware<ResourceSetFactory> serviceAwareF) throws IOException, InterruptedException, InvalidSyntaxException {

		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("rsf.name", "test");
		properties.put("rsf.model.target.filter", "(" + EMFNamespaces.EMF_MODEL_NAME + "=test)");
		
		
		AtomicReference<Configuration> cr=new AtomicReference<>();
		RuntimeMonitoringAssert.executeAndObserve(()->{
		Configuration configuration=	ca.getConfiguration(EMFNamespaces.ISOLATED_RESOURCE_SET_FACTORY_CONFIG_NAME, "?");
			configuration.update(properties);
			
			cr.set(configuration);
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasExactlyOneServiceEventRegisteredWith(ResourceSet.class)
		.hasExactlyOneServiceEventRegisteredWith(ResourceSetFactory.class)
		.hasExactlyOneServiceEventRegisteredWith(Resource.Factory.Registry.class)
		.hasExactlyOneServiceEventRegisteredWith(EPackage.Registry.class);
		
		Assertions.assertThat(serviceAware.getServiceReferences()).hasSize(4);
cr.get().delete();




		ServiceReference<?> rsfRef = serviceAwareF.getServiceReference();
		Object modelNames = rsfRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		List<String> modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		ServiceReference<?> rsRef = serviceAware.getServiceReference();
		modelNames = rsRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertFalse(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		Dictionary<String, Object> epackageProperties = new Hashtable<String, Object>();
		epackageProperties.put(EMFNamespaces.EMF_MODEL_NAME, BasicPackage.eNAME);
		BasicPackageConfigurator configurator1 = new BasicPackageConfigurator();
		rfcSC.assertCreations(1, false).assertRemovals(0, false);
		registerServiceForCleanup(configurator1, epackageProperties, new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()});
		rfcSC.assertCreations(2, true).assertRemovals(0, false);

		rsfRef = serviceAware.getServiceReference();
		modelNames = rsfRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));
		
		rsRef = serviceAwareF.getServiceReference();
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

		rsfRef = serviceAwareF.getServiceReference();
		modelNames = rsfRef.getProperty(EMFNamespaces.EMF_MODEL_NAME);
		assertNotNull(modelNames);
		assertTrue(modelNames instanceof String[]);
		modelNameList = Arrays.asList((String[]) modelNames);
		assertTrue(modelNameList.contains("ecore"));
		assertTrue(modelNameList.contains("test"));
		assertFalse(modelNameList.contains("test2"));

		rsRef = serviceAware.getServiceReference();;
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