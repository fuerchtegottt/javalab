<?php 
  echo $_SERVER['REMOTE_ADDR'];
  $remoteip = $_SERVER['REMOTE_ADDR'];
  $timestamp = time();
  $uzeit = date("H:i",$timestamp);
  $remoteip = $uzeit;
  $host = "localhost";
  $dbname = "usr_web286_1";
  $user = "web286";
  $pw = "_ranma_";
  $connect = @mysql_connect($host, $user, $pw);
  $sql_string = "UPDATE `web_prop` SET `value`=\"$remoteip\" WHERE `key` = 'IP'";
  $result = @mysql_db_query($dbname,$sql_string, $connect);
  mysql_close($connect);
php?>