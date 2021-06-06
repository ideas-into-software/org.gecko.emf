<#import "markdown.ftl" as md>
<#import "mermaid_classdiagram.ftl" as cd>

<#-- 
MACROS - START
-->




<#-- 
NAMESPACE
-->
<#macro printNamespace namespace level>

<@md.printTitle title=namespace.name() level=level iconUrl="" anchor="namespace_title_"+namespace.fqn()/>

<#if namespace.description()?has_content>
<@md.printTitle title="Description" level=level+1 iconUrl="" anchor="namespace_description_"+namespace.fqn()/>

${namespace.description()}
</#if>

<@md.printTitle title="Diagram" level=level+2 iconUrl="" anchor="namespace_diagram_"+namespace.fqn()/>

<@cd.printClassDiagram>
<#list namespace.classes() as class>
<@cd.printClass class=class/>

</#list>

<#list namespace.enums() as enum>
<@cd.printEnum class=enum/>

</#list>
</@cd.printClassDiagram>


<@printClasses namespace=namespace level=level+1/>

<#list namespace.namespaces() as ns>
<@printNamespace namespace=ns level=level+1/>

</#list>
</#macro>



<#-- 
CLASSES
-->
<#macro printClasses namespace level>
<@md.printTitle title="Classes" level=level+1 iconUrl="" anchor="classes_"+namespace.fqn()/>

<#list namespace.classes() as c>

<@printClass class=c level=level+1 />


</#list>


<@md.printTitle title="Enumerations" level=level+1 iconUrl="" anchor="enums_"+namespace.fqn()/>

<#list namespace.enums() as e>

<@printEnum enum=e level=level+1 />


</#list>
</#macro>


<#-- 
CLASS
-->
<#macro printClass class level>
<@md.printTitle title=class.name() level=level+1 iconUrl="" anchor="class_"+class.fqn()/>

<@md.printTitle title="Description" level=level+2 iconUrl="" anchor="class_description_"+class.fqn()/>

<#if class.description()?has_content>
${class.description()}
<#else>
none
</#if>

<@md.printTitle title="Diagram" level=level+2 iconUrl="" anchor="class_diagram_"+class.fqn()/>

<@cd.printClassDiagram>
<@cd.printClass class=class/>
</@cd.printClassDiagram>

<#if class.supertypes()?has_content>
| Name| Type| 
| ----| ----|
<#list class.supertypes() as m>
| **${m.name()}** | [${m.to().name()}](#class_${m.to().fqn()?lower_case}) |
</#list>
<#else>
none
</#if>


<@md.printTitle title="Fields" level=level+2 iconUrl="" anchor="class_fields_"+class.fqn()/>

<#if class.members()?has_content>
| Field| Type| Bounds| Description|
| -----| ----| ------| -----------|
<#list class.members() as m>
| **${m.name()}** | ${m.type()}| ${m.lowerBound()}..${m.upperBound()}|${m.description()?trim}|
</#list>
<#else>
none
</#if>

<@md.printTitle title="References" level=level+2 iconUrl="" anchor="class_references_"+class.fqn()/>

<#if class.references()?has_content>
| Name| Type| Bounds| Description|
| ----| ----| ------| -----------|
<#list class.references() as m>
| **${m.name()}** | [${m.to().name()}](#class_${m.to().fqn()?lower_case})| ${m.cardinalityTo()}|${m.description()?trim}|
</#list>
<#else>
none
</#if>

<@md.printTitle title="Methods" level=level+2 iconUrl="" anchor="class_methods_"+class.fqn()/>

<#if class.methods()?has_content>
| Method| Type| Bounds| Description|
| ------| ----| ------| -----------|
<#list class.methods() as m>
| **${m.name()}**| ${m.type()}| ${m.lowerBound()}..${m.upperBound()}|${m.description()?trim}|
</#list>
<#else>
none
</#if>


</#macro>

<#-- 
ENUM
-->
<#macro printEnum enum level>
<@md.printTitle title=enum.name() level=level+1 iconUrl="" anchor="enum_"+enum.fqn()/>

<#if enum.description()?has_content>
<@md.printTitle title="Description" level=level+2 iconUrl="" anchor="enum_description_"+enum.fqn()/>

${enum.description()}
</#if>

<@md.printTitle title="Literals" level=level+2 iconUrl="" anchor="enum_literals_"+enum.fqn()/>

| Literal| Description|
| -------| ----------|
<#list enum.members() as m>
| **${m.name()}**| ${m.description()?trim}|
</#list>

</#macro>
<#-- 
MACROS - END
-->


<@printNamespace namespace=model.namespace() level=1 />


