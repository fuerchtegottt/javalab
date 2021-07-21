<?php 
  echo $_SERVER['REMOTE_ADDR'];
  $remoteip = $_SERVER['REMOTE_ADDR'];
  $host = "localhost";
  $dbname = "#dbname";
  $user = "#username";
  $pw = "#password";
  $connect = @mysql_connect($host, $user, $pw);
  $sql_string = "UPDATE `web_prop` SET `value`=\"$remoteip\" WHERE `key` = 'IP'";
  $result = @mysql_db_query($dbname,$sql_string, $connect);
  mysql_close($connect);
php?>