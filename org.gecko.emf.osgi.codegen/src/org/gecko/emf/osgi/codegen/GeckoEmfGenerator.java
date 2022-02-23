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
package org.gecko.emf.osgi.codegen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelFactoryImpl;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.emf.osgi.codegen.GeckoEmfGenerator.GeneratorOptions;
import org.gecko.emf.osgi.codegen.adapter.BNDGeneratorAdapterFactory;

import aQute.bnd.build.Container;
import aQute.bnd.osgi.Jar;
import aQute.bnd.service.externalplugin.ExternalPlugin;
import aQute.bnd.service.generate.BuildContext;
import aQute.bnd.service.generate.Generator;
import aQute.bnd.service.generate.Options;
import aQute.lib.io.IO;

@ExternalPlugin(name = "geckoEMF", objectClass = Generator.class)
public class GeckoEmfGenerator implements Generator<GeneratorOptions> {

	/** OUTPUT_DEFAULT */
	private static final String OUTPUT_DEFAULT = "src-gen"; //$NON-NLS-1$
	/** PROP_GENMODEL */
	private static final String PROP_GENMODEL = "genmodel"; //$NON-NLS-1$
	/** PROP_OUTPUT */
	private static final String PROP_OUTPUT = "output"; //$NON-NLS-1$
	/** PROP_LOGFILE */
	private static final String PROP_LOGFILE = "logfile"; //$NON-NLS-1$
	private static PrintStream logWriter;

	public static void info(String message) {
		if(logWriter != null) {
			logWriter.println("[INFO] " + message); //$NON-NLS-1$
			logWriter.flush();
		} else {
			System.out.println("[INFO] " + message); //$NON-NLS-1$
		}
	}

	public static void error(String message) {
		if(logWriter != null) {
			logWriter.println("[ERROR] " + message); //$NON-NLS-1$
			logWriter.flush();
		} else {
			System.err.println("[ERROR] " + message); //$NON-NLS-1$
		}
	}

