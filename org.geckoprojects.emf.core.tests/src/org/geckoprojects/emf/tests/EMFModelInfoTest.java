/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.core.tests.AbstractOSGiTest;
import org.geckoprojects.emf.core.EPackageConfigurator;
import org.geckoprojects.emf.core.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.model.BasicFactory;
import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.example.model.basic.model.Person;
import org.geckoprojects.emf.osgi.model.info.EMFModelInfo;
import org.geckoprojects.emf.osgi.model.test.configurator.TestPackageConfigurator;
import org.geckoprojects.emf.osgi.test.extended.model.configurator.ExtendedTestPackageConfigurator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import org.geckoprojects.emf.model.info.EMFModelInfo;

/**
 * 
 * @author jalbert
 * @since 8 Nov 2018
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class EMFModelInfoTest  {



	/**
	 * Trying to load an instance with a registered {@link EPackage}, then check if the {@link EClass} can be identified.
	 * Than unload the EPackage and expect that no {@link EClass} can be found 
	 * @throws IOException 
	 */
	@Test
	public void testFindEClassByClass() throws IOException {
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup( configurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		ResourceSetFactory factory = getService(ResourceSetFactory.class);
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
		
		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		Optional<EClassifier> eClassifierForClass = emfModelInfo.getEClassifierForClass(Person.class);
		
		EClassifier eClassifier = eClassifierForClass.orElse(null);
		
		assertNotNull(eClassifier);
		
		unregisterService(configurator);
		
		eClassifierForClass = emfModelInfo.getEClassifierForClass(Person.class);
		
		eClassifier = eClassifierForClass.orElse(null);
		
		assertNull(eClassifier);

	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}, then check if the {@link EClass} can be identified.
	 * Than unload the EPackage and expect that no {@link EClass} can be found 
	 * @throws IOException 
	 */
	@Test
	public void testFindEClassByClassName() throws IOException {
		TestPackageConfigurator configurator = new TestPackageConfigurator();
		registerServiceForCleanup( configurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		ResourceSetFactory factory = getService(ResourceSetFactory.class);
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
		
		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		Optional<EClassifier> eClassifierForClass = emfModelInfo.getEClassifierForClass(Person.class.getName());
		
		EClassifier eClassifier = eClassifierForClass.orElse(null);
		
		assertNotNull(eClassifier);
		
		unregisterService(configurator);
		
		eClassifierForClass = emfModelInfo.getEClassifierForClass(Person.class.getName());
		
		eClassifier = eClassifierForClass.orElse(null);
		
		assertNull(eClassifier);
	}
	
	/**
	 * Trying to load an instance with a registered {@link EPackage}, then check if the {@link EClass} can be identified.
	 * Than unload the EPackage and expect that no {@link EClass} can be found 
	 * @throws IOException 
	 */
	@Test
	public void testBasicHierachy() throws IOException {
		TestPackageConfigurator testPackageConfigurator = new TestPackageConfigurator();
		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());

		ExtendedTestPackageConfigurator extendedPackageConfigurator = new ExtendedTestPackageConfigurator();
		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		List<EClass> personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		
		assertNotNull(personHirachy);
		assertEquals(2, personHirachy.size());
	}
	
	/**
	 * Test if the upper hierachy of the person is available in sync with the registered packages
	 * @throws IOException 
	 */
	@Test
	public void testBasicHierachyDynamic() throws IOException {
		TestPackageConfigurator testPackageConfigurator = new TestPackageConfigurator();
		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());

		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		List<EClass> personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		
		assertNotNull(personHirachy);
		assertEquals(1, personHirachy.size());


		ExtendedTestPackageConfigurator extendedPackageConfigurator = new ExtendedTestPackageConfigurator();
		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());

		personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHirachy);
		assertEquals(2, personHirachy.size());
		
		unregisterService(extendedPackageConfigurator);
		
		personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHirachy);
		assertEquals(1, personHirachy.size());
	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}, then check if the {@link EClass} can be identified.
	 * Than unload the EPackage and expect that no {@link EClass} can be found 
	 * @throws IOException 
	 */
	@Test
	public void testBasicHierachyDynamicOrdering() throws IOException {
		
		ExtendedTestPackageConfigurator extendedPackageConfigurator = new ExtendedTestPackageConfigurator();
		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());

		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		List<EClass> personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		
		assertNotNull(personHierachy);
		assertEquals(0, personHierachy.size());
		
		TestPackageConfigurator testPackageConfigurator = new TestPackageConfigurator();
		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
		
		unregisterService(extendedPackageConfigurator);
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(1, personHierachy.size());
	}
	
	@Test
	public void testBasicHierachyDynamicOrdering2() throws IOException {
		
		ExtendedTestPackageConfigurator extendedPackageConfigurator = new ExtendedTestPackageConfigurator();
		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		List<EClass> personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		
		assertNotNull(personHierachy);
		assertEquals(0, personHierachy.size());
		
		TestPackageConfigurator testPackageConfigurator = new TestPackageConfigurator();
		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
		
		unregisterService(extendedPackageConfigurator);
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(1, personHierachy.size());

		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
		
		unregisterService(testPackageConfigurator);
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(0, personHierachy.size());

		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
	}


	
}
