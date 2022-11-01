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
package org.gecko.emf.osgi.codegen.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class XMLProcessorClass
{
  protected static String nl;
  public static synchronized XMLProcessorClass create(String lineSeparator)
  {
    nl = lineSeparator;
    XMLProcessorClass result = new XMLProcessorClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * This class contains helper methods to serialize and deserialize XML documents" + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */";
  protected final String TEXT_8 = NL + "@Deprecated";
  protected final String TEXT_9 = NL + "@";
  protected final String TEXT_10 = "( name = ";
  protected final String TEXT_11 = ".eNAME + \"XMLProcessor\", service = ";
  protected final String TEXT_12 = ".class, scope = ";
  protected final String TEXT_13 = ".SINGLETON)" + NL + "public class ";
  protected final String TEXT_14 = " extends ";
  protected final String TEXT_15 = NL + "{";
  protected final String TEXT_16 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_17 = " copyright = ";
  protected final String TEXT_18 = ";";
  protected final String TEXT_19 = NL;
  protected final String TEXT_20 = NL + NL + "\t@";
  protected final String TEXT_21 = NL + "\tprivate ";
  protected final String TEXT_22 = " resourceFactory; " + NL;
  protected final String TEXT_23 = NL + NL + "\t/**" + NL + "\t * Public constructor to instantiate the helper." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@";
  protected final String TEXT_24 = NL + "\tpublic ";
  protected final String TEXT_25 = "(" + NL + "\t\t@";
  protected final String TEXT_26 = "(name = \"EPackageRegistry\", target = \"(component.name=DefaultEPackageRegistry)\") ";
  protected final String TEXT_27 = ".Registry registry";
  protected final String TEXT_28 = ",";
  protected final String TEXT_29 = NL + "\t\t@";
  protected final String TEXT_30 = "(name = ";
  protected final String TEXT_31 = ".eName) ";
  protected final String TEXT_32 = " the";
  protected final String TEXT_33 = NL + "\t\t)" + NL + "\t{" + NL + "\t\tsuper(new ";
  protected final String TEXT_34 = "(registry));" + NL + "\t\textendedMetaData.putPackage(null, the";
  protected final String TEXT_35 = ");";
  protected final String TEXT_36 = NL + "\t\t)" + NL + "\t{" + NL + "\t\tsuper(registry);";
  protected final String TEXT_37 = NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * Register for \"*\" and \"xml\" file extensions the ";
  protected final String TEXT_38 = " factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_39 = NL + "\t@Override";
  protected final String TEXT_40 = NL + "\tprotected Map";
  protected final String TEXT_41 = "<";
  protected final String TEXT_42 = ", ";
  protected final String TEXT_43 = ".Factory>";
  protected final String TEXT_44 = " getRegistrations()" + NL + "\t{" + NL + "\t\tif (registrations == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getRegistrations();" + NL + "\t\t\tregistrations.put(XML_EXTENSION, resourceFactory);" + NL + "\t\t\tregistrations.put(STAR_EXTENSION, resourceFactory);" + NL + "\t\t}" + NL + "\t\treturn registrations;" + NL + "\t}" + NL + "" + NL + "} //";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2005-2006 IBM Corporation and others. 
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
    genModel.getImportedName("java.util.Map");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_7);
    if (isJDK50 && genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Component"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName(genPackage.getQualifiedPackageInterfaceName()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genPackage.getXMLProcessorClassName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.ServiceScope"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getXMLProcessorClassName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getImportedXMLProcessorBaseClassName());
    stringBuffer.append(TEXT_15);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Reference"));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_22);
     boolean special = (genPackage.hasExtendedMetaData() && !genPackage.hasTargetNamespace()); 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Activate"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getXMLProcessorClassName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Reference"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_27);
    if (special) {
    stringBuffer.append(TEXT_28);
    }
    if (special) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.osgi.service.component.annotations.Reference"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageRegistryImpl"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_35);
    } else {
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_38);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource"));
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getXMLProcessorClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}
