
<?php
$servername = "localhost";
$username = "root";
$password = "Jembawan8";
$database = "bookinglapangan";

// Create connection to database
$conn = new mysqli($servername, $username, $password, $database);

// Check connection whether connect or not
if ($conn->connect_errno) {
    printf("Error: " ,  $conn->connect_error);
}

?>


