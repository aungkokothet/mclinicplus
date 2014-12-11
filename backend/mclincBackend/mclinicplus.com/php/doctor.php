<?php error_reporting(E_ALL);
  ini_set("display_errors", 1);
  header('Cache-Control: no-cache, must-revalidate');
  header('Content-type: application/json');
 require_once 'include/connection_util.php';


try{
$em = ConnectionUtil::getEntityManager();
if($_SERVER['REQUEST_METHOD']=='POST'){
    
} else if($_SERVER['REQUEST_METHOD']=='GET'){
    echo 'GET';
} else if($_SERVER['REQUEST_METHOD']=='PUT'){
    
} else if($_SERVER['REQUEST_METHOD']=='DELETE'){
    
}
}catch (Exception $e){
$log->LogError($e->getMessage());
}
?>