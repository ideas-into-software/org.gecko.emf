/**
 * Copyright (c) 2014 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.repository.mongo.query;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.geckoprojects.emf.repository.query.ValueQuery;

/**
 * Mongo implementation of an simple key value query.
 * @author Mark Hoffmann
 * @since 12.04.2015
 */
public class MongoValueQuery extends ValueQuery {

	public MongoValueQuery(String key, Object value, List<EStructuralFeature[]> projectionPaths) {
		super(key, value, projectionPaths);
	}


	@Override
	public String getFilterString() {
		if (getKey() != null && getValue() != null) {
			QueryBuilder queryBuilder = QueryBuilder.start(getKey());
			queryBuilder.is(getValue());
			return queryBuilder.get().toString();
		}
		return "";
	}

	@Override
	public String getProjectionString() {
		return ProjectionHelper.createProjectionString(getProjectionPaths());
	}

}
