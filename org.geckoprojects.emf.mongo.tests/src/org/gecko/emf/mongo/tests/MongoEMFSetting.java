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

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.gecko.core.tests.AbstractOSGiTest;
import org.gecko.emf.mongo.ConverterService;
import org.gecko.emf.mongo.InputStreamFactory;
import org.gecko.emf.mongo.OutputStreamFactory;
import org.gecko.emf.mongo.QueryEngine;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf..configurator.TestPackageConfigurator;
import org.gecko.mongo.osgi.MongoClientProvider;
import org.gecko.mongo.osgi.MongoDatabaseProvider;
import org.gecko.mongo.osgi.MongoIdFactory;
import org.gecko.mongo.osgi.configuration.ConfigurationProperties;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;

/**
 * 
 * @author mark
 * @since 21.11.2019
 */
public abstract class MongoEMFSetting extends AbstractOSGiTest {

	protected MongoClient client;
	protected MongoCollection<?> collection;
	protected String mongoHost = System.getProperty("mongo.host", "localhost");
	private ServiceRegistration<?> testPackageRegistration = null;
	
	/**
	 * Creates a new instance.
	 */
	public MongoEMFSetting() {
		super(FrameworkUtil.getBundle(MongoEMFSetting.class).getBundleContext());
	}
	
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
	
	protected void defaultSetup() throws IOException{//, InvalidSyntaxException {
//		// has to be a new configuration
//		Dictionary<String, Object> props = new Hashtable<String, Object>();
//		// add service properties
//		String clientId = "testClient";
//		String clientUri = "mongodb://" + mongoHost + ":27017";
//		props = new Hashtable<String, Object>();
//		props.put(MongoClientProvider.PROP_CLIENT_ID, clientId);
//		props.put(MongoClientProvider.PROP_URI, clientUri);
//		Configuration clientConfig = createConfigForCleanup(ConfigurationProperties.CLIENT_PID, "?", props);
//		assertNotNull(clientConfig);
//		
//		getServiceCheckerForConfiguration(clientConfig).assertCreations(1, true).trackedServiceNotNull();
//		
//		// add service properties
//		String dbAlias = "testDB";
//		String db = "test";
//		Dictionary<String, Object> dbp = new Hashtable<String, Object>();
//		dbp.put(MongoDatabaseProvider.PROP_ALIAS, dbAlias);
//		dbp.put(MongoDatabaseProvider.PROP_DATABASE, db);
//		Configuration databaseConfig = createConfigForCleanup(ConfigurationProperties.DATABASE_PID, "?", dbp);
//		getServiceCheckerForConfiguration(databaseConfig).trackedServiceNotNull();
//		
//		createStaticTrackedChecker(MongoIdFactory.class).assertCreations(1, true).trackedServiceNotNull();
//		createStaticTrackedChecker(QueryEngine.class).assertCreations(1, true).trackedServiceNotNull();
//		createStaticTrackedChecker(ConverterService.class).assertCreations(1, true).trackedServiceNotNull();
//		createStaticTrackedChecker(InputStreamFactory.class).assertCreations(1, true).trackedServiceNotNull();
//		createStaticTrackedChecker(OutputStreamFactory.class).assertCreations(1, true).trackedServiceNotNull();
	}

}
