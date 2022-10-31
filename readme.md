[![CI Build](https://github.com/gecko-org/org.gecko.emf.osgi/actions/workflows/build.yml/badge.svg)](https://github.com/gecko-org/org.gecko.emf.osgi/actions/workflows/build.yml)[![License](https://github.com/gecko-org/org.gecko.emf.osgi/actions/workflows/license.yml/badge.svg)](https://github.com/gecko-org/org.gecko.emf.osgi/actions/workflows/license.yml )[![Sonar](https://github.com/gecko-org/org.gecko.emf.osgi/actions/workflows/sonar.yml/badge.svg)](https://github.com/gecko-org/org.gecko.emf.osgi/actions/workflows/sonar.yml )[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=bugs)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=code_smells)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=coverage)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=alert_status)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=security_rating)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=sqale_index)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=geckoprojects-org_org.geckoprojects.emf&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=geckoprojects-org_org.geckoprojects.emf)

# org.geckoprojects.emf

## EMF for pure OSGi

EMF is one of the most powerful MDSD tools. Unfortunately it comes with strong ties to Eclipse and Equinox, because it uses Extension Points. It can be used in Java SE and other OSGi frameworks, but it usually requires a lot of manual work to register EPackages, ResoureFactories etc.

### GeckoEMF

GeckoEMF sets out to provide a way to use EMF in pure OSGi environments regardless of the framework you use. It is an extension on top of EMF, so EMF can be used as before, without any changes. The project is based on the [eModelling](https://github.com/BryanHunt/eModeling) project of Bryan Hunt.

### Usage

The latest Version is named here:

https://github.com/geckoprojects-org/org.geckoprojects.emf/blob/master/cnf/ext/version.bnd#L1

#### Maven BOM

We provide a maven BOM on central under the following coordinates:


```xml
<dependency>
      <groupId>org.geckoprojects.emf</groupId>
      <artifactId>org.gecko.emf.osgi.bom</artifactId>
      <version>${geckoemf.version}</version>
</dependency>
```

or as short GAV

```
org.geckoprojects.emf:org.gecko.emf.osgi.bom:${geckoemf.version}
```

#### BND

Besides the BOM we provide an  [OSGi Repository](http://devel.data-in-motion.biz/public/repository/gecko/release/geckoEMF/) as well.

We additionally provide a Workspace extension that provides some more comfort including a bnd code generator and project templates for EMF projects.

##### BND Library

Since bnd Version 6.1.0 the concept of [libraries](https://bnd.bndtools.org/instructions/library.html) was introduced, which provides some easy extensions for your bnd workspace setup.

Add the following maven dependency from maven central to you workspace:

```
org.geckoprojects.emf:org.gecko.emf.osgi.bnd.library.workspace:${geckoemf.version}
```

You can now activate the library bi adding the following instruction to your workspace (e.g. build.bnd) 



```properties
# If you are brave you can use the develop for the latest and greatest. We are ususally pretty stable
-library: geckoEMF
```

This will include a repository with all required depenencies together with the codegenerator and BND Tools Template for an example Project (klick next in the wizar or you will miss the required template variables).

An example project bnd file can look as follows:

```properties
# sets the usually required buildpath, you can extend it with the normal -buildpath to your liking
-library: enable-emf

# The code generation takes a bit of time and makes the build a bit slower.
# It might be a good idea to put comments around it, when you don't need it
-generate:\
	model/mymodel.genmodel;\
		generate=geckoEMF;\
		genmodel=model/mymodel.genmodel;\
		output=src
# Add this attribute to find some logging information
#		logfile=test.log;\

# always add the model in the same folder in jar as in your project
-includeresource: model=model

Bundle-Version: 1.0.0.SNAPSHOT
```

##### Bnd Codegenerator

The provided Code generator is based on the default code generator Version as declared in https://github.com/geckoprojects-org/org.geckoprojects.emf/blob/master/cnf/ext/repo_project.maven. As the use is not intended for PDE, no Manifest, plugin.xml or any other PDE Project specific files will be created. 

A few additions have been made though. It will create a Component that will register your EPackage, EPackageFactory and a Condition for you. If a ResourceFactory is generated, it will be Component as well. All generated Packages will be served with a `package-info.java` as well. If a `EAnnotation` with the source `Version` and a detailed entry with `value` as key is present on any `EPackage` this will define you exported version. If non is present the default is `1.0`.

As  

## Links

* [Documentation](https://github.com/geckoprojects-org/org.geckoprojects.emf)
* [Source Code](https://github.com/geckoprojects-org/org.geckoprojects.emf) (clone with `scm:git:git@github.com:gecko-org/org.gecko.emf.osgi.git`)


## Developers

* **Juergen Albert** (jalbert) / [j.albert@data-in-motion.biz](mailto:j.albert@data-in-motion.biz) @ [Data In Motion](https://www.datainmotion.de) - *architect*, *developer*
* **Mark Hoffmann** (mhoffmann) / [m.hoffmann@data-in-motion.biz](mailto:m.hoffmann@data-in-motion.biz) @ [Data In Motion](https://www.datainmotion.de) - *developer*, *architect*
* **Stefan Bischof** (bipolis) / [stbischof@bipolis.org](mailto:stbischof@bipolis.org) - *developer*
## Licenses

**EPL 2.0**

## Copyright

Data In Motion Consuling GmbH - All rights reserved

---
Data In Motion Consuling GmbH - [info@data-in-motion.biz](mailto:info@data-in-motion.biz)
