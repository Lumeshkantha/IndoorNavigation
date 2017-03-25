<?php include('DBConnection.php');
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed");
}
$sql = 'SELECT * FROM item';
$result = $conn->query($sql);
if ($result->num_rows > 0)
{
	$item=array();
	while($row = $result->fetch_assoc())
	{
        $item[]=array('name'=>$row["name"]);
	}
	echo json_encode($item);
}
$conn->close();
?>