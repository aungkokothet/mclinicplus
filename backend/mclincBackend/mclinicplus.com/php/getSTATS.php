<?php error_reporting(E_ALL);
  ini_set("display_errors", 1);
  header('Cache-Control: no-cache, must-revalidate');
  header('Content-type: application/json');
 require_once 'include/connection_util.php';


try{
$em = ConnectionUtil::getEntityManager();
echo '[';
$query =$em->createQuery("SELECT stats FROM STATS stats");
$array = $query->getResult();
if(count($array)==0){
echo "]},";
}else  {
for ($i=1; $i<count($array); $i++)
  {
  echo  $array[$i-1]->toJSON().",\n";

  }
echo  $array[count($array)-1]->toJSON()."]";
}

}catch (Exception $e){
$log->LogError($e->getMessage());
}
?>