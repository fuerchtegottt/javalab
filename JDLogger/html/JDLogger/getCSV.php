<?php
    /* fetch recent results into variables:
	   $power
	   $today_yield
	   $total_yield
	   $timestamp
	*/
  require("config.php");

function download_send_headers($filename) {
    // disable caching
    $now = gmdate("D, d M Y H:i:s");
    header("Expires: Tue, 03 Jul 2001 06:00:00 GMT");
    header("Cache-Control: max-age=0, no-cache, must-revalidate, proxy-revalidate");
    header("Last-Modified: {$now} GMT");

    // force download  
    header("Content-Type: application/force-download");
    header("Content-Type: application/octet-stream");
    header("Content-Type: application/download");

    // disposition / encoding on response body
    header("Content-Disposition: attachment;filename={$filename}");
    header("Content-Transfer-Encoding: binary");
}
  
  $sql_string = "SELECT * FROM `jd_logger` order by timestamp DESC;";  
  $results = @mysql_db_query($dbname, $sql_string, $connect);
  if ($results != null) {
	download_send_headers("pv_data_export_" . date("Y-m-d") . ".csv");  
	echo "timestamp; power; todays yield; total yield";
	echo "\r\n";
    while($row = mysql_fetch_assoc($results)) {

	  $row = mysql_fetch_array($results, MYSQL_NUM);
	  $power       = $row[1];
      $today_yield = preg_replace('/[.]/', ',', $row[2]);    /*$row[2];*/
	  $total_yield = preg_replace('/[.]/', ',', $row[3]);    /*$row[3];*/
	  $timestamp   = $row[0];
      echo $timestamp;
	  echo ";";
	  echo $power;
	  echo ";";
	  echo $today_yield;
	  echo ";";
	  echo $total_yield;
	  echo "\r\n";
    }	
  } else {
    echo "DB FETCH ERROR";
  }
  mysql_close($connect);
php?>