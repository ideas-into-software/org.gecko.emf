package org.gecko.emf.osgi.doc.gmf.impl;

import java.util.List;

public interface MClassMethod extends MClassMember {


	MethodClassifyer methodClassifyer();
	List<MParameter> paramaters();
}
