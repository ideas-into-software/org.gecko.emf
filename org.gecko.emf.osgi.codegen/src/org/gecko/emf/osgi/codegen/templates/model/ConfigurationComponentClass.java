package org.gecko.emf.osgi.codegen.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.ecore.EFactory;
import org.gecko.emf.osgi.EPackageConfigurator;
import java.util.Hashtable;
import org.osgi.service.condition.Condition;

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
  protected final String TEXT_6 = NL + "/**" + NL + " * The <b>PackageConfiguration</b> for the model." + NL + " * The package will be registered into a OSGi base model registry." + NL + " * " + NL + " * @generated" + NL + " */" + NL + "@Component(name = \"";
  protected final String TEXT_7 = "Configurator\")" + NL + "@";
  protected final String TEXT_8 = "( namespace = \"osgi.service\", attribute = { \"objectClass:List<String>=\\\"";
  protected final String TEXT_9 = ", org.eclipse.emf.ecore.EFactory\\\"\" , \"uses:=org.eclipse.emf.ecore,";
  protected final String TEXT_10 = "\" })" + NL + "@";
  protected final String TEXT_11 = ", org.eclipse.emf.ecore.EPackage\\\"\" , \"uses:=org.eclipse.emf.ecore,";
  protected final String TEXT_12 = "\\\"\" , \"uses:=org.eclipse.emf.ecore,";
  protected final String TEXT_13 = "\\\"\" , \"uses:=org.osgi.service.condition\" })" + NL + "public class ";
  protected final String TEXT_14 = "ConfigurationComponent" + NL + "{" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_15 = "<?> packageRegistration = null;" + NL + "\tprivate ";
  protected final String TEXT_16 = "<";
  protected final String TEXT_17 = "> ePackageConfiguratorRegistration = null;" + NL + "\tprivate ";
  protected final String TEXT_18 = "<?> eFactoryRegistration = null;" + NL + "\tprivate ";
  protected final String TEXT_19 = "<?> conditionRegistration = null;";
  protected final String TEXT_20 = NL + "\tprivate ";
  protected final String TEXT_21 = "<?> resourceFactoryRegistration = null;";
  protected final String TEXT_22 = NL + NL + "\t/**" + NL + "\t * Activates the Configuration Component." + NL + "\t *" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@";
  protected final String TEXT_23 = NL + "\tpublic void activate(BundleContext ctx) {" + NL + "\t\t";
  protected final String TEXT_24 = " ePackage = ";
  protected final String TEXT_25 = ".eINSTANCE;" + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_26 = "EPackageConfigurator packageConfigurator = registerEPackageConfiguratorService(ePackage, ctx);";
  protected final String TEXT_27 = NL + "\t\tregisterResourceFactoryService(ctx);";
  protected final String TEXT_28 = NL + "\t\tregisterEPackageService(ePackage, packageConfigurator, ctx);" + NL + "\t\tregisterEFactoryService(ePackage, packageConfigurator, ctx);" + NL + "\t\tregisterConditionService(packageConfigurator, ctx);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * Registers the ";
  protected final String TEXT_29 = "EPackageConfigurator as a service." + NL + "\t *" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_30 = "EPackageConfigurator registerEPackageConfiguratorService(";
  protected final String TEXT_31 = " ePackage, BundleContext ctx){" + NL + "\t\t";
  protected final String TEXT_32 = "EPackageConfigurator packageConfigurator = new ";
  protected final String TEXT_33 = "EPackageConfigurator(ePackage);" + NL + "\t\t// register the EPackageConfigurator" + NL + "\t\t";
  protected final String TEXT_34 = "<String, Object> properties = new ";
  protected final String TEXT_35 = "<String, Object>();" + NL + "\t\tproperties.putAll(packageConfigurator.getServiceProperties());" + NL + "\t\tePackageConfiguratorRegistration = ctx.registerService(";
  protected final String TEXT_36 = ".class, packageConfigurator, properties);" + NL + "" + NL + "\t\treturn packageConfigurator;" + NL + "\t}" + NL;
  protected final String TEXT_37 = NL + "\t/**" + NL + "\t * Registers the ";
  protected final String TEXT_38 = " as a service." + NL + "\t *" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate void registerResourceFactoryService(BundleContext ctx){" + NL + "\t\t";
  protected final String TEXT_39 = " factory = new ";
  protected final String TEXT_40 = "();" + NL + "\t\tHashtable<String, Object> properties = new Hashtable<String, Object>();" + NL + "\t\tproperties.putAll(factory.getServiceProperties());" + NL + "\t\tString[] serviceClasses = new String[] {";
  protected final String TEXT_41 = ".class.getName(), Factory.class.getName()};" + NL + "\t\tresourceFactoryRegistration = ctx.registerService(serviceClasses, factory, properties);" + NL + "\t}";
  protected final String TEXT_42 = NL + NL + "\t/**" + NL + "\t * Registers the ";
  protected final String TEXT_43 = " as a service." + NL + "\t *" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate void registerEPackageService(";
  protected final String TEXT_44 = " ePackage, ";
  protected final String TEXT_45 = "EPackageConfigurator packageConfigurator, BundleContext ctx){" + NL + "\t\t";
  protected final String TEXT_46 = "<String, Object>();" + NL + "\t\tproperties.putAll(packageConfigurator.getServiceProperties());" + NL + "\t\tString[] serviceClasses = new String[] {";
  protected final String TEXT_47 = ".class.getName(), ";
  protected final String TEXT_48 = ".class.getName()};" + NL + "\t\tpackageRegistration = ctx.registerService(serviceClasses, ePackage, properties);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Registers the ";
  protected final String TEXT_49 = " as a service." + NL + "\t *" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate void registerEFactoryService(";
  protected final String TEXT_50 = ".class.getName()};" + NL + "\t\teFactoryRegistration = ctx.registerService(serviceClasses, ePackage.get";
  protected final String TEXT_51 = "(), properties);" + NL + "\t}" + NL + "" + NL + "\tprivate void registerConditionService(";
  protected final String TEXT_52 = "EPackageConfigurator packageConfigurator, BundleContext ctx){" + NL + "\t\t// register the EPackage" + NL + "\t\t";
  protected final String TEXT_53 = "<String, Object>();" + NL + "\t\tproperties.putAll(packageConfigurator.getServiceProperties());" + NL + "\t\tproperties.put(";
  protected final String TEXT_54 = ".CONDITION_ID, ";
  protected final String TEXT_55 = ".eNS_URI);" + NL + "\t\tconditionRegistration = ctx.registerService(";
  protected final String TEXT_56 = ".class, ";
  protected final String TEXT_57 = ".INSTANCE, properties);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Deactivates and unregisters everything." + NL + "\t *" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@";
  protected final String TEXT_58 = NL + "\tpublic void deactivate() {" + NL + "\t\tconditionRegistration.unregister();" + NL + "\t\teFactoryRegistration.unregister();" + NL + "\t\tpackageRegistration.unregister();";
  protected final String TEXT_59 = NL + "\t\tresourceFactoryRegistration.unregister();";
  protected final String TEXT_60 = NL + NL + "\t\tePackageConfiguratorRegistration.unregister();" + NL + "\t\tEPackage.Registry.INSTANCE.remove(";
  protected final String TEXT_61 = ".eNS_URI);" + NL + "\t}" + NL + "}";
  protected final String TEXT_62 = NL;

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
    genModel.addImport(genPackage.getQualifiedPackageInterfaceName());
    genModel.addImport(genPackage.getQualifiedFactoryInterfaceName());
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    genModel.addImport(genPackage.getQualifiedResourceFactoryClassName());
    genModel.addImport("org.eclipse.emf.ecore.resource.Resource.Factory");
    }
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getImportedName("org.osgi.annotation.bundle.Capability"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getQualifiedFactoryInterfaceName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("org.osgi.annotation.bundle.Capability"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("org.osgi.annotation.bundle.Capability"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(EPackageConfigurator.class.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("org.osgi.annotation.bundle.Capability"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(Condition.class.getName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName("org.gecko.emf.osgi.EPackageConfigurator"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_19);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName("org.osgi.framework.ServiceRegistration"));
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Activate"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageClassName()));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_26);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName(EPackageConfigurator.class.getName()));
    stringBuffer.append(TEXT_36);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName(EFactory.class.getName()));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName(Hashtable.class.getName()));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getImportedName(Condition.class.getName()));
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getImportedName(Condition.class.getName()));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getImportedName(Condition.class.getName()));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Deactivate"));
    stringBuffer.append(TEXT_58);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_59);
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_61);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_62);
    return stringBuffer.toString();
  }
}
