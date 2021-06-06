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
package org.gecko.emf.osgi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.gecko.emf.osgi.model.info.EMFModelInfo;
import org.gecko.emf.osgi.model.test.Person;
import org.gecko.emf.osgi.model.test.TestFactory;
import org.gecko.emf.osgi.model.test.TestPackage;
import org.gecko.emf.osgi.model.test.configurator.TestPackageConfigurator;
import org.gecko.emf.osgi.test.extended.model.configurator.ExtendedTestPackageConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.FrameworkUtil;

/**
 * 
 * @author jalbert
 * @since 8 Nov 2018
 */
@RunWith(MockitoJUnitRunner.class)
public class EMFModelInfoTest extends AbstractOSGiTest {

	/**
	 * Creates a new instance.
	 * @param bundleContext
	 */
	public EMFModelInfoTest() {
		super(FrameworkUtil.getBundle(EMFModelInfoTest.class).getBundleContext());
	}

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
		Person p = TestFactory.eINSTANCE.createPerson();
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
		Person p = TestFactory.eINSTANCE.createPerson();
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
		
		List<EClass> personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		
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
		
		List<EClass> personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		
		assertNotNull(personHirachy);
		assertEquals(1, personHirachy.size());


		ExtendedTestPackageConfigurator extendedPackageConfigurator = new ExtendedTestPackageConfigurator();
		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());

		personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHirachy);
		assertEquals(2, personHirachy.size());
		
		unregisterService(extendedPackageConfigurator);
		
		personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
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
		
		List<EClass> personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		
		assertNotNull(personHierachy);
		assertEquals(0, personHierachy.size());
		
		TestPackageConfigurator testPackageConfigurator = new TestPackageConfigurator();
		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
		
		unregisterService(extendedPackageConfigurator);
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(1, personHierachy.size());
	}
	
	@Test
	public void testBasicHierachyDynamicOrdering2() throws IOException {
		
		ExtendedTestPackageConfigurator extendedPackageConfigurator = new ExtendedTestPackageConfigurator();
		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		EMFModelInfo emfModelInfo = getService(EMFModelInfo.class);
		
		List<EClass> personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		
		assertNotNull(personHierachy);
		assertEquals(0, personHierachy.size());
		
		TestPackageConfigurator testPackageConfigurator = new TestPackageConfigurator();
		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
		
		unregisterService(extendedPackageConfigurator);
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(1, personHierachy.size());

		registerServiceForCleanup( extendedPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
		
		unregisterService(testPackageConfigurator);
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(0, personHierachy.size());

		registerServiceForCleanup( testPackageConfigurator , new Hashtable<String, Object>(), EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName());
		
		personHierachy = emfModelInfo.getUpperTypeHierarchyForEClass(TestPackage.Literals.PERSON);
		assertNotNull(personHierachy);
		assertEquals(2, personHierachy.size());
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.util.test.common.test.AbstractOSGiTest#doBefore()
	 */
	@Override
	public void doBefore() {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.util.test.common.test.AbstractOSGiTest#doAfter()
	 */
	@Override
	public void doAfter() {
		// TODO Auto-generated method stub
		
	}
	
}