	public static void error(String message, Throwable t) {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintWriter print = new PrintWriter(baos)){
			print.println("Something went wrong: " + t.getMessage()); //$NON-NLS-1$
			t.printStackTrace(print);
			String error = new String(baos.toByteArray());
			error(message + " - " + error); //$NON-NLS-1$
		} catch (IOException e) {
			error("Error printing results " + e.getMessage()); //$NON-NLS-1$
		}
	}

	private static void initializeLog(File base, String file) throws IOException {
		File logFile = new File(base, file);
		IO.delete(logFile);
		if (logFile.createNewFile()) {
			logWriter = new PrintStream(logFile);
		}
	}

	// We don't really use it.
	public interface GeneratorOptions extends Options {
		Optional<File> output();
	}

	public Optional<String> generate(
			BuildContext context, 
			GeneratorOptions options) throws Exception {
		try {
			if(context.get(PROP_LOGFILE) != null) {
				initializeLog(context.getBase(), context.get(PROP_LOGFILE));
			}
			String genFolder = context.get(PROP_OUTPUT);
			File output = null;
			if(genFolder != null) {
				output = context.getFile(genFolder);
			} else {
				output = context.getFile(OUTPUT_DEFAULT);
			}
			info("Output configured: " + genFolder);
			info("Output result: " + output);
			output.mkdirs();

			String genmodel = context.get(PROP_GENMODEL); 
			if(genmodel == null) {
				return Optional.of("genmodel attribute not set");
			}

			File genmodelFile = new File(context.getBase(), genmodel);

			if(!genmodelFile.exists()) {
				return Optional.of("No genmodel found at " + genmodelFile.getPath());
			}

			Collection<Container> buildpath = context.getProject().getBuildpath();
			Map<Container, List<String>> refModels = new HashMap<>(); 
			for(Container c : buildpath) {
				File f = c.getFile();
				try (Jar jar = new Jar(f)){
					List<String> models = jar
							.getResourceNames(s -> s.endsWith(".ecore") || s.endsWith(".genmodel"))
							.collect(Collectors.toList());
					refModels.put(c, models);
				}
			}
			return doGenerate(genFolder, genmodel, 
					refModels, 
					context.getBase(), 
					context.getParent()
					.toString());
		} catch (Exception e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter print = new PrintWriter(baos);
			print.println("Something went wrong: " + e.getMessage());
			e.printStackTrace(print);
			print.close();
			String error = new String(baos.toByteArray());
			baos.close();
			error(error);
			return Optional.of(error);
		} finally {
			closeLog();
		}
	}

	/**
	 * 
	 */
	private static void closeLog() {
		if(logWriter != null) {
			logWriter.close();
			logWriter = null;
		}
	}

	private void configureEMFGenerator(org.eclipse.emf.codegen.ecore.generator.Generator gen) {
		info("Configuring Jet");
		gen.getAdapterFactoryDescriptorRegistry().addDescriptor(GenModelPackage.eNS_URI, BNDGeneratorAdapterFactory.DESCRIPTOR);
	}

	private void configureEMF(ResourceSet resourceSet, Map<Container, List<String>> refModels, String bsn, File base) {

		GenModelPackageImpl.init();
		GenModelFactoryImpl.init();

		resourceSet.getURIConverter().getURIHandlers().add(0, new ResourceUriHandler(refModels, bsn, base));
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put(GenModelPackage.eCONTENT_TYPE, new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put("application/xmi", new XMIResourceFactoryImpl());
	}

	/**
	 * @param output
	 * @param genmodel
	 * @param genmodelFile
	 * @param refModels 
	 * @param string 
	 * @param file 
	 * @return
	 * @throws IOException
	 */
	public Optional<String> doGenerate(String output, String genmodel, Map<Container, List<String>> refModels, File base, String bsn) throws IOException {
		info("Running for genmodel " + genmodel + " in " + base.getAbsolutePath()); 
		ResourceSet resourceSet = new ResourceSetImpl();

		configureEMF(resourceSet, refModels, bsn, base);
		URI genModelUri = URI.createURI("resource://" + bsn + "/" + genmodel);
		Resource resource = resourceSet.getResource(genModelUri, true);

		if(!resource.getErrors().isEmpty()) {
			return Optional.of(resource.getErrors().get(0).toString());
		}

		GenModel genModel = (GenModel) resource.getContents().get(0);
		org.eclipse.emf.codegen.ecore.generator.Generator gen = new org.eclipse.emf.codegen.ecore.generator.Generator();
		configureEMFGenerator(gen);

		genModel.setModelDirectory("/" + bsn + (output.startsWith("/") ? "" : "/") + output);
		gen.setInput(genModel);
		genModel.setCanGenerate(true);
		genModel.setUpdateClasspath(false);

		try(PrintStream tmpStream = new PrintStream(new ByteArrayOutputStream())) {

			Diagnostic diagnostic = gen.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, CodeGenUtil.EclipseUtil.createMonitor(new LoggingProgressMonitor(), 1));
			printResult(diagnostic);
			if(diagnostic.getSeverity() != Diagnostic.OK) {
				return Optional.of(diagnostic.toString());
			} 
		}
		return Optional.empty();
	}

	/**
	 * @param diagnostic
	 */
	private void printResult(Diagnostic diagnostic) {
		printResult(diagnostic, ""); //$NON-NLS-1$
	}
	private void printResult(Diagnostic diagnostic, String prefix) {
		if(diagnostic.getSeverity() == Diagnostic.OK) {
			info(prefix + diagnostic.getMessage() + " - " + diagnostic.getSource()); //$NON-NLS-1$
		} else {
			error(prefix + diagnostic.getMessage() + " - " + diagnostic.getSource()); //$NON-NLS-1$
			if(diagnostic.getException() != null) {
				error(prefix, diagnostic.getException());
			}
		}
		diagnostic.getChildren().forEach(c -> printResult(c, prefix + "  "));
	}

	public static class LoggingProgressMonitor implements IProgressMonitor{

		private String name;


		@Override
		public void beginTask(String name, int totalWork) {
			info("beginTask " + name);
		}


		@Override
		public void done() {
			info("done");
		}


		@Override
		public void internalWorked(double work) {
			info("internaly worked " + work + " on " + name);
		}


		@Override
		public boolean isCanceled() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public void setCanceled(boolean value) {
			info(name + " cancled");
		}


		@Override
		public void setTaskName(String name) {
			this.name = name;

		}


		@Override
		public void subTask(String name) {
			info("subtask " + name);

		}


		@Override
		public void worked(int work) {
			info(" worked " + work + " on " + name);
		}

	}

}