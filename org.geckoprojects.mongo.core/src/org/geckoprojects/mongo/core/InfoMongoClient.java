package org.geckoprojects.mongo.core;

import com.mongodb.client.MongoClient;

public interface InfoMongoClient extends MongoClient {

	/**
	 *  A long term unique identifyer of the Connection that will not change. E,g, if you use the atlas-cloud it can be the unique host from the uri (without port and protocol)
	 * 
	 * @return ClientUniqueIdentifyer
	 */
	String getClientUniqueIdentifyer();
}
