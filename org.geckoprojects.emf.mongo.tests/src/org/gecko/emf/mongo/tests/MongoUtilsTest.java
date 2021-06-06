/**
 * 
 */
package org.gecko.emf.mongo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.gecko.emf.mongo.MongoUtils;
import org.gecko.emf.osgi.model.test.TestPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mark
 *
 */
public class MongoUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.gecko.emf.mongo.MongoUtils#getEStructuralFeatureByName(org.eclipse.emf.ecore.EClass, java.lang.String)}.
	 */
	@Test
	public void testGetEStructuralFeatureByName() {
		EStructuralFeature feature = MongoUtils.getEStructuralFeatureByName(null, "value");
		assertNull(feature);
		feature = MongoUtils.getEStructuralFeatureByName(null, null);
		assertNull(feature);
		feature = MongoUtils.getEStructuralFeatureByName(TestPackage.Literals.PERSON, null);
		assertNull(feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(TestPackage.Literals.PERSON, "firstName");
		assertNotNull(feature);
		assertEquals(TestPackage.Literals.PERSON__FIRST_NAME, feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(TestPackage.Literals.TAG, "value");
		assertNotNull(feature);
		assertEquals(TestPackage.Literals.TAG__VALUE, feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(TestPackage.Literals.TAG, "description");
		assertNotNull(feature);
		assertEquals(TestPackage.Literals.TAG__DESCRIPTION, feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(TestPackage.Literals.TAG, "desc");
		assertNull(feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(TestPackage.Literals.TAG, "desc", true);
		assertNotNull(feature);
		assertEquals(TestPackage.Literals.TAG__DESCRIPTION, feature);
	}
	
	/**
	 * Test method for {@link org.gecko.emf.mongo.MongoUtils#getEStructuralFeatureByName(org.eclipse.emf.ecore.EClass, java.lang.String)}.
	 */
	@Test
	public void testGetNameByEStructuralFeature() {
		String name = MongoUtils.getNameByEStructuralFeature(null, false);
		assertNull(name);
		name = MongoUtils.getNameByEStructuralFeature(null, true);
		assertNull(name);
		
		name = MongoUtils.getNameByEStructuralFeature(TestPackage.Literals.PERSON__FIRST_NAME, false);
		assertNotNull(name);
		assertEquals("firstName", name);
		name = MongoUtils.getNameByEStructuralFeature(TestPackage.Literals.PERSON__FIRST_NAME, true);
		assertNotNull(name);
		assertEquals("firstName", name);
		
		name = MongoUtils.getNameByEStructuralFeature(TestPackage.Literals.TAG__DESCRIPTION, false);
		assertNotNull(name);
		assertEquals("description", name);
		name = MongoUtils.getNameByEStructuralFeature(TestPackage.Literals.TAG__DESCRIPTION, true);
		assertNotNull(name);
		assertEquals("desc", name);
	}

}
