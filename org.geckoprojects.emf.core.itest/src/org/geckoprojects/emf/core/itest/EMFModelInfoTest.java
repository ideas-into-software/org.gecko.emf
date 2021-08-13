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
package org.geckoprojects.emf.core.itest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.geckoprojects.emf.core.api.ResourceFactoryConfigurator;
import org.geckoprojects.emf.core.api.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.BasicPackage;
import org.geckoprojects.emf.example.model.extended.ExtendedPackage.Literals;
import org.geckoprojects.emf.example.model.extended.ExtendedPerson;
import org.geckoprojects.emf.example.model.manual.Foo;
import org.geckoprojects.emf.example.model.manual.ManualFactory;
import org.geckoprojects.emf.example.model.manual.model.configuration.ManualPackageConfigurator;
import org.geckoprojects.emf.model.info.EMFModelInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.assertj.bundle.BundleAssert;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * 
 * @author jalbert
 * @since 8 Nov 2018
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class EMFModelInfoTest {

	@InjectBundleContext
	BundleContext bc;

	/**
	 * Trying to load an instance with a registered {@link EPackage}, then check if
	 * the {@link EClass} can be identified. Than unload the EPackage and expect
	 * that no {@link EClass} can be found
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testFindEClassByClass(@InjectService(cardinality = 0) ServiceAware<ResourceSetFactory> saRF,
			@InjectService(cardinality = 0) ServiceAware<EMFModelInfo> saMI) throws IOException, InterruptedException {
		ManualPackageConfigurator configurator = new ManualPackageConfigurator();

		ServiceRegistration<?> reg = bc.registerService(
				new String[] { EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName() },
				configurator, new Hashtable<String, Object>());

		ResourceSetFactory factory = saRF.waitForService(100l);
		assertNotNull(factory);
		ResourceSet rs = factory.createResourceSet();
		assertNotNull(rs);
		URI uri = URI.createURI("person.test");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Resource testSaveResource = rs.createResource(uri);
		assertNotNull(testSaveResource);
		Foo foo = ManualFactory.eINSTANCE.createFoo();

		foo.setValue("Tester");
		testSaveResource.getContents().add(foo);
		testSaveResource.save(baos, null);

		EMFModelInfo emfModelInfo = saMI.waitForService(1000);

		Optional<EClassifier> eClassifierForClass = emfModelInfo.getEClassifierForClass(Foo.class);
		Optional<EClassifier> eClassifierForClassByName = emfModelInfo.getEClassifierForClass(Foo.class.getName());

		EClassifier eClassifierByClass = eClassifierForClass.orElse(null);
		EClassifier eClassifierByName = eClassifierForClassByName.orElse(null);
		assertNotNull(eClassifierByClass);
		assertNotNull(eClassifierByName);
		reg.unregister();

		eClassifierForClass = emfModelInfo.getEClassifierForClass(Foo.class);
		eClassifierForClassByName = emfModelInfo.getEClassifierForClass(Foo.class.getName());

		eClassifierByClass = eClassifierForClass.orElse(null);
		eClassifierByName = eClassifierForClass.orElse(null);

		assertNull(eClassifierByClass);
		assertNull(eClassifierByName);

	}

	/**
	 * Trying to load an instance with a registered {@link EPackage}, then check if
	 * the {@link EClass} can be identified. Than unload the EPackage and expect
	 * that no {@link EClass} can be found
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BundleException
	 */
	@Test
	public void testFindEClassByClassName(@InjectService(cardinality = 0) ServiceAware<EMFModelInfo> saMI)
			throws IOException, InterruptedException, BundleException {
		EClass mustExist = Literals.EXTENDED_PERSON;
		Assertions.assertThat(mustExist).isNotNull();
		Assertions.assertThat(ExtendedPerson.class).isNotNull();
		EMFModelInfo emfModelInfo = saMI.waitForService(1000);
		List<Bundle> bundles = Arrays.asList(bc.getBundles());

		Bundle basicBundle = bundles.stream()
				.filter(b -> "org.geckoprojects.emf.example.model.basic".equals(b.getSymbolicName())).findAny()
				.orElse(null);
		BundleAssert.assertThat(basicBundle).isNotNull();
		Bundle extBundle = bundles.stream()
				.filter(b -> "org.geckoprojects.emf.example.model.extended".equals(b.getSymbolicName())).findAny()
				.orElse(null);

		BundleAssert.assertThat(extBundle).isNotNull();

		List<EClass> personHirachy = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);

		assertNotNull(personHirachy);
		assertEquals(2, personHirachy.size());


//		

		extBundle.stop();
		Thread.sleep(1000);

		List<EClass> personHirachy2 = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		System.out.println("");
		
		System.out.println("______");
		System.out.println(personHirachy2);
		System.out.println("______");
		basicBundle.stop();
		Thread.sleep(1000);
		List<EClass> personHirachy3 = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		System.out.println("");
		System.out.println("______");
		System.out.println(personHirachy3);
		System.out.println("______");

		basicBundle.start();
		Thread.sleep(1000);
		List<EClass> personHirachy4 = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		System.out.println("");
		System.out.println("______");
		System.out.println(personHirachy4);
		System.out.println("______");
		
		
		extBundle.start();

		Thread.sleep(1000);
		List<EClass> personHirachy5 = emfModelInfo.getUpperTypeHierarchyForEClass(BasicPackage.Literals.PERSON);
		System.out.println("");
		System.out.println("______");
		System.out.println(personHirachy5);
		System.out.println("______");


		
		assertNotNull(personHirachy2);

		assertEquals(1, personHirachy2.size());

		assertNotNull(personHirachy3);
		assertEquals(0, personHirachy3.size());

		assertNotNull(personHirachy4);
		assertEquals(1, personHirachy4.size());

		assertNotNull(personHirachy5);
		assertEquals(2, personHirachy5.size());

	}

}
