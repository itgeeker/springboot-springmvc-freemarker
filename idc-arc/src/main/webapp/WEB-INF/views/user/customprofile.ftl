<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Custom Profile page">
 
 
<div id="content">
    <div id="form_container">
    
        <#if error??>
               <div id="error_message" class="error_message">${error}</div>
        </#if>
        <form id="signup" class="appnitro"  method="post" action="savecustomprofile">
              <div class="form_description">
                 <h2>${message!"Welcome to Awesome User Profile Project !"}</h2>
                 <p>Custom Profile Page</p>
              </div>                      
              
              <ul >
              <fieldset>
                <legend><label class="description" for="element_3">About Us </label></legend>
                    
                                         
                 <li id="li_1" >
                     <fieldset>
                      <legend><label class="description" for="element_3">Custom Profiles </label></legend>
                        <div>
                            <label for="hobby">About Me</label> 
                            <input id="aboutme" name="aboutme" class="element text large" value="" type="textarea" rows="4">                            
                        </div>
                        <div>
                            <label for="hobby">Hobby</label>
                            <input id="hobby" name="hobby" class="element text large" value="" type="text">                            
                        </div>
                        <div>
                            <label for="favorites">Favourite Movie </label>
                            <input id="favorites" name="movies" class="element text large" value="" type="text">                            
                        </div>
                        
                        <div>
                            <label for="favorites">Favourite Book </label>
                            <input id="book" name="book" class="element text large" value="" type="text">                            
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
 