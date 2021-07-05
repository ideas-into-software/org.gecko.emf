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
package org.geckoprojects.emf.mongo.tests;

import java.io.IOException;


import org.geckoprojects.emf.core.EPackageConfigurator;
import org.geckoprojects.emf.core.ResourceFactoryConfigurator;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

/**
 * 
 * @author mark
 * @since 21.11.2019
 */
public abstract class MongoEMFSetting {

	protected MongoClient client;
	protected MongoCollection<?> collection;
	protected String mongoHost = System.getProperty("mongo.host", "localhost");
	private ServiceRegistration<?> testPackageRegistration = null;
	

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doBefore()
	 */
	@Override
	public void doBefore() {
		MongoClientOptions options = MongoClientOptions.builder().build();
		client = new MongoClient(mongoHost, options);
		testPackageRegistration = getBundleContext().registerService(new String[] {EPackageConfigurator.class.getName(), ResourceFactoryConfigurator.class.getName()}, new TestPackageConfigurator(), null);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doAfter()
	 */
	@Override
	public void doAfter() {
		if (collection != null) {
			collection.drop();
		}
		if (client != null) {
			client.close();
		}
		if (testPackageRegistration != null) {
			testPackageRegistration.unregister();
			testPackageRegistration = null;
		}
	}
	
}
