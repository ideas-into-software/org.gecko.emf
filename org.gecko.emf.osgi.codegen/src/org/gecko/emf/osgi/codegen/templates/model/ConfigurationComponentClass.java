package org.gecko.emf.osgi.codegen.templates.model;

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
  protected final String TEXT_10 = "Configurator\", service= EPackageConfigurator.class)" + NL + "@EMFModel(name=";
  protected final String TEXT_11 = ".eNAME, nsURI={";
  protected final String TEXT_12 = ".eNS_URI}, version=\"1.0.0\")" + NL + "@RequireEMF" + NL + "@ProvideEMFModel(name = ";
  protected final String TEXT_13 = ".eNAME, nsURI = { ";
  protected final String TEXT_14 = ".eNS_URI }, version = \"1.0.0\")" + NL + "public class ";
  protected final String TEXT_15 = "ConfigurationComponent implements EPackageConfigurator" + NL + "{" + NL + "\tprivate ";
  protected final String TEXT_16 = "<?> packageRegistration = null;" + NL + "\t" + NL + "\t@";
  protected final String TEXT_17 = NL + "\tpublic void activate(BundleContext ctx) {" + NL + "\t\t";
  protected final String TEXT_18 = " p = ";
  protected final String TEXT_19 = ".init();" + NL + "\t\tif(p == null){" + NL + "\t\t\tp= ";
  protected final String TEXT_20 = ".eINSTANCE;" + NL + "\t\t\tEPackage.Registry.INSTANCE.put(";
  protected final String TEXT_21 = ".eNS_URI,p);" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_22 = "<String, Object> properties = new ";
  protected final String TEXT_23 = "<String, Object>();" + NL + "\t\tproperties.put(EMFNamespaces.EMF_MODEL_NAME, ";
  protected final String TEXT_24 = ".eNAME);" + NL + "\t\tproperties.put(EMFNamespaces.EMF_MODEL_NSURI, ";
  protected final String TEXT_25 = ".eNS_URI);" + NL + "\t\tproperties.put(EMFNamespaces.EMF_MODEL_FILE_EXT, \"";
  protected final String TEXT_26 = "\");";
  protected final String TEXT_27 = NL + "\t\tproperties.put(EMFNamespaces.EMF_MODEL_CONTENT_TYPE, \"";
  protected final String TEXT_28 = NL + "\t\tString[] serviceClasses = new String[] {";
  protected final String TEXT_29 = ".class.getName(), ";
  protected final String TEXT_30 = ".class.getName()};" + NL + "\t\tpackageRegistration = ctx.registerService(serviceClasses, p, properties);" + NL + "\t}" + NL + "" + NL + "\t" + NL + "\t/* " + NL + "\t * (non-Javadoc)" + NL + "\t * @see org.gecko.emf.osgi.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {" + NL + "\t\tregistry.put(";
  protected final String TEXT_31 = ".eNS_URI, ";
  protected final String TEXT_32 = ".init());" + NL + "\t}" + NL + "\t" + NL + "\t/* " + NL + "\t * (non-Javadoc)" + NL + "\t * @see org.gecko.emf.osgi.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {" + NL + "\t\tregistry.remove(";
  protected final String TEXT_33 = ".eNS_URI);" + NL + "\t}" + NL + "\t" + NL + "\t@";
  protected final String TEXT_34 = NL + "\tpublic void deactivate() {" + NL + "\t\tEPackage.Registry.INSTANCE.remove(";
  protected final String TEXT_35 = ".eNS_URI);" + NL + "\t\tif(packageRegistration != null){" + NL + "\t\t\tpackageRegistration.unregister();" + NL + "\t\t}" + NL + "\t}" + NL + "}";
  protected final String TEXT_36 = NL;

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
    genModel.addImport("org.gecko.emf.osgi.EMFNamespaces");
    genModel.addImport("org.gecko.emf.osgi.EPackageConfigurator");
    genModel.addImport("org.gecko.emf.osgi.annotation.EMFModel");
    genModel.addImport("org.gecko.emf.osgi.annotation.provide.ProvideEMFModel");
    genModel.addImport("org.gecko.emf.osgi.annotation.require.RequireEMF");
    genModel.addImport(genPackage.getQualifiedPackageInterfaceName());
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
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Activate"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getImportedName("java.util.Dictionary"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("java.util.Hashtable"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getFileExtension());
    stringBuffer.append(TEXT_26);
     if (genPackage.getContentTypeIdentifier() != null) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_26);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Deactivate"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_35);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_36);
    return stringBuffer.toString();
  }
}
