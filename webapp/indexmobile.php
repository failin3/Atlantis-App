<?php 
include 'memberInfo.php';
?>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div class="center-text">
<?php 
if (isset($_GET['vote'])) {
	$var = $_GET['vote'];
	echo "$var man heeft voor gestemd";
}?>
<h3>Taakjes</h3>
<div class="TaskContainer">

<?php
$multiplier = 0;
$timestamp = 17532; //17532 De dag waarop de taakjes zijn vastgelegd in het systeem
$taakjes = array("WC + Koelkasten", "Hok", "Keuken Maandag", "Keuken Woensdag", "Bovenonder+Trap", "Badkamer");
$date = date('U');
$date = $date / (60 * 60 * 24);
$day = $date;
$date = round($date-$timestamp);
//$multiplier = round($date / 7);
for($multiplier=0; $date >= 7; $multiplier++) {
	$date=$date-7;
}
$week = $multiplier;
//Dit was $multiplier % 5, dat werkt niet met index  = 0
//Nu trekken we steeds 6 er af totdat we onder 5 zitten. 
if($multiplier > 5) {		
	$i = 0;					
	do {
		$multiplier = $multiplier - 6;
	} while ($multiplier > 5);
}
?>

<span class="floating-box-tight"><b><?php echo $name1; ?></b>
<br>
<?php echo $taakjes[$multiplier]; ?></span>
<span class="floating-box-tight"><b><?php echo $name2; ?></b>
<br>
<?php if($multiplier+1>5) {$task = $multiplier+1 - 6;} else {$task = $multiplier+1;}echo $taakjes[$task]; ?></span>
<br>
<span class="floating-box-tight"><b><?php echo $name3; ?></b>
<br>
<?php if($multiplier+2>5) {$task = $multiplier+2 - 6;}else {$task = $multiplier+2;}echo $taakjes[$task]; ?></span>
<span class="floating-box-tight"><b><?php echo $name4; ?></b>
<br>
<?php if($multiplier+3>5) {$task = $multiplier+3 - 6;}else {$task = $multiplier+3;}echo $taakjes[$task]; ?></span>
<br>
<span class="floating-box-tight"><b><?php echo $name5; ?></b>
<br>
<?php if($multiplier+4>5) {$task = $multiplier+4 - 6;}else {$task = $multiplier+4;}echo $taakjes[$task]; ?></span>
<span class="floating-box-tight"><b><?php echo $name6; ?></b>
<br>
<?php if($multiplier+5>5) {$task = $multiplier+5 - 6;}else {$task = $multiplier+5;}echo $taakjes[$task]; ?></span>
</div>
<?php 
$day+=4-$timestamp;
for($multiplier=0; round($day) >= 7; $multiplier++) {
	$day=$day-7;
}
if(round($multiplier+1) % 2 == 0) {
echo '<img src="kliko-grijs-rest.svg" alt="rest-afval">';
} else {
echo '<img src="kliko-groen-gft.svg" alt="gft">';
} ?>
<br>
Zet het vuilnis <?php
$dagen = array("overmorgen","morgen","vandaag","over 6 dagen","over 5 dagen","over 4 dagen","over 3 dagen");
echo $dagen[date("w")];
?> aan de straat

<br>
<br>

Ome DUO betaalt  <?php
//2018
//$duodates = array("24-04-2018", "24-05-2018", "22-06-2018", "24-07-2018", "24-08-2018", "24-09-2018", "24-10-2018", "23-11-2018", "21-12-2018");
//2019
$todaytime = strtotime("today CEST");
for($i=0; $i < count($duodates); $i++) {
	$datetime = strtotime($duodates[$i]);
	if($datetime > $todaytime) {
		break;
	}
}
$datetime = $datetime - $todaytime;
$datetime = round($datetime / (60*60*24));
if($datetime > 1) {
	echo "over $datetime dagen";
} else if($datetime == 1) {
	echo "morgen";
} else if($datetime == 0) {
	echo "vandaag";
}

?>


<h3>Links</h3>
<div class="floating-link">
<form action="http://eetlijst.nl/login.php" method="post">
    <input type="hidden" name="login" value="aquavitae">
	<input type="hidden" name="pass" value="juliusstraat">
    <button>Taakjes</button>
</form>
</div>
<div class="floating-link">
<a href="https://wiebetaaltwat.nl/login">WBW</a>
</div>
</div>
<script>
window.onload = function() {
	document.getElementsByTagName('div')[document.getElementsByTagName('div').length-1].style.display= "none";
}
</script>
</body>
</html>