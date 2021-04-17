<?php
    $dbhost = "localhost";
    $dbuser = "root";
    $dbpass = "password";
    $db = "recipe";
    $conn = new mysqli($dbhost, $dbuser, $dbpass, $db);

    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }
    echo "Connected successfully";
?>
