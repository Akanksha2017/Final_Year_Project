<?php
	/**
	*Database config variables
	*/
	define("DB_HOST","localhost");
	define("DB_USER","id1392615_abp_admin");
	define("DB_PASSWORD","1stAndroidAppDb");
	define("DB_DATABASE","id1392615_ecadroid_db");
	$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
	if(mysqli_connect_errno()){
		die("Database connnection failed " . "(" .
			mysqli_connect_error() . " - " . mysqli_connect_errno() . ")"
				);
	}
?>