
<?php
$servername = "localhost";
$username = "root";
$password = "Jembawan8";
$database = "bookinglapangan";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_errno) {
    printf("Error: " ,  $conn->connect_error);
}

?>


