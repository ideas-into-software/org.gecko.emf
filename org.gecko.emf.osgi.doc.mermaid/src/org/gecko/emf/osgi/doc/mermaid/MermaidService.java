package org.gecko.emf.osgi.doc.mermaid;

import java.io.Writer;


public interface MermaidService {

	void generate(String uri, Writer writer);

}
