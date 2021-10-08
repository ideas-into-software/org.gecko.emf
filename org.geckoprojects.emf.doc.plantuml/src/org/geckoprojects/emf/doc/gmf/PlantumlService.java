package org.geckoprojects.emf.doc.gmf;

import java.io.Writer;


public interface PlantumlService {

	void generate(String uri, Writer writer);

}
