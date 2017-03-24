<?php include('DBConnection.php');
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed");
}
$sql = 'SELECT * FROM user where username="'.$_POST["uname"].'" and password="'.$_POST["pwd"].'"';
$result = $conn->query($sql);
if ($result->num_rows > 0)
{
	echo json_encode(array('status' => "Authenticated"));
}
else
{
    echo json_encode(array('status' => "Invalid Username or Password"));
}
$conn->close();
?>