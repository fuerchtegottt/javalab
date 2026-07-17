<?
    /* example call: https://www.yourhost.de/webfeed/put.php/?type=test&text=HalloWelt */
  require("config.php"); 
  $timestamp = time();
  $uzeit = date("H:i",$timestamp);
  $feed_type = $_GET["type"];
  $feed_text = $_GET["text"];
  $sql_string = "INSERT INTO `webfeed` (`feed_key`, `feed_type`, `feed_text`  ) VALUES (CURRENT_TIMESTAMP, \"$feed_type\", \"$feed_text\");";  
  echo $sql_string;
  $result = $conn->query($sql_string);
  $conn->close(); 
?>