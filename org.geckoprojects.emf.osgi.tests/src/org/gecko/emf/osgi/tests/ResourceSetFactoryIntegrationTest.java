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
package org.gecko.emf.osgi.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.core.tests.AbstractOSGiTest;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.FrameworkUtil;

/**
 * Integration test for the {@link ResourceSetFactory}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceSetFactoryIntegrationTest extends AbstractOSGiTest {

	/**
	 * Creates a new instance.
	 */
	public ResourceSetFactoryIntegrationTest() {
		super(FrameworkUtil.getBundle(ResourceSetFactoryIntegrationTest.class).getBundleContext());
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doBefore()
	 */
	@Override
	public void doBefore() {
		// TODO Auto-generated method stub
		
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doAfter()
	 */
	@Override
	public void doAfter() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Tests, if the service was set up correctly
	 */
	@Test
	public void testResourceSetFactoryExists() {
		ResourceSetFactory factory = createStaticTrackedChecker(ResourceSetFactory.class).trackedServiceNotNull().getTrackedService();
		assertNotNull(factory);
		ResourceSet rs1 = factory.createResourceSet();
		assertNotNull(rs1);
		ResourceSet rs2 = factory.createResourceSet();
		assertNotNull(rs2);
		assertNotEquals(rs1,  rs2);
	}
	
}