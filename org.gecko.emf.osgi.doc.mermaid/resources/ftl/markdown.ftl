<#-- 
TITLE
-->
<#macro printTitle title level anchor iconUrl>
<#list 1..level as x>#</#list> <#if iconUrl?has_content>'![]('~${iconUrl}~') '</#if>${title?trim}
<#if anchor?has_content> <a name="${anchor?lower_case}"></a></#if>
</#macro>



<#-- 
HORIZONTAL LINES
-->
<#macro printHorizontalLines>

----

</#macro>


