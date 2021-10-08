package org.geckoprojects.emf.doc.gmf.impl;

import java.util.List;

public interface MMethod extends MMember {

	MethodClassifyer methodClassifyer();
	List<MParameter> parameters();
}
