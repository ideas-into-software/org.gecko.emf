package org.geckoprojects.emf.codegen.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PackageInfoImpl
{
  protected static String nl;
  public static synchronized PackageInfoImpl create(String lineSeparator)
  {
    nl = lineSeparator;
    PackageInfoImpl result = new PackageInfoImpl();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "@org.osgi.annotation.versioning.Version(\"1.0.0\")" + NL + "@org.osgi.annotation.bundle.Export" + NL + "package ";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = ";";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    final GenPackage genPackage = (GenPackage)((Object[])argument)[0];
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
    stringBuffer.append(genPackage.getClassPackageSuffix());
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
