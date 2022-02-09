package org.gecko.emf.osgi.doc.mermaid.impl;

public interface MClassMember extends MMember {

	String type();

	VisibilityClassifier visibilityClassifier();

	int lowerBound();

	int upperBound();

}
