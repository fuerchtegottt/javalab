<?php
    /* fetch recent results into variables:
	   $power
	   $today_yield (must be calculated due to faulty values from inverter)
	   $total_yield
	   $timestamp
	*/
  require("config.php");
  
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
  } else {
	  $no_recs_today = 'X';
  }
  
  /* - get yesterdays last record to get yesterdays total yield 
     - calculate todays yield by total today - totay yesterday */
  $sql_last_yest = "SELECT * FROM `jd_logger` WHERE date(timestamp ) < current_date order by timestamp DESC limit 1 ;";  	 
  $res_yest = @mysql_db_query($dbname, $sql_last_yest, $connect);
  if ($res_yest != null) {
    $row = mysql_fetch_array($res_yest, MYSQL_NUM);
	$today_yield  = $total_yield - $row[3];
    $ts_yest_last = $row[0];
	if ($no_recs_today == 'X') {
		$timestamp = $row[0];
		$today_yield = '0';
		$total_yield = $row[3];
	}
  }	  
  
  
   /* - get daily production of last 7 days */
   
$daysBack = 14;
$total_yield_old = $total_yield;
$i = 1;

	  echo "<script>";
	  echo "var weekValues = [";
while ($i <= $daysBack):
  $tag_ts       = mktime(0, 0, 0, date("m")  , date("d")-$i, date("Y"));
  $tag_datum    = date("Y-m-d",$tag_ts);
  $sql_prod_day = "SELECT * FROM `jd_logger` WHERE date(timestamp ) = \"$tag_datum \" order by timestamp DESC limit 1 ;";
  $res_day = @mysql_db_query($dbname, $sql_prod_day, $connect);

  if ($res_day != null) {
    $row = mysql_fetch_array($res_day, MYSQL_NUM);
    $total_yield_day = $row[3];	
    $day_yield = $total_yield_old - $total_yield_day;	
	$total_yield_old = $total_yield_day;
   echo "{x:'";
		echo $i;		
		echo "', y:'";
		echo $day_yield;	
		echo "'},";
  } 

    $i++;
endwhile;

	  echo "];";
	  echo "</script>";   
   
  
  
  
  mysql_close($connect);
  
  /* determine ONLINE status by comparing frontend timestamp with inverter timestamp 
     (if inverter timestamp is more than 5 minutes ago --> inverter offline)
  */  
if ($no_recs_today != 'X'){
  $now_frontend = new DateTime();
  $now_inverter = date_create_from_format('Y-m-d H:i:s', $timestamp);
  $tsdiff = date_diff($now_frontend, $now_inverter);

  IF ($tsdiff->i > 5){
    $is_online = '';
  } else {
    $is_online = 'X';
  }  
} else {
  $is_online = '';
}
  
php?>