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
package org.gecko.emf.osgi.urihandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.gecko.emf.osgi.EMFUriHandlerConstants;

/**
 * URI Handler with basic authentication
 * @author Juergen Albert
 * @since 17.12.2012
 */
public class RestfulURIHandlerImpl extends URIHandlerImpl {

	private static final Logger LOG = Logger.getLogger(RestfulURIHandlerImpl.class.getName());

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.URIHandlerImpl#createOutputStream(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OutputStream createOutputStream(URI uri, final Map<?, ?> options) throws IOException {
		URL url = new URL(uri.toString());
		final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		String method = "PUT";
		if (options.containsKey(EMFUriHandlerConstants.OPTION_HTTP_METHOD)) {
			method = options.get(EMFUriHandlerConstants.OPTION_HTTP_METHOD).toString().toUpperCase();
		}
		httpURLConnection.setRequestMethod(method);
		httpURLConnection.setDoOutput(Boolean.TRUE);
		setRequestHeaders(httpURLConnection,
				(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
		if (options.containsKey("EClass")) {
			httpURLConnection.setRequestProperty("Content-Class", options.get("EClass").toString());
		}
		handleBasicAuth(httpURLConnection, options);
		return new FilterOutputStream(httpURLConnection.getOutputStream()) {
			@Override
			public void close() throws IOException {
				super.close();
				try {
					int responseCode = httpURLConnection.getResponseCode();
					Map<Object, Object> response = getResponse(options);
					if (response != null) {
						try {
							String lastModified = httpURLConnection.getHeaderField("Last-Modified");
							if (lastModified != null) {
								Long lm = Long.parseLong(lastModified);
								response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, lm);
							}
						} catch (Exception e) {
							LOG.log(Level.SEVERE, "Error reading last modified header from the response", e);
						}
						response.put("HTTPResponseCode", responseCode);
						response.putAll(httpURLConnection.getHeaderFields());
					}
					InputStream in = extreactStreamAndLogResponse(options, httpURLConnection);
					switch (responseCode) {
					case HttpURLConnection.HTTP_OK:
					case HttpURLConnection.HTTP_CREATED:
						Resource responseResource = (Resource) options
								.get(EMFUriHandlerConstants.OPTIONS_EXPECTED_RESPONSE_RESOURCE);
						if (responseResource != null) {
							responseResource.load(in, (Map<?, ?>) options
									.get(EMFUriHandlerConstants.OPTIONS_EXPECTED_RESPONSE_RESOURCE_OPTIONS));
						}
					case HttpURLConnection.HTTP_NO_CONTENT: {
						break;
					}
					default: {
						throw new IOException(httpURLConnection.getRequestMethod() + " failed with HTTP response code " + responseCode);
					}
					}
				} finally {
					httpURLConnection.disconnect();
				}
			}
		};
	}

