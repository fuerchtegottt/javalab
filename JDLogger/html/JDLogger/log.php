<?
    /* example call: https://www.yourhost.de/jdlogger/log.php/?power=70&totyield=3.4&actyield=1.1 */
  require("config.php"); 
  $timestamp = time();
  $uzeit = date("H:i",$timestamp);
  $power = $_GET["power"];
  $total_yield = $_GET["totyield"];
  $today_yield = $_GET["actyield"];
  $sql_string = "INSERT INTO `jd_logger` (`timestamp`, `power`, `today_yield`, `total_yield` ) VALUES (CURRENT_TIMESTAMP, \"$power\", \"$today_yield\", \"$total_yield\");";  
  $result = $conn->query($sql_string);
  $conn->close(); 
?>