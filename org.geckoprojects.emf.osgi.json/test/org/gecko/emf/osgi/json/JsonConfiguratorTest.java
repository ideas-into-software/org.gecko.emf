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
package org.gecko.emf.osgi.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gecko.emf.osgi.model.test.Contact;
import org.gecko.emf.osgi.model.test.ContactContextType;
import org.gecko.emf.osgi.model.test.ContactType;
import org.gecko.emf.osgi.model.test.GenderType;
import org.gecko.emf.osgi.model.test.Person;
import org.gecko.emf.osgi.model.test.TestFactory;
import org.gecko.emf.osgi.model.test.TestPackage;
import org.gecko.emf.osgi.model.test.util.TestResourceFactoryImpl;
import org.junit.Test;

public class JsonConfiguratorTest {

	@Test
	public void test() {
		ResourceSet resourceSet = createResourceSet();
		EMFJSONResourceFactoryConfigurator configurator  = new EMFJSONResourceFactoryConfigurator();
		configurator.configureResourceFactory(resourceSet.getResourceFactoryRegistry());
		
		Person p = createSamplePerson();
		Resource xmiResource = resourceSet.createResource(URI.createURI("person.test"));
		assertNotNull(xmiResource);
		xmiResource.getContents().add(p);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			xmiResource.save(baos, null);
		} catch (IOException e) {
			fail("Not expected save exception for XMI");
		}
		
		System.out.println(new String(baos.toByteArray()));
		
		Resource xmiLoadResource = resourceSet.createResource(URI.createURI("person_load.test"));
		assertNotNull(xmiLoadResource);
		assertNotEquals(xmiLoadResource, xmiResource);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			xmiLoadResource.load(bais, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected load exception Bson");
		}
		assertFalse(xmiLoadResource.getContents().isEmpty());
		
		Resource jsonResource = resourceSet.createResource(URI.createURI("/home/mark/person.json"));
		assertNotNull(jsonResource);
		baos = new ByteArrayOutputStream();
		jsonResource.getContents().add(EcoreUtil.copy(p));
		try {
			jsonResource.save(baos, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected save exception Bson");
		}
		
		Resource jsonLoadResource = resourceSet.createResource(URI.createURI("person_load.json"));
		assertNotNull(jsonLoadResource);
		assertNotEquals(jsonLoadResource, jsonResource);
		
		bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			jsonLoadResource.load(bais, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected load exception Bson");
		}
		assertFalse(jsonLoadResource.getContents().isEmpty());
		
		Person pLoaded = (Person) jsonLoadResource.getContents().get(0);
		
		assertEquals(p.getFirstName(), pLoaded.getFirstName());
		assertEquals(p.getLastName(), pLoaded.getLastName());
		assertEquals(p.getGender(), pLoaded.getGender());
		assertEquals(p.getContact().size(), pLoaded.getContact().size());
		Contact c = p.getContact().get(0);
		Contact cLoaded = pLoaded.getContact().get(0);
		assertEquals(c.getContext(), cLoaded.getContext());
		assertEquals(c.getType(), cLoaded.getType());
		assertEquals(c.getValue(), cLoaded.getValue());
		
	}
	
	/**
	 * 
	 */
	private Person createSamplePerson() {
		Person p = TestFactory.eINSTANCE.createPerson();
		p.setId("mh");
		p.setFirstName("Mark");
		p.setLastName("Hoffmann");
		p.setGender(GenderType.MALE);
		
		Contact email = TestFactory.eINSTANCE.createContact();
		email.setContext(ContactContextType.WORK);
		email.setType(ContactType.EMAIL);
		email.setValue("mh@mycomp.de");
		
		p.getContact().add(email);
		return p;
	}

	/**
	 * @return
	 */
	private ResourceSet createResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(TestPackage.eNS_URI, TestPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("test", new TestResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put(TestPackage.eCONTENT_TYPE, new TestResourceFactoryImpl());
		return resourceSet;
	}

}
