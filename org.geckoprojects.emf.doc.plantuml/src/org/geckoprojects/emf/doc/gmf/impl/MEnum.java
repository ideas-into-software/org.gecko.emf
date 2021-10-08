package org.geckoprojects.emf.doc.gmf.impl;

public interface MEnum extends MClassifyer {
	@Override
	default ClassType classType() {
		return ClassType.ENUMERATION;
	}

	

}
