<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<!-- Hier dann der Inhalt des Stylesheets -->
  <xsl:template match="/">
    <html>
	  <head>
	    <link rel="stylesheet" type="text/css" href="flex.css"/>
	  </head>
      <body class='siteGlobals'>
	    <div class="flex">
          <xsl:apply-templates/>
	    </div>
      </body>
    </html>	  
  </xsl:template>

  <xsl:template match="LINK">
	
    <xsl:variable name="hyperlink"><xsl:value-of select="URL" /></xsl:variable>	
  <div><a href="{$hyperlink}">
  <xsl:value-of select="DESCR"/>
  </a></div>
	
	
  </xsl:template>
</xsl:stylesheet>