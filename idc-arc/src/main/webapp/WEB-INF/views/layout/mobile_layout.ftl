<#macro defaultLayout title="Rakuten-arc">
  <html>
  	<head>
      <title>
        ${title}
      </title>
    </head
    <body>
	    <div id="header">
			 <#include "header.ftl"/>
		</div>
		
		<div id="nav">
			 <#include "menu.ftl"/>
		</div>
		
		<div id="section">
			 <#nested/>
		</div>
		
		<div id="footer">
			<#include "footer.ftl"/>
		</div>   
    </body>
  </html>
</#macro>