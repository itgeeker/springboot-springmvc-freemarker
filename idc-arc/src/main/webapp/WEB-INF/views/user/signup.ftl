<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
 
 
<div id="content">
    <div id="form_container">
    
        <#if error??>
               <div id="error_message" class="error_message">${error}</div>
        </#if>
        <form id="signup" class="appnitro"  method="post" action="dosignup">
              <div class="form_description">
                 <h2>${message!"Welcome to Awesome User Profile Project !"}</h2>
                 <p>Registration Page</p>
              </div>                      
              
			  <ul >
			  <fieldset>
				<legend><label class="description" for="element_3">User Credentials </label></legend>
					
					<li id="li_1" >
					<label class="description" for="username" >Username </label>
                    <span>
                        <input id="username" name= "username" class="element text large" maxlength="255" size="16" value=""/>                        
                    </span>
                   </li>      
                <li id="li_2" >
                    <label class="description" for="element_2">Password </label>
                    <span>
                        <input type="password" id="password" name= "password" class="element text" maxlength="255" size="16" value=""/>                        
                    </span>                    
                 </li>       
                 
                 <li id="li_3" >
				 <fieldset>
                  <legend><label class="description" for="element_3">Profile </label></legend>
                    <div>
                        <input id="pr_email" name="pr_email" class="element text large" value="" type="text">
                        <label for="pr_email">Email</label>
                    </div>
    
                    <span>
                        <input id="pr_first_name" name= "pr_first_name" class="element text" maxlength="255" size="8" value=""/>
                        <label>First Name</label>
                    </span>
                    <span>
                        <input id="pr_last_name" name= "pr_last_name" class="element text" maxlength="255" size="14" value=""/>
                        <label>Last Name</label>
                    </span> 
					</fieldset>
                 </li> 
                 
                 
                 <li id="li_4" >
				  <fieldset>
						<legend>
							<label class="description" for="element_3">Address </label>                     
						</legend>
						<span>
							<input id="ad_first_name" name= "ad_first_name" class="element text" maxlength="255" size="8" value=""/>
							<label>First Name</label>
						</span>
						<span>
							<input id="ad_last_name" name= "ad_last_name" class="element text" maxlength="255" size="14" value=""/>
							<label>Last Name</label>
						</span> 
						
						 <div>
							<input id="ad_street_address1" name="ad_street_address1" class="element text large" value="" type="text">
							<label for="ad_street_address1">Street Address</label>
						</div>
						
						<div>
							<input id="ad_street_address2" name="ad_street_address2" class="element text large" value="" type="text">
							<label for="ad_street_address2">Street Address 2</label>
						</div>
						
						  <div class="left">
							<input id="ad_country_cd" name="ad_country_cd" class="element text medium" value="" type="text">
							<label for="ad_country_cd">Country CD</label>
						</div>
		
						<div class="right">
							<input id="ad_region_cd" name="ad_region_cd" class="element text medium" value="" type="text">
							<label for="ad_region_cd">Region CD</label>
						</div>
		
						<div class="left">
							<input id="ad_postal_cd" name="ad_postal_cd" class="element text medium" maxlength="15" value="" type="text">
							<label for="ad_postal_cd">Postal / Zip Code</label>
						</div>    
					</fieldset>                
                 </li>                       
 				 <li id="li_5" >
					 <fieldset>
	                  <legend><label class="description" for="element_3">Custom Profiles </label></legend>
	                    <div>
	                        <input id="hobby" name="hobby" class="element text large" value="" type="text">
	                        <label for="hobby">Hobby</label>
	                    </div>
						<div>
							<input id="favorites" name="favorites" class="element text large" value="" type="text">
							<label for="favorites">Favourites </label>
						</div>
	                  </fieldset>
	              </li>
                <li class="buttons">
                <input type="hidden" name="form_id" value="1020154" />                
                    <input id="saveForm" class="button_text" type="submit" name="submit" value="Submit" />
                </li>
            </ul>
        </form>         
 </div>

</@layout.defaultLayout>
 