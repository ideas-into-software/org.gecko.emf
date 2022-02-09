package org.gecko.emf.osgi.doc.mermaid.impl;

import java.util.List;

public interface MClassifyer extends Describeable, MBase {
	public ClassType classType();

	List<? extends MMember> members();
}
