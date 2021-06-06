/**
 * Copyright (c) 2012 - 2021 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.mongo;

/**
 * A MongoIdFactory provides the equivalent of an auto-increment primary key.
 * Calling getNextId() will return the next value to be used as the _id in the
 * collection. The current value is stored in the collection to maintain
 * integrity across server restarts. There is an obvious performance penalty
 * when using the MongoIdFactory instead of letting MongoDB generate the _id
 * for you.
 * 
 * @author bhunt
 */
public interface MongoIdFactory {
	/**
	 * The service property key for the MongoDB database reference filter.
	 */
	String PROP_DATABASE_FILTER = "MongoDatabaseProvider.target";

	/**
	 * The service property key for configuring the database collection. Set
	 * the value of the collection property to the name of the database collection
	 * for which you want to use a sequential _id.
	 */
	String PROP_COLLECTION = "collection";

	/**
	 * Returns the URI of the collection in the form mongodb://host[:port]/database/collection
	 * @return the URI of the collection in the form mongodb://host[:port]/database/collection
	 */
	String getCollectionURI();

	/**
	 * Returns the next id
	 * @return the next auto-increment id value to be used as the _id value
	 */
	Object getNextId();
	
}

