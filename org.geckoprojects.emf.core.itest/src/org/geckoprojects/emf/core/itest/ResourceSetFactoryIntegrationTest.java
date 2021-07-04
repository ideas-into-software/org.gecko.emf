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
package org.geckoprojects.emf.core.itest;


import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.ResourceSetFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import org.osgi.test.common.annotation.InjectService;

/**
 * Integration test for the {@link ResourceSetFactory}
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
		Assertions.assertNotEquals(rs1,  rs2);
	}
	
}