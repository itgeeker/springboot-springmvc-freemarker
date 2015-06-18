<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Login Landing">
 
<div class="content">
    <div id="form_container">
        <div class="text-center">
            <h2>Hi ${user.userName} Welcome to Awesome UserProfile Project!</h2>
            <p class="lead">
                Waddup!!!!!!!!!!!!!!!!!!!!
            </p>    
            <p class="lead">
               <a href="/profile">Go to Your Profile Page</a>
            </p>           
        </div>
    </div>   
</div>
</@layout.defaultLayout>