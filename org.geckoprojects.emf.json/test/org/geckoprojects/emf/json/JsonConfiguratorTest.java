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
package org.geckoprojects.emf.json;



import static org.assertj.core.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.geckoprojects.emf.example.model.basic.BasicFactory;
import org.geckoprojects.emf.example.model.basic.BasicPackage;
import org.geckoprojects.emf.example.model.basic.Contact;
import org.geckoprojects.emf.example.model.basic.ContactContextType;
import org.geckoprojects.emf.example.model.basic.ContactType;
import org.geckoprojects.emf.example.model.basic.GenderType;
import org.geckoprojects.emf.example.model.basic.Person;
import org.geckoprojects.emf.example.model.basic.util.BasicResourceFactoryImpl;
import org.junit.jupiter.api.Test;

public class JsonConfiguratorTest {

	@Test
	public void test() {
		ResourceSet resourceSet = createResourceSet();
		EMFJSONResourceFactoryConfigurator configurator  = new EMFJSONResourceFactoryConfigurator();
		configurator.configureResourceFactory(resourceSet.getResourceFactoryRegistry());
		
		Person p = createSamplePerson();
		Resource xmiResource = resourceSet.createResource(URI.createURI("person.test"));
		Assertions.assertThat(xmiResource).isNotNull();
		xmiResource.getContents().add(p);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			xmiResource.save(baos, null);
		} catch (IOException e) {
			fail("Not expected save exception for XMI");
		}
		
		System.out.println(new String(baos.toByteArray()));
		
		Resource xmiLoadResource = resourceSet.createResource(URI.createURI("person_load.test"));
		Assertions.assertThat(xmiLoadResource).isNotNull().isEqualTo(xmiResource);
		

		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			xmiLoadResource.load(bais, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected load exception Bson");
		}
		Assertions.assertThat(xmiLoadResource.getContents().isEmpty()).isTrue();
		
		Resource jsonResource = resourceSet.createResource(URI.createURI("/home/mark/person.json"));
		Assertions.assertThat(jsonResource).isNotNull();
		baos = new ByteArrayOutputStream();
		jsonResource.getContents().add(EcoreUtil.copy(p));
		try {
			jsonResource.save(baos, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected save exception Bson");
		}
		
		Resource jsonLoadResource = resourceSet.createResource(URI.createURI("person_load.json"));
		Assertions.assertThat(jsonLoadResource).isNotNull().isNotEqualTo( jsonResource);
		
		bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			jsonLoadResource.load(bais, null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not expected load exception Bson");
		}
		Assertions.assertThat(jsonLoadResource.getContents().isEmpty()).isFalse();
		
		Person pLoaded = (Person) jsonLoadResource.getContents().get(0);
		
		Assertions.assertThat(p.getFirstName()).isEqualTo( pLoaded.getFirstName());
		Assertions.assertThat(p.getLastName()).isEqualTo( pLoaded.getLastName());
		Assertions.assertThat(p.getGender()).isEqualTo( pLoaded.getGender());
		Assertions.assertThat(p.getContact().size()).isEqualTo( pLoaded.getContact().size());
		Contact c = p.getContact().get(0);
		Contact cLoaded = pLoaded.getContact().get(0);
		Assertions.assertThat(c.getContext()).isEqualTo( cLoaded.getContext());
		Assertions.assertThat(c.getType()).isEqualTo( cLoaded.getType());
		Assertions.assertThat(c.getValue()).isEqualTo( cLoaded.getValue());
		
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
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(BasicPackage.eNS_URI, BasicPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("test", new BasicResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put(BasicPackage.eCONTENT_TYPE, new BasicResourceFactoryImpl());
		return resourceSet;
	}

}
