<?php
  function isSessionValid(){
    $remoteip = $_SERVER['REMOTE_ADDR'];
    return isIPValid($remoteip);
  }
  
  function isIPValid($ip){
    return false;
  }
  
  function getNavBar(){
  	return "<a href='?goto=home'>HOME</a> <a href='?goto=new'>NEW</a> SETTINGS <a href='?goto=archive'>ARCHIVE</a> <a href='?goto=logout'>LOGOUT</a>";
  }

  function isUserValid($uname, $user_pwd, $connect, $dbname){
  	
  	if ($uname == null){
  	  return false;
  	}
  	
    $sql_string = "select pwd from fc_user where uname = '$uname';";
	$result = @mysql_db_query($dbname, $sql_string, $connect);	
	if ($result == null){
	  return false;
	}
	else {
	  $row = mysql_fetch_array($result, MYSQL_NUM);
	  if ($row[0] == $user_pwd){
	  	$_session['username'] = $uname;
	    return true;
	  } else {
	    return false;
	  }
	}
  }  
  
?>