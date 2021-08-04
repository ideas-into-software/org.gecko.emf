package org.geckoprojects.emf.doc.mermaid.impl;

import java.util.List;

public interface MClassifyer extends Describeable, MBase {
	public ClassType classType();

	List<? extends MMember> members();
}
