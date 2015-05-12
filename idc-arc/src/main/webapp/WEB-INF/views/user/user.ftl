<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
 
 
<div id="content">
     
  <fieldset>
  	<legend>Add User</legend>
  	<form name="user" action="adduser" method="post">
    	Firstname: <input type="text" name="firstname"> <br>
    	Lastname: <input type="text" name="lastname">   <br>
    	<input type="submit" value="   Save   ">
 	 </form>
  </fieldset>
  <br>
   
    
	<table class="datatable">
		<tbody>
			<tr>	
				<th>Email</th>  <th>Name</th>
		
			</tr>
			<#list users as user>
			<tr>
				<td>${user.email}</td> <td>${user.name}</td>
			</tr>
	</#list>
		</tbody>
	</table>
 </div>  

</@layout.defaultLayout>
 