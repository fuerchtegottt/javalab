<!DOCTYPE html>
<html>
<!-- {%https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js} -->
<script src="Chart.js"></script>
<?php require("fetchLatest.php"); php?>
<head>
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
	aktuelle Werte
	</div>
	  <div class = "entrydesc">
	  <img src = "logo.gif"  width = "400em" alt = "Logo JDLogger"/>
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
    <td><?php echo $timestamp; php?></td>	
   </tr>
</table>
	  </div>
	</div>
	
	<div class = "blogentry">
	<div class = "entryheader">
	Tagesverlauf (Watt / Uhrzeit)
	</div>
	  <div class = "entrydesc">
  <canvas id="myChart" style="width:100%;max-width:700px"></canvas>	  
	  <script>
new Chart("myChart", {
  type: "scatter",
  data: {
    datasets: [{
      pointRadius: 4,
      pointBackgroundColor: "rgb(0,0,255)",
      data: xyValues
    }]
  },
  options: {
    legend: {display: false},	
    scales: {
      xAxes: [{ticks: {min: 5, max:23 }}],
      yAxes: [{ticks: {min: 0, max:600 }}],
    }
  }
});
</script>
	  </div>
	</div>

	<div class = "blogentry">
	<div class = "entryheader">
	Datenexport
	</div>
	  <div class = "entrydesc">
        <a href = "getCSV.php" > <img src = "csvIcon.png"  width = "100em" alt = "Download db (csv)"/> </a>
	  </div>
	</div>		
	
  </div>
  <div class = "footer">
   powered by
    <a href = "https://github.com/fuerchtegottt/javalab/tree/master/XMLBlog" >BlogKit Design </a> &
	<a href = "https://www.chartjs.org" > chartsjs.org </a>
  </div>
</body>
</html>