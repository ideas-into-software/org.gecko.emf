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
package org.geckoprojects.emf.bson.annotation;

import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.geckoprojects.emf.core.EMFNamespaces;
import org.geckoprojects.emf.core.ResourceSetConfigurator;
import org.geckoprojects.emf.json.annotation.RequireEMFJson;
import org.osgi.annotation.bundle.Requirement;

@Documented
@Retention(CLASS)
@Target({ TYPE, PACKAGE })
@Requirement(
		namespace = EMFNamespaces.EMF_CONFIGURATOR_NAMESPACE,
		name = ResourceSetConfigurator.EMF_CONFIGURATOR_NAME,
		filter = "(" + EMFNamespaces.EMF_CONFIGURATOR_NAME + "=EMFBson)"
		)
/**
 * Metaannotation to generate a Require Capability for EMFBson
 * @author Mark Hoffmann
 * @since 10 Oct 2018
 */
@RequireEMFJson
public @interface RequireEMFBson {

}
