<?php
// MYSQL DB Connectivity
$servername = "localhost";
$username = "root";
$password = "";
$database = "water";

//creating a new connection object using mysqli 
$conn = new mysqli($servername, $username, $password, $database);

//if there is some error connecting to the database
//with die we will stop the further execution by displaying a message causing the error 
if ($conn->connect_error) {
    die("Connection failed:Server Name is Wrong " . $conn->connect_error);
}
?>
