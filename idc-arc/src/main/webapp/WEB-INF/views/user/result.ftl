<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
<style>
table, th, td {
    border: 1px inset blue;
	bgcolor: grey
}
</style> 
<div id="content" width="75%">
<center>
  <br> Result : ${result}
    		
	<#if userDetails??>
		<br><br>Here are the Details....
		<#list userDetails?keys as key>
		<fieldset>
			<legend ><label class="description" for="element_3"> ${key} </label></legend>
			<table>
				<#assign propertyMap=userDetails[key]> 
				<#list propertyMap?keys as key2>
				<tr><td>${key2}</td><td> ${propertyMap[key2]!"-"} </td></tr>
				</#list>
			</table>
		</fieldset> 
		</#list>
		
	<#else>
		No Info Found...
	</#if>
	<br><br>
	</center>
</div>  

</@layout.defaultLayout>