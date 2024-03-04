<?php
//Customer Registration API
 //getting the database connection
	require_once 'DbConnect.php';
	require_once 'function.php';
	//an array to display response
	$response = array();
	
	//if it is an api call 
	//that means a get parameter named api call is set in the URL 
	//and with this parameter we are concluding that it is an api call 
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			
						
				//in this part we will handle the registration
							case 'signup':
				//checking the parameters required are available or not 
				if(isTheseParametersAvailable(array('username','mobile','email','address','passwd'))){
					
					//getting the values 
					$username = $_POST['username']; 
                    $mobile = $_POST['mobile']; 
					$email = $_POST['email']; 
                                        $address=$_POST['address'];
					$passwd = md5($_POST['passwd']);
					
					
					//checking if the user is already exist with this username or email
					//as the email and username should be unique for every user 
					$stmt = $conn->prepare("SELECT id FROM customerregistration WHERE email = ? OR mobile = ?");
					$stmt->bind_param("ss", $email, $mobile);
					$stmt->execute();
					$stmt->store_result();
					
					//if the user already exist in the database 
					if($stmt->num_rows > 0){
						$response['error'] = true;
						$response['message'] = 'User already registered';
						$stmt->close();
					}else{
						
						//if user is new creating an insert query 
						$stmt = $conn->prepare("INSERT INTO customerregistration (username,mobile,email,address,passwd) VALUES (?, ?, ?, ?,?)");
						$stmt->bind_param("sssss",$username,$mobile,$email,$address,$passwd);
						
						//if the user is successfully added to the database 
						if($stmt->execute()){
							
							//fetching the user back 
							$stmt = $conn->prepare("SELECT id,id,username,mobile,email,address FROM customerregistration WHERE mobile = ?"); 
							$stmt->bind_param("s",$mobile);
							$stmt->execute();
							$stmt->bind_result($userid, $id,$username,$mobile,$email,$address); 
							$stmt->fetch();
							
							$user = array(
								'id'=>$id, 
								'username'=>$username, 
                                                                'mobile'=>$mobile,
								'email'=>$email,
							        'address'=>$address
							);


							
							$stmt->close();
							
							//adding the user data in response 
							$response['error'] = false; 
							$response['message'] = 'User registered successfully'; 
							$response['user'] = $user; 
						}
						
						else{
							$response['error'] = true; 
							$response['message'] = 'User Not registered'; 
							
						}
					}
					
				} //signup parameter check end
				else{
					$response['error'] = true; 
					$response['message'] = 'required parameters are not available'; 
				}
				
			break; 
			
				case 'login':
				
				if(isTheseParametersAvailable(array('mobile','passwd'))){
					
					$mobile = $_POST['mobile'];
					$passwd = md5($_POST['passwd']); 
					
					$stmt = $conn->prepare("SELECT id,username,mobile,email,address FROM customerregistration WHERE mobile = ? AND passwd = ?");
					$stmt->bind_param("ss",$mobile,$passwd);
					
					$stmt->execute();
					
					$stmt->store_result();
					//echo $stmt->num_rows;
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id,$username,$mobile,$email,$address);
						$stmt->fetch();
						
						$user = array(
							'id'=>$id, 
							'username'=>$username, 
							'mobile'=>$mobile, 
							'email'=>$email,
							'address'=>$address
						);
						
						$response['error'] = false; 
						$response['message'] = 'Login successfull'; 
						$response['user'] = $user; 
					}else{
						$response['error'] = true; 
						$response['message'] = 'Invalid mobile or passwd';
					}
				}
				else{
					$response['error'] = false; 
						$response['message'] = 'Invalid mobile or passwd******';
				}
			break; 
			
			default: 
				$response['error'] = true; 
				$response['message'] = 'Invalid Operation Called';
		}
		
	}
else{
		$response['error'] = true; 
		$response['message'] = 'Invalid API Call';
	}
	
	echo json_encode($response);
	
	?>
			
			
