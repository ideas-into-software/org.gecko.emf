/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.codegen.adapter;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * Used to avoid the PDE specific Files
 * 
 * @author Juergen Albert
 * @since 13 May 2022
 */
public class GeckoGenModelGeneratorAdapter extends GenModelGeneratorAdapter {

	/**
	 * Creates a new instance.
	 * @param generatorAdapterFactory
	 */
	public GeckoGenModelGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter#generateModel(java.lang.Object, org.eclipse.emf.common.util.Monitor)
	 */
	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		monitor.worked(1);
		return Diagnostic.OK_INSTANCE;
	}
}
