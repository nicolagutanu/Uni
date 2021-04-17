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
$author="'".$_POST["author"]."'";
$name="'".$_POST["name"]."'";
$type="'".$_POST["type"]."'";
$desc="'".$_POST["desc"]."'";

$sqlCommand = "UPDATE Recipe SET Author=".$author.", Name=".$name.", Type=".$type.", Description=".$desc." WHERE id=".$id;
$conn->query($sqlCommand);
if ($conn->affected_rows == 1) {
    echo '<p style="font-size: 20px; margin-left: 90px;">Record updated successfully</p>';
} else {
    echo '<p style="font-size: 20px; margin-left: 90px;">Record could not be updated</p>';
}
$conn->close();
?>
