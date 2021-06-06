/**
 * Copyright (c) 2012 - 2019 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.mongo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.gecko.emf.mongo.Options;
import org.gecko.emf.osgi.model.test.TestPackage;
import org.junit.Test;

/**
 * 
 * @author mark
 * @since 28.09.2019
 */
public class TestMongoOptions {

	@Test
	public void testOptionCollectionName() {
		assertNull(Options.getCollectionName(null));
		
		Map<String, Object> properties = new HashMap<String, Object>();
		assertNull(Options.getCollectionName(properties));
		
		properties.put("test", "me");
		assertNull(Options.getCollectionName(properties));
		
		properties.put(Options.OPTION_COLLECTION_NAME, TestPackage.Literals.ADDRESS);
		assertEquals(TestPackage.Literals.ADDRESS.getName(), Options.getCollectionName(properties));
		
		properties.put(Options.OPTION_COLLECTION_NAME, "uhu");
		assertEquals("uhu", Options.getCollectionName(properties));
		
		properties.put(Options.OPTION_COLLECTION_NAME, Integer.valueOf(123));
		assertEquals("123", Options.getCollectionName(properties));
		
	}

}
