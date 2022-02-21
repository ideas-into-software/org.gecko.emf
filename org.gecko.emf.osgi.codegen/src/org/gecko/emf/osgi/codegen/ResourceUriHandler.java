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
package org.gecko.emf.osgi.codegen;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.StringJoiner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;

import aQute.bnd.build.Container;
import aQute.bnd.exceptions.Exceptions;
import aQute.bnd.osgi.Constants;
import aQute.bnd.osgi.Jar;
import aQute.bnd.osgi.Resource;
import aQute.lib.io.IO;

/**
 * An URI Handler to translate between EMF and BND Dependencies
 * @author Juergen Albert
 * @since 18 Jan 2021
 */
public class ResourceUriHandler implements URIHandler {

	/** RESOURCE_SCHEMA_NAME */
	private static final String RESOURCE_SCHEMA_NAME = "resource"; //$NON-NLS-1$
	/** APPLICATION_XMI */
	private static final String APPLICATION_XMI = "application/xmi"; //$NON-NLS-1$
	/** SLASH */
	private static final String SLASH = "/"; //$NON-NLS-1$
	/** SCHEMA_RESOURCE */
	private static final String SCHEMA_RESOURCE = "resource://"; //$NON-NLS-1$
	/** PLATFORM_PLUGIN */
	private static final String PLATFORM_PLUGIN = "platform:/plugin/"; //$NON-NLS-1$
	/** PLATFORM_RESOURCE */
	private static final String PLATFORM_RESOURCE = "platform:/resource/"; //$NON-NLS-1$
	private final Map<Container, List<String>> buildPathModels;
	private String bsn;
	private File base;
	
	
	/**
	 * Creates a new instance.
	 */
	public ResourceUriHandler(Map<Container, List<String>> buildPathModels, String bsn, File base) {
		this.buildPathModels = buildPathModels;
		this.bsn = bsn;
		this.base = base;
	}
	
	@Override
	public boolean canHandle(URI uri) {
		GeckoEmfGenerator.info("Asked to handle " + uri); //$NON-NLS-1$
		return uri.scheme().equals(RESOURCE_SCHEMA_NAME) || uri.toString().startsWith(PLATFORM_RESOURCE) || uri.toString().startsWith(PLATFORM_PLUGIN); 
	}

	private Optional<URI> sanitize(URI toSanatize) {
		if (toSanatize == null) {
			return Optional.empty();
		}
		if(toSanatize.toString().startsWith(PLATFORM_RESOURCE)) {
			toSanatize = URI.createURI(SCHEMA_RESOURCE + toSanatize.toString().substring(PLATFORM_RESOURCE.length()));
		} else if(toSanatize.toString().startsWith(PLATFORM_PLUGIN)) {
			toSanatize = URI.createURI(SCHEMA_RESOURCE + toSanatize.toString().substring(PLATFORM_PLUGIN.length()));
		}
		return doSanitize(toSanatize);
	}

	private Optional<URI> doSanitize(URI toSanitize) {
		String uri = ""; //$NON-NLS-1$
		for(int i = toSanitize.segmentCount() -1; i >= 0;  i--) {
			String segment = toSanitize.segment(i);
			if("..".equals(segment)) { //$NON-NLS-1$
				i--;
			} else {
				if(toSanitize.segmentCount() - 1 == i) {
					uri = segment;
				} else {
					uri = segment + SLASH + uri;
				}
			}
			if(i <= 0 ) {
				String host = toSanitize.host();
				if("..".equals(segment)) { //$NON-NLS-1$
					return Optional.of(URI.createURI(toSanitize.scheme() + "://" +uri)); //$NON-NLS-1$
				}
				return Optional.of(URI.createURI(toSanitize.scheme() + "://"+ host + SLASH + uri)); //$NON-NLS-1$
			}
		};
		return Optional.empty();
	}

