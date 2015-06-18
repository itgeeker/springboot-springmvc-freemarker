<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Login Landing">
<script>
function goBack() {
    window.history.back();
}
</script>

<div class="content">
    <div id="form_container">
        <div class="text-center">
            <h2>Hi ${user.userName!""}! Welcome to Awesome UserProfile Project!</h2>
            <p class="lead">
                ${result!""} 
            </p>    
            <p class="lead">
            <form name="user" action="profile" method="post">
               <input type="submit" value="View my Profile !">
            </p>
            <p>
	            <div>	
					<button onclick="goBack()">Go Back !</button>
				</div>
			</p>           
        </div>
    </div>   
</div>
</@layout.defaultLayout>