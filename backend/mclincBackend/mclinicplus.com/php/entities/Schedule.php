<?php
/** @Entity
  * @Table(name="CP_Schedule")
*/
class Schedule
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




   /** @Column(type="integer") */
      private $clinicId;

      public function getClinicId(){
	        return $this->clinicId;
	        }

	  	  public function setClinicId($clinicId){
	  	   $this->clinicId=$clinicId;
	   }



	  /** @Column(type="integer") */
      private $doctorId;

      public function getDoctorId(){
	        return $this->doctorId;
	  }

	  public function setDoctorId($doctorId){
	  	   $this->doctorId=$doctorId;
	   }

  /** @Column(type="string") */
      private $day;

      public function getDay(){
	        return $this->day;
	  }

	  public function setDay($day){
	  	   $this->day=$day;
	   }

	     /** @Column(type="string") */
	         private $startTime;

	         public function getStartTime(){
	   	        return $this->startTime;
	   	  }

	   	  public function setStartTime($startTime){
	   	  	   $this->startTime=$startTime;
	   }

   /** @Column(type="string") */
      private $endTime;

      public function getEndTime(){
	  return $this->endTime;
	  }

	  public function setEndTime($endTime){
	  $this->endTime=$endTime;
	   }




 public function toJSON(){
	// return '{"name":"'.$this->name;
	 return '{ "day":"'.$this->day.'","startTime":"'.$this->startTime.'","endTime":"'.$this->endTime.'"}';
	 }
}
?>