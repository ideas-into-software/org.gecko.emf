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
package org.gecko.emf.osgi.codegen.adapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * EMF codegen generator adapter that is responsible to generate an adequate Bnd-project setup with
 * natures, builders and bnd.bnd file
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
public class BNDGeneratorAdapter extends GenModelGeneratorAdapter {

	public BNDGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}


	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor)
	{
		monitor.beginTask("", 7);

		return Diagnostic.OK_INSTANCE;
	}
}
