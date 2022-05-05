package org.gecko.emf.osgi.codegen.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ResourceFactoryClass
{
  protected static String nl;
  public static synchronized ResourceFactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceFactoryClass result = new ResourceFactoryClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Resource Factory</b> associated with the package." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */" + NL + " @";
  protected final String TEXT_8 = "( name = ";
  protected final String TEXT_9 = ".eNAME + \"Factory\", service = Resource.Factory.class, scope = ";
  protected final String TEXT_10 = ".SINGLETON)" + NL + " @ProvideEMFResourceConfigurator( name = ";
  protected final String TEXT_11 = ".eNAME,";
  protected final String TEXT_12 = NL + "\tcontentType = { \"";
  protected final String TEXT_13 = "\" },";
  protected final String TEXT_14 = NL + "\tcontentType = { \"\" },";
  protected final String TEXT_15 = " ";
  protected final String TEXT_16 = NL + "\tfileExtension = {" + NL + "\t\"";
  protected final String TEXT_17 = "\"";
  protected final String TEXT_18 = "," + NL + " \t\"";
  protected final String TEXT_19 = NL + " \t},";
  protected final String TEXT_20 = " " + NL + "\tversion = \"1.0.0\"" + NL + ")";
  protected final String TEXT_21 = NL + "@Deprecated";
  protected final String TEXT_22 = NL + "public class ";
  protected final String TEXT_23 = " extends ";
  protected final String TEXT_24 = NL + "{";
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_26 = " copyright = ";
  protected final String TEXT_27 = ";";
  protected final String TEXT_28 = NL;
  protected final String TEXT_29 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_30 = " extendedMetaData;" + NL;
  protected final String TEXT_31 = " xmlMap = new ";
  protected final String TEXT_32 = "();" + NL;
  protected final String TEXT_33 = NL + "\t/**" + NL + "\t * Creates an instance of the resource factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_34 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_35 = NL + "\t\textendedMetaData = new ";
  protected final String TEXT_36 = "(new ";
  protected final String TEXT_37 = "(";
  protected final String TEXT_38 = ".Registry.INSTANCE));" + NL + "\t\textendedMetaData.putPackage(null, ";
  protected final String TEXT_39 = ".eINSTANCE);";
  protected final String TEXT_40 = NL + "\t\txmlMap.setNoNamespacePackage(";
  protected final String TEXT_41 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the resource." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_42 = NL + "\t@Override";
  protected final String TEXT_43 = NL + "\tpublic Resource createResource(URI uri)" + NL + "\t{";
  protected final String TEXT_44 = NL + "\t\t";
  protected final String TEXT_45 = " result = new ";
  protected final String TEXT_46 = "(uri);" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_47 = ".OPTION_EXTENDED_META_DATA, ";
  protected final String TEXT_48 = "Boolean.TRUE";
  protected final String TEXT_49 = "extendedMetaData";
  protected final String TEXT_50 = ");" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_51 = ");" + NL + "" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_52 = ".OPTION_SCHEMA_LOCATION, Boolean.TRUE);" + NL + "" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_53 = ".OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_54 = ".OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);" + NL + "" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_55 = ".OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);";
  protected final String TEXT_56 = NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_57 = ".OPTION_USE_DATA_CONVERTER, Boolean.TRUE);";
  protected final String TEXT_58 = ".OPTION_XML_MAP, xmlMap);" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_59 = ".OPTION_XML_MAP, xmlMap);";
  protected final String TEXT_60 = NL + "\t\tResource result = new ";
  protected final String TEXT_61 = "(uri);";
  protected final String TEXT_62 = NL + "\t\treturn result;";
  protected final String TEXT_63 = NL + "\t}" + NL + "" + NL + "} //";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_5);
    genModel.getImportedName("org.eclipse.emf.common.util.URI");
    genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource");
    genModel.addImport("org.gecko.emf.osgi.annotation.provide.ProvideEMFResourceConfigurator");
    genModel.addImport(genPackage.getQualifiedPackageInterfaceName());
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedResourceClassName());
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Component"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.ServiceScope"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_11);
    if (genPackage.getContentTypeIdentifier() != null) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_13);
    } else {
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    if (!genPackage.getFileExtensionList().isEmpty()) {
    Iterator<String> fileExtensionIterator = genPackage.getFileExtensionList().iterator(); if (fileExtensionIterator.hasNext()) { String fileExtension = fileExtensionIterator.next();
    stringBuffer.append(TEXT_16);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_17);
    while(fileExtensionIterator.hasNext()) { fileExtension = fileExtensionIterator.next();
    stringBuffer.append(TEXT_18);
    stringBuffer.append(fileExtension);
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_20);
    if (isJDK50 && genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getImportedResourceFactoryBaseClassName());
    stringBuffer.append(TEXT_24);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_28);
    }
    if (genPackage.hasExtendedMetaData() && !genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.ExtendedMetaData"));
    stringBuffer.append(TEXT_30);
    } else if (genPackage.hasXMLMap()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource$XMLMap"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.XMLMapImpl"));
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_34);
    if (genPackage.hasExtendedMetaData() && !genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicExtendedMetaData"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageRegistryImpl"));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_39);
    } else if (genPackage.hasXMLMap() && !genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_41);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_42);
    }
    stringBuffer.append(TEXT_43);
    if (genPackage.hasExtendedMetaData()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_47);
    if (genPackage.hasTargetNamespace()){
    stringBuffer.append(TEXT_48);
    }else{
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_47);
    if (genPackage.hasTargetNamespace()){
    stringBuffer.append(TEXT_48);
    }else{
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_55);
    if (genPackage.isDataTypeConverters() && genPackage.hasDocumentRoot()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_57);
    }
    } else if (genPackage.hasXMLMap()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_59);
    } else {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_61);
    }
    stringBuffer.append(TEXT_62);
    //ResourceFactoryClass/createResource.override.javajetinc
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_28);
    return stringBuffer.toString();
  }
}
