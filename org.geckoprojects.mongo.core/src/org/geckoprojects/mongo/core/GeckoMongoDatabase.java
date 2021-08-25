package org.geckoprojects.mongo.core;

import com.mongodb.client.MongoDatabase;

public interface GeckoMongoDatabase extends MongoDatabase{

	String databaseAlias();

	String internalName();
	
	/**
	 * if port is 27017 it will be removed. all other ports are used
	 * 
	 * @return uri
	 */
	String getURI();
	
	String databaseIdent();
 }

