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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.gecko.core.tests.AbstractOSGiTest;
import org.gecko.core.tests.ServiceChecker;
import org.gecko.emf.osgi.HughDataResourceSet;
import org.gecko.emf.osgi.ResourceSetFactory;
import org.gecko.emf.osgi.model.test.Person;
import org.gecko.emf.osgi.model.test.TestFactory;
import org.gecko.emf.osgi.model.test.TestPackage;
import org.gecko.emf.osgi.model.test.util.TestResourceFactoryImpl;
import org.gecko.emf.osgi.resourceset.HughDataResourceSetImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.promise.Promise;
import org.osgi.util.promise.PromiseFactory;
import org.osgi.util.promise.TimeoutException;

/**
 * Integration test for the {@link ResourceSetFactory}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class HughDataResourceSetIntegrationTest extends AbstractOSGiTest {

	/**
	 * Creates a new instance.
	 */
	public HughDataResourceSetIntegrationTest() {
		super(FrameworkUtil.getBundle(HughDataResourceSetIntegrationTest.class).getBundleContext());
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
	@
	Test
	public void testHughDataResourceSet() throws InterruptedException {
		HughDataResourceSet hdr = new HughDataResourceSetImpl();
		hdr.getPackageRegistry().put(TestPackage.eNS_URI, TestPackage.eINSTANCE);
		hdr.getResourceFactoryRegistry().getProtocolToFactoryMap().put("file", new TestResourceFactoryImpl());
		
		URI lastUri = null;
		URI firstUri = null;
		// create 
		System.out.println("Creating 500000 Persons");
		for(int i = 1; i <= 500000; i++) {
			lastUri = URI.createURI("file://test/" + i);
			Resource resource = hdr.createResource(lastUri);
			Person p = TestFactory.eINSTANCE.createPerson();
			p.setId(Integer.toString(i));
			resource.getContents().add(p);
			lastUri = lastUri.appendFragment(Integer.toString(i));
			if(firstUri == null) {
				firstUri = lastUri;
			}
		}
		
		long start = System.currentTimeMillis();
		Person result = (Person) hdr.getEObject(firstUri, false);
		assertNotNull(result);
		System.out.println("Checking 1 first uri took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++) {
			result = (Person) hdr.getEObject(lastUri, false);
			assertNotNull(result);
		}
		System.out.println("Checking 1 last uri 100 times took: " + (System.currentTimeMillis() - start));
		
		assertEquals(500000, hdr.getResources().size());
		
		start = System.currentTimeMillis();
		hdr.getResources().clear();
		long fastRun = System.currentTimeMillis() - start;
		
		result = (Person) hdr.getEObject(firstUri, false);
		assertNull(result);
		result = (Person) hdr.getEObject(lastUri, false);
		assertNull(result);
		
		System.out.println("Clearing resource set with 500.000 entries took " + (System.currentTimeMillis() - start));
		hdr.setUseResourceLocator(false);
		System.out.println("Creating 500000 Persons (second try use locator = false)");
		
		firstUri = null;
		for(int i = 1; i <= 500000; i++) {
			lastUri = URI.createURI("file://test/" + i);
			Resource resource = hdr.createResource(lastUri);
			Person p = TestFactory.eINSTANCE.createPerson();
			p.setId(Integer.toString(i));
			resource.getContents().add(p);
			lastUri = lastUri.appendFragment(Integer.toString(i));
			if(firstUri == null) {
				firstUri = lastUri;
			}
		}
		
		start = System.currentTimeMillis();
		result = (Person) hdr.getEObject(firstUri, false);
		assertNotNull(result);
		System.out.println("Checking first uri (second try) took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++) {
			result = (Person) hdr.getEObject(lastUri, false);
		}
		System.out.println("Checking last uri (second try) 100 times took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		PromiseFactory pf = new PromiseFactory(Executors.newCachedThreadPool());
		Promise<Void> promise = pf.submit(()->{ 
			hdr.getResources().clear();
			return null; });
		assertTrue(promise.timeout(fastRun * 10).getFailure() instanceof TimeoutException);
		
		System.out.println("Clearing resource set with 500.000 entries (second try) took " + (System.currentTimeMillis() - start));
	}
	@Test
	public void testWrappedHughDataResourceSet() throws InterruptedException {
		
		ServiceChecker<ResourceSetFactory> rsfSC = createStaticTrackedChecker(ResourceSetFactory.class).run();
		ServiceReference<ResourceSetFactory> reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSetFactory factory = rsfSC.getTrackedService();
		assertNotNull(factory);
		ResourceSet rs1 = factory.createResourceSet();
		assertNotNull(rs1);
		assertTrue(rs1 instanceof ResourceSetImpl);
		
		rs1.getPackageRegistry().put(TestPackage.eNS_URI, TestPackage.eINSTANCE);
		
		URI lastUri = null;
		URI firstUri = null;
		// create 
		System.out.println("Creating 500000 Persons");
		for(int i = 1; i <= 500000; i++) {
			lastUri = URI.createURI("file://test/" + i);
			Resource resource = rs1.createResource(lastUri);
			Person p = TestFactory.eINSTANCE.createPerson();
			p.setId(Integer.toString(i));
			resource.getContents().add(p);
			lastUri = lastUri.appendFragment(Integer.toString(i));
			if(firstUri == null) {
				firstUri = lastUri;
			}
		}
		
		long start = System.currentTimeMillis();
		Person result = (Person) rs1.getEObject(firstUri, false);
		assertNotNull(result);
		System.out.println("Checking first uri took: " + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++) {
			result = (Person) rs1.getEObject(lastUri, false);
			assertNotNull(result);
		}
		System.out.println("Checking last uri 100 times took: " + (System.currentTimeMillis() - start));
		
		assertEquals(500000, rs1.getResources().size());
		
		start = System.currentTimeMillis();
		rs1.getResources().clear();
		

		result = (Person) rs1.getEObject(firstUri, false);
		assertNull(result);
		result = (Person) rs1.getEObject(lastUri, false);
		assertNull(result);
		
		System.out.println("Clearing resource set with 500.000 entries took " + (System.currentTimeMillis() - start));
		
		System.out.println("Creating 500000 Persons (second try)");
		HughDataResourceSet hdr = new HughDataResourceSetImpl(rs1);
		firstUri = null;
		for(int i = 1; i <= 500000; i++) {
			lastUri = URI.createURI("file://test/" + i);
			Resource resource = hdr.createResource(lastUri);
			Person p = TestFactory.eINSTANCE.createPerson();
			p.setId(Integer.toString(i));
			resource.getContents().add(p);
			lastUri = lastUri.appendFragment(Integer.toString(i));
			if(firstUri == null) {
				firstUri = lastUri;
			}
		}
		
		start = System.currentTimeMillis();
		result = (Person) hdr.getEObject(firstUri, false);
		assertNotNull(result);
		System.out.println("Checking first uri (second try) took: " + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++) {
			result = (Person) hdr.getEObject(lastUri, false);
		}
		System.out.println("Checking last uri (second try) 100 times took: " + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		hdr.getResources().clear();

		result = (Person) hdr.getEObject(firstUri, false);
		assertNull(result);
		result = (Person) hdr.getEObject(lastUri, false);
		assertNull(result);
		
		System.out.println("Clearing resource set with 500.000 entries (second try) took " + (System.currentTimeMillis() - start));

	}
	
	@Test
	public void testDeactivateHughdataResourceSet() throws InterruptedException {
		
		ServiceChecker<ResourceSetFactory> rsfSC = createStaticTrackedChecker(ResourceSetFactory.class).run();
		ServiceReference<ResourceSetFactory> reference = rsfSC.getTrackedServiceReference();
		assertNotNull(reference);
		ResourceSetFactory factory = rsfSC.getTrackedService();
		assertNotNull(factory);
		ResourceSet rs1 = factory.createResourceSet();
		assertNotNull(rs1);
		assertTrue(rs1 instanceof ResourceSetImpl);
		
		rs1.getPackageRegistry().put(TestPackage.eNS_URI, TestPackage.eINSTANCE);
		
		System.out.println("Creating 500000 Persons");
		HughDataResourceSet hdr = new HughDataResourceSetImpl(rs1);
		URI lastUri = null;
		URI firstUri = null;
		for(int i = 1; i <= 500000; i++) {
			lastUri = URI.createURI("file://test/" + i);
			Resource resource = hdr.createResource(lastUri);
			Person p = TestFactory.eINSTANCE.createPerson();
			p.setId(Integer.toString(i));
			resource.getContents().add(p);
			lastUri = lastUri.appendFragment(Integer.toString(i));
			if(firstUri == null) {
				firstUri = lastUri;
			}
		}
		
		long start = System.currentTimeMillis();
		Person result = (Person) hdr.getEObject(firstUri, false);
		assertNotNull(result);
		System.out.println("Checking first uri took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++) {
			result = (Person) hdr.getEObject(lastUri, false);
		}
		System.out.println("Checking last uri 100 times took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		hdr.getResources().clear();
		long fastRun = System.currentTimeMillis() - start;
		
		result = (Person) hdr.getEObject(firstUri, false);
		assertNull(result);
		result = (Person) hdr.getEObject(lastUri, false);
		assertNull(result);
		
		System.out.println("Clearing resource set with 500.000 entries (second try) took " + (System.currentTimeMillis() - start));
		
		hdr.setUseResourceLocator(false);
		System.out.println("Creating 500000 Persons (second try use locator = false)");
		firstUri = null;
		for(int i = 1; i <= 500000; i++) {
			lastUri = URI.createURI("file://test/" + i);
			Resource resource = hdr.createResource(lastUri);
			Person p = TestFactory.eINSTANCE.createPerson();
			p.setId(Integer.toString(i));
			resource.getContents().add(p);
			lastUri = lastUri.appendFragment(Integer.toString(i));
			if(firstUri == null) {
				firstUri = lastUri;
			}
		}
		
		start = System.currentTimeMillis();
		result = (Person) hdr.getEObject(firstUri, false);
		assertNotNull(result);
		System.out.println("Checking first uri (second try) took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for(int i = 0 ; i < 100; i++) {
			result = (Person) hdr.getEObject(lastUri, false);
		}
		System.out.println("Checking last uri (second try) 100 times took: " + (System.currentTimeMillis() - start));
		
		PromiseFactory pf = new PromiseFactory(Executors.newCachedThreadPool());
		Promise<Void> promise = pf.submit(()->{ 
			hdr.getResources().clear();
			return null; });
		assertTrue(promise.timeout(fastRun * 10).getFailure() instanceof TimeoutException);
		
		System.out.println("Clearing resource set with 500.000 entries (second try) took " + (System.currentTimeMillis() - start));
	}
	
}