package org.geckoprojects.emf.doc.gmf.impl;

import java.util.List;

public interface MClass extends MClassifyer {
	@Override
	default ClassType classType() {
		return ClassType.CLASS;
	}

	@Override
	List<MClassMember> members();

	List<MClassMethod> methods();

	List<MRelation> references();

	List<MRelation> supertypes();
}
