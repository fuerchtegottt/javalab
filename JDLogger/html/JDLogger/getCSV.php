<?
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
  $results = $conn->query($sql_string);
  $rowcnt = 0;
  $rowcnt = mysqli_num_rows($results);
  if ($rowcnt > 0) {
	download_send_headers("pv_data_export_" . date("Y-m-d") . ".csv");  
	echo "date; time; power; todays yield; total yield; timestamp";
	echo "\r\n";
    while($row = $results->fetch_assoc()) {

	  $power       = $row['power'];
      $today_yield = preg_replace('/[.]/', ',', $row['today_yield']);    /*$row[2];*/
	  $total_yield = preg_replace('/[.]/', ',', $row['total_yield']);    /*$row[3];*/
	  $timestamp   = $row['timestamp'];
	  $out_date    = substr($timestamp, 0, 10);
	  $out_time    = substr($timestamp, 11, 8);
      echo $out_date;
	  echo ";";
	  echo $out_time;
	  echo ";";
	  echo $power;
	  echo ";";
	  echo $today_yield;
	  echo ";";
	  echo $total_yield;
	  echo ";";	
      echo $timestamp;	  
	  echo "\r\n";
    }	
  } else {
    echo "DB FETCH ERROR";
  }
  $conn->close(); 
?>