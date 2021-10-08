package org.geckoprojects.emf.doc.gmf.impl;

import java.util.List;

public interface MClassMethod extends MClassMember {


	MethodClassifyer methodClassifyer();
	List<MParameter> paramaters();
}
