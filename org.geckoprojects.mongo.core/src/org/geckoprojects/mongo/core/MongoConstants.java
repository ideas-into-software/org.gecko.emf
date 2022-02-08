package org.geckoprojects.mongo.core;

public interface MongoConstants {

	public static final String PID_MONGO_CLIENT = "org.geckoprojects.mongo.client";
	public static final String PID_MONGO_DATABASE = "org.geckoprojects.mongo.database";

	public static final String CLIENT_PROP_CLIENT_IDENT = "ident";
	public static final String CLIENT_PROP_CLIENT_STATUS = "status";
	public static final String CLIENT_PROP_CONNECTION_STRING = "connectionString";

	
	public static final String DB_PROP_DATABASE_NAME = "name";
	public static final String DB_PROP_DATABASE_ALIAS = "alias";
	public static final String DB_PROP_DATABASE_MUST_EXIST = "must.exist";
	
	public static final String TARGET_MONGOCLIENT = "mongoclient.target";

	public static String TARGET_FILTER_CLIENT_BY_IDENT= "(" + MongoConstants.CLIENT_PROP_CLIENT_IDENT + "=%s)";
	public static String TARGET_DO_NOT_BIND = "(&(must.not.bind.by.default=*)(!(must.not.bind.by.default=*)))";
	public static String TARGET_MONGO_DATABASE_CONNECTED_UNBINDABLE = "("+MongoConstants.CLIENT_PROP_CLIENT_STATUS+"=true)";

	public static String TARGET_DEFAULT_MONGO_DATABASE_CONNECTED = "(&"+TARGET_DO_NOT_BIND+TARGET_MONGO_DATABASE_CONNECTED_UNBINDABLE+")";

	

	

	
	
}
