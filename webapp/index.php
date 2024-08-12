<?php 
include 'memberInfo.php';
?>
<html>
<head>
<meta name="theme-color" content="#3F51B5" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
</head>

<body>
  <nav class="navbar navbar-expand sticky-top navbar-dark" style="background: #3F51B5 !important;">
	<a class="navbar-brand" href="#">Atlantis</a>
	<div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
			<li class="nav-item">
                <a class="nav-link" href="bestellingen.php?loc=pc"><img src="icons8-shopping-cart-96.png" width="30" height="30"></a>
            </li>
			<li class="nav-item">
                <a class="nav-link" href="taakjes.php?loc=pc"><img src="icons8-janitor-96.png" width="30" height="30"></a>
            </li>
			<li class="nav-item">
                <a class="nav-link" href="paallijst.php?loc=pc"><img src="icons8-beer-96.png" width="30" height="30"></a>
            </li>
			<li class="nav-item">
                <a class="nav-link" href="streepjes.php?loc=pc"><img src="icons8-judge-96.png" width="30" height="30"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="aanbiedingen.php?loc=pc"><img src="icons8-discount-96.png" width="30" height="30" alt=""></a>
            </li>
        </ul>
	</div>
  </nav>



<div class="center-text" style="padding-top: 20px; font-size:0.95rem;">
<h6>Taakjes</h6>
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

<span class="floating-box-tight" style="font-size:14px;"><b><?php echo $name1; ?></b>
<br>
<?php echo $taakjes[$multiplier]; ?></span>
<span class="floating-box-tight" style="font-size:14px;"><b><?php echo $name2; ?></b>
<br>
<?php if($multiplier+1>5) {$task = $multiplier+1 - 6;} else {$task = $multiplier+1;}echo $taakjes[$task]; ?></span>
<br>
<span class="floating-box-tight" style="font-size:14px;"><b><?php echo $name3; ?></b>
<br>
<?php if($multiplier+2>5) {$task = $multiplier+2 - 6;}else {$task = $multiplier+2;}echo $taakjes[$task]; ?></span>
<span class="floating-box-tight" style="font-size:14px;"><b><?php echo $name4; ?></b>
<br>
<?php if($multiplier+3>5) {$task = $multiplier+3 - 6;}else {$task = $multiplier+3;}echo $taakjes[$task]; ?></span>
<br>
<span class="floating-box-tight" style="font-size:14px;"><b><?php echo $name5; ?></b>
<br>
<?php if($multiplier+4>5) {$task = $multiplier+4 - 6;}else {$task = $multiplier+4;}echo $taakjes[$task]; ?></span>
<span class="floating-box-tight" style="font-size:14px;"><b><?php echo $name6; ?></b>
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

<?php if ((date("W") % 2) == 1) {
	echo '<img src="doos-karton.svg" alt="kartonnen doos" >';
} ?>

<br>

Zet het oud papier <?php
$dagen = array("over 4 dagen","over 3 dagen","overmorgen","morgen","vandaag","over 6 dagen","over 5 dagen");
if ((date("W") % 2) == 1) {
	//They get oud papier on uneven weeks here.
	echo $dagen[date("w")];
} else {
	echo "volgende week";
}
?> aan de straat

<br>
<br>

Ome DUO betaalt  <?php
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
<h6>Links</h6>
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
<div class="center-text">
<script>
window.onload = function() {
	document.getElementsByTagName('div')[document.getElementsByTagName('div').length-1].style.display= "none";
}
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>