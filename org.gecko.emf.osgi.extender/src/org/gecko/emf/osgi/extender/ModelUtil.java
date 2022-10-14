/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.gecko.emf.osgi.extender;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.osgi.EMFNamespaces;
import org.gecko.emf.osgi.extender.model.BundleState;
import org.gecko.emf.osgi.extender.model.Model;
import org.gecko.emf.osgi.extender.model.ModelsFile;
import org.osgi.framework.Bundle;

public class ModelUtil {

	public static final class Report {

		public final List<String> warnings = new ArrayList<>();

		public final List<String> errors = new ArrayList<>();
	}

	/**
	 * Read all models from a bundle
	 * @param provider The bundle provider
	 * @param paths The paths to read from
	 * @param report The report for errors and warnings
	 * @return The bundle state.
	 */
	public static BundleState readModelsFromBundle(final Bundle bundle,
			final ResourceSet resourceSet,
			final Set<String> paths,
			final Report report) {
		final BundleState config = new BundleState();

		final List<ModelsFile> allFiles = new ArrayList<>();
		for(final String path : paths) {
			final List<ModelsFile> files = readModel(bundle, resourceSet, path, report);
			allFiles.addAll(files);
		}
		Collections.sort(allFiles);

		config.addFiles(allFiles);

		return config;
	}

	/**
	 * Read all json files from a given path in the bundle
	 *
	 * @param provider The bundle provider
	 * @param path The path
	 * @param report The report for errors and warnings
	 * @return A list of configuration files - sorted by url, might be empty.
	 */
	public static List<ModelsFile> readModel(final Bundle bundle,
			final ResourceSet resourceSet,
			final String path,
			final Report report) {
		final List<ModelsFile> result = new ArrayList<>();
		final Enumeration<URL> urls = bundle.findEntries(path, "*.ecore", false);
		if ( urls != null ) {
			while ( urls.hasMoreElements() ) {
				final URL url = urls.nextElement();

				final String filePath = url.getPath();

				try {
					final ModelsFile file = readModel(bundle, resourceSet, filePath, url, report);
					if ( file != null ) {
						result.add(file);
					}
				} catch ( final IOException ioe ) {
					report.errors.add("Unable to load ecore " + path + " : " + ioe.getMessage());
				}
			}
			Collections.sort(result);
		} else {
			report.errors.add("No ecore models found at path " + path);
		}
		return result;
	}

	/**
	 * Read a single JSON file
	 * @param converter type converter
	 * @param name The name of the file
	 * @param url The url to that file or {@code null}
	 * @param bundleId The bundle id of the bundle containing the file
	 * @param contents The contents of the file
	 * @param report The report for errors and warnings
	 * @return The configuration file or {@code null}.
	 */
	public static ModelsFile readModel(final Bundle bundle,
			final ResourceSet resourceSet,
			final String path,
			final URL url,
			final Report report) throws IOException {
		final String identifier = (url == null ? path : url.toString());
		final List<Model> list = createModelInstance(bundle, resourceSet, identifier, url, report);
		if ( !list.isEmpty() ) {
			final ModelsFile file = new ModelsFile(url, list);
			return file;
		}
		return null;
	}

	/**
	 * Read the configurations JSON
	 * @param converter The converter to use
	 * @param bundleId The bundle id
	 * @param identifier The identifier
	 * @param configs The map containing the configurations
	 * @param report The report for errors and warnings
	 * @return The list of {@code Config}s or {@code null}
	 */
	public static List<Model> createModelInstance(final Bundle bundle,
			final ResourceSet resourceSet,
			final String path,
			final URL url,
			final Report report) throws IOException{
		Resource r = resourceSet.createResource(URI.createURI(path)); 
		r.load(url.openStream(), null);
		if (!r.getContents().isEmpty()) {
			EPackage ePackage = (EPackage) r.getContents().get(0);
			Dictionary<String, Object> properties = new Hashtable<>();
			String nsUri = ePackage.getNsURI();
			properties.put(EMFNamespaces.EMF_MODEL_NAME, ePackage.getName());
			properties.put(EMFNamespaces.EMF_MODEL_NSURI, nsUri);
			Model m = new Model(ePackage, properties, bundle.getBundleId(), 0);
			for (Diagnostic de : r.getErrors()) {
				report.errors.add(de.toString());
			}
			for (Diagnostic dw : r.getWarnings()) {
				report.warnings.add(dw.toString());
			}
			return Collections.singletonList(m);
		}
		return Collections.emptyList();

	}
}