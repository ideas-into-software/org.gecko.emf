/**
 * Copyright (c) 2014 Data In Motion UG and others.
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Data In Motion UG - initial API and implementation
 */
package org.gecko.emf.osgi.rest.jaxrs.annotation;

/**
 * Triggers check for Empty Content
 * @author Jürgen Albert
 * @since 06.11.2014 
 */
public @interface ContentNotEmpty {
	
	String message() default "Empty content not allowed"; 

}
