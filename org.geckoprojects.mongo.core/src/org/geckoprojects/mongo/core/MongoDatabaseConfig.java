package org.geckoprojects.mongo.core;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition()
public @interface MongoDatabaseConfig {

	@AttributeDefinition(required = false, name = "databaseIdent", description = "A Identifyer for the mongo database. if not set  generated using `clientIdent`.`InternalName`")
	String database_ident();

	@AttributeDefinition(required = true)
	String internal_name();

	@AttributeDefinition(required = false, name = "databaseAlias", description = "additional alias for the database")
	String databaseAlias();

	@AttributeDefinition(required = false, description = "verifyes, that the database exists on the for the client.")
	boolean must_exist() default false;

}
