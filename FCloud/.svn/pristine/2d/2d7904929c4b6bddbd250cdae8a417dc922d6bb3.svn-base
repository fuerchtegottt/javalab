<?php session_start(); php?>
<?php include("config.php"); php?>
<?php include("functions.php"); php?>
<?php
$uname    = $_POST['username'];
$user_pwd = $_POST['password'];

if ( (isset($_SESSION['username'])) or (isUserValid($uname, $user_pwd, $connect, $dbname) == true)) {
	if (!isset($_SESSION['username'])){
	  $_SESSION['username'] = $uname;
	}
	$uname = $_SESSION['username'];

  if ($_GET['hash']){
   $form_hash = $_GET['hash'];
   $sql_string = "select hash category private hash_text hash_filename owner timestamp from fc_hash where hash = $form_hash";
   $sql_string = "SELECT `hash`, `category`, `private`, `hash_text`, `hash_filename`, `owner`, `timestamp` FROM `fc_hash` WHERE hash = '$form_hash'";
   $result = @mysql_db_query($dbname,$sql_string, $connect);

   if($result) {
   $row = mysql_fetch_array($result, MYSQL_NUM);
   $form_category  = $row[1];
   $form_private   = $row[2];
   $form_text      = $row[3];
   $form_filename  = $row[4];
   $form_owner     = $row[5];
   $form_timestamp = $row[6];
   if (($form_owner == $uname) or ($form_private == 0)){
   	include("readhash.php");
   } else {
   	$message_type = "F";
   	$message = "Sie haben keinen Zugriff auf den Hash $form_hash";
   	include('message.php');
   }
   } else {
   	include("hash.php");
   }
 } elseif ($_POST['submit']) {
   $form_action   = $_POST['action'];
   $form_hash     = addslashes($_POST['hashkey']);
   $form_hashtext = addslashes($_POST['hashtext']);
   if (isset($_POST['action'])){
   switch ($form_action){
     case "checkout":
	   $message = "Hash wurde erfolgreich geschrieben!";
	   $message_type = "C";
	   include('message.php');
	   break;
	 case "delete":
	   echo "LÖSCHEN";
	   break;
	 case "login":
        include("home.php");
	 	break;
   }
   } 
} elseif ($_GET['goto']){
  switch ($_GET['goto']){
  	case "logout":
  	  unset($_SESSION['username']);
  	  include("login.php");
  	  break;
  	case "new";
  	  include("hash.php");
  	  break;
  	case "delete";
  	  echo "delete...";
  	  break;
  	case "archive";
  	  include("archive.php");
  	  break;
  	case "home";
  	  include("home.php");
  	  break;
  	case "hash";
  	  if (isset($_GET['hash'])){
   $form_hash = $_GET['hash'];
   echo "LESEN:";
   echo $form_hash;
  	  }
  	  // include("hash.php");
  	  break;
  }
	
} else {
  include("home.php");
  //echo "Default-Home";
  //echo "<p> <a href='?goto=logout'>LOGOUT</a>";
}
} else {
  include("login.php");
} 
 php?>