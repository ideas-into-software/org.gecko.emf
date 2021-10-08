package org.geckoprojects.emf.doc.gmf.impl;

import java.util.List;

public interface MClassifyer extends Describeable, MBase {
	public ClassType classType();

	List<? extends MMember> members();
}
