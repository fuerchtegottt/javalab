<?
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
  
  $total_yield = 0;
  $no_recs_today = "";
  $power = 0;
  $timestamp = "";
  
  $res_today = $conn->query($sql_str_today);
  $rowcnt = 0;
  $rowcnt = mysqli_num_rows($res_today);
  if ($rowcnt > 0) {
	  echo "<script>";
	  echo "var xyValues = [";
	  while ($row = $res_today->fetch_assoc()) {
	  	$power       = $row['power'];
	    $today_yield = $row['today_yield'];
	    $total_yield = $row['total_yield'];
	    $timestamp   = $row['timestamp'];
		
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
  $res_yest = $conn->query($sql_last_yest);
  if ($res_yest != null) {
    $row = $res_yest->fetch_assoc();
	$today_yield  = $total_yield - $row['total_yield'];
    $ts_yest_last = $row['timestamp'];
	if ($no_recs_today == 'X') {
		$timestamp = $row['timestamp'];
		$today_yield = '0';
		$total_yield = $row['total_yield'];
	}
  }	  
  
  
   /* - get daily production of last 14 days */
   
$daysBack = 14;
$total_yield_old = $total_yield;
$i = 1;

	  echo "<script>";
	  echo "var weekValues = [";
while ($i <= $daysBack):
  $y = $i + 1;
  $tag_ts       = mktime(0, 0, 0, date("m")  , date("d")-$y, date("Y"));
  $tag_datum    = date("Y-m-d",$tag_ts);
  $sql_prod_day = "SELECT * FROM `jd_logger` WHERE date(timestamp ) = \"$tag_datum \" order by timestamp DESC limit 1 ;";
  $res_day = $conn->query($sql_prod_day);

  if ($res_day != null) {
    $row = $res_day->fetch_assoc();
    $total_yield_day = $row['total_yield'];	
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
   
  $conn->close(); 
  
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
// fix negative yield total bug
  if ($today_yield < 0 ){
    $today_yield = 0;
  }
  $today_yield = round($today_yield, 3);
?>