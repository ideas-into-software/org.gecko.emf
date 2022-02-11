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
package org.gecko.emf.osgi.doc.mermaid.impl;

import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.gecko.emf.osgi.doc.mermaid.MermaidService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import aQute.lib.io.IO;
import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Component(service = MermaidService.class)
public class MermaidServiceImpl implements MermaidService {

	@Activate
	BundleContext bc;

	@Override
	public void generate(String uri, Writer writer) {
		ClassModel model = Generator.toModel(uri);
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

		ByteArrayTemplateLoader loader = new ByteArrayTemplateLoader();
		try {

			loader.putTemplate("mermaid_classdiagram.ftl",
					IO.read(bc.getBundle().getEntry("ftl/mermaid_classdiagram.ftl").openStream()));

			loader.putTemplate("markdown.ftl", IO.read(bc.getBundle().getEntry("ftl/markdown.ftl").openStream()));

			loader.putTemplate("ecore_readme.ftl",
					IO.read(bc.getBundle().getEntry("ftl/ecore_readme.ftl").openStream()));

			cfg.setTemplateLoader(loader);
			// Some other recommended settings:
			cfg.setIncompatibleImprovements(new Version(2, 3, 20));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(Locale.US);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

			// 2. Proccess template(s)

			Map<String, Object> input = new HashMap<>();
			input.put("model", model);
			// 2.2. Get the template

			freemarker.template.Template template = cfg.getTemplate("ecore_readme.ftl");

			// 2.3. Generate the output

			// Write out

			template.process(input, writer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
