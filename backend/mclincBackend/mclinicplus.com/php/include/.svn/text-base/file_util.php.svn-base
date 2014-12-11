<?php
require_once 'connection_util.php';

class FileUtil{
		public static function deleteFilesInFolder($dir){
			$d = dir($dir); 
			while($entry = $d->read()) { 
 				if ($entry!= "." && $entry!= "..") { 
 					@unlink($dir.$entry); 
 				} 
			} 
			$d->close(); 
		}
		public static function deleteFile($path){
			$logger=new KLogger ( "../logs/log.txt" , KLogger::DEBUG );
			$logger->LogDebug($path);
			if(file_exists($path)){
				$logger->LogDebug("inside");
				@unlink($path);
			}
		}
		public static function moveFile($path,$destination){
			if (file_exists($path) && copy($path,$destination)) {
 				 @unlink($path);
			}
		}
		
		public static function deleteFolder($dir){
			FileUtil::deleteFilesInFolder($dir);
			rmdir($dir);
		}
		
		public static function validateFileName($name){
			$name = str_replace("\\'","'",$name);
			$fileName=preg_replace('/[^A-Za-z0-9_\-]/', '_', $name);
			if(strlen($fileName)>255)
				return substr($fileName,0,255);
			else
				return $fileName;
			
		}
		
		public static function createDir($dir){
			if(!file_exists($dir))
				mkdir($dir);
		}
	}
?>