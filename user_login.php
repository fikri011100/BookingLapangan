<?php
    include "server.php";
    
    if (isset($_POST['email']) && isset($_POST['password'])) {
        
        $user_name = $_POST['email'];
        $user_password = $_POST['password'];
        $password=md5($user_password);
        $response = array();
        
        $query = "SELECT * FROM user WHERE email ='$user_name' AND password='$password'";
        $hasil = mysqli_query($conn,$query);
        
        if(!$hasil ||mysqli_num_rows($hasil)>0){
            $response['user']= array();
            
            while ($row = mysqli_fetch_assoc($hasil)) {
                
                $data= array();
                
                $data['user_id']=$row['user_id'];
                $data['username']=$row['username'];
                $data['email']=$row['email'];
                $data['password']=$row['password'];
                $data['no_telp']=$row['no_telp'];
                $data['status']=$row['status'];
                
                array_push($response['user'],$data);
            }
            
            $response['result']= true ;
            $response['msg']="Berhasil";
            echo json_encode($response);
            
            
        } else {
            $response['result']= false ;
            $response['msg']="Gagal";
            echo json_encode($response);
        }
    }
    ?>
