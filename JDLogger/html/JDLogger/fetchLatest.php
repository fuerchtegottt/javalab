<?php
    /* fetch recent results into variables:
	   $power
	   $today_yield (must be calculated due to faulty values from inverter)
	   $total_yield
	   $timestamp
	*/
  require("config.php");

  /*
  $sql_string = "SELECT * FROM `jd_logger` order by timestamp DESC limit 1 ;";
  $result = @mysql_db_query($dbname, $sql_string, $connect);
  if ($result != null) {
    $row = mysql_fetch_array($result, MYSQL_NUM);
	$power       = $row[1];
	$today_yield = $row[2];
	$total_yield = $row[3];
	$timestamp   = $row[0];
  } else {
    echo "DB FETCH ERROR";
  }
  */
  
    /* fetch todays array:
	- select data
	- build js array for values
	- save last record in current variables*/
  $sql_str_today = "SELECT * FROM `jd_logger` WHERE date(timestamp ) = current_date order by timestamp ASC;";
  
  $res_today = @mysql_db_query($dbname, $sql_str_today, $connect);
  if ($res_today != null) {
	  echo "<script>";
	  echo "var xyValues = [";
	  while ($row = mysql_fetch_array($res_today, MYSQL_NUM)) {
	  	$power       = $row[1];
	    $today_yield = $row[2];
	    $total_yield = $row[3];
	    $timestamp   = $row[0];
		
		echo "{x:'";
	    $time = substr($timestamp, 11, 5);
        $time = preg_replace('/[:]/', '.', $time);		
		echo $time;		
		echo "', y:'";
		echo $power;	
		echo "'},";
	  }
	  echo "];";
	  echo "</script>";
  }
  
  /* - get yesterdays last record to get yesterdays total yield 
     - calculate todays yield by total today - totay yesterday */
  $sql_last_yest = "SELECT * FROM `jd_logger` WHERE date(timestamp ) < current_date order by timestamp DESC limit 1 ;";  	 
  $res_yest = @mysql_db_query($dbname, $sql_last_yest, $connect);
  if ($res_yest != null) {
    $row = mysql_fetch_array($res_yest, MYSQL_NUM);
	$today_yield  = $total_yield - $row[3];
    $ts_yest_last = $row[0];
  }	  
  
  mysql_close($connect);
  
php?>