<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head> <?php include('head.php'); php?> </head>
   <body class='siteGlobals'>
	   	 <div class='NavFrame'>
	       <div class='NavCanvas'>
             <img src='./images/logo.jpg'/ width=100%/><br><br>
			</div>
	   </div>
	   		
	   <div class = 'MainFrame'>
	     <div class ='MainCanvas'>
	       <?php echo getNavBar(); php?>
	     </div>
	     	     <div class = 'MainCanvas'>
           <div class='SimpleHead'>HASH</div>
		   <div class ='SimpleCanvas'>
 <form action="">
        <?php
          if (!$form_hash){
             $form_hash = "#direkt";
           }
        php?>
	   <input name="hash" type="text" size="10" maxlength="10" value="<?php echo $form_hash; php?>">
	   <select name="category" size="1">
         <option>bookmark</option>
		 <option selected="selected" >code</option>
		 <option>executable</option>
		 <option>image</option>
		 <option>message</option>
		 <option>text</option>
       </select>
	   <select name="specifier" size="1">
		 <option>public</option>
		 <option selected="selected" >private</option>
       </select>	   
	   </p>
	   <textarea name="clipbrd" cols="50" rows="10">paste text here</textarea>
	   </p> <input name="blobfile" type="file" size="50" maxlength="100000" accept="text/*"> </p>
	   <p><input type="submit" value=" check in ">
	   <a href="?goto=new"><img src="./images/undo.png" width="30" height="30" border="0" alt="clear"></a>
	   <a href="?goto=delete"><img src="./images/trash.png" width="30" height="30" border="0" alt="delete"></a>
	   </p>
	</form>
           </div>
		 </div>
	   </div>
	   </div>
  </body>
</html>