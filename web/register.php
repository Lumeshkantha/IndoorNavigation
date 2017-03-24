<?php include('DBConnection.php');
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed");
}
$sql = 'SELECT * FROM user where username="'.$_POST["uname"].'"';
$result = $conn->query($sql);
if ($result->num_rows > 0)
{
	echo json_encode(array('status' => "Please choose diffrent username"));
}
else
{
	$sql = 'insert into user values(0,"'.$_POST["uname"].'","'.$_POST["pwd"].'","'.$_POST["fname"].'","'.$_POST["lname"].'");';
	$result = $conn->query($sql);
	if ($result=== TRUE)
	{
		echo json_encode(array('status' => "Registerd"));
	}
	else
	{
		echo json_encode(array('status' => "Registration failed"));
	}
}
$conn->close();
?>