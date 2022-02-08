package org.geckoprojects.mongo.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bson.codecs.configuration.CodecRegistry;
import org.geckoprojects.mongo.core.InfoMongoClient;
import org.geckoprojects.mongo.core.MongoClientConfig;
import org.geckoprojects.mongo.core.MongoConstants;
import org.geckoprojects.mongo.core.impl.delegated.ClusterListenerDelegate;
import org.geckoprojects.mongo.core.impl.delegated.CommandListenerDelegate;
import org.geckoprojects.mongo.core.impl.delegated.ConnectionPoolListenerDelegate;
import org.osgi.annotation.bundle.Capability;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.converter.Converters;

import com.mongodb.AutoEncryptionSettings;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientSettings.Builder;
import com.mongodb.MongoCompressor;
import com.mongodb.MongoCredential;
import com.mongodb.MongoDriverInformation;
import com.mongodb.ReadConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.StreamFactoryFactory;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.CommandListener;
import com.mongodb.event.ConnectionPoolListener;
import com.mongodb.event.ServerHeartbeatFailedEvent;
import com.mongodb.event.ServerHeartbeatStartedEvent;
import com.mongodb.event.ServerHeartbeatSucceededEvent;
import com.mongodb.event.ServerListener;
import com.mongodb.event.ServerMonitorListener;
import com.mongodb.selector.ServerSelector;

@Designate(factory = true, ocd = MongoClientConfig.class)

//Just a component prevent from registering as a ServerMonitorListener
@Component(service = {}, scope = ServiceScope.SINGLETON, configurationPid = MongoConstants.PID_MONGO_CLIENT)
@Capability(namespace = "osgi.service", attribute = {
MongoClientController.OBJECT_CLASS_MONGO_CLIENT }, uses = MongoClient.class)
@Capability(namespace = "osgi.service", attribute = {
MongoClientController.OBJECT_CLASS_INFO_MONGO_CLIENT }, uses = MongoClient.class)
public class MongoClientController implements ServerMonitorListener {

	static final String OBJECT_CLASS_MONGO_CLIENT = "objectClass:List<String>=\'com.mongodb.client.MongoClient\'";

	static final String OBJECT_CLASS_INFO_MONGO_CLIENT = "objectClass:List<String>=\'org.geckoprojects.mongo.core.InfoMongoClient\'"
			;

	private ClusterListenerDelegate clusterListenerDelegate = new ClusterListenerDelegate();

	CommandListenerDelegate commandListenerDelegate = new CommandListenerDelegate();
	ConnectionPoolListenerDelegate connectionPoolListenerDelegate = new ConnectionPoolListenerDelegate();

	org.osgi.util.converter.Converter converter = Converters.standardConverter();
	
	@Reference(service = LoggerFactory.class, cardinality = ReferenceCardinality.OPTIONAL)
	void  bindLogservice(Logger logger){
		this.oLogger=Optional.ofNullable(logger);
	}
	private Optional<Logger> oLogger;

	private List<ServiceRegistration<MongoDatabase>> regDatabases = new ArrayList<>();
	private ServiceRegistration<?> regMongoClient = null;
	private MongoClient mongoClient = null;

	@Reference(name = "serverSelector", cardinality = ReferenceCardinality.OPTIONAL)
	ServerSelector serverSelector;

	@Reference(name = "connectionPoolListeners", cardinality = ReferenceCardinality.MULTIPLE)
	List<ConnectionPoolListener> connectionPoolListeners;
	@Reference(name = "serverListeners", cardinality = ReferenceCardinality.MULTIPLE)
	List<ServerListener> serverListeners;
	@Reference(name = "serverMonitorListeners", cardinality = ReferenceCardinality.MULTIPLE)
	List<ServerMonitorListener> serverMonitorListeners;
	@Reference(name = "codecRegistry", cardinality = ReferenceCardinality.OPTIONAL)
	CodecRegistry codecRegistry;
	@Reference(name = "compressorList", cardinality = ReferenceCardinality.MULTIPLE)
	List<MongoCompressor> compressorList;

	@Reference(name = "autoEncryptionSettings", cardinality = ReferenceCardinality.OPTIONAL)
	AutoEncryptionSettings autoEncryptionSettings;

	@Reference(name = "streamFactoryFactory", cardinality = ReferenceCardinality.OPTIONAL)
	StreamFactoryFactory streamFactoryFactory;
	private MongoClientConfig mongoConfig;
	private AtomicBoolean clientStatus = new AtomicBoolean(false);

	public MongoClientController() {
		
	}



	@Activate
	public void activate(BundleContext bundleContext,	Map<String,Object> clientProps) {

		MongoClientConfig mongoConfig = converter.convert(clientProps).to(MongoClientConfig.class);
		this.mongoConfig = mongoConfig;
		mongoClient = createMongo(mongoConfig);

		Map<String, Object> props = clientServiceProps();
		regMongoClient = bundleContext.registerService(
				new String[] { MongoClient.class.getName(), InfoMongoClient.class.getName() }, mongoClient,
				new Hashtable<>(props));
	}

