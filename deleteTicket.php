<?php
include 'server.php';
$data = array();

if (isset($_POST['booking_id']) && isset($_POST['booking_status'])) {

	$booking_id = $_POST['booking_id'];
	$booking_status = $_POST['booking_status'];

	$sql = "UPDATE booking SET booking_status = '$booking_status' WHERE booking_id = '$booking_id'";
	$user_code = $conn->query($sql);

	if ($user_code === TRUE) {
		$data['hasil'] = '1';
		$data['msg'] = 'Berhasil';
		
	}else{
		$data['hasil'] = '0';
		$data['msg'] = 'Gagal';
	}
	echo json_encode($data);

}
?>
