package org.gecko.emf.osgi.doc.gmf.impl;

public interface MEnum extends MClassifyer {
	@Override
	default ClassType classType() {
		return ClassType.ENUMERATION;
	}

	

}
