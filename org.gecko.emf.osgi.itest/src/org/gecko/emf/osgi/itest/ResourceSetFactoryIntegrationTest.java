/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.itest;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.gecko.emf.osgi.example.model.basic.BasicPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.ServiceReference;
import org.osgi.service.condition.Condition;
import org.osgi.test.assertj.dictionary.DictionaryAssert;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * Integration test for the {@link ResourceSetFactory}
 * 
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class ResourceSetFactoryIntegrationTest {

	/**
	 * Tests, if the service was set up correctly
	 */
	@Test
	public void testResourceSetFactoryExists(@InjectService ResourceSetFactory factory) {
		Assertions.assertNotNull(factory);
		ResourceSet rs1 = factory.createResourceSet();
		Assertions.assertNotNull(rs1);
		ResourceSet rs2 = factory.createResourceSet();
		Assertions.assertNotNull(rs2);
		Assertions.assertNotEquals(rs1, rs2);
	}

	/**
	 * Tests, if the service was set up correctly
	 */
	@Test
	public void testResourceSetFactoryExists(
			@InjectService(filter = "(" + Condition.CONDITION_ID + "=" + ResourceSetFactory.CONDITION_ID + ")") ServiceAware<Condition> condition,
			@InjectService BasicPackage basicPackage
			) {
		
		
		ServiceReference<Condition> reference = condition.getServiceReference();
		assertThat(reference).isNotNull();

		DictionaryAssert.assertThat(reference.getProperties()).containsKey(EMFNamespaces.EMF_MODEL_NAME)
				.extractingByKey(EMFNamespaces.EMF_MODEL_NAME).isNotNull()
				.isInstanceOfSatisfying(String[].class, arr -> {
					assertThat(arr).contains(BasicPackage.eNAME);
				});

	}

}