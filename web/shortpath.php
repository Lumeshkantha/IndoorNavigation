<?php include('DBConnection.php');
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed");
}
$flocation=4;
$selectedracks=array(1,2,5);
$orderedracks=array();
foreach($selectedracks as $rack)
{
	$status=false;
	if(count($orderedracks)==0)
	{
		$sql = 'SELECT * FROM `'.$flocation.'` ORDER BY distance';
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			while($row = $result->fetch_assoc())
			{
				foreach($selectedracks as $srack)
				{
					if($srack==$row["rid"])
					{
						$orderedracks[]=array('rid'=>$row["rid"]);
						$status=true;
						break;
					}
				}
				if($status)
				{
					break;
				}
			}
		}
	}
	else
	{
		$sql = 'SELECT * FROM `'.$orderedracks[count($orderedracks)-1]['rid'].'` ORDER BY distance';
		$result = $conn->query($sql);
		if ($result->num_rows > 0)
		{
			while($row = $result->fetch_assoc())
			{
				foreach($selectedracks as $srack)
				{
					if($srack==$row["rid"])
					{
						foreach($orderedracks as $orack)
						{
							if($orack["rid"]==$row["rid"])
							{
							}
							else
							{
								$orderedracks[]=array('rid'=>$row["rid"]);
								//echo $row["rid"];
								break 2;
							}
						}
					}
				}
			}
		}
		break;
	}
}

echo json_encode($orderedracks);
$conn->close();
?>