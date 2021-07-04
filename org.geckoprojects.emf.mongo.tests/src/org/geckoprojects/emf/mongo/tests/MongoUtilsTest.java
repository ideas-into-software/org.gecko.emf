/**
 * 
 */
package org.geckoprojects.emf.mongo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.geckoprojects.emf.mongo.MongoUtils;
import org.geckoprojects.emf.osgi.model.test.TestPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

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
		feature = MongoUtils.getEStructuralFeatureByName(BasicPackage.Literals.PERSON, null);
		assertNull(feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(BasicPackage.Literals.PERSON, "firstName");
		assertNotNull(feature);
		assertEquals(BasicPackage.Literals.PERSON__FIRST_NAME, feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(BasicPackage.Literals.TAG, "value");
		assertNotNull(feature);
		assertEquals(BasicPackage.Literals.TAG__VALUE, feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(BasicPackage.Literals.TAG, "description");
		assertNotNull(feature);
		assertEquals(BasicPackage.Literals.TAG__DESCRIPTION, feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(BasicPackage.Literals.TAG, "desc");
		assertNull(feature);
		
		feature = MongoUtils.getEStructuralFeatureByName(BasicPackage.Literals.TAG, "desc", true);
		assertNotNull(feature);
		assertEquals(BasicPackage.Literals.TAG__DESCRIPTION, feature);
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
		
		name = MongoUtils.getNameByEStructuralFeature(BasicPackage.Literals.PERSON__FIRST_NAME, false);
		assertNotNull(name);
		assertEquals("firstName", name);
		name = MongoUtils.getNameByEStructuralFeature(BasicPackage.Literals.PERSON__FIRST_NAME, true);
		assertNotNull(name);
		assertEquals("firstName", name);
		
		name = MongoUtils.getNameByEStructuralFeature(BasicPackage.Literals.TAG__DESCRIPTION, false);
		assertNotNull(name);
		assertEquals("description", name);
		name = MongoUtils.getNameByEStructuralFeature(BasicPackage.Literals.TAG__DESCRIPTION, true);
		assertNotNull(name);
		assertEquals("desc", name);
	}

}
