/**
 * Project: de.dim.server.application
 * $HeadURL$
 * $LastChangedDate$
 * $lastChangedBy$
 * $Revision$
 * (c) Data In Motion 2013
 */
package org.geckoprojects.emf.rest.jaxrs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author Juergen Albert
 * @since 07.02.2013
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EMFResourceOptions {

	/**
	 * @return options for the {@link Resource} for load and save 
	 */
	ResourceOption[] options();
	
}
