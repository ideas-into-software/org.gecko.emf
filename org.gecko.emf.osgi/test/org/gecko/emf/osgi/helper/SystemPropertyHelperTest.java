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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.osgi.framework.Constants;

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
	
	@Test
	public void testGetServiceId() {
		Optional<Long> serviceId = ServicePropertiesHelper.getServiceId(null);
		assertFalse(serviceId.isPresent());
		
		serviceId = ServicePropertiesHelper.getServiceId(Collections.emptyMap());
		assertFalse(serviceId.isPresent());
		
		Map<String, Object> properties = new HashMap<>();
		properties.put(Constants.SERVICE_ID, "test");
		assertThrows(ClassCastException.class, ()->ServicePropertiesHelper.getServiceId(properties));
		
		properties.clear();
		properties.put(Constants.SERVICE_ID, 3);
		assertThrows(ClassCastException.class, ()->ServicePropertiesHelper.getServiceId(properties));

		properties.clear();
		properties.put(Constants.SERVICE_ID, 3l);
		serviceId = ServicePropertiesHelper.getServiceId(properties);
		assertTrue(serviceId.isPresent());
		assertEquals(3, serviceId.get());
	}
	
	@Test
	public void testUpdateNameMap() {
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.updateNameMap(null, null, null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.updateNameMap(Collections.emptyMap(), null, null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.updateNameMap(null, Collections.emptySet(), null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.updateNameMap(null, null, 42l));
		
		Map<Long, Set<String>> nameMap = new HashMap<>();
		Set<String> names = Collections.emptySet();
		ServicePropertiesHelper.updateNameMap(nameMap, names, 42l);
		assertEquals(1, nameMap.size());
		assertTrue(nameMap.get(42l).isEmpty());
		
		names = Collections.singleton("test");
		ServicePropertiesHelper.updateNameMap(nameMap, names, 42l);
		assertEquals(1, nameMap.size());
		assertEquals(1, nameMap.get(42l).size());
		assertTrue(nameMap.get(42l).contains("test"));
		
		names = Collections.singleton("toast");
		ServicePropertiesHelper.updateNameMap(nameMap, names, 42l);
		assertEquals(1, nameMap.size());
		assertEquals(1, nameMap.get(42l).size());
		assertTrue(nameMap.get(42l).contains("toast"));
		
	}
	
	@Test
	public void testAppendNameMap() {
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendNameMap(null, null, null, true));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendNameMap(Collections.emptyMap(), null, null, true));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendNameMap(null, Collections.emptySet(), null, true));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendNameMap(null, null, 42l, true));
		
		Map<Long, Set<String>> nameMap = new HashMap<>();
		Set<String> names = Collections.emptySet();
		ServicePropertiesHelper.appendNameMap(nameMap, names, 42l, false);
		assertEquals(1, nameMap.size());
		assertTrue(nameMap.get(42l).isEmpty());
		
		names = Collections.singleton("test");
		ServicePropertiesHelper.appendNameMap(nameMap, names, 42l, false);
		assertEquals(1, nameMap.size());
		assertEquals(1, nameMap.get(42l).size());
		assertTrue(nameMap.get(42l).contains("test"));
		
		names = Collections.singleton("toast");
		ServicePropertiesHelper.appendNameMap(nameMap, names, 42l, false);
		assertEquals(1, nameMap.size());
		assertEquals(1, nameMap.get(42l).size());
		assertTrue(nameMap.get(42l).contains("toast"));
		
		names = Collections.singleton("test");
		ServicePropertiesHelper.appendNameMap(nameMap, names, 42l, true);
		assertEquals(1, nameMap.size());
		assertEquals(2, nameMap.get(42l).size());
		assertTrue(nameMap.get(42l).contains("test"));
		assertTrue(nameMap.get(42l).contains("toast"));
		
	}
	
	@Test
	public void testAppendToDictionary() {
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendToDictionary(null, null, null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendToDictionary("test", null, null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendToDictionary(null, Collections.emptyMap(), null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.appendToDictionary(null, null, new Hashtable<>()));
	
		Map<Long, Set<String>> source = new HashMap<>();
		String key = "test";
		Dictionary<String, Object> dictionary = new Hashtable<>();
		
		ServicePropertiesHelper.appendToDictionary(key, source, dictionary);
		assertTrue(dictionary.isEmpty());
		
		source.put(1l, Collections.singleton("one"));
		Set<String> strings = new HashSet<>();
		strings.add("two");
		strings.add("three");
		source.put(2l, strings);

		ServicePropertiesHelper.appendToDictionary(key, source, dictionary);
		assertFalse(dictionary.isEmpty());
		
		Object result = dictionary.get(key);
		assertInstanceOf(String[].class, result);
		String[] stringArray = (String[]) result;
		assertEquals(3, stringArray.length);
		
		List<String> stringList = Arrays.asList(stringArray);
		assertTrue(stringList.contains("one"));
		assertTrue(stringList.contains("two"));
		assertTrue(stringList.contains("three"));
		
	}
	
	@Test
	public void testMergeNameMap() {
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.mergeNameMap(null, null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.mergeNameMap(Collections.emptyMap(), null));
		assertThrows(NullPointerException.class, ()->ServicePropertiesHelper.mergeNameMap(null, Collections.emptyMap()));
		
		Map<Long, Set<String>> sourceMap = new HashMap<>();
		Map<Long, Set<String>> targetMap = new HashMap<>();
		
		assertTrue(targetMap.isEmpty());
		ServicePropertiesHelper.mergeNameMap(sourceMap, targetMap);
		assertTrue(targetMap.isEmpty());
		
		sourceMap.put(Long.valueOf(12), Collections.singleton("test"));
		ServicePropertiesHelper.mergeNameMap(sourceMap, targetMap);
		assertFalse(targetMap.isEmpty());
		assertEquals(1, targetMap.size());
		assertEquals("test", targetMap.get(Long.valueOf(12)).iterator().next());
		
		targetMap.clear();
		targetMap.put(Long.valueOf(42), Collections.singleton("foo"));
		ServicePropertiesHelper.mergeNameMap(sourceMap, targetMap);
		assertFalse(targetMap.isEmpty());
		assertEquals(2, targetMap.size());
		assertEquals("test", targetMap.get(Long.valueOf(12)).iterator().next());
		assertEquals("foo", targetMap.get(Long.valueOf(42)).iterator().next());
		
		targetMap.clear();
		Set<String> testSet = new HashSet<>();
		testSet.add("foo");
		targetMap.put(Long.valueOf(42), testSet);
		sourceMap.clear();
		testSet = new HashSet<>();
		testSet.add("test");
		sourceMap.put(Long.valueOf(12), testSet);
		testSet = new HashSet<>();
		testSet.add("bar");
		sourceMap.put(Long.valueOf(42), testSet);
		testSet = new HashSet<>();
		testSet.add("two");
		testSet.add("three");
		sourceMap.put(Long.valueOf(13), testSet);
		
		ServicePropertiesHelper.mergeNameMap(sourceMap, targetMap);
		assertFalse(targetMap.isEmpty());
		assertEquals(3, targetMap.size());
		assertTrue(targetMap.get(Long.valueOf(12)).contains("test"));
		assertEquals(2, targetMap.get(Long.valueOf(42)).size());
		assertTrue(targetMap.get(Long.valueOf(42)).contains("bar"));
		assertTrue(targetMap.get(Long.valueOf(42)).contains("foo"));
		assertEquals(2, targetMap.get(Long.valueOf(13)).size());
		assertTrue(targetMap.get(Long.valueOf(13)).contains("two"));
		assertTrue(targetMap.get(Long.valueOf(13)).contains("three"));
	}
	
}
