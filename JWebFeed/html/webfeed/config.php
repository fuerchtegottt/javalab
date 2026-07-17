<?php
   $host = "localhost";
   $dbname = "g3ll3rt_prod";
   $user = "g3ll3rt_produ1";
   $pw = "_ranma_"; 
   
   $conn = mysqli_connect($host, $user, $pw, $dbname);   
   if ($conn->connect_error) {
     die("Connection failed: " . $conn->connect_error);
   }  
?>