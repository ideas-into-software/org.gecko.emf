package org.geckoprojects.emf.doc.mermaid;

import java.io.Writer;


public interface MermaidService {

	void generate(String uri, Writer writer);

}
