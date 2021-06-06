/**
 * Copyright (c) 2012 - 2017 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.core.tests.AbstractOSGiTest;
import org.gecko.core.tests.ServiceChecker;
import org.gecko.emf.osgi.ResourceSetCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * Integration test for the {@link ResourceSetCache}
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceSetCacheIntegrationTest extends AbstractOSGiTest {
	
	/**
	 * Creates a new instance.
	 */
	public ResourceSetCacheIntegrationTest() {
		super(FrameworkUtil.getBundle(ResourceSetFactoryIntegrationTest.class).getBundleContext());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doBefore()
	 */
	@Override
	public void doBefore() {
		// TODO Auto-generated method stub
		
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doAfter()
	 */
	@Override
	public void doAfter() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Tests, if the service was set up correctly
	 * @throws IOException 
	 * @throws InvalidSyntaxException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testResourceSetCacheExists() throws IOException, InvalidSyntaxException, InterruptedException {
		ServiceChecker<ResourceSetCache> rscSC = createTrackedChecker(ResourceSetCache.class, true);
		rscSC.assertCreations(0, false);
		
		Dictionary<String, Object> configProperties01 = new Hashtable<>();
		configProperties01.put(ResourceSetCache.RESOURCE_SET_CACHE_NAME, "test1");
		createConfigForCleanup("ResourceSetCache", "one", "?", configProperties01);
		rscSC.assertCreations(1, true);
		
		ServiceReference<ResourceSetCache> cacheRef = rscSC.getTrackedServiceReference();
		assertNotNull(cacheRef);
		
		assertEquals("test1", cacheRef.getProperty(ResourceSetCache.RESOURCE_SET_CACHE_NAME));
		ResourceSetCache cache1 = rscSC.getTrackedService();
		assertNotNull(cache1);
		ResourceSet rs11 = cache1.getResourceSet();
		assertNotNull(rs11);
		ResourceSet rs21 = cache1.getResourceSet();
		assertNotNull(rs21);
		assertEquals(rs11,  rs21);
		
		Dictionary<String, Object> configProperties02 = new Hashtable<>();
		configProperties02.put(ResourceSetCache.RESOURCE_SET_CACHE_NAME, "test2");
		createConfigForCleanup("ResourceSetCache", "two", "?", configProperties02);
		
		rscSC.assertCreations(2, true);
		
		ServiceChecker<Object> cacheRef2SC = createTrackedChecker("(" + ResourceSetCache.RESOURCE_SET_CACHE_NAME + "=test2)", false);
		cacheRef2SC.assertCreations(1, true);
		ServiceReference<?> cacheRef2 = cacheRef2SC.getTrackedServiceReference();
		Collection<ServiceReference<ResourceSetCache>> references = getBundleContext().getServiceReferences(ResourceSetCache.class, null);
		assertEquals(2, references.size());
		assertEquals("test2", cacheRef2.getProperty(ResourceSetCache.RESOURCE_SET_CACHE_NAME));
		ResourceSetCache cache2 = (ResourceSetCache) cacheRef2SC.getTrackedService();
		
		assertNotEquals(cache1, cache2);
		
		assertNotNull(cache2);
		ResourceSet rs12 = cache2.getResourceSet();
		assertNotNull(rs12);
		ResourceSet rs22 = cache2.getResourceSet();
		assertNotNull(rs22);
		assertEquals(rs12,  rs22);
		
		assertNotEquals(rs11, rs12);
	}
	
}
