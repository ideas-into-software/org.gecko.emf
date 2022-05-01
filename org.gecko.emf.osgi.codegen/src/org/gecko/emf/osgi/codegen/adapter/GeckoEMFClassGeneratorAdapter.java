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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * EMF codegen generator adapter that is responsible to generate the 
 * Model, that can work with {@link EPackage}s that have no  static eInstance.
 * 
 * @author Juergen Albert
 * @since 25.07.2017
 */
public class GeckoEMFClassGeneratorAdapter extends GenClassGeneratorAdapter {


	protected static final int CLASS = 3;

	private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("model/Class.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.Class")
			};

	public GeckoEMFClassGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	/**
	 * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter.
	 * The contents of the returned array should never be changed. Rather,
	 * subclasses may override this method to return a different array altogether.
	 */
	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		List<JETEmitterDescriptor> descs = new LinkedList<JETEmitterDescriptor>();
		descs.addAll(Arrays.asList(super.getJETEmitterDescriptors()));
		descs.addAll(Arrays.asList(JET_EMITTER_DESCRIPTORS));
		return descs.toArray(new JETEmitterDescriptor[descs.size()]);
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {

	    GenClass genClass = (GenClass)object;
	    message = CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object[] { genClass.getFormattedName() });
	    monitor.subTask(message);

	    GenModel genModel = genClass.getGenModel();
	    ensureProjectExists
	      (genModel.getModelDirectory(), genClass, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

	    generateInterface(genClass, monitor);
		boolean doPureOSGi = (genModel.isOSGiCompatible() & !genClass.getGenPackage().isLiteralsInterface());
		
		if(doPureOSGi) {
			generateClass(genClass, monitor);
		} else {
			super.generateClass(genClass, monitor);
		}
	    
	    return Diagnostic.OK_INSTANCE;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter#generateClass(org.eclipse.emf.codegen.ecore.genmodel.GenClass, org.eclipse.emf.common.util.Monitor)
	 */
	@Override
	protected void generateClass(GenClass genClass, Monitor monitor) {
		GenModel genModel = genClass.getGenModel();
	    GenPackage genPackage = genClass.getGenPackage();

	    if (!genClass.isInterface())
	    {
	      message = CodeGenEcorePlugin.INSTANCE.getString
	         ("_UI_GeneratingJavaClass_message", new Object[] { genClass.getQualifiedClassName() });
	      monitor.subTask(message);
	      generateJava
	        (genModel.getModelDirectory(),
	         genPackage.getClassPackageName(),
	         genClass.getClassName(),
	         getJETEmitter(getJETEmitterDescriptors(), CLASS),
	         new Object[] { new Object[] { genClass, genModel.isSuppressInterfaces() ? Boolean.TRUE : Boolean.FALSE, Boolean.TRUE }},
	         createMonitor(monitor, 1)); 
	    }
	    else
	    {
	      monitor.worked(1);
	    }
	}
}
