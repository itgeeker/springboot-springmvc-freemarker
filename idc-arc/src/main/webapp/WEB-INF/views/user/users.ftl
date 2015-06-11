<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
<style>
table, th, td {
    border: 1px solid black;
}
</style>
<div id="content">
	<table class="datatable">
		<tbody>
			<tr>	
				<th>Email</th>  <th>First Name</th>  <th>Last Name</th>  <th>Username</th>
		
			</tr>
			<#list users as user>
			<tr>
				<td>${user.email!"-"}</td><td>${user.firstName!"-"}</td><td>${user.lastName!"-"}</td><td>${user.userName!"-"}</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>  
</@layout.defaultLayout>
 