<?php
    /* fetch recent results into variables:
	   $power
	   $today_yield
	   $total_yield
	   $timestamp
	*/
  require("config.php"); 
  $sql_string = "SELECT * FROM `jd_logger` order by timestamp DESC limit 1 ;";  
  $result = @mysql_db_query($dbname, $sql_string, $connect);
  if ($result != null) {
    $row = mysql_fetch_array($result, MYSQL_NUM);
	$power       = $row[1];
	$today_yield = $row[2];
	$total_yield = $row[3];
	$timestamp   = $row[0];
	/*echo $power;
	echo $timestamp; */
  } else {
    echo "DB FETCH ERROR";
  }
  mysql_close($connect);
php?>