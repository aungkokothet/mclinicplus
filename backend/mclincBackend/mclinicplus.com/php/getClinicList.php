<?php error_reporting(E_ALL);
  ini_set("display_errors", 1);
  header('Cache-Control: no-cache, must-revalidate');
  header('Content-type: application/json');
 require_once 'include/connection_util.php';


try{
$em = ConnectionUtil::getEntityManager();
$docQuery =$em->createQuery("SELECT doctor FROM Doctor doctor");
$docArray = $docQuery->getResult();
$doctorArray =array();

if(count($docArray)!=0){

for ($i=0; $i<count($docArray); $i++)
  {
     $doctorArray[$docArray[$i]->getId()]=$docArray[$i];
   //   echo $docArray[i]->getId();
  //    echo $docArray[i]->toJSON().",";
  }

}



echo '[';
$query =$em->createQuery("SELECT clinic FROM Clinic clinic");
$array = $query->getResult();
if(count($array)==0){
echo "]},";
}else  {
for ($i=1; $i<count($array); $i++)
  {
      $scheduleQuery =$em->createQuery("SELECT schedule FROM Schedule schedule WHERE schedule.clinicId = :cID");
      $scheduleQuery->setParameter('cID',$array[$i-1]->getId() );
$scheduleArray = $scheduleQuery->getResult();
for ($j=0; $j<count($scheduleArray); $j++)
  { 
      $doctor=$doctorArray[$scheduleArray[$j]->getDoctorId()];
      $doctor->addSchedule($scheduleArray[$j]);
       $array[$i-1]->addDoctor($doctor);
//      echo $doctorArray[$scheduleArray[$j-1]->getDoctorId()]->toJSON().",";
 //   echo  $scheduleArray[$j-1]->toJSON().",";  
  }
  echo  $array[$i-1]->toJSON().",";

  }
      $scheduleQuery =$em->createQuery("SELECT schedule FROM Schedule schedule WHERE schedule.clinicId = :cID");
      $scheduleQuery->setParameter('cID',$array[count($array)-1]->getId() );
      $scheduleArray = $scheduleQuery->getResult();
for ($j=0; $j<count($scheduleArray); $j++)
  { 
      $doctor=$doctorArray[$scheduleArray[$j]->getDoctorId()];
      $doctor->addSchedule($scheduleArray[$j]);
      $array[count($array)-1]->addDoctor($doctor);
    //  echo $doctorArray[$scheduleArray[$j-1]->getDoctorId()]->toJSON().",";
 //   echo  $scheduleArray[$j-1]->toJSON().",";  
  }
echo  $array[count($array)-1]->toJSON()."]";
}

}catch (Exception $e){
$log->LogError($e->getMessage());
}
?>