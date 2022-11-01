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

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;

public class ValidatorClass
{
  protected static String nl;
  public static synchronized ValidatorClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ValidatorClass result = new ValidatorClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Validator</b> for the model." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */";
  protected final String TEXT_8 = NL + "@Deprecated";
  protected final String TEXT_9 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_10 = NL + "public class ";
  protected final String TEXT_11 = " extends ";
  protected final String TEXT_12 = NL + "{";
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_14 = " copyright = ";
  protected final String TEXT_15 = ";";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL + "\t" + NL + "\t/**" + NL + "\t * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.common.util.Diagnostic#getSource()" + NL + "\t * @see org.eclipse.emf.common.util.Diagnostic#getCode()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final String DIAGNOSTIC_SOURCE = \"";
  protected final String TEXT_18 = "\";";
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint '";
  protected final String TEXT_20 = "' of '";
  protected final String TEXT_21 = "'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final int ";
  protected final String TEXT_22 = " = ";
  protected final String TEXT_23 = NL + "\t/**" + NL + "\t * A constant with a fixed name that can be used as the base value for additional hand written constants." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int GENERATED_DIAGNOSTIC_CODE_COUNT = ";
  protected final String TEXT_24 = ";" + NL + "" + NL + "\t/**" + NL + "\t * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;" + NL;
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * The cached base package validator." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = "Validator;" + NL;
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * Delegates evaluation of the given invariant expression against the object in the given context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static boolean validate(";
  protected final String TEXT_29 = " eClass, ";
  protected final String TEXT_30 = " eObject, DiagnosticChain diagnostics, ";
  protected final String TEXT_31 = " context, ";
  protected final String TEXT_32 = " validationDelegate, ";
  protected final String TEXT_33 = " invariant, ";
  protected final String TEXT_34 = " expression, int severity, ";
  protected final String TEXT_35 = " source, int code)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_36 = ".validate(eClass, eObject, diagnostics, context, validationDelegate, invariant, expression, severity, source, code);" + NL + "\t}" + NL;
  protected final String TEXT_37 = NL + NL + "\tprivate final ";
  protected final String TEXT_38 = " ePackage;" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_39 = "(";
  protected final String TEXT_40 = " ePackage)" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t\tthis.ePackage = ePackage;";
  protected final String TEXT_41 = NL + "\t\t";
  protected final String TEXT_42 = "Validator = ";
  protected final String TEXT_43 = ".INSTANCE;";
  protected final String TEXT_44 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the package of this validator switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_45 = NL + "\t@Override";
  protected final String TEXT_46 = NL + "\tprotected EPackage getEPackage()" + NL + "\t{" + NL + "\t  return ePackage;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>validateXXX</code> for the corresponding classifier of the model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_47 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_48 = NL + "\tprotected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, ";
  protected final String TEXT_49 = " context)" + NL + "\t{" + NL + "\t\tswitch (classifierID)" + NL + "\t\t{";
  protected final String TEXT_50 = NL + "\t\t\tcase ";
  protected final String TEXT_51 = ".";
  protected final String TEXT_52 = ":";
  protected final String TEXT_53 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_54 = "(((";
  protected final String TEXT_55 = ")value).";
  protected final String TEXT_56 = "(), diagnostics, context);";
  protected final String TEXT_57 = "((";
  protected final String TEXT_58 = ")value, diagnostics, context);";
  protected final String TEXT_59 = "(value, diagnostics, context);";
  protected final String TEXT_60 = NL + "\t\t\tdefault:" + NL + "\t\t\t\treturn true;" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_61 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_62 = NL + "\t * ";
  protected final String TEXT_63 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_64 = NL + "\t@Deprecated";
  protected final String TEXT_65 = NL + "\tpublic boolean validate";
  protected final String TEXT_66 = ", DiagnosticChain ";
  protected final String TEXT_67 = ", ";
  protected final String TEXT_68 = ")" + NL + "\t{";
  protected final String TEXT_69 = NL + "\t\treturn true;";
  protected final String TEXT_70 = NL + "\t\treturn validate_EveryDefaultConstraint(";
  protected final String TEXT_71 = ")";
  protected final String TEXT_72 = ");";
  protected final String TEXT_73 = NL + "\t\tif (!validate_NoCircularContainment(";
  protected final String TEXT_74 = ")) return false;";
  protected final String TEXT_75 = NL + "\t\tboolean ";
  protected final String TEXT_76 = "validate";
  protected final String TEXT_77 = "_";
  protected final String TEXT_78 = NL + "\t\tif (";
  protected final String TEXT_79 = " || ";
  protected final String TEXT_80 = " != null) ";
  protected final String TEXT_81 = " &= ";
  protected final String TEXT_82 = NL + "\t\treturn ";
  protected final String TEXT_83 = NL + "\t}" + NL;
  protected final String TEXT_84 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_85 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_86 = "__VALUE = ";
  protected final String TEXT_87 = "__UPPER_BOUND = ";
  protected final String TEXT_88 = ";" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_89 = "__LOWER_BOUND = ";
  protected final String TEXT_90 = NL + "\t */" + NL + "\tpublic static final  PatternMatcher [][] ";
  protected final String TEXT_91 = "__VALUES =" + NL + "\t\tnew PatternMatcher [][]" + NL + "\t\t{";
  protected final String TEXT_92 = NL + "\t\t\tnew PatternMatcher []" + NL + "\t\t\t{";
  protected final String TEXT_93 = NL + "\t\t\t\t";
  protected final String TEXT_94 = ".createPatternMatcher(";
  protected final String TEXT_95 = NL + "\t\t\t}";
  protected final String TEXT_96 = NL + "\t\t};" + NL;
  protected final String TEXT_97 = NL + "\t */";
  protected final String TEXT_98 = NL + "\tpublic static final ";
  protected final String TEXT_99 = "__VALUES =" + NL + "\t\twrapEnumerationValues" + NL + "\t\t\t(new Object[]" + NL + "\t\t\t {";
  protected final String TEXT_100 = NL + "\t\t\t\t ";
  protected final String TEXT_101 = "new ";
  protected final String TEXT_102 = NL + "\t\t\t });" + NL;
  protected final String TEXT_103 = NL + "\t/**" + NL + "\t * The cached validation expression for the ";
  protected final String TEXT_104 = " constraint of '<em>";
  protected final String TEXT_105 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_106 = "__";
  protected final String TEXT_107 = "__EEXPRESSION = \"";
  protected final String TEXT_108 = NL + "\t/**" + NL + "\t * Validates the ";
  protected final String TEXT_109 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean validate";
  protected final String TEXT_110 = " = true;" + NL + "\t\tfor (";
  protected final String TEXT_111 = " i = ";
  protected final String TEXT_112 = ".iterator(); i.hasNext() && (result || diagnostics != null); )" + NL + "\t\t{" + NL + "\t\t\tObject item = i.next();";
  protected final String TEXT_113 = NL + "\t\t\tif (";
  protected final String TEXT_114 = ".isInstance(item))" + NL + "\t\t\t{" + NL + "\t\t\t\tresult &= ";
  protected final String TEXT_115 = ").";
  protected final String TEXT_116 = "()";
  protected final String TEXT_117 = ");" + NL + "\t\t\t}" + NL + "\t\t\telse";
  protected final String TEXT_118 = NL + "\t\t\tif (!";
  protected final String TEXT_119 = ".isInstance(item))";
  protected final String TEXT_120 = NL + "\t\t\t{" + NL + "\t\t\t\tresult = false;" + NL + "\t\t\t\treportDataValueTypeViolation(";
  protected final String TEXT_121 = ", item, ";
  protected final String TEXT_122 = ");" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_123 = NL + "\t\tif (diagnostics != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_124 = " tempDiagnostics = new BasicDiagnostic();";
  protected final String TEXT_125 = ", tempDiagnostics, ";
  protected final String TEXT_126 = ")) return true;";
  protected final String TEXT_127 = ".isInstance(";
  protected final String TEXT_128 = "))" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_129 = "if (";
  protected final String TEXT_130 = ")) ";
  protected final String TEXT_131 = "return true;" + NL + "\t\t\t}";
  protected final String TEXT_132 = NL + "\t\t\tfor (";
  protected final String TEXT_133 = " diagnostic : tempDiagnostics.getChildren())" + NL + "\t\t\t{" + NL + "\t\t\t\tdiagnostics.add(diagnostic);" + NL + "\t\t\t}";
  protected final String TEXT_134 = NL + "\t\t\t";
  protected final String TEXT_135 = " children = tempDiagnostics.getChildren();" + NL + "\t\t\tfor (int i = 0; i < children.size(); i++)" + NL + "\t\t\t{" + NL + "\t\t\t\tdiagnostics.add((";
  protected final String TEXT_136 = ")children.get(i));" + NL + "\t\t\t}";
  protected final String TEXT_137 = NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_138 = ", null, ";
  protected final String TEXT_139 = NL + "\t\t}" + NL + "\t\treturn false;";
  protected final String TEXT_140 = NL + "\t\treturn validatePattern(";
  protected final String TEXT_141 = "__VALUES, ";
  protected final String TEXT_142 = " = new ";
  protected final String TEXT_143 = "__VALUES.contains(";
  protected final String TEXT_144 = ");" + NL + "\t\tif (!";
  protected final String TEXT_145 = " && ";
  protected final String TEXT_146 = " != null)" + NL + "\t\t\treportEnumerationViolation(";
  protected final String TEXT_147 = ");" + NL + "\t\treturn ";
  protected final String TEXT_148 = "__VALUE;" + NL + "\t\tif (!";
  protected final String TEXT_149 = " != null)";
  protected final String TEXT_150 = NL + "\t\t\treportMinViolation(";
  protected final String TEXT_151 = ", new ";
  protected final String TEXT_152 = "), new ";
  protected final String TEXT_153 = "__VALUE), ";
  protected final String TEXT_154 = "__VALUE, ";
  protected final String TEXT_155 = NL + "\t\tint ";
  protected final String TEXT_156 = ".compareCalendar(";
  protected final String TEXT_157 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_158 = " == 0 || ";
  protected final String TEXT_159 = " == 1;";
  protected final String TEXT_160 = ".compareDuration(";
  protected final String TEXT_161 = ".compareTo(";
  protected final String TEXT_162 = "__VALUE) ";
  protected final String TEXT_163 = " 0;";
  protected final String TEXT_164 = NL + "\t\tif (!";
  protected final String TEXT_165 = " != null)" + NL + "\t\t\treportMinViolation(";
  protected final String TEXT_166 = NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_167 = " == -1;";
  protected final String TEXT_168 = " != null)" + NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_169 = NL + "\t\tint length = ";
  protected final String TEXT_170 = ";" + NL + "\t\tboolean ";
  protected final String TEXT_171 = " = length >= ";
  protected final String TEXT_172 = ";" + NL + "\t\tif (!";
  protected final String TEXT_173 = " != null)" + NL + "\t\t\treportMinLengthViolation(";
  protected final String TEXT_174 = ", length, ";
  protected final String TEXT_175 = " = length <= ";
  protected final String TEXT_176 = " != null)" + NL + "\t\t\treportMaxLengthViolation(";
  protected final String TEXT_177 = " > ";
  protected final String TEXT_178 = "__LOWER_BOUND && ";
  protected final String TEXT_179 = " < ";
  protected final String TEXT_180 = "__UPPER_BOUND;" + NL + "\t\tif (!";
  protected final String TEXT_181 = NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_182 = "), ";
  protected final String TEXT_183 = NL + "\t\tint scale = ";
  protected final String TEXT_184 = ".scale();" + NL + "\t\tint totalDigits = scale < 0 ? ";
  protected final String TEXT_185 = ".precision() - scale : ";
  protected final String TEXT_186 = ".precision();" + NL + "\t\tboolean ";
  protected final String TEXT_187 = " = totalDigits <= ";
  protected final String TEXT_188 = " != null)" + NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_189 = "__LOWER_BOUND) > 0 && ";
  protected final String TEXT_190 = "__UPPER_BOUND) < 0;" + NL + "\t\tif (!";
  protected final String TEXT_191 = ".scale() <= ";
  protected final String TEXT_192 = " != null)" + NL + "\t\t\treportFractionDigitsViolation(";
  protected final String TEXT_193 = NL + "\t\t// TODO override the constraint, if desired" + NL + "\t\t// -> uncomment the scaffolding" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_194 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_195 = ".add";
  protected final String TEXT_196 = NL + "\t\t\t\t\t(createDiagnostic" + NL + "\t\t\t\t\t\t(";
  protected final String TEXT_197 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t \"_UI_GenericConstraint_diagnostic\",";
  protected final String TEXT_198 = NL + "\t\t\t\t\t\t new Object[] { \"";
  protected final String TEXT_199 = "\", getValueLabel(";
  protected final String TEXT_200 = ") },";
  protected final String TEXT_201 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_202 = " }," + NL + "\t\t\t\t\t\t context));";
  protected final String TEXT_203 = NL + "\t\t\t\t\t(new ";
  protected final String TEXT_204 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_205 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_206 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_207 = ") }),";
  protected final String TEXT_208 = " }));";
  protected final String TEXT_209 = NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_210 = NL + "\t\treturn" + NL + "\t\t\tvalidate" + NL + "\t\t\t\t(";
  protected final String TEXT_211 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_212 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_213 = "\",";
  protected final String TEXT_214 = NL + "\t\t\t\t \"";
  protected final String TEXT_215 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_216 = ".ERROR," + NL + "\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t 0);";
  protected final String TEXT_217 = NL + "\t\t// TODO implement the constraint" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_218 = NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_219 = "\", getObjectLabel(";
  protected final String TEXT_220 = NL + "\t/**" + NL + "\t * Returns the resource locator that will be used to fetch messages for this validator's diagnostics." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_221 = NL + "\tpublic ";
  protected final String TEXT_222 = " getResourceLocator()" + NL + "\t{";
  protected final String TEXT_223 = NL + "\t\t// TODO" + NL + "\t\t// Specialize this to return a resource locator for messages specific to this validator." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn super.getResourceLocator();";
  protected final String TEXT_224 = NL + "} //";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2013 IBM Corporation and others.
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
    final String singleWildcard = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<?>" : "";
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
    genModel.addImport("java.util.Map");
    final String _Map = genModel.useGenerics() ? "Map<" + genModel.getImportedName("java.lang.Object") + ", " + genModel.getImportedName("java.lang.Object") + ">" : "Map";
    final String objectArgument = genModel.useGenerics() ? "<" + genModel.getImportedName("java.lang.Object") + ">" : "";
    genModel.addImport("org.eclipse.emf.common.util.DiagnosticChain");
    genModel.addImport("org.eclipse.emf.ecore.EPackage");
    if (!genPackage.hasJavaLangConflict() && !genPackage.getUtilitiesPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    genModel.markImportLocation(stringBuffer);
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.Descriptor");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.Registry");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.PatternMatcher");
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_7);
    if (isJDK50 && genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_8);
    } else if (isJDK50 && GenModelUtil.hasAPIDeprecatedTag(genPackage.getGenClassifiers())) {
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_12);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_16);
    int count = 0; for (GenClass genClass : genPackage.getGenClasses()) {
    for (GenOperation genOperation : genClass.getInvariantOperations()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(++count);
    stringBuffer.append(TEXT_5);
    }}
    stringBuffer.append(TEXT_23);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_24);
    for (GenPackage baseGenPackage : genPackage.getAllValidatorBaseGenPackages()) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(baseGenPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getValidatorPackageUniqueSafeName(baseGenPackage));
    stringBuffer.append(TEXT_27);
    }
    if (genPackage.hasInvariantExpressions()) {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_40);
    for (GenPackage baseGenPackage : genPackage.getAllValidatorBaseGenPackages()) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getValidatorPackageUniqueSafeName(baseGenPackage));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(baseGenPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_44);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_45);
    }
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    if (genClassifier.isUncheckedCast()) {
    stringBuffer.append(TEXT_47);
    break; }
    }
    stringBuffer.append(TEXT_48);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_49);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_52);
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (genDataType.isPrimitiveType()) {
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_56);
    } else {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_58);
    }
    } else if (genDataType.isObjectType()) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_59);
    } else {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genDataType.getObjectType().getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_58);
    }
    } else { GenClass genClass = (GenClass)genClassifier;
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_58);
    }
    }
    stringBuffer.append(TEXT_60);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {String result = "result".equals(genClassifier.getSafeUncapName()) ? "theResult" : "result"; String diagnostics = "diagnostics".equals(genClassifier.getSafeUncapName()) ? "theDiagnostics" : "diagnostics"; String item = "item".equals(genClassifier.getSafeUncapName()) ? "theItem" : "item"; String context = "context".equals(genClassifier.getSafeUncapName()) ? "theContext" : "context";
    stringBuffer.append(TEXT_61);
    if (genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_63);
    if (isJDK50 && genClassifier.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_64);
    }
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClassifier.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_68);
    if (genClassifier.getAllGenConstraints().isEmpty()) {
    stringBuffer.append(TEXT_69);
    } else if (genClassifier.hasOnlyDefaultConstraints()) {
    stringBuffer.append(TEXT_70);
    if (!((GenClass)genClassifier).isEObjectExtension()){
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else { boolean first = true;
    for (String constraint : genClassifier.getAllGenConstraints()) {GenClassifier constraintImplementor = genClassifier.getConstraintImplementor(constraint);
  String delegate = constraintImplementor == null || constraintImplementor.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(constraintImplementor.getGenPackage()) + "Validator.";
  String cast = constraintImplementor == null && genClassifier instanceof GenClass && !((GenClass)genClassifier).isEObjectExtension() ? "(" + genModel.getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  String accessor = constraintImplementor != null && genClassifier instanceof GenDataType && !((GenDataType)genClassifier).isPrimitiveType() && ((GenDataType)constraintImplementor).isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50 ? "." + ((GenDataType)constraintImplementor).getPrimitiveValueFunction() + "()" : "";
    if ("NoCircularContainment".equals(constraint)) {
    stringBuffer.append(TEXT_73);
    if (!((GenClass)genClassifier).isEObjectExtension()){
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_74);
    } else if (first) { first = false;
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(constraintImplementor == null ? "" : constraintImplementor.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_78);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(constraintImplementor == null ? "" : constraintImplementor.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    }
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_83);
    for (String constraint : genClassifier.getGenConstraints())
{GenClassifier constraintDelegate = genClassifier.getConstraintDelegate(constraint);
  String constant = genClassifier.getClassifierID() + "__" + CodeGenUtil.format(constraint, '_', null, false, false).toUpperCase(genClassifier.getGenModel().getLocale());
  String delegate = constraintDelegate == null || constraintDelegate.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(constraintDelegate.getGenPackage()) + "Validator.";
  String cast = constraintDelegate == null && genClassifier instanceof GenClass && !((GenClass)genClassifier).isEObjectExtension() ? "(" + genModel.getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  String accessor = constraintDelegate != null && genClassifier instanceof GenDataType && !((GenDataType)genClassifier).isPrimitiveType() && ((GenDataType)constraintDelegate).isPrimitiveType()  && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50 ? "." + ((GenDataType)constraintDelegate).getPrimitiveValueFunction() + "()" : "";
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (constraint.equals("Min") && genDataType.getMinLiteral() != null) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genDataType.getStaticValue(genDataType.getMinLiteral()));
    stringBuffer.append(TEXT_5);
    } else if (constraint.equals("Max") && genDataType.getMaxLiteral() != null) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genDataType.getStaticValue(genDataType.getMaxLiteral()));
    stringBuffer.append(TEXT_5);
    } else if (constraint.equals("TotalDigits") && genDataType.getTotalDigits() != -1 && !"java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) { String value = "1"; for (int digitCount = genDataType.getTotalDigits(); digitCount > 0; --digitCount) value += "0"; 
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genDataType.getStaticValue(value));
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genDataType.getStaticValue("-" + value));
    stringBuffer.append(TEXT_5);
    } else if (constraint.equals("Pattern") && !genDataType.getPatterns().isEmpty()) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_91);
    for (Iterator<List<String>> k = genDataType.getPatterns().iterator(); k.hasNext(); ) { List<String> patternList = k.next();
    stringBuffer.append(TEXT_92);
    for (Iterator<String> p = patternList.iterator(); p.hasNext(); ) { String pattern = p.next();
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(p.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_95);
    stringBuffer.append(k.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_96);
    } else if (constraint.equals("Enumeration") && !genDataType.getEnumerationLiterals().isEmpty()) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_97);
    if (genDataType.isUncheckedCast()) {
    stringBuffer.append(TEXT_47);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(objectArgument);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_99);
    for (Iterator<String> k = genDataType.getEnumerationLiterals().iterator(); k.hasNext(); ) { String literal = k.next();
    stringBuffer.append(TEXT_100);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(genDataType.getStaticValue(literal, false));
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(k.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_102);
    }
    }
    if (genClassifier.hasConstraintExpression(constraint)) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClassifier.getFormattedName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genClassifier.getClassifierID());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(CodeGenUtil.upperName(constraint));
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClassifier.getConstraintExpression(constraint, "\t\t"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_108);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClassifier.getFormattedName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClassifier.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_68);
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (constraint.equals("ItemType") && genDataType.getItemType() != null) { GenDataType itemType = genDataType.getItemType(); String itemDelegate = itemType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(itemType.getGenPackage()) + "Validator.";
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_112);
    if (itemType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(itemDelegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(itemType.getName());
    stringBuffer.append(TEXT_39);
    if (itemType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_39);
    }
    if (!itemType.isObjectType()) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(itemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(item);
    if (itemType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(itemType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_116);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_117);
    } else {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_122);
    } else if (constraint.equals("MemberTypes") && !genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_124);
    for (ListIterator<GenDataType> k = genDataType.getMemberTypes().listIterator(); k.hasNext(); ) { GenDataType memberType = k.next(); String memberDelegate = memberType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(memberType.getGenPackage()) + "Validator.";
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_126);
    } else {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(memberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_128);
    if (memberType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_39);
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_39);
    }
    if (!memberType.isObjectType() && !memberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(memberType.getImportedWildcardObjectInstanceClassName());
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(memberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_116);
    }
    stringBuffer.append(TEXT_125);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_130);
    }
    stringBuffer.append(TEXT_131);
    }
    }
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_133);
    } else {
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_136);
    }
    stringBuffer.append(TEXT_137);
    for (ListIterator<GenDataType> k = genDataType.getMemberTypes().listIterator(); k.hasNext(); ) { GenDataType memberType = k.next(); String memberDelegate = memberType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(memberType.getGenPackage()) + "Validator.";
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_126);
    } else {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(memberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_128);
    if (memberType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_39);
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_39);
    }
    if (!memberType.isObjectType() && !memberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(memberType.getImportedWildcardObjectInstanceClassName());
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(memberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_116);
    }
    stringBuffer.append(TEXT_138);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_130);
    }
    stringBuffer.append(TEXT_131);
    }
    }
    stringBuffer.append(TEXT_139);
    } else if (constraint.equals("Pattern") && !genDataType.getPatterns().isEmpty()) {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(genDataType.getSafeUncapName());
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else if (constraint.equals("Enumeration") && !genDataType.getEnumerationLiterals().isEmpty()) { String variable = genDataType.getSafeUncapName();
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) { variable = variable + "Object";
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else if (constraint.equals("Min") && genDataType.getMinLiteral() != null) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genDataType.isMinInclusive() ? ">=" : ">");
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_149);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else { String comparison = "comparison".equals(genClassifier.getSafeUncapName()) ? "theComparison" : "comparison";
    if (genDataType.isXMLCalendar()) {
    stringBuffer.append(TEXT_155);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    if (genDataType.isMinInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_159);
    } else if (genDataType.isXMLDuration()) {
    stringBuffer.append(TEXT_155);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    if (genDataType.isMinInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genDataType.isMinInclusive() ? ">=" : ">");
    stringBuffer.append(TEXT_163);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    }
    } else if (constraint.equals("Max") && genDataType.getMaxLiteral() != null) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genDataType.isMaxInclusive() ? "<=" : "<");
    stringBuffer.append(TEXT_26);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_149);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else { String comparison = "comparison".equals(genClassifier.getSafeUncapName()) ? "theComparison" : "comparison";
    if (genDataType.isXMLCalendar()) {
    stringBuffer.append(TEXT_155);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    if (genDataType.isMaxInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_167);
    } else if (genDataType.isXMLDuration()) {
    stringBuffer.append(TEXT_155);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    if (genDataType.isMaxInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_167);
    } else {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genDataType.isMaxInclusive() ? "<=" : "<");
    stringBuffer.append(TEXT_163);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    }
    } else if (constraint.equals("MinLength") && genDataType.getMinLength() != -1) {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genDataType.getLengthAccessorFunction());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genDataType.getMinLength());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genDataType.getMinLength());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else if (constraint.equals("MaxLength") && genDataType.getMaxLength() != -1) {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genDataType.getLengthAccessorFunction());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genDataType.getMaxLength());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genDataType.getMaxLength());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else if (constraint.equals("TotalDigits") && genDataType.getTotalDigits() != -1) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_149);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    }
    } else if ("java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else if (constraint.equals("FractionDigits") && genDataType.getFractionDigits() != -1 && "java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genDataType.getFractionDigits());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getFractionDigits());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_15);
    } else if (constraintDelegate != null) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_195);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_202);
    } else {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(constraintDelegate.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    if (genClassifier.hasConstraintExpression(constraint)) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genClassifier.getValidationDelegate(constraint));
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClassifier.getClassifierID());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(CodeGenUtil.upperName(constraint));
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_216);
    } else {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_195);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_202);
    } else {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_218);
    }
    }
    } else { GenOperation genOperation = ((GenClass)genClassifier).getInvariantOperation(constraint); if (genOperation != null) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else if (constraintDelegate != null) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_195);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_202);
    } else {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(constraintDelegate.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_72);
    } else {
    if (genClassifier.hasConstraintExpression(constraint)) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_211);
    if (!((GenClass)genClassifier).isEObjectExtension()) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genClassifier.getValidationDelegate(constraint));
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClassifier.getClassifierID());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(CodeGenUtil.upperName(constraint));
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_216);
    } else {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_195);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_202);
    } else {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_218);
    }
    }}
    stringBuffer.append(TEXT_83);
    }
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_220);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.ResourceLocator"));
    stringBuffer.append(TEXT_222);
    if (genModel.hasModelPluginClass()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedModelPluginClassName()));
    stringBuffer.append(TEXT_43);
    } else {
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genPackage.getValidatorClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
