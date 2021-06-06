/**
 * Copyright (c) 2017 Data In Motion UG and others.
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Data In Motion Consulting GmbH - initial API and implementation
 */
package org.gecko.emf.osgi.rest.jaxrs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Juergen Albert
 * @since 07.02.2013
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceOption {

	/**
	 * The key for the Option
	 */
	String key();
	
	/**
	 * The value for the for the option to set
	 */
	String value();
	
	/**
	 * @return the type of the value.
	 */
	Class<?> valueType() default String.class;
	
}
