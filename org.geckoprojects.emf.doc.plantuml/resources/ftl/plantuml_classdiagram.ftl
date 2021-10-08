<#-- 
CLASS
-->
<#macro printClass class members=true methods=true references=true supertypes=true>
 class ${class.name()}{
<#if members && class.members()?has_content>
<#list class.members() as m>
 ${m.type()} ${m.name()}
</#list>
</#if>
<#if methods && class.methods()?has_content>
<#list class.methods() as m>
 ${m.type()} ${m.name()}(...) <#-- TODO: MethodParameter -->
</#list>
</#if>
 }

<#if references && class.references()?has_content>
<#list class.references() as m>
 ${class.name()} "${m.cardinalityFrom()}" --> "${m.cardinalityTo()}" ${m.to().name()} : ${m.name()}
</#list>
</#if>

<#if supertypes && class.supertypes()?has_content>
<#list class.supertypes() as m>
 ${class.name()} ..> ${m.to().name()}
</#list>
</#if>
</#macro>


<#-- 
ENUM
-->
<#macro printEnum class members=true>
 class ${class.name()}{
 <<enumeration>>
<#if members && class.members()?has_content>
<#list class.members() as m>
 ${m.name()}
</#list>
</#if>
 }
</#macro>



<#-- 
MermaidCode
-->
<#macro printMermaidCode>
```mermaid
<#nested>

```
</#macro>


<#-- 
ClassDiagram
-->
<#macro printClassDiagram>
<@printMermaidCode>
classDiagram
<#nested>
</@printMermaidCode>
</#macro>

