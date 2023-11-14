/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Mark Hoffmann
 * @since 06.11.2023
 */
public class SystemPropertyHelperTest {

	@Test
	public void testGetNamesValuesEmpty() {
		Map<Long, Set<String>> map = Collections.emptyMap();
		String[] result = ServicePropertiesHelper.getNamesArray(map);
		assertNotNull(result);
		assertEquals(0, result.length);
		
		map = new HashMap<>();
		map.put(Long.valueOf(0), null);
		result = ServicePropertiesHelper.getNamesArray(map);
		assertNotNull(result);
		assertEquals(0, result.length);
		
		map = new HashMap<>();
		Set<String> stringSet = new HashSet<String>();
		map.put(Long.valueOf(0), stringSet);
		result = ServicePropertiesHelper.getNamesArray(map);
		assertNotNull(result);
		assertEquals(0, result.length);
	}
	
	@Test
	public void testGetNamesValues() {
		Map<Long, Set<String>> map01 = new HashMap<>();
		Set<String> stringSet01 = new HashSet<String>();
		stringSet01.add("a");
		stringSet01.add("b");
		
		map01.put(Long.valueOf(0), stringSet01);
		String[] result = ServicePropertiesHelper.getNamesArray(map01);
		assertNotNull(result);
		assertEquals(2, result.length);
		assertTrue(Arrays.asList(result).contains("a"));
		assertTrue(Arrays.asList(result).contains("b"));
		
		Set<String> stringSet02 = new HashSet<String>();
		stringSet02.add("c");
		stringSet02.add("d");
		map01.put(Long.valueOf(0), stringSet02);
		
		result = ServicePropertiesHelper.getNamesArray(map01);
		assertNotNull(result);
		assertEquals(2, result.length);
		assertTrue(Arrays.asList(result).contains("c"));
		assertFalse(Arrays.asList(result).contains("a"));
		
		map01.put(Long.valueOf(1), stringSet01);
		result = ServicePropertiesHelper.getNamesArray(map01);
		assertNotNull(result);
		assertEquals(4, result.length);
		assertTrue(Arrays.asList(result).contains("c"));
		assertTrue(Arrays.asList(result).contains("a"));
		
		stringSet02.clear();
		stringSet02.add("a");
		stringSet02.add("c");
		
		result = ServicePropertiesHelper.getNamesArray(map01);
		assertNotNull(result);
		assertEquals(3, result.length);
		assertTrue(Arrays.asList(result).contains("c"));
		assertTrue(Arrays.asList(result).contains("a"));
		assertFalse(Arrays.asList(result).contains("d"));
		
	}
	
	@Test
	public void testGetStringPlus() {
		Map<String, Object> properties = new HashMap<>();
		String key = "Foo";
		Set<String> stringValues = ServicePropertiesHelper.getStringPlusValue(null, null);
		assertNull(stringValues);
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, null);
		assertNull(stringValues);
		stringValues = ServicePropertiesHelper.getStringPlusValue(null, key);
		assertNull(stringValues);
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertNull(stringValues);
		properties.put("Bar", "Test");
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertNull(stringValues);
		properties.put(key, Integer.valueOf(42));
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertEquals(1, stringValues.size());
		assertEquals("42", stringValues.iterator().next());
		properties.put(key,"fizz");
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertEquals(1, stringValues.size());
		assertEquals("fizz", stringValues.iterator().next());
		List<Object> l = new ArrayList<Object>();
		l.add("fizz");
		l.add(Integer.valueOf(42));
		properties.put(key, l);
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertEquals(2, stringValues.size());
		assertTrue(stringValues.contains("fizz"));
		assertTrue(stringValues.contains("42"));
		properties.put(key,new String[]{"fizz", "buzz"});
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertEquals(2, stringValues.size());
		assertTrue(stringValues.contains("fizz"));
		assertTrue(stringValues.contains("buzz"));
		properties.put(key,new Object[]{Integer.valueOf(42), "buzz"});
		stringValues = ServicePropertiesHelper.getStringPlusValue(properties, key);
		assertEquals(2, stringValues.size());
		assertTrue(stringValues.contains("42"));
		assertTrue(stringValues.contains("buzz"));
		
	}
	
}
