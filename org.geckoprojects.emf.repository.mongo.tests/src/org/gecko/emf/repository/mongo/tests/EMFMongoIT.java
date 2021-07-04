/**
 * Copyright (c) 2012 - 2020 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.repository.mongo.tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.gecko.core.tests.AbstractOSGiTest;
import org.geckoprojects.emf.mongo.ConverterService;
import org.geckoprojects.emf.mongo.InputStreamFactory;
import org.geckoprojects.emf.mongo.OutputStreamFactory;
import org.geckoprojects.emf.mongo.QueryEngine;
import org.geckoprojects.emf.osgi.EPackageConfigurator;
import org.geckoprojects.emf.osgi.ResourceFactoryConfigurator;
import org.geckoprojects.emf.osgi.model.test.configurator.TestPackageConfigurator;
import org.gecko.mongo.osgi.MongoIdFactory;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;

/**
 * Abstract basic test for a default setting
 * @author mark
 * @since 07.03.2020
 */
public class EMFMongoIT  {
	
	protected MongoClient client;
	protected List<MongoCollection<?>> collections = new LinkedList<>();
	protected String mongoHost = System.getProperty("mongo.host", "localhost");
	private ServiceRegistration<?> testPackageRegistration = null;
	

	/* 
	 * (non-Javadoc)
	 * @see org.gecko.core.tests.AbstractOSGiTest#doBefore()
	 */
	@Befo
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
		collections.forEach(MongoCollection::drop);
		if (client != null) {
			client.close();
		}
		if (testPackageRegistration != null) {
			testPackageRegistration.unregister();
			testPackageRegistration = null;
		}
	}
	
	protected MongoCollection<?> getCollection(String database, String collection) {
		MongoDatabase db = client.getDatabase(database);
		assertNotNull(db);
		MongoCollection<?> c = db.getCollection(collection); 
		assertNotNull(c);
		collections.add(c);
		return c;
	}
	
	protected void defaultCheck() throws IOException, InvalidSyntaxException {
		createStaticTrackedChecker(MongoIdFactory.class).assertCreations(1, true).trackedServiceNotNull();
		createStaticTrackedChecker(QueryEngine.class).assertCreations(1, true).trackedServiceNotNull();
		createStaticTrackedChecker(ConverterService.class).assertCreations(1, true).trackedServiceNotNull();
		createStaticTrackedChecker(InputStreamFactory.class).assertCreations(1, true).trackedServiceNotNull();
		createStaticTrackedChecker(OutputStreamFactory.class).assertCreations(1, true).trackedServiceNotNull();
	}

}
