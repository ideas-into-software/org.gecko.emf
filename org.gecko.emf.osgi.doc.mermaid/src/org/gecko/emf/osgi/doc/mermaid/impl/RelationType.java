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

public enum RelationType {

	INHERITANCE, COMPOSITION, AGGREGATION, ASSOCIATION, LINK_SOLID, DEPENDENCY, REALIZATION, LINK_DASHED

//	classA --|> classB : Inheritance
//	classC --* classD : Composition
//	classE --o classF : Aggregation
//	classG --> classH : Association
//	classI -- classJ : Link(Solid)
//	classK ..> classL : Dependency
//	classM ..|> classN : Realization
//	classO .. classP : Link(Dashed)

}
