package org.geckoprojects.emf.codegen.asn1;

import java.io.IOException;
import java.util.Collections;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class Main {
	public void generate(final String pathToOutputFile) throws IOException {
		// it is very important that we do everything through ResourceSet's
		ResourceSet resourceSet = new ResourceSetImpl();

		// next we will create our own new package to contained what we will generate
		final EPackage newPackage = createPackage("customerDB", "customerDB", "http://customerDB");

		// next, create the row class
		EClass customerRow = createEClass("CustomerRow");
		// add to package before we do anything else
		newPackage.getEClassifiers().add(customerRow);
		// add our features
		addAttribute(customerRow, "id", EcorePackage.Literals.ESTRING, true, 1, 1);
		addAttribute(customerRow, "firstName", EcorePackage.Literals.ESTRING, false, 0, 1);
		addAttribute(customerRow, "lastName", EcorePackage.Literals.ESTRING, false, 0, 1);

		// next, create the table class
		EClass customers = createEClass("Customers");
		// add to package before we do anything else
		newPackage.getEClassifiers().add(customers);
		// add our features
		addReference(customers, "rows", customerRow, 0, -1);

		// now create a new resource to serialize the ecore model
		URI uri = URI.createFileURI(pathToOutputFile);
		System.out.println(uri);
		Resource outputRes = resourceSet.createResource(uri);
		// add our new package to resource contents
		outputRes.getContents().add(newPackage);
		// and at last, we save to standard out. Remove the first argument to save to
		// file specified in pathToOutputFile
		outputRes.save(System.out, Collections.emptyMap());
	}

	private void addAttribute(EClass customerRow, String name, EClassifier type, boolean isId, int lowerBound,
			int upperBound) {
		final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
		// always add to container first
		customerRow.getEStructuralFeatures().add(attribute);
		attribute.setName(name);
		attribute.setEType(type);
		attribute.setID(isId);
		attribute.setLowerBound(lowerBound);
		attribute.setUpperBound(upperBound);
	}

	private void addReference(EClass customerRow, String name, EClassifier type, int lowerBound, int upperBound) {
		final EReference reference = EcoreFactory.eINSTANCE.createEReference();
		// always add to container first
		customerRow.getEStructuralFeatures().add(reference);
		reference.setName(name);
		reference.setEType(type);
		reference.setLowerBound(lowerBound);
		reference.setUpperBound(upperBound);
	}

	private EPackage createPackage(final String name, final String prefix, final String uri) {
		final EPackage epackage = EcoreFactory.eINSTANCE.createEPackage();
		epackage.setName(name);
		epackage.setNsPrefix(prefix);
		epackage.setNsURI(uri);
		return epackage;
	}

	private EClass createEClass(final String name) {
		final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		return eClass;
	}

	public static void main(String[] args) throws IOException {

		final String outputPath = "./foo.ecore";

		// note: these are extra steps that are need from main, but not if running
		// inside Eclipse
		initStandalone();

		new Main().generate(outputPath);
	}

	private static void initStandalone() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		// NOTE: It is very important that you DO NOT make the call to any
		// EPackage.eINSTANCE's here. Unlike in the FAQ example, we want to be sure
		// that all non-plugin EPackages are loaded directly from ecore files, not
		// generated
		// Java classes.
	}
}