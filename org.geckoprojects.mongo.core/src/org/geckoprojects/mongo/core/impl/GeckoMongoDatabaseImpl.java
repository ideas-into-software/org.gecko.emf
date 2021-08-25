package org.geckoprojects.mongo.core.impl;

import java.util.Objects;

import org.geckoprojects.mongo.core.GeckoMongoClient;
import org.geckoprojects.mongo.core.GeckoMongoDatabase;
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
		GeckoMongoDatabase.class }, scope = ServiceScope.PROTOTYPE, configurationPid = MongoConstants.PID_MONGO_DATABASE)
public class GeckoMongoDatabaseImpl extends AbstractMongoDatabase implements MongoDatabase, GeckoMongoDatabase {

	private GeckoMongoClient client;

	private MongoDatabase delegate;

	private MongoDatabaseConfig config;

	@Reference(name = "mongoclient",target = "(&(must.not.bind.by.default=*)(!(must.not.bind.by.default=*)))")
	public void bindClient(GeckoMongoClient client) {
		this.client = client;
	}

	@Activate
	//no @Modified because an activated service would still exist if an exception happens in while modify
	public void activate(MongoDatabaseConfig config) {
		if (config.must_exist()) {
			boolean exist = false;
			for (String s : client.listDatabaseNames()) {

				if (Objects.equals(s, config.internal_name())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				throw new IllegalArgumentException(
						"No Database with the name '" + config.internal_name() + "' exist. Dataset must exist");
			}
		}
		this.config=config;
		delegate = client.getDatabase(config.internal_name());
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
	public String databaseAlias() {
		return config.databaseAlias();
	}
	

	@Override
	public  String internalName() {
		return config.internal_name();
	

	}

	@Override
	public String getURI() {
		return new StringBuilder("mongodb://").append(client.mongoHost()).append("/").append(internalName()).toString().replace(":27017/", "/");
	}

	@Override
	public String databaseIdent() {
		return config.database_ident()!=null?config.database_ident():client.clientIdent()+"."+internalName();
	}
}
