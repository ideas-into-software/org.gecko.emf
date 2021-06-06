package org.geckoprojects.emf.doc.mermaid.impl;

import java.util.List;

public interface MClassMethod extends MClassMember {


	MethodClassifyer methodClassifyer();
	List<MParameter> paramaters();
}
