package org.geckoprojects.emf.doc.mermaid.itest;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.geckoprojects.emf.doc.mermaid.MermaidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
public class MainTest {

	@Test
	public void main(@InjectService MermaidService mermaidService) throws Exception {

		String ecorePath = "./model/bsm.ecore";
//		Writer consoleWriter = new OutputStreamWriter(System.out);
		Writer fileWriter = new FileWriter(new File("output.md"));

		mermaidService.generate(ecorePath, fileWriter);

	}
}