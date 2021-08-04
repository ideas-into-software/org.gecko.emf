package org.geckoprojects.emf.core.api;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * A few Constants to enable customized UriHandler behavior
 * @author Juergen Albert
 * @since 27.07.2017
 */
public interface EMFUriHandlerConstants {

	/**
	 * The Request Methode to be used. Only applies for Outputstream to determin if out or post is used
	 */
	String OPTION_HTTP_METHOD = "method";
	
	/**
	 * Must provide a {@link Map}p of additional Headers that will be set to the request. They will be added last and thus may overwrite existing headers
	 */
	String OPTION_HTTP_HEADERS = "headers";
	
	/**
	 * If a complex Object in response is expected the Reponse will be feed as input stream to the given {@link Resource}
	 */
	String OPTIONS_EXPECTED_RESPONSE_RESOURCE = "expected.response.resource";
	
	/**
	 * A {@link Map} of Resource options that will be feed to the given response {@link Resource} as well
	 */
	String OPTIONS_EXPECTED_RESPONSE_RESOURCE_OPTIONS = "expected.response.options";
	
	/**
	 * if this option is set to <code>true</code> the response will be logged by the Urihandlers logger
	 */
	String OPTIONS_LOG_RESPONSE = "log.response";
	
	/**
	 * if set together with password it will be used for basic auth
	 */
	String OPTIONS_AUTH_USER = "user";
	
	/**
	 * if set together with user it will be used for basic auth
	 */
	String OPTIONS_AUTH_MANDANT = "mandant";
	
	/**
	 * if set together with user it will be used for basic auth
	 */
	String OPTIONS_AUTH_PASSWORD = "password";

}
