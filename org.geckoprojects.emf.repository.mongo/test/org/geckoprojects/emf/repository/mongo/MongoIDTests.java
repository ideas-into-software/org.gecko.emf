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
package org.geckoprojects.emf.repository.mongo;

import org.bson.types.ObjectId;
import org.eclipse.emf.ecore.EObject;
import org.geckoprojects.emf.example.model.basic.BasicFactory;
import org.geckoprojects.emf.example.model.basic.Person;
import org.geckoprojects.emf.repository.helper.RepositoryHelper;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author mark
 * @since 16.05.2018
 */
public class MongoIDTests {

	@Test
	public void testObjectIds() {
		Person p = BasicFactory.eINSTANCE.createPerson();
		p.setFirstName("Test");
		
		p.getContact().add(BasicFactory.eINSTANCE.createContact());
		p.getContact().add(BasicFactory.eINSTANCE.createContact());
		
		setIDs(p);
		org.assertj.core.api.Assertions.assertThat(p.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(ObjectId.isValid(p.getId())).isTrue();
		
	}
	
	protected void setIDs(EObject rootObject) {
		RepositoryHelper.setIds(rootObject, () -> new ObjectId().toString(), () -> new ObjectId().toString());	
	}
	
	
}
