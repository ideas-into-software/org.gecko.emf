/**
 * Copyright (c) 2017 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.emf.osgi.model.test.Address;
import org.gecko.emf.osgi.model.test.TestFactory;
import org.gecko.emf.osgi.model.test.TestPackage;
import org.gecko.emf.repository.helper.RepositoryHelper;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests to reproduce the bug reported in #17
 * 
 * @author ilenia
 * @since Aug 9, 2019
 */
public class TestResource {

	private File repoTest;

	@Before
	public void before() {
		File base = new File(System.getProperty("java.io.tmpdir"));
		repoTest = new File(base, "repoTest");
		File addressTest = new File(base, "Address");
		addressTest.mkdirs();
	}

	public void after() {
		repoTest.delete();
	}

	@Test
	public void testReproduceBug17() throws Exception {
		try (DefaultEMFRepository defaultEMFRepository = new DefaultEMFRepository() {

			@Override
			public ResourceSet createResourceSet() {
				ResourceSet set = new ResourceSetImpl();
				TestFactory.eINSTANCE.createAddress();
				set.getResourceFactoryRegistry().getProtocolToFactoryMap().put("file", new XMIResourceFactoryImpl());
				return set;
			}

			@Override
			public String getBaseUri() {
				return "file:" + repoTest.getAbsolutePath();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.gecko.emf.repository.DefaultEMFRepository#setIDs(org.eclipse.emf.ecore.
			 * EObject)
			 */
			@Override
			protected void setIDs(EObject rootObject) {
				RepositoryHelper.setIds(rootObject);
			}
		}) {

			Address address = TestFactory.eINSTANCE.createAddress();
			address.setId(UUID.randomUUID().toString());

			Address add = defaultEMFRepository.getEObject(TestPackage.Literals.ADDRESS, address.getId());
			assertNull(add);
			defaultEMFRepository.save(address);

			Address retrievedAdd = defaultEMFRepository.getEObject(TestPackage.Literals.ADDRESS, address.getId());
			assertNotNull(retrievedAdd);
		}
	}


}