	/**
	 * Sets the given headers to the url connection
	 * 
	 * @param httpURLConnection
	 * @param headers
	 */
	private void setRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> headers) {
		if (headers != null) {
			for (Entry<String, String> entry : headers.entrySet()) {
				httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.URIHandlerImpl#createInputStream(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		try {
			URL url = new URL(uri.toString());
			final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			handleBasicAuth(httpURLConnection, options);
			setRequestHeaders(httpURLConnection,
					(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
			final int responseCode = httpURLConnection.getResponseCode();
			Map<Object, Object> response = getResponse(options);
			if (response != null) {
				try {
					String lastModified = httpURLConnection.getHeaderField("Last-Modified");
					if (lastModified != null) {
						Long lm = Long.parseLong(lastModified);
						response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, lm);
					}
				} catch (Exception e) {
					LOG.log(Level.SEVERE, "Error reading last modified header from the response", e);
				}
			}
			InputStream result = extreactStreamAndLogResponse(options, httpURLConnection);
			return new FilterInputStream(result) {

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.io.FilterInputStream#read()
				 */
				@Override
				public int read() throws IOException {
					if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
						return -1;
					}
					return super.read();
				}

				@Override
				public void close() throws IOException {
					super.close();

					int responseCode = httpURLConnection.getResponseCode();
					httpURLConnection.disconnect();
					switch (responseCode) {
						case HttpURLConnection.HTTP_OK:
						case HttpURLConnection.HTTP_CREATED:
						case HttpURLConnection.HTTP_NO_CONTENT: {
							break;
						}
						default: {
							throw new IOException(httpURLConnection.getRequestMethod() + " failed with HTTP response code " + responseCode);
						}
					}
				}

			};
		} catch (RuntimeException exception) {
			throw new Resource.IOWrappedException(exception);
		}
	}

	/**
	 * @param options
	 * @param httpURLConnection
	 * @return
	 * @throws IOException
	 */
	private InputStream extreactStreamAndLogResponse(Map<?, ?> options, final HttpURLConnection httpURLConnection)
			throws IOException {
		InputStream result = httpURLConnection.getErrorStream();
		if (result == null) {
			result = httpURLConnection.getInputStream();
		}
		if (Boolean.TRUE.equals(options.get(EMFUriHandlerConstants.OPTIONS_LOG_RESPONSE))) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = result.read();
			while (read != -1) {
				baos.write(read);
				read = result.read();
			}
			byte[] responseArray = baos.toByteArray();
			baos.close();
			result = new ByteArrayInputStream(responseArray);
		}
		return result;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.URIHandlerImpl#delete(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
		try {
			URL url = new URL(uri.toString());
			final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			;
			httpURLConnection.setDoOutput(true);
			handleBasicAuth(httpURLConnection, options);
			setRequestHeaders(httpURLConnection,
					(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
			httpURLConnection.setRequestMethod("DELETE");
			int responseCode = httpURLConnection.getResponseCode();
			httpURLConnection.disconnect();
			switch (responseCode) {
			case HttpURLConnection.HTTP_OK:
			case HttpURLConnection.HTTP_ACCEPTED:
			case HttpURLConnection.HTTP_NO_CONTENT: {
				break;
			}
			default: {
				throw new IOException(httpURLConnection.getRequestMethod() + " failed with HTTP response code " + responseCode);
			}
			}
		} catch (RuntimeException exception) {
			throw new Resource.IOWrappedException(exception);
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.URIHandlerImpl#getAttributes(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
		Map<String, Object> result = new HashMap<String, Object>();
		Set<String> requestedAttributes = getRequestedAttributes(options);
		try {
			URL url = new URL(uri.toString());
			URLConnection urlConnection = null;
			if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_READ_ONLY)) {

				urlConnection = url.openConnection();
				if (urlConnection instanceof HttpURLConnection) {
					HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
					httpURLConnection.setRequestMethod("OPTIONS");
					handleBasicAuth(httpURLConnection, options);
					setRequestHeaders(httpURLConnection,
							(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
					int responseCode = httpURLConnection.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) {
						String allow = httpURLConnection.getHeaderField("Allow");
						result.put(URIConverter.ATTRIBUTE_READ_ONLY, allow == null || !allow.contains("PUT"));
					}
					urlConnection = null;
				} else {
					result.put(URIConverter.ATTRIBUTE_READ_ONLY, true);
				}
			}

			if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_TIME_STAMP)) {
				if (urlConnection == null) {
					urlConnection = url.openConnection();
					if (urlConnection instanceof HttpURLConnection) {
						HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
						setRequestHeaders(httpURLConnection,
								(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
						httpURLConnection.setRequestMethod("HEAD");
						httpURLConnection.getResponseCode();
					}
				}
				if (urlConnection.getHeaderField("last-modified") != null) {
					result.put(URIConverter.ATTRIBUTE_TIME_STAMP, urlConnection.getLastModified());
				}
			}

			if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_LENGTH)) {
				if (urlConnection == null) {
					urlConnection = url.openConnection();
					if (urlConnection instanceof HttpURLConnection) {
						HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
						setRequestHeaders(httpURLConnection,
								(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
						httpURLConnection.setRequestMethod("HEAD");
						httpURLConnection.getResponseCode();
					}
				}
				if (urlConnection.getHeaderField("content-length") != null) {
					result.put(URIConverter.ATTRIBUTE_LENGTH, urlConnection.getContentLength());
				}
			}
		} catch (IOException exception) {
			// Ignore exceptions.
		}
		return result;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.URIHandlerImpl#exists(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
		try {
			URL url = new URL(uri.toString());
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("HEAD");
			handleBasicAuth(httpURLConnection, options);
			setRequestHeaders(httpURLConnection,
					(Map<String, String>) options.get(EMFUriHandlerConstants.OPTION_HTTP_HEADERS));
			int responseCode = httpURLConnection.getResponseCode();
			Map<Object, Object> response = getResponse(options);
			if (response != null) {
				try {
					String lastModified = httpURLConnection.getHeaderField("Last-Modified");
					if (lastModified != null) {
						Long lm = Long.parseLong(httpURLConnection.getHeaderField("Last-Modified"));
						response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, lm);
					}
				} catch (Exception e) {
					LOG.log(Level.SEVERE, "Error reading last modified header from the response", e);
				}
			}
			httpURLConnection.disconnect();
			return responseCode == HttpURLConnection.HTTP_OK;
		} catch (Throwable exception) {
			return false;
		}
	}

	/**
	 * This implementation delegates to the {@link #getURIConverter(Map) URI
	 * converter}'s {@link URIConverter#getContentHandlers() content handlers}.
	 */
	public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
		return ContentHandler.INVALID_CONTENT_DESCRIPTION;
	}

	/**
	 * Handles the basic authentication setting for the {@link URLConnection}
	 * 
	 * @param httpURLConnection
	 *            the {@link URLConnection}
	 * @param options
	 *            a map with options
	 */
	private void handleBasicAuth(HttpURLConnection httpURLConnection, Map<?, ?> options) {
		String username = (String) options.get(EMFUriHandlerConstants.OPTIONS_AUTH_USER);
		String mandant = (String) options.get(EMFUriHandlerConstants.OPTIONS_AUTH_MANDANT);
		String password = (String) options.get(EMFUriHandlerConstants.OPTIONS_AUTH_PASSWORD);
		if (username != null && password != null) {

			String userpassword = username + (mandant != null ? "@" + mandant : "") + ":" + password;
			String encodedAuthorization = Base64.getEncoder().encodeToString(userpassword.getBytes());
			httpURLConnection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.URIHandlerImpl#canHandle(org.eclipse.emf.common.util.URI)
	 */
	@Override
	public boolean canHandle(URI uri) {
		return uri.scheme().equalsIgnoreCase("http") || uri.scheme().equalsIgnoreCase("https");
	}

}