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
			<tr><td>${key} </td><td> ${userDetails[key]} </td><tr>
			</#list>
		</table> 
	<#else>
		No Info Found...
	</#if>
	<br><br>
</div>  

</@layout.defaultLayout>