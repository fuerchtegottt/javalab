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
<?
if ($is_online == 'X') {
	$filename_logo = 'logo.gif';
} else {
	$filename_logo = 'logoOffline.jpg';
	$power = 0;
}
?>

<body>
  <div class = "blog">
	<div class = "blogentry">
	<div class = "entryheader">
	aktuelle Werte
	</div>
	  <div class = "entrydesc">
	  <img src = "<? echo $filename_logo; ?>"  width = "65%" alt = "Logo JDLogger"/>
<table  >
  <tr>
    <th>akt. Leistung (W)</th>
    <th>Prod. heute (kWh)</th>
	<th>Prod. total (kWh)</th>
    <th>Timestamp</th>	
  </tr>
  <tr>
    <td><? echo $power; ?></td>
    <td><? echo $today_yield; ?></td>
	<td><? echo $total_yield; ?></td>
    <td><? echo $timestamp; ?></td>	
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
      yAxes: [{ticks: {min: 0, max:650 }}],
    }
  }
});
</script>
	  </div>
	</div>	

	<div class = "blogentry">
	<div class = "entryheader">
	14-Tages-Trend (Tage/ Prod.in kWh)
	</div>
	  <div class = "entrydesc">
  <canvas id="14DaysChart" style="width:100%;max-width:700px"></canvas>	  
	  <script>
new Chart("14DaysChart", {
  type: "scatter",
  data: {
    datasets: [{
      pointRadius: 4,
      pointBackgroundColor: "rgb(0,0,255)",
      data: weekValues
    }]
  },
  options: {
    legend: {display: false},	
    scales: {
      xAxes: [{ticks: {min: 1, max:14 }}],
      yAxes: [{ticks: {min: 0, max:6 }}],
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
  <div class = "footer">
   web node V2.0 powered by
    <a href = "https://github.com/fuerchtegottt/javalab/tree/master/XMLBlog" >BlogKit Design </a> &
	<a href = "https://www.chartjs.org" > chartsjs.org </a>
  </div>	  
	</div>		
	
  </div>
</body>
</html>