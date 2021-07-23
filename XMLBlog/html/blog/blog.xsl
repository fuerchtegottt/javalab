<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/blog">
     <html>
	  <head>
	    <link rel="stylesheet" type="text/css" href="blog.css"/>
	  </head>	 
     <body>
	 <div class="blog">
	 <div class="blogtitle"> <xsl:value-of select="/blog/@title"/> </div>
	 
        <xsl:for-each select="entry">
        <div class="blogentry">
          <div class="entryheader">
		  	<div class="entrytitle"><xsl:value-of select="title"/></div> 
		    <div class="entrydate"><xsl:value-of select="date"/></div> 
	      </div>
          <xsl:variable name="image"><xsl:value-of select="eimage" /></xsl:variable>		  
		  <div class="entryimage"><img src="{$image}" alt="{$image}"></img></div>
          <div class="entrydesc"><xsl:value-of select="descr"/></div>
      <xsl:choose>
        <xsl:when test="code">
				  <div class="codebox"><xsl:value-of select="code"/></div>
		</xsl:when>
      </xsl:choose>			  
          <xsl:variable name="hyperlink"><xsl:value-of select="hyperlink" /></xsl:variable>	  
		  <div class="entrylink"><a href="{$hyperlink}"><xsl:value-of select="hyperlink"/></a></div>
		 </div>
         </xsl:for-each>
	 </div>
     <div class = "footer">
       <a href = "https://github.com/fuerchtegottt/javalab/tree/master/XMLBlog" > powered by BlogKit Design </a>
     </div>	 
     </body>
     </html>
</xsl:template>
</xsl:stylesheet>