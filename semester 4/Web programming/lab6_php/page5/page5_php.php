<?php
$dbhost = "localhost";
$dbuser = "root";
$dbpass = "password";
$db = "recipe";
$conn = new mysqli($dbhost, $dbuser, $dbpass, $db);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$type="'".$_GET["type"]."'";

$sqlCommand="SELECT * FROM Recipe WHERE Type=".$type;
$result = $conn->query($sqlCommand);

echo '<table style="border-collapse: collapse; margin: auto; height: 150px; width: 300px;">';
echo '<tr>';
echo '<th style="border: 1px solid black;font-size: 20px;">ID</th>';
echo '<th style="border: 1px solid black;font-size: 20px;">Author</th>';
echo '<th style="border: 1px solid black;font-size: 20px;">Name</th>';
echo '<th style="border: 1px solid black;font-size: 20px;">Description</th>';
echo '</tr>';
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        echo '<tr>';
        echo '<td style="border: 1px solid black;font-size: 20px;">'.$row["id"].'</td>';
        echo '<td style="border: 1px solid black;font-size: 20px;">'.$row["Author"].'</td>';
        echo '<td style="border: 1px solid black;font-size: 20px;">'.$row["Name"].'</td>';
        echo '<td style="border: 1px solid black;font-size: 20px;">'.$row["Description"].'</td>';
        echo '</tr>';
    }
} else {
    echo "0 results";
}
echo '</table>';
$conn->close();
?>