	private URI trimmedSanitize(URI toSanitize) {
		Optional<URI> sanitized = sanitize(toSanitize);
		return sanitized.map(URI::trimFragment).map(URI::trimQuery).orElse(null); 
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked to open InputStream for " + uri); //$NON-NLS-1$
		URI theUri = trimmedSanitize(uri);
		if (theUri == null) {
			GeckoEmfGenerator.error("URI is null, InputStream cannot be created"); //$NON-NLS-1$
			return null;
		}
		GeckoEmfGenerator.info("sanatized uri " + theUri); //$NON-NLS-1$
		String uriBSN = theUri.host();
		GeckoEmfGenerator.info("bsn according to URI" + uriBSN); //$NON-NLS-1$
		if(bsn.equals(uriBSN)) {
			GeckoEmfGenerator.info("The bsn segment part fits to: " + bsn); //$NON-NLS-1$
			StringJoiner joiner = new StringJoiner(SLASH);
			uri.segmentsList().stream().forEach(joiner::add);
			return IO.stream( new File(base, joiner.toString() ));
		} 
		
		for (Entry<Container, List<String>> entry : buildPathModels.entrySet()) {
			Container c = entry.getKey();
			String containerBSN = getBSN(c);
			GeckoEmfGenerator.info("Comparing " + uriBSN + "with container using getBSN() " + containerBSN);  //$NON-NLS-1$//$NON-NLS-2$
			if(containerBSN.equals(uriBSN)) {
				GeckoEmfGenerator.info("Match in " + c); //$NON-NLS-1$
				for(String path : entry.getValue()) {
					String testUri = SCHEMA_RESOURCE + containerBSN + SLASH + path;
					GeckoEmfGenerator.info("comparing URIs " + testUri + " with the requested " + theUri); //$NON-NLS-1$ //$NON-NLS-2$
					if(testUri.equals(theUri.toString())) {
						try(Jar jar = new Jar(c.getFile())){
							Resource resource = jar.getResource(path);
							try {
								byte[] data = IO.read(resource.openInputStream());
								return new ByteArrayInputStream(data);
							} catch (Exception e) {
								Exceptions.duck(e);
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	private String getBSN(Container c) {
		try {
			String symbolicName = c.getManifest().getMainAttributes().getValue(Constants.BUNDLE_SYMBOLICNAME);
			if(symbolicName.contains(";")) { //$NON-NLS-1$
				symbolicName = symbolicName.substring(0, symbolicName.indexOf(';'));
			}
			return symbolicName;
		} catch (Exception e) {
			GeckoEmfGenerator.info(String.format("Could not parse BSN from %s. Error was %s. Returning %s", c, e.getMessage(), c.getBundleSymbolicName())); //$NON-NLS-1$
			return c.getBundleSymbolicName();
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.URIHandler#createOutputStream(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked to open OutputStream for " + uri); //$NON-NLS-1$
		URI theUri = trimmedSanitize(uri);
		if (theUri == null) {
			GeckoEmfGenerator.error("URI is null, OutputStream cannot be created"); //$NON-NLS-1$
			return null;
		}
		GeckoEmfGenerator.info("Sanatized " + theUri); //$NON-NLS-1$
		String uriBSN = theUri.host();
		if(bsn.equals(uriBSN)) {
			StringJoiner joiner = new StringJoiner(SLASH);
			theUri.segmentsList().stream().forEach(joiner::add);
			File theFile = new File(base, joiner.toString());
			theFile.getParentFile().mkdirs();
			GeckoEmfGenerator.info("Opening file " + theFile); //$NON-NLS-1$
			return new FileOutputStream(theFile);
		} 
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.URIHandler#delete(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked to delete " + uri); //$NON-NLS-1$
		URI theUri = trimmedSanitize(uri);
		if (theUri == null) {
			GeckoEmfGenerator.error("URI is null, Delete cannot be executed"); //$NON-NLS-1$
			return;
		}
		GeckoEmfGenerator.info("Sanatized " + theUri); //$NON-NLS-1$
		String uriBSN = theUri.segment(0);
		if(bsn.equals(uriBSN)) {
			StringJoiner joiner = new StringJoiner(SLASH);
			uri.segmentsList().stream().skip(1).forEach(joiner::add);
			IO.delete(new File(base, joiner.toString() + uri.fileExtension()));
		} 
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.URIHandler#contentDescription(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@Override
	public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked for content Descriptor " + uri); //$NON-NLS-1$
		return Collections.singletonMap(ContentHandler.CONTENT_TYPE_PROPERTY, APPLICATION_XMI);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.URIHandler#exists(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
		GeckoEmfGenerator.info("Asked if exists " + uri); //$NON-NLS-1$
		URI theUri = trimmedSanitize(uri);
		if (theUri == null) {
			GeckoEmfGenerator.error("URI is null, existence cannot be checked"); //$NON-NLS-1$
			return false;
		}
		GeckoEmfGenerator.info("Sanatized " + uri); //$NON-NLS-1$
		String uriBSN = theUri.segment(0);
		if(bsn.equals(uriBSN)) {
			StringJoiner joiner = new StringJoiner(SLASH);
			uri.segmentsList().stream().skip(1).forEach(joiner::add);
			return new File(base, joiner.toString() + uri.fileExtension()).exists();
		} 
		return false;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.URIHandler#getAttributes(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@Override
	public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
		return Collections.emptyMap();
	}


	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.URIHandler#setAttributes(org.eclipse.emf.common.util.URI, java.util.Map, java.util.Map)
	 */
	@Override
	public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
		// Nothing to set here
	}

}
