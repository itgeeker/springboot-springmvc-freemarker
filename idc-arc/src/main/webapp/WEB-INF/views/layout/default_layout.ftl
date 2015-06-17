<#macro defaultLayout title="Rakuten-arc">
  <html>
  	<head>  	
      <title>
        ${title}
      </title>
      <link rel="stylesheet" type="text/css" href="./resources/css/core.css" media="all">
    </head
    <body id="main_body">
	    <div id="header">
			 <#include "../includes/sites/${Request.sitename}/header.ftl"/>
		</div>
		
		<div id="nav">
			 <#include "../includes/sites/${Request.sitename}/menu.ftl"/>
		</div>
		
		<div id="section">
			 <#nested/>
		</div>
		
		<div id="footer">
			<#include "../includes/sites/${Request.sitename}/footer.ftl"/>
		</div>   
    </body>
  </html>
</#macro>