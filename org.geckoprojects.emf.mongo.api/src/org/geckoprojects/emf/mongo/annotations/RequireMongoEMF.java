/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.mongo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.geckoprojects.emf.mongo.Keywords;
import org.osgi.annotation.bundle.Requirement;

/**
 * Requires MongoEMF support
 * @author Juergen Albert
 * @since 14 Feb 2018
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({
		ElementType.TYPE, ElementType.PACKAGE
})
@Requirement(namespace = Keywords.CAPABILITY_NAMESPACE, //
		name = Keywords.CAPABILITY_NAME, //
		version = Keywords.CAPABILITY_VERSION)
public @interface RequireMongoEMF {

}
