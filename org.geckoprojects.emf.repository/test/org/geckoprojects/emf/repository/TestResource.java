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
package org.geckoprojects.emf.repository;

import java.io.File;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.geckoprojects.emf.example.model.basic.model.Address;
import org.geckoprojects.emf.example.model.basic.model.BasicFactory;
import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.repository.helper.RepositoryHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests to reproduce the bug reported in #17
 * 
 * @author ilenia
 * @since Aug 9, 2019
 */
public class TestResource {

	private File repoTest;

	@BeforeEach
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
				BasicFactory.eINSTANCE.createAddress();
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
			 * org.geckoprojects.emf.repository.DefaultEMFRepository#setIDs(org.eclipse.emf.ecore.
			 * EObject)
			 */
			@Override
			protected void setIDs(EObject rootObject) {
				RepositoryHelper.setIds(rootObject);
			}
		}) {

			Address address = BasicFactory.eINSTANCE.createAddress();
			address.setId(UUID.randomUUID().toString());

			Address add = defaultEMFRepository.getEObject(BasicPackage.Literals.ADDRESS, address.getId());
			Assertions.assertThat(add).isNull();
			defaultEMFRepository.save(address);

			Address retrievedAdd = defaultEMFRepository.getEObject(BasicPackage.Literals.ADDRESS, address.getId());
			
			Assertions.assertThat(retrievedAdd).isNotNull();

		}
	}


}
