package org.geckoprojects.mongo.core.impl;

import org.geckoprojects.mongo.core.GeckoMongoClient;
import org.geckoprojects.mongo.core.MongoClientConfig;

import com.mongodb.client.MongoClient;

public class GeckoMongoClientImpl extends AbstractMongoClient implements MongoClient, GeckoMongoClient {
	private MongoClient delegate;
	private MongoClientConfig mongoConfig;

	private GeckoMongoClientImpl() {

	}

	public GeckoMongoClientImpl(MongoClient mongoClient, MongoClientConfig mongoConfig) {
		this();
		this.delegate = mongoClient;
		this.mongoConfig = mongoConfig;

	}

	@Override
	MongoClient delegate() {

		return delegate;
	}

	@Override
	public String mongoIdent() {
		return mongoConfig.mongoIdent();
	}

	@Override
	public String mongoHost()  {
		String part = mongoConfig.connectionString().replace("mongodb://", "").replace("mongodb+srv://", "");
		//Remove Auth Section
		int iAuth = part.indexOf("@");
		if (iAuth > 0) {
			part = part.substring(iAuth);
		}

		// do not use /database
		int iPath = part.indexOf("/");
		if (iPath > 0) {
			part = part.substring(0, iPath);
		}
		// no QueryParams
		int iQuery = part.indexOf("?");
		if (iQuery > 0) {
			part = part.substring(0, iQuery);
		}

		return part;

	}

}
