package org.geckoprojects.mongo.core;

public interface MongoConstants {

	public static final String PID_MONGO_CLIENT = "org.geckoprojects.mongo.client";
	public static final String PID_MONGO_DATABASE = "org.geckoprojects.mongo.database";

	public static final String CLIENT_PROP_CLIENT_IDENT = "client.ident";
	public static final String CLIENT_PROP_CONNECTION_STRING = "connectionString";
	public static final String CLIENT_PROP_CLIENT_STATUS = "client_status";

	
	public static final String DB_PROP_DATABASE_IDENT = "database.ident";
	public static final String DB_PROP_DATABASE_INTERNAL_NAME = "internal.name";
	public static final String DB_PROP_DATABASE_DATABASE_ALIAS = "databaseAlias";
	public static final String DB_PROP_DATABASE_MUST_EXIST = "must.exist";
	
	public static final String TARGET_MONGOCLIENT = "mongoclient.target";

	public static String TARGET_FILTER_CLIENT_BY_IDENT= "(" + MongoConstants.CLIENT_PROP_CLIENT_IDENT + "=%s)";
	
	

	

	
	
}
