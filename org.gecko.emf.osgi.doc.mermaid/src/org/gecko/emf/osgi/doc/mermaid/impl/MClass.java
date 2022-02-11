/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *      Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.doc.mermaid.impl;

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
