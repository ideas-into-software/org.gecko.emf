package org.geckoprojects.emf.doc.mermaid.impl;

import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.geckoprojects.emf.doc.mermaid.MermaidService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

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
					bc.getBundle().getEntry("ftl/mermaid_classdiagram.ftl").openStream().readAllBytes());

			loader.putTemplate("markdown.ftl", bc.getBundle().getEntry("ftl/markdown.ftl").openStream().readAllBytes());

			loader.putTemplate("ecore_readme.ftl",
					bc.getBundle().getEntry("ftl/ecore_readme.ftl").openStream().readAllBytes());

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
