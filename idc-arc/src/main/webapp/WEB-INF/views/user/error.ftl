<#import "../layout/default_layout.ftl" as layout>
<@layout.defaultLayout "Home page">
<style>
table, th, td {
    border: 1px inset blue;
	bgcolor: grey
}
</style> 
<script>
function goBack() {
    window.history.back();
}
</script>

<div id="content" width="75%">
	<center>
		You have encountered an Error !!! 
		<br><br> Error Code : ${errorCode!""}
  		<br><br> Error Message : ${errorMessage!""}
    	<br><br> Localized Message : ${localizedMessage!""}
    	<br><br>
            <p>
	            <div>	
					<button onclick="goBack()">Go Back !</button>
				</div>
			</p>      
	</center>
</div>  

</@layout.defaultLayout>