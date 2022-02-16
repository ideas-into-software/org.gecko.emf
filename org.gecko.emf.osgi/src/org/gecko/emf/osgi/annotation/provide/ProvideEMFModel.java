/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.annotation.provide;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.EPackageConfigurator;
import org.osgi.annotation.bundle.Attribute;
import org.osgi.annotation.bundle.Capability;

/**
 * Marker annotation that the bundle has the capability to provide a certain
 * model
 * 
 * @author Juergen Albert
 * @since 9 Feb 2018
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.TYPE, ElementType.PACKAGE })
@Capability(namespace = EMFNamespaces.EMF_CONFIGURATOR_NAMESPACE, //
		name = EPackageConfigurator.EMF_CONFIGURATOR_NAME)
public @interface ProvideEMFModel {

	@Attribute()
	String name();

	@Attribute()
	String[] nsURI();

	
	String version() default "1.0.0";
}
