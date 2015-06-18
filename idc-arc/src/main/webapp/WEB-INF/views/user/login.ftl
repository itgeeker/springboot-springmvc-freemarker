<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
 
 
<div id="content">
     
  <fieldset>
  	<legend>Add User</legend>
  	<#if error??>
  		Error while logging in ${error!""}
  	</#if>
  	<form name="user" action="authenticate" method="post">
  	<table>
    	<tr><td>Username: </td><td> <input type="text" name="username"> </td></tr>
    	<tr><td>Password: </td><td> <input type="password" name="password">   </td></tr>
    	<tr><td><input type="submit" value="   Login   "> </td><td> <button type="button" onclick="window.location='./signup'">Register Me!</button> </td></tr>
    </table>
 	 </form>
  </fieldset>
  <br>
  </div>  

</@layout.defaultLayout>
 