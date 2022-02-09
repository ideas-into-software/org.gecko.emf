package org.gecko.emf.osgi.doc.gmf.impl;

public interface MRelation extends Describeable {
	MBase from();

	String cardinalityFrom();

	String cardinalityTo();

	String name();

	RelationType type();

	MBase to();

}
