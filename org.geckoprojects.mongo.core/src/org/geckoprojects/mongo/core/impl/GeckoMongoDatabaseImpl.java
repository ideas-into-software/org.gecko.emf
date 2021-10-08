package org.geckoprojects.mongo.core.impl;

import java.util.Objects;

import org.geckoprojects.mongo.core.InfoMongoClient;
import org.geckoprojects.mongo.core.InfoMongoDatabase;
import org.geckoprojects.mongo.core.MongoConstants;
import org.geckoprojects.mongo.core.MongoDatabaseConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;

import com.mongodb.client.MongoDatabase;

@Designate(factory = true, ocd = MongoDatabaseConfig.class)
@Component(service = { MongoDatabase.class,
		InfoMongoDatabase.class }, scope = ServiceScope.PROTOTYPE, configurationPid = MongoConstants.PID_MONGO_DATABASE)
public class GeckoMongoDatabaseImpl extends AbstractMongoDatabase implements MongoDatabase, InfoMongoDatabase {

	private InfoMongoClient client;

	private MongoDatabase delegate;

	private MongoDatabaseConfig config;

	@Reference(name = "mongoclient", target = MongoConstants.TARGET_DO_NOT_RESOLVE)
	public void bindClient(InfoMongoClient client) {
		this.client = client;
	}

	@Activate
	// no @Modified because an activated service would still exist if an exception
	// happens in while modify
	public void activate(MongoDatabaseConfig config) {
		if (config.must_exist()) {
			boolean exist = false;
			for (String s : client.listDatabaseNames()) {

				if (Objects.equals(s, config.name())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				throw new IllegalArgumentException(
						"No Database with the name '" + config.name() + "' exist. Dataset must exist");
			}
		}
		this.config = config;
		delegate = client.getDatabase(config.name());
	}

	@Deactivate
	void deactivate() {
		delegate = null;
	}

	@Override
	protected MongoDatabase delegate() {

		return delegate;
	}

	@Override
	public String getAlias() {
		return config.alias() != null && config.alias().isBlank() ? config.alias() : config.name();
	}

	@Override
	public String getDatabaseUniqueIdentifyer() {
		return client.getClientUniqueIdentifyer() + "/" + getAlias();
	}

}
