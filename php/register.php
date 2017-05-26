<?php
 
require_once 'include/db_function.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['name']) && isset($_POST['year']) && isset($_POST['deptt']) && isset($_POST['mail']) && isset($_POST['category']) && isset($_POST['password'])) {
 
    // receiving the post params
    $name = $_POST['name'];
	$year = $_POST['year'];
	$deptt = $_POST['deptt'];
	$mail = $_POST['mail'];
	$category = $_POST['category'];
	$password = $_POST['password'];
 
    // check if user already exists with the same email
    if(emailExists($mail)){
		// email already exists
        $response["error"] = TRUE;
        $response["error_msg"] = "Email already exists with " . $email;
        echo json_encode($response);
	}else {
        // create a new user
        $user = registerStudent($name, $year, $mail, $deptt, $password, $category );
        if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
			$response["message"] = "You were registered successfully!";
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred! $user appended ". $user;
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>