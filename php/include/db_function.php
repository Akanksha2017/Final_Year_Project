<?php 
require_once 'include/db_connection.php';
	function registerStudent($name, $year, $email, $department, $password, $category){
		global $connection;
		$flag = false;
		$return_string = "";
		$content = "log of ". $name;
		$logPath = $_SERVER['DOCUMENT_ROOT'] . "/log/".$name.".txt";
		$fp = fopen($logPath,"wb");
		fwrite($fp,$content);
		fclose($fp);
		$query = "INSERT INTO student(";
		$query .= "name,year,deptt,logFilePath,mail) ";
		$query .= "VALUES('{$name}', '{$year}', '{$department}', '{$logPath}', '{$email}')";
		$result = mysqli_query($connection, $query);
		
		if($result){
			$user = "SELECT * FROM student WHERE mail = '{$email}'";
			$res = mysqli_query($connection, $user);
			while ($user = mysqli_fetch_assoc($res)){
				$flag = true;
				break;
			}
		}else{
				$flag = false;
		}
		
		if($flag){
			if($category == 'council'){
				$id = $user['id'];
				$query_pass = "INSERT INTO council(";
				$query_pass .= "password, student_id)";
				$query_pass .= "VALUES ('{$password}', '{$id}')";
				$res_council = mysqli_query($connection, $query_pass);
				if($res_council){
					$flag = true;
				}
			}
			else if($category == 'president'){}
			else if($category == 'member'){}
			else
				$flag = false;
		}
		return $flag;
	}
	function getUserByEmailAndPassword($mail, $password, $category){
		global $connection;
		$query = "SELECT * from student where mail = '{$mail}'";

	    $res = mysqli_query($connection, $query);
		
		if($res){
			while ($user = mysqli_fetch_assoc($res)){
				break;
			}
		}
		else{
			return false;
		}	
		if($user){
			if($category == 'council'){
                                $id = $user['id'];
				$query_pass = "SELECT * from council where student_id = '{$id}'";
				$result = mysqli_query($connection, $query_pass);
				if($result){
					while($pass_c = mysqli_fetch_assoc($result)){break;}
				}
				else return false;
				if($pass_c['password'] == $password){
					$res_council['category'] = $category;
					$res_council['mail'] = $mail;
					return $res_council;
                }
				else 
					return false;
			}
			else if($category == 'president'){}
			else if($category == 'member'){}
			else
				return false;
		}
		else{
			return false;
		}
	}
	function emailExists($email){
		global $connection;
		$query = "SELECT mail from student where mail = '{$email}'";
		$result = mysqli_query($connection, $query);
		if(mysqli_num_rows($result) > 0){
			return true;
		}else{
			return false;
		}
	}
?>