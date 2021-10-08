package org.geckoprojects.emf.doc.gmf.impl;

public interface MClassMember extends MMember {

	String type();

	VisibilityClassifier visibilityClassifier();

	int lowerBound();

	int upperBound();

}
