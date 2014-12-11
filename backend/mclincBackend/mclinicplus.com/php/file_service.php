<?php

	include_once 'include/file_util.php';
	include_once 'include/commonutil.php';
	
	$error = "";
	$msg = "";
	$fileName='';
	$filePath='';
	$fileElementName = 'file';
	$mode="";
	$logger=new KLogger ( "../logs/log.txt" , KLogger::DEBUG );
	if(isset($_POST["mode"])){
		$mode=$_POST["mode"];
	}
	if(!empty($_FILES[$fileElementName]['error']) && $mode=="save")
	{
		switch($_FILES[$fileElementName]['error'])
		{

			case '1':
				$error = 'The uploaded file exceeds the upload_max_filesize directive in php.ini!';
				break;
			case '2':
				$error = 'The uploaded file exceeds the MAX_FILE_SIZE directive that was specified in the HTML form!';
				break;
			case '3':
				$error = 'The uploaded file was only partially uploaded!';
				break;
			case '4':
				$error = 'No file was uploaded!';
				break;

			case '6':
				$error = 'Missing a temporary folder!';
				break;
			case '7':
				$error = 'Failed to write file to disk!';
				break;
			case '8':
				$error = 'File upload stopped by extension!';
				break;
			case '999':
			default:
				$error = 'No error code avaiable!';
		}
	}elseif((empty($_FILES['file']['tmp_name']) || $_FILES['file']['tmp_name'] == 'none') && $mode=='save')
	{
		$error = 'No file was uploaded..';
//	}else if(!isset($_POST["companyName"]))
	}else if($mode=='save')
	{
			try{
				 $fileName = $_FILES['file']['name'] ;
//				 $filePath="../images/". $_POST["companyName"]."/".$_FILES['file']['name'];
				$filePath="../images/temp/".$_FILES['file']['name'];
				$companyName="";
				if(isset($_POST["companyName"]))
					$companyName=FileUtil::validateFileName($_POST["companyName"])."/";
				 $logger->LogDebug("../images/".$companyName.$_FILES['file']['name']);
				if(!file_exists($filePath) && !file_exists("../images/".$companyName.$_FILES['file']['name'])){
//				if(!file_exists("../images/Test_s_/2.jpeg")){
		//			$msg .= " File Size: " . @filesize($_FILES['file']['tmp_name']);
					//for security reason, we force to remove all uploaded file
		//			@unlink($_FILES['fileToUpload']);		
					move_uploaded_file( $_FILES['file']['tmp_name'],$filePath);
				}
				else{
					$error="Duplicate file name!";
				}
//				@unlink($_FILES['file']);
				
			}
			catch(Exception $e){
				$error=$e->getMessage();
			}
	}
	else if($mode=='delete'){
		try{
			
//			@unlink($_POST['filePath']);
			$temp=$_POST["filePath"];
			if(strpos($_POST["filePath"],"/") ==0)
				$temp=".."+$temp;
			FileUtil::deleteFile($temp);
		}
		catch(Exception $e){
			$error=$e->getMessage();
		}
	}else if($mode=="cancel"){
		try{
			$dir = "../images/temp/"; 
//			$d = dir($dir); 
//			while($entry = $d->read()) { 
// 				if ($entry!= "." && $entry!= "..") { 
// 					@unlink($dir.$entry); 
// 				} 
//			} 
//			$d->close(); 
////			rmdir($dir); 
			FileUtil::deleteFilesInFolder($dir);
		}catch(Exception $e){
			$error=$e->getMessage();
		}
		
	}		
	echo "{";
	echo				"\"error\": \"" . $error . "\",\n";
	echo				"\"filePath\": \"" . $filePath . "\",\n";
	echo				"\"fileName\": \"" . $fileName . "\"\n";
	echo "}";
?>