package org.geckoprojects.emf.mongo.core.itest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bson.Document;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.geckoprojects.emf.core.api.EMFNamespaces;
import org.geckoprojects.emf.core.api.ResourceSetConfigurator;
import org.geckoprojects.emf.core.api.ResourceSetFactory;
import org.geckoprojects.emf.example.model.basic.BasicFactory;
import org.geckoprojects.emf.example.model.basic.Geometry;
import org.geckoprojects.emf.mongo.handlers.MongoResourceSetConfigurator;
import org.geckoprojects.mongo.core.MongoConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.test.assertj.NotPartOfPR.Conditions;
import org.osgi.test.assertj.NotPartOfPR.Predicates;
import org.osgi.test.assertj.monitoring.MonitoringAssertion;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Tests the correct implementation of the ArrayConverter and the changes made
 * to the DBObjectBuilder and EObjectBuilder to handle multi-dimensional arrays
 * 
 * @author ilenia
 * @since May 14, 2020
 */
@SuppressWarnings("restriction") // Fragment Host
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class CustomArrayDataTypeTest {

	static final String mongoHost = System.getProperty("mongo.host", "127.0.0.1");
	static final String clientUri = "mongodb://" + mongoHost + ":27017";
	static final String mongoIdent = "MyMongoIdent1";

	static final String dbAlias = "MyMongoDatabaseAlias";
	static final String dbInternal = "MyMongoDatabaseInternamName";

	static final String filterResourceSetConfigurator = "(&(" + EMFNamespaces.EMF_CONFIGURATOR_NAME + "=mongo)("
			+ MongoConstants.DB_PROP_DATABASE_ALIAS + "=" + dbAlias + "))";
	static final String filterResourceSetFactory = "(" + EMFNamespaces.EMF_CONFIGURATOR_NAME + "=mongo)";

	@Test
	public void testSimpleArray(@InjectService(cardinality = 1) ConfigurationAdmin cm,
			@InjectService(cardinality = 0) ServiceAware<MongoClient> saMongoClient,
			@InjectService(cardinality = 0) ServiceAware<MongoDatabase> saMongoDatabase,
			@InjectService(cardinality = 0, filter = filterResourceSetConfigurator) ServiceAware<ResourceSetConfigurator> saResourceSetConfigurator,
			@InjectService(cardinality = 0, filter = filterResourceSetFactory) ServiceAware<ResourceSetFactory> saResourceSetFactory)
			throws Exception {

		//

		MonitoringAssertion.executeAndObserve(() -> {
			// so something. could be Throwable

			Configuration confClient = cm.getFactoryConfiguration(MongoConstants.PID_MONGO_CLIENT, "test1", "?");
			confClient.update(new Hashtable<>(Map.of(MongoConstants.CLIENT_PROP_CLIENT_IDENT, mongoIdent,
					MongoConstants.CLIENT_PROP_CONNECTION_STRING, clientUri)));

		})
				// todo LDAP filter entsprechend frameworkutil
				// anytype also nzt filter oder nut map

				.untilServiceEvent(Predicates.ServiceEvents.isTypeRegistered(MongoClient.class))
				.assertWithTimeoutThat(1000).isNotTimedOut()
				.hasServiceEventsInOrder(
						List.of(Conditions.ServiceEventConditions.typeRegisteredAndObjectClass(MongoClient.class)))
				.hasServiceEventsThat()// ListAssert<ServiceEvents)
				.areAtMost(2, Conditions.ServiceEventConditions.serviceReferenceIsNotNull());

		MongoClient mongoClient = saMongoClient.waitForService(1000);

		assertThat(mongoClient).isNotNull();

		Configuration c = cm.getFactoryConfiguration(MongoConstants.PID_MONGO_DATABASE, "base", "?");
		MonitoringAssertion.executeAndObserve(() -> {
			try {

				c.update(Dictionaries.dictionaryOf(MongoConstants.DB_PROP_DATABASE_NAME, dbInternal,
						MongoConstants.DB_PROP_DATABASE_ALIAS, dbAlias, MongoConstants.DB_PROP_DATABASE_MUST_EXIST, false,
						MongoConstants.TARGET_MONGOCLIENT,String.format("(%s=%s)",MongoConstants.CLIENT_PROP_CLIENT_IDENT, mongoIdent)));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(3000000);
		//.hasExactlyNServiceEventRegisteredWith(1,				MongoDatabase.class);
		
		MongoDatabase mongoDatabase = saMongoDatabase.waitForService(3000000);

		assertThat(mongoDatabase).isNotNull();

		ResourceSetConfigurator rsc = saResourceSetConfigurator.waitForService(5000);
		assertThat(rsc).isNotNull().isInstanceOf(MongoResourceSetConfigurator.class);

		ResourceSetFactory rsf = saResourceSetFactory.getService();
		assertThat(rsf).isNotNull();
		ResourceSet resourceSet = rsf.createResourceSet();

		System.out.println("Dropping DB");
		MongoCollection<Document> geoCollection = mongoClient.getDatabase(dbInternal).getCollection("Geometry");
		if(geoCollection.countDocuments()>0) {
			geoCollection.drop();
		}
		
		assertThat(geoCollection.countDocuments()).isEqualTo(0);

		Resource resource = resourceSet
				.createResource(URI.createURI("mongodb://" + mongoHost+":27017/"+dbInternal+"/Geometry/"));
//		Create the Geometry object
		Geometry geometry = BasicFactory.eINSTANCE.createGeometry();
		Double[] coord1 = new Double[] { 11.23, 58.98 };
		Double[] coord2 = new Double[] { 11.45, 57.44 };

		geometry.getCoordinates().add(coord1);
		geometry.getCoordinates().add(coord2);
		String geoId = UUID.randomUUID().toString();
		geometry.setId(geoId);

//		save the Geometry object
		resource.getContents().add(geometry);
		resource.save(null);

		resource.getContents().clear();
		resource.unload();

		// load the Geometry object from the db

		URI uri = URI.createURI("mongodb://" +mongoHost+":27017/"+dbInternal+"/Geometry/" + geometry.getId());
		Resource findResource = resourceSet.createResource(uri);
		findResource.load(null);
		assertNotNull(findResource);
		assertFalse(findResource.getContents().isEmpty());
		assertEquals(1, findResource.getContents().size());

		assertTrue(findResource.getContents().get(0) instanceof Geometry);
		Geometry retrievedGeometry = (Geometry) findResource.getContents().get(0);
		assertEquals(geoId, retrievedGeometry.getId());
		assertNotNull(retrievedGeometry.getCoordinates());
		assertFalse(retrievedGeometry.getCoordinates().isEmpty());
		assertEquals(2, retrievedGeometry.getCoordinates().size());
		assertTrue(retrievedGeometry.getCoordinates().get(0) instanceof Double[]);
		Double[] retrievedCoord1 = retrievedGeometry.getCoordinates().get(0);
		assertEquals(2, retrievedCoord1.length);
		assertEquals(11.23, retrievedCoord1[0], 0.001);
		assertEquals(58.98, retrievedCoord1[1], 0.001);

		assertTrue(retrievedGeometry.getCoordinates().get(1) instanceof Double[]);
		Double[] retrievedCoord2 = retrievedGeometry.getCoordinates().get(1);
		assertEquals(2, retrievedCoord2.length);
		assertEquals(11.45, retrievedCoord2[0], 0.001);
		assertEquals(57.44, retrievedCoord2[1], 0.001);

		geoCollection.drop();
	}

//	@Test
//	public void testMultiDimensionalArray(@InjectService MongoClient mongoClient)
//			throws IOException, InvalidSyntaxException {
//
//		defaultSetup();
//
//		String dbAlias = "testDB";
//		String filter = "(&(" + EMFNamespaces.EMF_CONFIGURATOR_NAME
//				+ "=mongo)(objectClass=org.geckoprojects.emf.core.ResourceSetConfigurator)("
//				+ MongoResourceSetConfiguratorComponent.PROP_MONGO_ALIAS + "=" + dbAlias + "))";
//		ResourceSetConfigurator rsc = (ResourceSetConfigurator) createTrackedChecker(filter, true)
//				.trackedServiceNotNull().getTrackedService();
//		assertTrue(rsc instanceof MongoResourceSetConfigurator);
//
//		filter = "(&(" + EMFNamespaces.EMF_CONFIGURATOR_NAME
//				+ "=mongo)(objectClass=org.geckoprojects.emf.core.ResourceSetFactory))";
//		ResourceSetFactory rsf = (ResourceSetFactory) createTrackedChecker(filter, true).trackedServiceNotNull()
//				.getTrackedService();
//		ResourceSet resourceSet = rsf.createResourceSet();
//
//		System.out.println("Dropping DB");
//		MongoCollection<Document> geoCollection = mongoClient.getDatabase("test").getCollection("Geometry");
//		geoCollection.drop();
//
//		assertEquals(0, geoCollection.countDocuments());
//		Resource resource = resourceSet
//				.createResource(URI.createURI("mongodb://" + mongoHost + ":27017/test/Geometry/"));
//
////		Create the Geometry object
//		Geometry geometry = BasicFactory.eINSTANCE.createGeometry();
//		Double[][] multiDimensionalArray = new Double[2][];
//		Double[] coord1 = new Double[] { 11.23, 58.98 };
//		Double[] coord2 = new Double[] { 11.45, 57.44 };
//		multiDimensionalArray[0] = coord1;
//		multiDimensionalArray[1] = coord2;
//		geometry.getMultiDimensionalCoordinates().add(multiDimensionalArray);
//		String geoId = UUID.randomUUID().toString();
//		geometry.setId(geoId);
//
////		save the Geometry object
//		resource.getContents().add(geometry);
//		resource.save(null);
//
//		resource.getContents().clear();
//		resource.unload();
//
//		// load the Geometry object from the db
//		Resource findResource = resourceSet
//				.createResource(URI.createURI("mongodb://" + mongoHost + ":27017/test/Geometry/" + geometry.getId()));
//		findResource.load(null);
//		assertNotNull(findResource);
//		assertFalse(findResource.getContents().isEmpty());
//		assertEquals(1, findResource.getContents().size());
//
//		assertTrue(findResource.getContents().get(0) instanceof Geometry);
//		Geometry retrievedGeometry = (Geometry) findResource.getContents().get(0);
//		assertEquals(geoId, retrievedGeometry.getId());
//		assertNotNull(retrievedGeometry.getMultiDimensionalCoordinates());
//		assertFalse(retrievedGeometry.getMultiDimensionalCoordinates().isEmpty());
//		assertEquals(1, retrievedGeometry.getMultiDimensionalCoordinates().size());
//		assertTrue(retrievedGeometry.getMultiDimensionalCoordinates().get(0) instanceof Double[][]);
//		Double[][] retrievedMultiDCoord = retrievedGeometry.getMultiDimensionalCoordinates().get(0);
//		assertEquals(2, retrievedMultiDCoord.length);
//
//		assertTrue(retrievedMultiDCoord[0] instanceof Double[]);
//		Double[] retrievedCoord1 = retrievedMultiDCoord[0];
//		assertEquals(2, retrievedCoord1.length);
//		assertEquals(11.23, retrievedCoord1[0], 0.001);
//		assertEquals(58.98, retrievedCoord1[1], 0.001);
//
//		assertTrue(retrievedMultiDCoord[1] instanceof Double[]);
//		Double[] retrievedCoord2 = retrievedMultiDCoord[1];
//		assertEquals(2, retrievedCoord2.length);
//		assertEquals(11.45, retrievedCoord2[0], 0.001);
//		assertEquals(57.44, retrievedCoord2[1], 0.001);
//
//		geoCollection.drop();
//
//	}

}
