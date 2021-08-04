package org.geckoprojects.emf.doc.mermaid.impl;

public interface MClassMember extends MMember {

	String type();

	VisibilityClassifier visibilityClassifier();

	int lowerBound();

	int upperBound();

}
