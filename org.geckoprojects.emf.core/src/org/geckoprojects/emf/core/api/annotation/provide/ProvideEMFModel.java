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
package org.geckoprojects.emf.core.api.annotation.provide;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.geckoprojects.emf.core.api.EMFNamespaces;
import org.geckoprojects.emf.core.api.EPackageConfigurator;
import org.osgi.annotation.bundle.Attribute;
import org.osgi.annotation.bundle.Capability;

/**
 * Marker annotation that the bundle has the capability to provide a certain model 
 * @author Juergen Albert
 * @since 9 Feb 2018
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({
		ElementType.TYPE, ElementType.PACKAGE
})
@Capability(namespace = EMFNamespaces.EMF_CONFIGURATOR_NAMESPACE, //
		name = EPackageConfigurator.EMF_CONFIGURATOR_NAME)
public @interface ProvideEMFModel {

//	@Attribute(EMFNamespaces.EMF_MODEL_NAME)
	@Attribute("name")
	String name(); 
	
//	@Attribute(EMFNamespaces.EMF_MODEL_NSURI)
	@Attribute("nsURI")
	String[] nsURI();
	
	String version();
}
