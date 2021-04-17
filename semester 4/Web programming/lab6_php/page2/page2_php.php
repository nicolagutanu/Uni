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

$sqlCommand = "insert into Recipe (id, Author, Name, Type, Description)
values (".$id.",".$author.",".$name.",".$type.",".$desc.")";
$conn->query($sqlCommand);
if ($conn->affected_rows == 1) {
    echo '<p style="font-size: 20px; margin-left: 90px;">Record added successfully</p>';
} else {
    echo '<p style="font-size: 20px; margin-left: 90px;">Record could not be added</p>';
}
$conn->close();
?>
