<?php 
  $host = "localhost";
  $dbname = "#dbname";
  $user = "#username";
  $pw = "#password";
  $connect = @mysql_connect($host, $user, $pw);
  $sql_string = "SELECT value FROM `web_prop` where `key` = 'IP'";
  $result = @mysql_db_query($dbname,$sql_string, $connect);
  if ($result != null) {
    $row = mysql_fetch_array($result, MYSQL_NUM);
	$hyperlink = "<a href='http://$row[0]' target='blanck_'>$row[0]</a>";
	echo $hyperlink;
  } else {
  echo "Fehler";
  }  
php?>