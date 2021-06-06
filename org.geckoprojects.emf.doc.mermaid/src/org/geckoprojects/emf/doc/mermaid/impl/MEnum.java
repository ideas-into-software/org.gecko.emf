package org.geckoprojects.emf.doc.mermaid.impl;

public interface MEnum extends MClassifyer {
	@Override
	default ClassType classType() {
		return ClassType.ENUMERATION;
	}

	

}
