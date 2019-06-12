<?php
    
    include 'server.php';
    // $upload_path = 'image_lapangan/';
    // $server_ip = gethostbyname(gethostname());
    
    if(isset($_POST['field_name'])){
        
        $id_user = ($_POST['field_name']);
        // $filename = $_FILES['field_image']['name'];
        
        // move_uploaded_file($_FILES['field_image']['tmp_name'], $upload_path.$_FILES['field_image']['name']);
        
        $sql = "INSERT INTO field (field_name, field_image) VALUES ('$id_user', '')";
        $hasil = mysqli_query($conn,$sql);
        
        
        if($hasil){
            $response['error'] = false;
            $response['url'] = 'Berhasil';
            echo json_encode($response);
        }else {
            $response['error']= true ;
            $response['url']="maaf,terjadi kesalahan";
            echo json_encode($response);
        }
        
    } else{
        $response['error']= true ;
        
        $response['msg']="maaf, data salah";
        echo json_encode($response);
    }
    ?>




