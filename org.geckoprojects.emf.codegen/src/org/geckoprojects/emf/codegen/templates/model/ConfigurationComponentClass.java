package org.geckoprojects.emf.codegen.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ConfigurationComponentClass
{
  protected static String nl;
  public static synchronized ConfigurationComponentClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ConfigurationComponentClass result = new ConfigurationComponentClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ".configuration;" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>EPackageConfiguration</b> and <b>ResourceFactoryConfigurator</b> for the model." + NL + " * The package will be registered into a OSGi base model registry." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_7 = NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_8 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_9 = NL + " * @see EPackageConfigurator" + NL + " * @see ResourceFactoryConfigurator" + NL + " * @generated" + NL + " */" + NL + "@Component(name=\"";
  protected final String TEXT_10 = "Configurator\", service= {EPackageConfigurator.class, ResourceFactoryConfigurator.class})" + NL + "@EMFModel(name=";
  protected final String TEXT_11 = ".eNAME, nsURI={";
  protected final String TEXT_12 = ".eNS_URI}, version=\"1.0.0\")" + NL + "@RequireEMF" + NL + "@ProvideEMFModel(name = ";
  protected final String TEXT_13 = ".eNAME, nsURI = { ";
  protected final String TEXT_14 = ".eNS_URI }, version = \"1.0.0\")" + NL + "@ProvideEMFResourceConfigurator( name = ";
  protected final String TEXT_15 = ".eNAME,";
  protected final String TEXT_16 = NL + "\tcontentType = { \"";
  protected final String TEXT_17 = "\" },";
  protected final String TEXT_18 = NL + "\tcontentType = { \"\" },";
  protected final String TEXT_19 = " ";
  protected final String TEXT_20 = NL + "\tfileExtension = {" + NL + "\t\"";
  protected final String TEXT_21 = "\"";
  protected final String TEXT_22 = "," + NL + " \t\"";
  protected final String TEXT_23 = NL + " \t},";
  protected final String TEXT_24 = " " + NL + "\tversion = \"1.0.0\"" + NL + ")" + NL + "public class ";
  protected final String TEXT_25 = "ConfigurationComponent implements EPackageConfigurator, ResourceFactoryConfigurator" + NL + "{" + NL + "\tprivate ";
  protected final String TEXT_26 = "<?> packageRegistration = null;" + NL + "\t" + NL + "\t@";
  protected final String TEXT_27 = NL + "\tpublic void activate(BundleContext ctx) {" + NL + "\t\t";
  protected final String TEXT_28 = " p = ";
  protected final String TEXT_29 = ".init();" + NL + "\t\tif(p == null){" + NL + "\t\t\tp= ";
  protected final String TEXT_30 = ".eINSTANCE;" + NL + "\t\t\tEPackage.Registry.INSTANCE.put(";
  protected final String TEXT_31 = ".eNS_URI,p);" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_32 = "<String, Object> properties = new ";
  protected final String TEXT_33 = "<String, Object>();" + NL + "\t\tproperties.put(\"emf.model.name\", ";
  protected final String TEXT_34 = ".eNAME);" + NL + "\t\tproperties.put(\"emf.model.nsURI\", ";
  protected final String TEXT_35 = ".eNS_URI);" + NL + "\t\tproperties.put(\"fileExtension\", \"";
  protected final String TEXT_36 = "\");";
  protected final String TEXT_37 = NL + "\t\tproperties.put(\"contentType\", \"";
  protected final String TEXT_38 = NL + "\t\tString[] serviceClasses = new String[] {";
  protected final String TEXT_39 = ".class.getName(), ";
  protected final String TEXT_40 = ".class.getName()};" + NL + "\t\tpackageRegistration = ctx.registerService(serviceClasses, p, properties);" + NL + "\t}" + NL + "" + NL + "\t/* " + NL + "\t * (non-Javadoc)" + NL + "\t * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#configureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void configureResourceFactory(Registry registry) {" + NL + "\t\t";
  protected final String TEXT_41 = "registry.getExtensionToFactoryMap().put(\"";
  protected final String TEXT_42 = "\", new ";
  protected final String TEXT_43 = "());";
  protected final String TEXT_44 = " " + NL + "\t\t";
  protected final String TEXT_45 = "registry.getContentTypeToFactoryMap().put(\"";
  protected final String TEXT_46 = " " + NL + "\t}" + NL + "\t" + NL + "\t/* " + NL + "\t * (non-Javadoc)" + NL + "\t * @see org.geckoprojects.emf.core.ResourceFactoryConfigurator#unconfigureResourceFactory(org.eclipse.emf.ecore.resource.Resource.Factory.Registry)" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void unconfigureResourceFactory(Registry registry) {" + NL + "\t\t";
  protected final String TEXT_47 = "registry.getExtensionToFactoryMap().remove(\"";
  protected final String TEXT_48 = "registry.getContentTypeToFactoryMap().remove(\"";
  protected final String TEXT_49 = " " + NL + "\t}" + NL + "\t" + NL + "\t/* " + NL + "\t * (non-Javadoc)" + NL + "\t * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {" + NL + "\t\tregistry.put(";
  protected final String TEXT_50 = ".eNS_URI, ";
  protected final String TEXT_51 = ".init());" + NL + "\t}" + NL + "\t" + NL + "\t/* " + NL + "\t * (non-Javadoc)" + NL + "\t * @see org.geckoprojects.emf.core.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {" + NL + "\t\tregistry.remove(";
  protected final String TEXT_52 = ".eNS_URI);" + NL + "\t}" + NL + "\t" + NL + "\t@";
  protected final String TEXT_53 = NL + "\tpublic void deactivate() {" + NL + "\t\tEPackage.Registry.INSTANCE.remove(";
  protected final String TEXT_54 = ".eNS_URI);" + NL + "\t\tif(packageRegistration != null){" + NL + "\t\t\tpackageRegistration.unregister();" + NL + "\t\t}" + NL + "\t}" + NL + "}";
  protected final String TEXT_55 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    final GenPackage genPackage = (GenPackage)((Object[])argument)[0]; final GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer, genPackage);
    genModel.addImport("org.osgi.service.component.annotations.Component");
    genModel.addImport("org.osgi.service.component.annotations.Deactivate");
    genModel.addImport("org.osgi.framework.BundleContext");
    genModel.addImport("org.eclipse.emf.ecore.resource.Resource.Factory.Registry");
    genModel.addImport("org.geckoprojects.emf.core.api.EPackageConfigurator");
    genModel.addImport("org.geckoprojects.emf.core.api.ResourceFactoryConfigurator");
    genModel.addImport("org.geckoprojects.emf.core.api.annotation.EMFModel");
    genModel.addImport("org.geckoprojects.emf.core.api.annotation.provide.ProvideEMFModel");
    genModel.addImport("org.geckoprojects.emf.core.api.annotation.provide.ProvideEMFResourceConfigurator");
    genModel.addImport("org.geckoprojects.emf.core.api.annotation.require.RequireEMF");
    genModel.addImport(genPackage.getQualifiedPackageInterfaceName());
    if (!GenResourceKind.NONE_LITERAL.equals(genPackage.getResource())) {
    genModel.addImport(genPackage.getQualifiedResourceFactoryClassName());
    }
    stringBuffer.append(TEXT_6);
    if (genPackage.hasDocumentation()) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_15);
    if (genPackage.getContentTypeIdentifier() != null) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_17);
    } else {
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    if (!genPackage.getFileExtensionList().isEmpty()) {
    Iterator<String> fileExtensionIterator = genPackage.getFileExtensionList().iterator(); if (fileExtensionIterator.hasNext()) { String fileExtension = fileExtensionIterator.next();
    stringBuffer.append(TEXT_20);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_21);
    while(fileExtensionIterator.hasNext()) { fileExtension = fileExtensionIterator.next();
    stringBuffer.append(TEXT_22);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Activate"));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName("java.util.Dictionary"));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getImportedName("java.util.Hashtable"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genPackage.getFileExtension());
    stringBuffer.append(TEXT_36);
     if (genPackage.getContentTypeIdentifier() != null) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_40);
    if (!genPackage.getFileExtensionList().isEmpty() && !GenResourceKind.NONE_LITERAL.equals(genPackage.getResource())) {
    for (String fileExtension : genPackage.getFileExtensionList()) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_43);
    }
    }
    stringBuffer.append(TEXT_44);
    if (genPackage.getContentTypeIdentifier() != null && !GenResourceKind.NONE_LITERAL.equals(genPackage.getResource())) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_46);
    if (!genPackage.getFileExtensionList().isEmpty() && !GenResourceKind.NONE_LITERAL.equals(genPackage.getResource())) {
    for (String fileExtension : genPackage.getFileExtensionList()) {
    stringBuffer.append(TEXT_47);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_36);
    }
    }
    stringBuffer.append(TEXT_44);
    if (genPackage.getContentTypeIdentifier() != null && !GenResourceKind.NONE_LITERAL.equals(genPackage.getResource())) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Deactivate"));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_54);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_55);
    return stringBuffer.toString();
  }
}
