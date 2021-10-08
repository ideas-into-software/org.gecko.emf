package org.geckoprojects.mongo.core;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition()
public @interface MongoDatabaseConfig {

	@AttributeDefinition(required = true, description = "the name of the database the mongo-client used to call the Database")
	String name();

	@AttributeDefinition(required = false, description = "additional alias for the database,this overrides of the `name` in the databaseUniqueIdentifyer")
	String alias();

	@AttributeDefinition(required = false, description = "verifyes, that the database exists on the for the client.")
	boolean must_exist() default false;

}
