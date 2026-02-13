<?
  //Testsatz 100W erzeugen mit URL.../jdlogger/logtest.php?power=100
  require("config.php");
  $timestamp = time();
  $uzeit = date("H:i",$timestamp);
  $power = 0;
  $power = $_GET["power"];
  $sql_string = "INSERT INTO `jd_logger` (`timestamp`, `power`,`today_yield` , `total_yield`) VALUES (CURRENT_TIMESTAMP, \"$power\", 1, 1);";
  echo "Testsatz eingefügt mit ";
  echo $power;
  echo " Watt um :";
  echo $uzeit;
  $conn->query($sql_string);
  $conn->close(); 
?>