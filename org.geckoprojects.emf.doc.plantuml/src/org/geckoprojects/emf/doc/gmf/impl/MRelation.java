package org.geckoprojects.emf.doc.gmf.impl;

public interface MRelation extends Describeable {
	MBase from();

	String cardinalityFrom();

	String cardinalityTo();

	String name();

	RelationType type();

	MBase to();

}
