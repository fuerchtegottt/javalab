<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<?php include("config.php"); php?>

<?php
  $sqlstring = "select * from fc_folder;";
  $result = mysql_db_query($dbname, $sqlstring, $connect);
php?>

<html>
  <head>
    <title> Datenbank-Test </title>
  </head>
  <body>
    <br> Datenbanktest <br>
<?php
    while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
	 echo "   $row[0] <br> \n";
	}
php?>
  </body>
</html>