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
package org.gecko.emf.osgi.bson.itest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
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
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class BsonConfiguratorTest {

	@Test
	public void testBson() {
		ResourceSet resourceSet = createResourceSet();
		EMFBsonResourceFactoryConfigurator configurator  = new EMFBsonResourceFactoryConfigurator();
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
		
		Resource bsonResource = resourceSet.createResource(URI.createURI("person.bson"));
		assertNotNull(bsonResource);
		bsonResource.getContents().add(EcoreUtil.copy(p));
		baos = new ByteArrayOutputStream();
		try {
			bsonResource.save(baos, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected save exception Bson " + e.getMessage());
		}
		
		Resource bsonLoadResource = resourceSet.createResource(URI.createURI("person_load.bson"));
		assertNotNull(bsonLoadResource);
		assertNotEquals(bsonLoadResource, bsonResource);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			bsonLoadResource.load(bais, null);
		} catch (IOException e) {
			fail("Not expected load exception Bson");
			e.printStackTrace();
		}
		assertFalse(bsonLoadResource.getContents().isEmpty());
		
		Person pLoaded = (Person) bsonLoadResource.getContents().get(0);
		
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
	
	public static ObjectMapper createBsonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper(new BsonFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        return mapper;
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
		resourceSet.getPackageRegistry().put(TestPackage.eNS_URI, TestPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("test", new TestResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put(TestPackage.eCONTENT_TYPE, new TestResourceFactoryImpl());
		return resourceSet;
	}

}
