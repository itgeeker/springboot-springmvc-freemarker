<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
 
<div class="content">
    <div class="text-center">
        <h1>Welcome</h1>
        <p class="lead">
            Waddup!!!!!!!!!!!!!!!!!!!!
        </p>
        <p>${message}</p>
        <p>${Request.sitename}</p>
    </div>   
</div>
</@layout.defaultLayout>
 