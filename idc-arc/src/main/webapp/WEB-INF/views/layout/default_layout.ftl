<#macro defaultLayout title="Rakuten-arc">
  <html>
  	<head>
      <title>
        ${title}
      </title>
    </head
    <body>
	    <div id="header">
			 <#include "../includes/sites/default/header.ftl"/>
		</div>
		
		<div id="nav">
			 <#include "../includes/sites/default/menu.ftl"/>
		</div>
		
		<div id="section">
			 <#nested/>
		</div>
		
		<div id="footer">
			<#include "../includes/sites/default/footer.ftl"/>
		</div>   
    </body>
  </html>
</#macro>