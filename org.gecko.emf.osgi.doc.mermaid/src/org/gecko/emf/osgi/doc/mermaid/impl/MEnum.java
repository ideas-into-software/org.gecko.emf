package org.gecko.emf.osgi.doc.mermaid.impl;

public interface MEnum extends MClassifyer {
	@Override
	default ClassType classType() {
		return ClassType.ENUMERATION;
	}

	

}
