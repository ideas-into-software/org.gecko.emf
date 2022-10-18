/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.extender.itest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

/**
 * 
 * @author mark
 * @since 14.10.2022
 */
@ExtendWith(ServiceExtension.class)
@ExtendWith(BundleContextExtension.class)
public class EMFModelExtenderRestartTest {
	
	/** EXTENDER_TEST_MODEL_BSN */
	private static final String EXTENDER_TEST_MODEL_BSN = "org.gecko.emf.osgi.example.model.extender";
	private BundleContext ctx;

	@BeforeEach
	private void before(@InjectBundleContext BundleContext ctx) {
		this.ctx = ctx;
	}
	
	@AfterEach
	private void after() {
		for (Bundle b : ctx.getBundles()) {
			if (EXTENDER_TEST_MODEL_BSN.equals(b.getSymbolicName())) {
				if (b.getState() != Bundle.ACTIVE) {
					try {
						b.start();
					} catch (BundleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000l);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Test
	public void stopBundleTest(@InjectService(filter = "(emf.model.name=manual)") ServiceAware<ResourceSet> rsAware) {
		ResourceSet rs = rsAware.getService();
		assertNotNull(rs);
		EFactory eFactory = rs.getPackageRegistry().getEFactory("http://gecko.org/example/model/manual/1.0");
		assertNotNull(eFactory);
		EPackage ePackage = eFactory.getEPackage();
		assertNotNull(ePackage);
		// Foo class exists
		EClass foo = (EClass) ePackage.getEClassifier("Foo");
		assertNotNull(foo);
		EClass bar = (EClass) ePackage.getEClassifier("Bar");
		assertNull(bar);
		
		Collection<ServiceReference<EPackageConfigurator>> configurators = Collections.emptyList();
		try {
			configurators = ctx.getServiceReferences(EPackageConfigurator.class, "(emf.model.name=manual)");
		} catch (InvalidSyntaxException e1) {
			fail("Invalid filter");
		}
		assertFalse(configurators.isEmpty());
		assertEquals(1, configurators.size());
		ServiceReference<EPackageConfigurator> manualConfigurator = configurators.iterator().next();
		Bundle origin = manualConfigurator.getBundle();
		assertNotNull(origin);
		assertEquals(EXTENDER_TEST_MODEL_BSN, origin.getSymbolicName());
		try {
			origin.stop();
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(rsAware.isEmpty());
	}
	
	@Test
	public void restartBundleTest(@InjectService(filter = "(emf.model.name=manual)") ServiceAware<ResourceSet> rsAware) {
		ResourceSet rs = rsAware.getService();
		assertNotNull(rs);
		EFactory eFactory = rs.getPackageRegistry().getEFactory("http://gecko.org/example/model/manual/1.0");
		assertNotNull(eFactory);
		EPackage ePackage = eFactory.getEPackage();
		assertNotNull(ePackage);
		// Foo class exists
		EClass foo = (EClass) ePackage.getEClassifier("Foo");
		assertNotNull(foo);
		EClass bar = (EClass) ePackage.getEClassifier("Bar");
		assertNull(bar);
		
		Collection<ServiceReference<EPackageConfigurator>> configurators = Collections.emptyList();
		try {
			configurators = ctx.getServiceReferences(EPackageConfigurator.class, "(emf.model.name=manual)");
		} catch (InvalidSyntaxException e1) {
			fail("Invalid filter");
		}
		assertEquals(1, configurators.size());
		ServiceReference<EPackageConfigurator> manualConfigurator = configurators.iterator().next();
		Bundle origin = manualConfigurator.getBundle();
		assertNotNull(origin);
		assertEquals(EXTENDER_TEST_MODEL_BSN, origin.getSymbolicName());
		try {
			origin.stop();
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(200l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(rsAware.isEmpty());
		try {
			origin.start();
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(200l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(rsAware.isEmpty());
		try {
			configurators = ctx.getServiceReferences(EPackageConfigurator.class, "(emf.model.name=manual)");
		} catch (InvalidSyntaxException e1) {
			fail("Invalid filter");
		}
		assertEquals(1, configurators.size());
		manualConfigurator = configurators.iterator().next();
		origin = manualConfigurator.getBundle();
		assertNotNull(origin);
		assertEquals(EXTENDER_TEST_MODEL_BSN, origin.getSymbolicName());
	}
	
}
