<?php session_start() ?>
<?php
if (isset($_GET['logout'])) {
	session_destroy();
	header('Location: index.php');
}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<title>Artist Alert - SD&amp;D</title><link href="default.css" rel="stylesheet" type="text/css"></head>
<body>

<?php require_once('header.php'); ?>

<div id="content">
	<?php require_once('sidebar.php') ?>
	<div id="main">
		<div id="welcome" class="post">
			<h2 class="title">Your Music Source</h2>*Insert VISION STATEMENT here* 	
		</div>		
	</div>
</div>

<?php require_once('footer.php'); ?>

</body></html>