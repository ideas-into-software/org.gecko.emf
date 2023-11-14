/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.annotation.provide;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.ResourceSetConfigurator;
import org.osgi.annotation.bundle.Attribute;
import org.osgi.annotation.bundle.Capability;
import org.osgi.service.component.annotations.ComponentPropertyType;

/**
 * Marker annotation that the bundle has the  capability to provide a {@link ResourceSetConfigurator} 
 * with certain abilities
 * @author Juergen Albert
 * @since 9 Feb 2018
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({
		ElementType.TYPE, ElementType.PACKAGE
})
@Capability(namespace = EMFNamespaces.EMF_CONFIGURATOR_NAMESPACE, 
		name = ResourceSetConfigurator.EMF_CONFIGURATOR_NAME)
@ComponentPropertyType
public @interface ProvideEMFResourceConfigurator {

	String PREFIX_ = EMFNamespaces.EMF_RESOURCE_CONFIGURATOR_PREFIX;
	
	@Attribute(EMFNamespaces.EMF_CONFIGURATOR_NAME)
	String name(); 
	
	@Attribute(EMFNamespaces.EMF_CONFIGURATOR_CONTENT_TYPE)
	String[] contentType();

	@Attribute(EMFNamespaces.EMF_CONFIGURATOR_FILE_EXT)
	String[] fileExtension();
	
//	@Attribute
	String version() default "1.0.0";
}
