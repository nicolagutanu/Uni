<?php
$dbhost = "localhost";
$dbuser = "root";
$dbpass = "password";
$db = "recipe";
$conn = new mysqli($dbhost, $dbuser, $dbpass, $db);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
$id=$_POST["id"];

$sqlCommand = "DELETE FROM Recipe WHERE id=".$id;
$conn->query($sqlCommand);
if ($conn->affected_rows == 1) {
    echo '<p style="font-size: 20px; margin-left: 90px;">Record deleted successfully</p>';
} else {
    echo '<p style="font-size: 20px; margin-left: 90px;">Record could not be deleted</p>';
}
$conn->close();
?>
