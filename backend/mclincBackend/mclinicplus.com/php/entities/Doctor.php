<?php
/** @Entity
  * @Table(name="CP_DOCTOR")
*/
class Doctor
{
/** @Id @Column(type="integer")
 * @GeneratedValue
 */
   private $id;

      public function getId(){
      return $this->id;
      }

	  public function setId($id){
	   $this->id=$id;
	   }




   /** @Column(type="string") */
      private $name;

      public function getName(){
	        return $this->name;
	        }

	  	  public function setName($name){
	  	   $this->name=$name;
	   }



	  /** @Column(type="string") */
      private $qualification;

      public function getQualification(){
	        return $this->qualification;
	  }

	  public function setQualification($qualification){
	  	   $this->qualification=$qualification;
	   }


   /** @Column(type="string") */
      private $specialization;

      public function getSpecialization(){
	  return $this->specialization;
	  }

	  public function setSpecialization($specialization){
	  $this->specialization=$specialization;
	   }

        private $schedules;

     public function addSchedule($schedule){
         $this->schedules[]=$schedule;
     }

function __construct() {
       $this->schedules=array();
   }

 public function toJSON(){
	// return '{"name":"'.$this->name;
	 $ret= '{ "name":"'.$this->name.'","qualification":"'.$this->qualification.'","specialization":"'.$this->specialization.'","schedule":[{}';
     if(count($this->schedules)!=0){
      foreach($this->schedules as $schedule){
   $ret=$ret.','.$schedule->toJSON();
 }   
     }
     $ret=$ret.']}';
     return $ret;
	 }
}
?>