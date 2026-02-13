<?php
   $host = "localhost";
   $dbname = "g3ll3rt_test";
   $user = "g3ll3rt_testu1";
   $pw = "#######"; 
   
   $conn = mysqli_connect($host, $user, $pw, $dbname);   
   if ($conn->connect_error) {
     die("Connection failed: " . $conn->connect_error);
   }  
?>