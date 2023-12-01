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
package org.gecko.emf.osgi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.resource.Resource;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.ResourceFactoryConfigurator;
import org.gecko.emf.osgi.annotation.require.RequireEMF;
import org.osgi.service.component.annotations.ComponentPropertyType;

/**
 * A meta annotation for the {@link EMFNamespaces#EMF_RESOURCE_CONFIGURATOR_FEATURE} property of the {@link ResourceFactoryConfigurator} and EMF Whiteboard.
 * It can annotate a {@link ResourceFactoryConfigurator} or {@link Resource.Factory} 
 * @author Juergen Albert
 * @since 12 Feb 2018
 */
@ComponentPropertyType
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@RequireEMF
public @interface EMFConfigurator {
	String PREFIX_ = EMFNamespaces.EMF_CONFIGURATOR_PREFIX;

	String name();
	String[] feature() default "";
	String[] protocol() default "";
	String[] contentType() default "";
	String[] fileExtension() default "";
	
}
