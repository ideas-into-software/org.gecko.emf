package org.gecko.emf.osgi.doc.gmf.impl;

import java.util.List;

public interface MMethod extends MMember {

	MethodClassifyer methodClassifyer();
	List<MParameter> parameters();
}
