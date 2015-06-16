<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
<style>
table, th, td {
    border: 1px inset blue;
	bgcolor: grey
}
</style> 
<div id="content">

  <br> Result : ${result}
    		
	<#if userDetails??>
		<br><br>Here are the Details....
		<table>
			<#list userDetails?keys as key>
			<tr><td>${key} </td><td><table>
									<#assign propertyMap=userDetails[key]> 
									<#list propertyMap?keys as key2>
									<tr><td>${key2}</td><td> ${propertyMap[key2]!"-"} </td></tr>
									</#list>
									</table>
 			</td></tr>
			</#list>
		</table> 
	<#else>
		No Info Found...
	</#if>
	<br><br>
</div>  

</@layout.defaultLayout>