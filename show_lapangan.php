<?php
    include 'server.php';  //memanggil file db.php
        $query = "SELECT * FROM  field";
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
        $response = ['status' => $status, 'lapangan' => $response_data];
        echo json_encode($response);
    
    
?>
