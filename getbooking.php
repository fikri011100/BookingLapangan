<?php
    include 'server.php';  //memanggil file db.php
    if (isset($_POST["booking_user_email"])) {
        $booking_user_id = $_POST['booking_user_email'];
        $query = "SELECT * FROM  booking WHERE booking_user_email = '$booking_user_id' order by booking_id desc";
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
        $response = ['status' => $status, 'booking' => $response_data];
        echo json_encode($response);
    } else {
        $response['result']= false ;
            
        $response['msg']="maaf, data salah";
        echo json_encode($response);
    }
    
?>
