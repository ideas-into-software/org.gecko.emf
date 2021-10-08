package org.geckoprojects.mongo.core;

import com.mongodb.client.MongoDatabase;

public interface InfoMongoDatabase extends MongoDatabase {

	/**
	 * An alternative name for the database, if set otherwise the database name
	 * @return
	 */
	String getAlias();

	/**
	 * The client unique identifier / the database alias
	 * @return the unique identifier that identifies client and database
	 */
	String getDatabaseUniqueIdentifyer();
}
