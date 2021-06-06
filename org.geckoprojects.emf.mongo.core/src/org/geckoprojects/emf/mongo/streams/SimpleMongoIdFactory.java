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
package org.geckoprojects.emf.mongo.streams;

import org.bson.types.ObjectId;
import org.geckoprojects.emf.mongo.MongoIdFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * Returns the {@link ObjectId} as id. This is the default implementation of mongo
 * @author Mark Hoffmann
 * @since 06.05.2016
 */
@Component(name="MongoIdFactory", immediate=true, service=MongoIdFactory.class, scope = ServiceScope.SINGLETON)

public class SimpleMongoIdFactory implements MongoIdFactory{

	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.mongo.MongoIdFactory#getCollectionURI()
	 */
	@Override
	public String getCollectionURI() {
	
		return "*";
	}

	/* 
	 * (non-Javadoc)
	 * @see org.geckoprojects.emf.mongo.MongoIdFactory#getNextId()
	 */
	@Override
	public Object getNextId() {
		
		return new ObjectId();
	}

}
