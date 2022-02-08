package org.geckoprojects.mongo.core.impl;

import org.geckoprojects.mongo.core.InfoMongoClient;
import org.geckoprojects.mongo.core.MongoClientConfig;

import com.mongodb.client.MongoClient;

public class GeckoMongoClientImpl extends AbstractMongoClient implements MongoClient, InfoMongoClient {
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
	public String getClientUniqueIdentifyer() {
		return mongoConfig.ident();
	}

}
