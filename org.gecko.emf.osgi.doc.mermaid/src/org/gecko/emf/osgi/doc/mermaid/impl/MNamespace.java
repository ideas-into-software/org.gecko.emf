package org.gecko.emf.osgi.doc.mermaid.impl;

import java.util.List;

public interface MNamespace extends Describeable, MBase {

	List<MNamespace> namespaces();

	List<MClass> classes();

	List<MEnum> enums();

}
