<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
 
 
<div id="content">
     
  <fieldset>
  	<legend>Register User:</legend>
  	<form name="user" action="addUser" method="post">
  	<table>
    	<tr><td>First Name: </td><td> <input type="text" name="firstName"> </td> </tr>
    	<tr><td>Last Name: </td><td>  <input type="text" name="lastName">   </td> </tr>
    	<tr><td>Email Name: </td><td> <input type="text" name="email">   </td></tr>
    	<tr><td>Password: </td><td>   <input type="password" name="password">   </td></tr>
    	<tr><td>Re-Enter: </td><td>   <input type="password" name="reEnteredPassword">   </td></tr>
    	<tr><td><input type="submit" value="   Register   "> </td><td> <button type="button" onclick="window.location='./users'">Rakuten Customers !!</button> </td></tr>
    </table>
 	 </form>
  </fieldset>
  <br>
</div>  

</@layout.defaultLayout>
 