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
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

/**
 * EMF codegen generator adapter factory that is responsible to create the Bnd adapter
 * @author Mark Hoffmann
 * @since 30.03.2018
 */
public class BNDGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory {

	public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor(){
		public GeneratorAdapterFactory createAdapterFactory(){
			return new BNDGeneratorAdapterFactory();
		}
	};
	  

	@Override
	public Adapter createGenModelAdapter() {
		if (genModelGeneratorAdapter == null)
		{
			genModelGeneratorAdapter = new BNDGeneratorAdapter(this);
		}
		return genModelGeneratorAdapter;
	}


	@Override
	public Adapter createGenPackageAdapter() {
		if (genPackageGeneratorAdapter == null)
		{
			genPackageGeneratorAdapter = new ConfigurationComponentGeneratorAdapter(this);
		}
		return genPackageGeneratorAdapter;
	}

}
