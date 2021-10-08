package org.geckoprojects.mongo.core.itest;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.assertj.core.api.Assertions;
import org.geckoprojects.mongo.core.MongoConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.test.assertj.monitoring.MonitoringAssertion;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.dictionary.Dictionaries;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class MongoClientProviderTest {

	private String mongoHost = System.getProperty("mongo.host", "127.0.0.1");
	private String connectionString = "mongodb://" + mongoHost;

	@InjectService(timeout = 1000)
	ConfigurationAdmin cm;

	@Test
	public void testCreateMongoClientProvider(
			@InjectService(cardinality = 0) ServiceAware<MongoClient> serviceAwareClient,
			@InjectService(cardinality = 0) ServiceAware<MongoDatabase> serviceAwareDB)

			throws Exception {

		Assertions.assertThat(serviceAwareClient.getServices()).hasSize(0);
		Assertions.assertThat(serviceAwareDB.getServices()).hasSize(0);
		// add service properties
		String clientId = "testClient";

		Dictionary<String, Object> p = new Hashtable<String, Object>();
		p.put(MongoConstants.CLIENT_PROP_CLIENT_IDENT, clientId);
//		p.put("credential.type", "SCRAM_SHA_1");

		p.put(MongoConstants.CLIENT_PROP_CONNECTION_STRING, connectionString);
		AtomicReference<Configuration> cref = new AtomicReference<>();
		MonitoringAssertion.executeAndObserve(() -> {

			try {
				Configuration c = cm.createFactoryConfiguration(MongoConstants.PID_MONGO_CLIENT, "?");

				c.update(p);
				cref.set(c);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			System.out.println("sss");
		}).untilServiceEventRegistered(MongoClient.class).assertWithTimeoutThat(1000).hasServiceEventsThat().hasSize(2);

		MongoClient mc = serviceAwareClient.waitForService(1000);

//		mc.getDatabase("foo").getCollection("test").count();
		System.out.println(mc);
		Assertions.assertThat(mc).isNotNull();
		Assertions.assertThat(serviceAwareClient.getServices()).hasSize(1);

		Assertions.assertThat(mc.getClusterDescription().getServerDescriptions()).hasSize(1);
//		Assertions.assertThat().isEqualTo("");

		// create db

		Assertions.assertThat(serviceAwareClient.getServices()).hasSize(1);
		Assertions.assertThat(serviceAwareDB.getServices()).hasSize(0);

		// remove

		MonitoringAssertion.executeAndObserve(() -> {

			try {
				cref.get().delete();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasServiceEventsThat().hasSize(1);

		Assertions.assertThat(serviceAwareClient.getServices()).hasSize(0);
		Assertions.assertThat(serviceAwareDB.getServices()).hasSize(0);

	}

	@Test
	void testDatabase(@InjectService(cardinality = 0) ServiceAware<MongoClient> serviceAwareClient,
			@InjectService(cardinality = 0) ServiceAware<MongoDatabase> serviceAwareDB) throws Exception {

		final String clientIdent = "aaa";
		String internalName = UUID.randomUUID().toString();
		MonitoringAssertion.executeAndObserve(() -> {

			try {
				Configuration c = cm.getFactoryConfiguration(MongoConstants.PID_MONGO_CLIENT, "base", "?");
				c.update(Dictionaries.dictionaryOf(MongoConstants.CLIENT_PROP_CLIENT_IDENT, clientIdent,
						MongoConstants.CLIENT_PROP_CONNECTION_STRING, connectionString));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasServiceEventsThat().hasSizeGreaterThan(0);

		MongoClient mc = serviceAwareClient.waitForService(100);
		Assertions.assertThat(mc).isNotNull();

		Configuration c = cm.getFactoryConfiguration(MongoConstants.PID_MONGO_DATABASE, "base", "?");
		MonitoringAssertion.executeAndObserve(() -> {
			try {

				c.update(Dictionaries.dictionaryOf(MongoConstants.DB_PROP_DATABASE_NAME, internalName,
						MongoConstants.DB_PROP_DATABASE_MUST_EXIST, false));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).untilNoMoreServiceEventWithin(1000).assertWithTimeoutThat(1000).hasExactlyNServiceEventRegisteredWith(0,
				MongoDatabase.class);

		MonitoringAssertion.executeAndObserve(() -> {
			try {

				c.update(Dictionaries.dictionaryOf(MongoConstants.DB_PROP_DATABASE_NAME, internalName,
						MongoConstants.DB_PROP_DATABASE_MUST_EXIST, false, MongoConstants.TARGET_MONGOCLIENT,String.format(
						MongoConstants.TARGET_FILTER_CLIENT_BY_IDENT,clientIdent)));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			System.out.println(1);
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasExactlyNServiceEventRegisteredWith(1,
				MongoDatabase.class);

		MongoDatabase db = serviceAwareDB.waitForService(100);

		Assertions.assertThat(db).isNotNull();

		MonitoringAssertion.executeAndObserve(() -> {
			try {

				c.update(Dictionaries.dictionaryOf(MongoConstants.DB_PROP_DATABASE_NAME,
						UUID.randomUUID().toString(), MongoConstants.DB_PROP_DATABASE_MUST_EXIST, true,
						MongoConstants.TARGET_MONGOCLIENT, String.format(MongoConstants.TARGET_FILTER_CLIENT_BY_IDENT,clientIdent)));
				System.out.println(111);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).untilNoMoreServiceEventWithin(100).assertWithTimeoutThat(1000).hasServiceEventsThat().hasSizeGreaterThan(0);
		db = serviceAwareDB.waitForService(100);
		Assertions.assertThat(db).isNull();

	}

}