	private Map<String, Object> clientServiceProps() {
		Map<String, Object> map = new HashMap<>();
		map.put(MongoConstants.CLIENT_PROP_CLIENT_IDENT, mongoConfig.ident());

		map.put(MongoConstants.CLIENT_PROP_CLIENT_STATUS, clientStatus.get());

		return map;
	}

	private MongoClient createMongo(MongoClientConfig mongoConfig) {
		Builder settingsBuilder = MongoClientSettings.builder();

		if (mongoConfig.applicationName() != null) {
			settingsBuilder.applicationName(mongoConfig.applicationName());
		}

		settingsBuilder.applyToClusterSettings(cs -> {

			ControllerUtils.applyClusterSettings(mongoConfig, List.of(clusterListenerDelegate), serverSelector, cs);

		});

		settingsBuilder.applyToConnectionPoolSettings(cps -> {
			ControllerUtils.applyConnectionPoolSettings(mongoConfig, List.of(connectionPoolListenerDelegate), cps);

		});

		settingsBuilder.applyToServerSettings(ss -> {
			List<ServerMonitorListener> monitors = new ArrayList<>();
			if (serverMonitorListeners != null) {
				serverMonitorListeners.addAll(serverMonitorListeners);
			}
			monitors.add(this);
			ControllerUtils.applyServerSettings(mongoConfig, serverListeners, monitors, ss);
		});

		settingsBuilder.applyToSocketSettings(ss -> {

			ControllerUtils.applySocketSettings(mongoConfig, ss);

		});

		settingsBuilder.applyToSslSettings(ssl -> {

			ControllerUtils.applySSL(mongoConfig, ssl);

		});

		if (autoEncryptionSettings != null) {
			settingsBuilder.autoEncryptionSettings(autoEncryptionSettings);
		}

		if (codecRegistry != null) {
			settingsBuilder.codecRegistry(codecRegistry);
		}

		settingsBuilder.addCommandListener(commandListenerDelegate);

		if (compressorList != null) {
			settingsBuilder.compressorList(compressorList);
		}

		MongoCredential credential = ControllerUtils.toCredential(mongoConfig);
		if (credential != null) {
			settingsBuilder.credential(credential);
		}

		if (mongoConfig.readConcern() != null) {
			settingsBuilder.readConcern(new ReadConcern(mongoConfig.readConcern()));

		}
//		settingsBuilder.readPreference(null);

		if (mongoConfig.retryWrites() != null) {

			settingsBuilder.retryWrites(mongoConfig.retryWrites());
		}

		if (streamFactoryFactory != null) {
			settingsBuilder.streamFactoryFactory(streamFactoryFactory);
		}

		if (mongoConfig.uuidRepresentation() != null) {
			settingsBuilder.uuidRepresentation(mongoConfig.uuidRepresentation());
		}

//		settingsBuilder.writeConcern(null);

		MongoClientSettings settings = settingsBuilder.build();

		MongoDriverInformation driverInfo = ControllerUtils.toDriverInformation(mongoConfig);
		MongoClient mongoClient = MongoClients.create(settings, driverInfo);

		GeckoMongoClientImpl geckoMongoClientImpl = new GeckoMongoClientImpl(mongoClient,mongoConfig);
		return geckoMongoClientImpl;
	}

	@Deactivate
	public void deactivate() {
		regDatabases.forEach(ServiceRegistration::unregister);

		regMongoClient.unregister();

		mongoClient.close();
		mongoClient = null;
	}

	@Override
	public void serverHearbeatStarted(ServerHeartbeatStartedEvent event) {
	}

	@Override
	public void serverHeartbeatSucceeded(ServerHeartbeatSucceededEvent event) {

		if (!clientStatus.compareAndSet(false,true)) {
	
			oLogger.ifPresent(l->l.warn("Mongo Client first time gets an Hartbeat from Server ", event));
			Map<String, Object> props = clientServiceProps();
			regMongoClient.setProperties(new Hashtable<>(props));
		}
	}

	@Override
	public void serverHeartbeatFailed(ServerHeartbeatFailedEvent event) {

		if (clientStatus.compareAndSet(true,false)) {
			oLogger.ifPresent(l->l.warn("Mongo Client does not get an Hartbeat from Server ", event));
			Map<String, Object> props = clientServiceProps();
			regMongoClient.setProperties(new Hashtable<>(props));
		}
	}

	@Reference(name = "clusterListeners", cardinality = ReferenceCardinality.MULTIPLE)
	public void bindClusterListener(ClusterListener clusterListeners) {
		clusterListenerDelegate.bindListener(clusterListeners);

	}

	public void unbindClusterListener(ClusterListener clusterListeners) {
		clusterListenerDelegate.unbindListener(clusterListeners);
	}

	@Reference(name = "commandListeners", cardinality = ReferenceCardinality.MULTIPLE)
	public void bindClusterListener(CommandListener commandListener) {
		commandListenerDelegate.bindListener(commandListener);

	}

	public void unbindClusterListener(CommandListener commandListener) {
		commandListenerDelegate.unbindListener(commandListener);
		
	};

}