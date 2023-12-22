/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.emf.osgi.codegen.adapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * EMF codegen generator adapter that is responsible to generate the OSGi
 * service component, that registers the {@link EPackage} and
 * {@link ResourceFactoryImpl} instances to the corresponding registry
 * 
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
public class GeckoGenPackageGeneratorAdapter extends GenPackageGeneratorAdapter {

	/** PACKAGE_INFO_NAME */
	private static final String PACKAGE_INFO_NAME = "package-info";
	protected static final int PACKAGE_OSGI_CONFIGURATION = 14;
	protected static final int PACKAGE_INFO = 15;
	protected static final int PACKAGE_INFO_IMPL = 16;
	protected static final int PACKAGE_INFO_UTIL = 17;
	protected static final int RESOURCE_FACOTRY = 18;
	protected static final int EPACKAGE_CONFIGURATOR_CLASS = 19;
	protected static final int PACKAGE_CLASS = 20;
	protected static final int FACTORY_CLASS = 21;

	private static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("model/ConfigComponent.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.ConfigurationComponentClass"),
			new JETEmitterDescriptor("model/PackageInfo.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.PackageInfo"),
			new JETEmitterDescriptor("model/PackageInfoImpl.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.PackageInfoImpl"),
			new JETEmitterDescriptor("model/PackageInfoUtil.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.PackageInfoUtil"),
			new JETEmitterDescriptor("model/ResourceFactoryClass.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.ResourceFactoryClass"),
			new JETEmitterDescriptor("model/EPackageConfigurator.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.EPackageConfiguratorClass"),
			new JETEmitterDescriptor("model/PackageClass.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.PackageClass"),
			new JETEmitterDescriptor("model/FactoryClass.javajet",
					"org.gecko.emf.osgi.codegen.templates.model.FactoryClass") };

	public GeckoGenPackageGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	/**
	 * Returns the set of <code>JETEmitterDescriptor</code>s used by the adapter.
	 * The contents of the returned array should never be changed. Rather,
	 * subclasses may override this method to return a different array altogether.
	 */
	@Override
	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		List<JETEmitterDescriptor> descs = new LinkedList<>();
		descs.addAll(Arrays.asList(super.getJETEmitterDescriptors()));
		descs.addAll(Arrays.asList(JET_EMITTER_DESCRIPTORS));
		return descs.toArray(new JETEmitterDescriptor[descs.size()]);
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		monitor.beginTask("Generating model", 15);

		GenPackage genPackage = (GenPackage) object;
		message = "Generating Code for " + genPackage.getBasicPackageName();
		monitor.subTask(message);

		GenModel genModel = genPackage.getGenModel();

		ensureProjectExists(genModel.getModelDirectory(), genPackage, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(),
				createMonitor(monitor, 1));

		super.generateSchema(genPackage, monitor);
		super.generatePackagePublication(genPackage, monitor);
		super.generatePackageSerialization(genPackage, monitor);
		super.generateXMLProcessorClass(genPackage, monitor);
		super.generateResourceClass(genPackage, monitor);
		generatePackageInterface(genPackage, monitor);
		super.generatePackageClass(genPackage, monitor);
		generateFactoryInterface(genPackage, monitor);
		super.generateFactoryClass(genPackage, monitor);
		super.generateSwitchClass(genPackage, monitor);
		super.generateAdapterFactoryClass(genPackage, monitor);
		super.generateValidatorClass(genPackage, monitor);

		if (genModel.isOSGiCompatible()) {
			generateEPackageConfigurator(genPackage, monitor);
			generateResourceFactoryClass(genPackage, monitor);
			generateConfigurationComponent(genPackage, monitor);
			generatePackageInfo(genPackage, monitor);

		} else {
			super.generateResourceFactoryClass(genPackage, monitor);
		}

		return Diagnostic.OK_INSTANCE;
	}

	@Override
	protected void generateResourceFactoryClass(GenPackage genPackage, Monitor monitor) {
		if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
			message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingJavaClass_message",
					new Object[] { genPackage.getQualifiedResourceFactoryClassName() });
			monitor.subTask(message);
			generateJava(genPackage.getGenModel().getModelDirectory(), genPackage.getUtilitiesPackageName(),
					genPackage.getResourceFactoryClassName(),
					getJETEmitter(getJETEmitterDescriptors(), RESOURCE_FACOTRY), null, createMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
	}

	/**
	 * Generates the EPackageConfigurator for the {@link GenPackage}
	 * 
	 * @param genPackage the package to create the configuration component for
	 * @param monitor    the progress monitor
	 */
	protected void generateEPackageConfigurator(GenPackage genPackage, Monitor monitor) {
		GenModel genModel = genPackage.getGenModel();
		if (genPackage.hasClassifiers()) {
			message = String.format("Generating EPackageConfigurator for package '%s'", genPackage.getPackageName());
			monitor.subTask(message);
			generateJava(genModel.getModelDirectory(), getConfigurationPackageName(genPackage),
					getBasicConfiguratorClassName(genPackage),
					getJETEmitter(getJETEmitterDescriptors(), EPACKAGE_CONFIGURATOR_CLASS),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE } },
					createMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
	}

	/**
	 * Generates the configuration service component for the {@link GenPackage}
	 * 
	 * @param genPackage the package to create the configuration component for
	 * @param monitor    the progress monitor
	 */
	protected void generateConfigurationComponent(GenPackage genPackage, Monitor monitor) {
		GenModel genModel = genPackage.getGenModel();
		if (genPackage.hasClassifiers()) {
			message = String.format("Generating OSGi configuration component for package '%s'",
					genPackage.getPackageName());
			monitor.subTask(message);
			generateJava(genModel.getModelDirectory(), getConfigurationPackageName(genPackage),
					getBasicConfigurationComponentClassName(genPackage),
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_OSGI_CONFIGURATION),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE } },
					createMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
	}

	/**
	 * Generates the package info for the {@link GenPackage}
	 * 
	 * @param genPackage the package to create the configuration component for
	 * @param monitor    the progress monitor
	 */
	protected void generatePackageInfo(GenPackage genPackage, Monitor monitor) {
		GenModel genModel = genPackage.getGenModel();
		if (genPackage.hasClassifiers()) {
			message = String.format("Generating package info for package '%s'", genPackage.getPackageName());
			monitor.subTask(message);
			generateJava(genModel.getModelDirectory(), genPackage.getInterfacePackageName(), PACKAGE_INFO_NAME,
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_INFO),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE } },
					createMonitor(monitor, 1));
			generateJava(genModel.getModelDirectory(), getClassPackageName(genPackage), PACKAGE_INFO_NAME,
					getJETEmitter(getJETEmitterDescriptors(), PACKAGE_INFO_IMPL),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE } },
					createMonitor(monitor, 1));
			if ((genPackage.hasClassifiers() && genPackage.getResource().getValue() == GenResourceKind.XML)
					|| (genPackage.getResource() != GenResourceKind.NONE_LITERAL)
					|| (genPackage.hasClassifiers() && genPackage.isAdapterFactory()
							&& !genPackage.getGenClasses().isEmpty())
					|| (genPackage.hasClassifiers() && genPackage.hasConstraints())) {
				generateJava(genModel.getModelDirectory(), genPackage.getUtilitiesPackageName(), PACKAGE_INFO_NAME,
						getJETEmitter(getJETEmitterDescriptors(), PACKAGE_INFO_UTIL),
						new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE } },
						createMonitor(monitor, 1));
			}
		} else {
			monitor.worked(1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter#
	 * generatePackageInterface(org.eclipse.emf.codegen.ecore.genmodel.GenPackage,
	 * org.eclipse.emf.common.util.Monitor)
	 */
	@Override
	protected void generatePackageInterface(GenPackage genPackage, Monitor monitor) {
		GenModel genModel = genPackage.getGenModel();

		if (genPackage.hasClassifiers() && !genModel.isSuppressEMFMetaData() && !genModel.isSuppressInterfaces()) {
			message = String.format("Generating package interface info for package '%s'",
					genPackage.getQualifiedPackageInterfaceName());
			monitor.subTask(message);
			
			@SuppressWarnings("unchecked")
			Map<String, Object> props = (Map<String, Object>) getAdapterFactory().getGenerator().getOptions().data[0];
			
			generateJava(genModel.getModelDirectory(), genPackage.getReflectionPackageName(),
					genPackage.getPackageInterfaceName(), getJETEmitter(getJETEmitterDescriptors(), PACKAGE_CLASS),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE, props } },
					createMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter#
	 * generateFactoryInterface(org.eclipse.emf.codegen.ecore.genmodel.GenPackage,
	 * org.eclipse.emf.common.util.Monitor)
	 */
	@Override
	protected void generateFactoryInterface(GenPackage genPackage, Monitor monitor) {
		GenModel genModel = genPackage.getGenModel();

		if (genPackage.hasClassifiers() && !genModel.isSuppressInterfaces()) {
			message = String.format("Generating factory interface info for package '%s'",
					genPackage.getQualifiedFactoryInterfaceName());
			monitor.subTask(message);
			generateJava(genModel.getModelDirectory(), genPackage.getReflectionPackageName(),
					genPackage.getFactoryInterfaceName(), getJETEmitter(getJETEmitterDescriptors(), FACTORY_CLASS),
					new Object[] { new Object[] { genPackage, Boolean.TRUE, Boolean.FALSE } },
					createMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
	}

	/**
	 * Returns the configuration component package name
	 * 
	 * @param genPackage the generator package
	 * @return the package name
	 */
	private String getConfigurationPackageName(GenPackage genPackage) {
		String result = genPackage.getInterfacePackageName();
		return result + ".configuration";
	}

	/**
	 * Returns the Package impl names
	 * 
	 * @param genPackage the generator package
	 * @return the package name
	 */
	private String getClassPackageName(GenPackage genPackage) {
		String result = genPackage.getInterfacePackageName();
		return result + "." + genPackage.getClassPackageSuffix();
	}

	/**
	 * Returns the class name of the configuration component
	 * 
	 * @param genPackage the generator package
	 * @return the class name
	 */
	private String getBasicConfigurationComponentClassName(GenPackage genPackage) {
		return genPackage.getPrefix() + "ConfigurationComponent";
	}

	/**
	 * Returns the class name of the EPackageConfigurator
	 * 
	 * @param genPackage the generator package
	 * @return the class name
	 */
	private String getBasicConfiguratorClassName(GenPackage genPackage) {
		return genPackage.getPrefix() + "EPackageConfigurator";
	}

}
