<?php
    
    include 'server.php';
    $upload_path = 'image_lapangan/';
    $server_ip = gethostbyname(gethostname());
    $response = array();
    
    
    if($_SERVER['REQUEST_METHOD']=='POST'){
        
        if(isset($_FILES['field_image']['name'])&& isset($_POST['field_name'])){
            
            $id_user = ($_POST['field_name']);
            $filename = $_FILES['field_image']['name'];
            
            try{
                move_uploaded_file($_FILES['field_image']['tmp_name'], $upload_path.$_FILES['field_image']['name']);
                
                $sql = "INSERT INTO field (field_image, field_name) VALUES ('$filename', '$id_user')";
                
                if($conn->query($sql)){
                    $response['error'] = false;
                    $response['url'] = 'Berhasil';
                }
                
            }catch(Exception $e){
                $response['error']=true;
                $response['message']= 'Gagal';
            }
            echo json_encode($response);
            
            $conn->close();
        }else{
            $response['error']=true;
            $response['message']='Please choose a file';
        }
    }
    ?>
