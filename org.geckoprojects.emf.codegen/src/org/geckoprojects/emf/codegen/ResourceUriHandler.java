/**
 * Copyright (c) 2012 - 2021 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.geckoprojects.emf.codegen;

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
import java.util.StringJoiner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;

import aQute.bnd.build.Container;
import aQute.bnd.osgi.Constants;
import aQute.bnd.osgi.Jar;
import aQute.bnd.osgi.Resource;
import aQute.lib.exceptions.Exceptions;
import aQute.lib.io.IO;

/**
 * An URI Handler to translate between EMF and BND Dependencies
 * @author Juergen Albert
 * @since 18 Jan 2021
 */
public class ResourceUriHandler implements URIHandler {

	/** PLATFORM_PLUGIN */
	private static final String PLATFORM_PLUGIN = "platform:/plugin/";
	/** PLATFORM_RESOURCE */
	private static final String PLATFORM_RESOURCE = "platform:/resource/";
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
		GeckoEmfGenerator.info("Asked to handle " + uri);
		return uri.scheme().equals("resource") || uri.toString().startsWith(PLATFORM_RESOURCE) || uri.toString().startsWith(PLATFORM_PLUGIN); 
	}

	private URI sanatize(URI toSanatize) {
		if(toSanatize.toString().startsWith(PLATFORM_RESOURCE)) {
			toSanatize = URI.createURI("resource://" + toSanatize.toString().substring(PLATFORM_RESOURCE.length()));
		}
		if(toSanatize.toString().startsWith(PLATFORM_PLUGIN)) {
			toSanatize = URI.createURI("resource://" + toSanatize.toString().substring(PLATFORM_PLUGIN.length()));
		}
		String uri = "";
		for(int i = toSanatize.segmentCount() -1; i >= 0;  i--) {
			String segment = toSanatize.segment(i);
			if("..".equals(segment)) {
				i--;
			} else {
				if(toSanatize.segmentCount() - 1 == i) {
					uri = segment;
				} else {
					uri = segment + "/" + uri;
				}
			}
			if(i <= 0 ) {
				String host = toSanatize.host();
				if("..".equals(segment)) {
					return URI.createURI(toSanatize.scheme() + "://" +uri);
				}
				return URI.createURI(toSanatize.scheme() + "://"+ host + "/" + uri);
			}
		};
		return null;
	}
	

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked to open InputStream for " + uri);
		URI theUri = sanatize(uri).trimFragment().trimQuery();
		GeckoEmfGenerator.info("sanatized uri " + theUri);
		String uriBSN = theUri.host();
		GeckoEmfGenerator.info("bsn according to URI" + uriBSN);
		if(bsn.equals(uriBSN)) {
			GeckoEmfGenerator.info("The bsn segment part fits to: " + bsn);
			StringJoiner joiner = new StringJoiner("/");
			uri.segmentsList().stream().forEach(joiner::add);
			return IO.stream( new File(base, joiner.toString() ));
		} 
		
		for (Entry<Container, List<String>> entry : buildPathModels.entrySet()) {
			Container c = entry.getKey();
			String containerBSN = getBSN(c);
			GeckoEmfGenerator.info("Comparing " + uriBSN + "with container using getBSN() " + containerBSN);
			if(containerBSN.equals(uriBSN)) {
				GeckoEmfGenerator.info("Match in " + c);
				for(String path : entry.getValue()) {
					String testUri = "resource://" + containerBSN + "/" + path;
					GeckoEmfGenerator.info("comaparing URIs " + testUri + " with the requested " + theUri);
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
			String bsn = c.getManifest().getMainAttributes().getValue(Constants.BUNDLE_SYMBOLICNAME);
			if(bsn.contains(";")) {
				bsn = bsn.substring(0, bsn.indexOf(';'));
			}
			return bsn;
		} catch (Exception e) {
			GeckoEmfGenerator.info(String.format("Could not parse BSN from %s. Error was %s. Returning %s", c, e.getMessage(), c.getBundleSymbolicName()));
			return c.getBundleSymbolicName();
		}
	}

	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked to open OutputStream for " + uri);
		URI theUri = sanatize(uri).trimFragment().trimQuery();
		GeckoEmfGenerator.info("Sanatized " + theUri);
		String uriBSN = theUri.host();
		if(bsn.equals(uriBSN)) {
			StringJoiner joiner = new StringJoiner("/");
			theUri.segmentsList().stream().forEach(joiner::add);
			File theFile = new File(base, joiner.toString());
			theFile.getParentFile().mkdirs();
			GeckoEmfGenerator.info("Opening file " + theFile);
			return new FileOutputStream(theFile);
		} 
		return null;
	}

	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked to delete " + uri);
		URI theUri = sanatize(uri).trimFragment().trimQuery();
		GeckoEmfGenerator.info("Sanatized " + theUri);
		String uriBSN = theUri.segment(0);
		if(bsn.equals(uriBSN)) {
			StringJoiner joiner = new StringJoiner("/");
			uri.segmentsList().stream().skip(1).forEach(joiner::add);
			IO.delete(new File(base, joiner.toString() + uri.fileExtension()));
		} 
	}

	@Override
	public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
		GeckoEmfGenerator.info("Asked for content Descriptor " + uri);
		return Collections.singletonMap(ContentHandler.CONTENT_TYPE_PROPERTY, "application/xmi");
	}

	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
		GeckoEmfGenerator.info("Asked if exists " + uri);
		URI theUri = sanatize(uri).trimFragment().trimQuery();
		GeckoEmfGenerator.info("Sanatized " + uri);
		String uriBSN = theUri.segment(0);
		if(bsn.equals(uriBSN)) {
			StringJoiner joiner = new StringJoiner("/");
			uri.segmentsList().stream().skip(1).forEach(joiner::add);
			return new File(base, joiner.toString() + uri.fileExtension()).exists();
		} 
		return false;
	}

	@Override
	public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
		// TODO Auto-generated method stub

	}

}
