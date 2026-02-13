<!DOCTYPE html>
<html>
<!-- {%https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js} -->
<script src="Chart.js"></script>
<? require("fetchLatest.php"); ?>
<head>
<meta http-equiv="refresh" content="60">
<link rel="icon" href="pvicon.ico" type="image/vnd.microsoft.icon">
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
	<meta name="author" content="g3ll3rt.de">
	<link rel="stylesheet" href="blog.css" type="text/css">
	<title>JDLogger (PV Anlage)</title>
</head>

<!-- set logo image depending on online status -->
<body>
  <?  
    echo "Hallo Welt! ";
	if ($is_online == 'X'){
	  echo "Wechselrichter online"; 
	}
	else {
	  echo "Wechselrichter offline";
    }
  ?>
</body>
</html>