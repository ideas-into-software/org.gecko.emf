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

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * EMF codegen generator adapter that is responsible to generate the OSGi service component, that
 * registers the {@link EPackage} and {@link ResourceFactoryImpl} instances to the corresponding registry
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
public class ConfigurationComponentGeneratorAdapter extends GenPackageGeneratorAdapter {

	protected static final int PACKAGE_OSGI_CONFIGURATION = 14;
	protected static final int PACKAGE_INFO = 15;
	protected static final int PACKAGE_INFO_IMPL = 16;
	protected static final int PACKAGE_INFO_UTIL = 17;

	private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS =
		{
				new JETEmitterDescriptor("model/ConfigComponent.javajet", "org.gecko.emf.osgi.codegen.templates.model.ConfigurationComponentClass"),
				new JETEmitterDescriptor("model/PackageInfo.javajet", "org.gecko.emf.osgi.codegen.templates.model.PackageInfo"),
				new JETEmitterDescriptor("model/PackageInfoImpl.javajet", "org.gecko.emf.osgi.codegen.templates.model.PackageInfoImpl"),
				new JETEmitterDescriptor("model/PackageInfoUtil.javajet", "org.gecko.emf.osgi.codegen.templates.model.PackageInfoUtil")
		};

	public ConfigurationComponentGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}
	/**
	 * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter. The contents of the returned array
	 * should never be changed. Rather, subclasses may override this method to return a different array altogether.
	 */
	protected JETEmitterDescriptor[] getJETEmitterDescriptors()
	{
		List<JETEmitterDescriptor> descs = new LinkedList<JETEmitterDescriptor>();
		descs.addAll(Arrays.asList(super.getJETEmitterDescriptors()));
		descs.add(JET_EMITTER_DESCRIPTORS[0]);
		descs.add(JET_EMITTER_DESCRIPTORS[1]);
		descs.add(JET_EMITTER_DESCRIPTORS[2]);
		descs.add(JET_EMITTER_DESCRIPTORS[3]);
		return descs.toArray(new JETEmitterDescriptor[descs.size()]);
	}


	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor)
	{
		monitor.beginTask("Generating model", 15);

		GenPackage genPackage = (GenPackage)object;
		message = "Generating Code for " + genPackage.getBasicPackageName();
		monitor.subTask(message);

		GenModel genModel = genPackage.getGenModel();
		ensureProjectExists
		(genModel.getModelDirectory(), genPackage, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

		generateSchema(genPackage, monitor);
		generatePackagePublication(genPackage, monitor);
		generatePackageSerialization(genPackage, monitor);
		generatePackageInterface(genPackage, monitor);
		generatePackageClass(genPackage, monitor);
		generateFactoryInterface(genPackage, monitor);
		generateFactoryClass(genPackage, monitor);
		generateXMLProcessorClass(genPackage, monitor);
		generateValidatorClass(genPackage, monitor);
		generateSwitchClass(genPackage, monitor);
		generateAdapterFactoryClass(genPackage, monitor);
		generateResourceFactoryClass(genPackage, monitor);
		generateResourceClass(genPackage, monitor);
		generateConfigurationComponent(genPackage, monitor);
		generatePackageInfo(genPackage, monitor);

		return Diagnostic.OK_INSTANCE;
	}

	/**
	 * Generates the configuration service component for the {@link GenPackage}
	 * @param genPackage the package to create the configuration component for
	 * @param monitor the progress monitor
	 */
	protected void generateConfigurationComponent(GenPackage genPackage, Monitor monitor)
	{
		GenModel genModel = genPackage.getGenModel();
		if (genPackage.hasClassifiers())
		{
			message = String.format("Generating OSGi configuration component for package '%s'", genPackage.getPackageName());
			monitor.subTask(message);
			generateJava
			(genModel.getModelDirectory(),
					getConfigurationPackageName(genPackage),
					getBasicConfigurationClassName(genPackage),
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_OSGI_CONFIGURATION),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE }},
					createMonitor(monitor, 1)); 
		}
		else
		{
			monitor.worked(1);
		}
	}
	
	/**
	 * Generates the package info for the {@link GenPackage}
	 * @param genPackage the package to create the configuration component for
	 * @param monitor the progress monitor
	 */
	protected void generatePackageInfo(GenPackage genPackage, Monitor monitor)
	{
		GenModel genModel = genPackage.getGenModel();
		if (genPackage.hasClassifiers())
		{
			message = String.format("Generating package info for package '%s'", genPackage.getPackageName());
			monitor.subTask(message);
			generateJava
			(genModel.getModelDirectory(),
					genPackage.getInterfacePackageName(),
					"package-info",
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_INFO),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE }},
					createMonitor(monitor, 1)); 
			generateJava
			(genModel.getModelDirectory(),
					genPackage.getUtilitiesPackageName(),
					"package-info",
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_INFO_UTIL),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE }},
					createMonitor(monitor, 1)); 
			generateJava
			(genModel.getModelDirectory(),
					getClassPackageName(genPackage),
					"package-info",
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_INFO_IMPL),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE }},
					createMonitor(monitor, 1)); 
		}
		else
		{
			monitor.worked(1);
		}
	}

	/**
	 * Returns the configuration component package name
	 * @param genPackage the generator package
	 * @return the package name
	 */
	private String getConfigurationPackageName(GenPackage genPackage)
	{
		String result = genPackage.getInterfacePackageName();
		return result + ".configuration";
	}

	/**
	 * Returns the Package impl names
	 * @param genPackage the generator package
	 * @return the package name
	 */
	private String getClassPackageName(GenPackage genPackage)
	{
		String result = genPackage.getInterfacePackageName();
		return result + "." + genPackage.getClassPackageSuffix();
	}

	/**
	 * Returns the class name of the configuration component
	 * @param genPackage the generator package
	 * @return the class name
	 */
	private String getBasicConfigurationClassName(GenPackage genPackage)
	{
		return genPackage.getPrefix() + "ConfigurationComponent";
	}

}
