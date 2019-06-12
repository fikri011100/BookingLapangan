<?php
    include 'server.php';  //memanggil file db.php
    
    //mengecek variabel
    if(isset($_POST["booking_user_email"]) && isset ($_POST["booking_place"]) && isset ($_POST["booking_category"]) && isset ($_POST["booking_hour"]) && isset ($_POST["booking_hour_until"]) && isset ($_POST["booking_dates"]) && isset ($_POST["booking_price"]) && isset ($_POST["booking_status"]))
    {
        $booking_user_id= $_POST['booking_user_email'];
        $booking_place= $_POST['booking_place'];
        $booking_category= $_POST['booking_category'];
        $booking_hour= $_POST['booking_hour'];
        $booking_hour_until= $_POST['booking_hour_until'];
        $booking_dates= $_POST['booking_dates'];
        $booking_price= $_POST['booking_price'];
        $booking_status= $_POST['booking_status'];
        
        
        $query = "INSERT INTO booking (booking_user_email,booking_place,booking_category, booking_hour, booking_hour_until,booking_dates , booking_price, booking_status) VALUES ('$booking_user_id','$booking_place','$booking_category','$booking_hour','$booking_hour_until' , '$booking_dates' ,'$booking_price' ,'$booking_status')";
        $hasil  = mysqli_query($conn,$query);
        if($hasil)
        {
            $response['result']= true ;
            $response["msg"]= "Pesanan berhasil, silakan Tunggu Konfirmasi";
            echo json_encode($response);
        }
        else {
            $response['result']= false ;
            $response['msg']="maaf,terjadi kesalahan";
            echo json_encode($response);
        }
        }else{
            $response['result']= false ;
            
            $response['msg']="maaf, data salah";
            echo json_encode($response);
        }
    
    ?>
