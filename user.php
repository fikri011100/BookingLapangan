<?php
    include 'server.php';  //memanggil file db.php
    if (isset($_POST["email"])) {
        $booking_user_id = $_POST['email'];
        $query = "SELECT * FROM  user WHERE email = '$booking_user_id'";
        $hasil  = mysqli_query($conn,$query);
        $response_data = null;
        
        while ($data = $hasil->fetch_assoc()) {
            $response_data[] = $data;
        }

        if (is_null($response_data)) {
            $status = false;
        } else {
            $status = true;
        }
        header('Content-Type: application/json');
        $response = ['status' => $status, 'user' => $response_data];
        echo json_encode($response);
    } else {
        $response['result']= false ;
            
        $response['msg']="maaf, data salah";
        echo json_encode($response);
    }
    
?>
