<?php require("fetchLatest.php"); php?>
<html><head>
<link rel="icon" href="g3ll3rt.ico" type="image/vnd.microsoft.icon">
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
	<meta name="author" content="g3ll3rt.de">
	<link rel="stylesheet" href="blog.css" type="text/css">
	<title>JDLogger (PV Anlage)</title>
</head>
<body>
  <div class = "blog">
	<div class = "blogentry">
	<div class = "entryheader">
	PV - Anlage
	</div>
	  <div class = "entryimage">
	  <img src = "logo.gif"  width = "400em" alt = "Logo JDLogger"/>
	  </div>
	  <div class = "entrydesc">
<table>
  <tr>
    <th>akt. Leistung (W)</th>
    <th>Prod. heute (kWh)</th>
	<th>Prod. total (kWh)</th>
    <th>Timestamp</th>	
  </tr>
  <tr>
    <td><?php echo $power; php?></td>
    <td><?php echo $today_yield; php?></td>
	<td><?php echo $total_yield; php?></td>
    <td><?php echo $timestamp; php?></</td>	
   </tr>
</table>
<a href = "getCSV.php" > <img src = "csvIcon.png"  width = "100em" alt = "Download db (csv)"/> </a>
	  </div>
	</div>
  </div>
  <div class = "footer">
    <a href = "https://github.com/fuerchtegottt/javalab/tree/master/XMLBlog" > powered by BlogKit Design </a>
  </div>
</body>
</html>