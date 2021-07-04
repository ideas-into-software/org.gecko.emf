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
package org.geckoprojects.emf.bson.itest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.geckoprojects.emf.bson.EMFBsonResourceFactoryConfigurator;
import org.geckoprojects.emf.example.model.basic.model.BasicFactory;
import org.geckoprojects.emf.example.model.basic.model.BasicPackage;
import org.geckoprojects.emf.example.model.basic.model.Contact;
import org.geckoprojects.emf.example.model.basic.model.ContactContextType;
import org.geckoprojects.emf.example.model.basic.model.ContactType;
import org.geckoprojects.emf.example.model.basic.model.GenderType;
import org.geckoprojects.emf.example.model.basic.model.Person;
import org.geckoprojects.emf.example.model.basic.model.util.BasicResourceFactoryImpl;
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
		Person p = BasicFactory.eINSTANCE.createPerson();
		p.setId("mh");
		p.setFirstName("Mark");
		p.setLastName("Hoffmann");
		p.setGender(GenderType.MALE);
		
		Contact email = BasicFactory.eINSTANCE.createContact();
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
		resourceSet.getPackageRegistry().put(BasicPackage.eNS_URI, BasicPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("test", new BasicResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put(BasicPackage.eCONTENT_TYPE, new BasicResourceFactoryImpl());
		return resourceSet;
	}

}
