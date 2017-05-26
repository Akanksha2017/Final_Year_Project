<?php
require_once 'include/db_function.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['mail']) && isset($_POST['password']) && isset($_POST['category'])) {
 
    // receiving the post params
    $mail = $_POST['mail'];
    $password = $_POST['password'];
	$category = $_POST['category'];
 
    // get the user by email and password
    $user = getUserByEmailAndPassword($mail, $password, $category);
 
    if ($user) {
        // user is found
        $response["error"] = FALSE;
	$response["user"]["category"] = $user["category"];
        $response["user"]["mail"] = $user["mail"];
		
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Wrong email or password entered! Please try again!";
        echo json_encode($response);
    }
} else {
    //required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing :(!";
    echo json_encode($response);
}
?>