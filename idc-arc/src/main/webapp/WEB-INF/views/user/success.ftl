<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
 
<div class="content">
    <div id="form_container">
        <div class="text-center">
            <h1>Welcome to Awesome UserProfile Project!</h1>
            <p class="lead">
                Waddup!!!!!!!!!!!!!!!!!!!!
            </p>
            <p>${createMemberModel}</p>
        </div>
    </div>   
</div>
</@layout.defaultLayout>