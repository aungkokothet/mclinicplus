<?php
/** @Entity
  * @Table(name="CP_STATS")
*/
class STATS
{
/** @Id @Column(type="string")
 */
   private $installationId;

      public function getInstallationId(){
      return $this->installationId;
      }

	  public function setInstallationId($installationId){
	   $this->installationId=$installationId;
	   }




   /** @Column(type="integer") */
      private $callCount;

      public function getCallCount(){
	        return $this->callCount;
	        }

	  	  public function setCallCount($callCount){
	  	   $this->callCount=$callCount;
	   }



	  /** @Column(type="integer") */
      private $appointmentCount;

      public function getAppointmentCount(){
	        return $this->appointmentCount;
	  }

	  public function setAppointmentCount($appointmentCount){
	  	   $this->appointmentCount=$appointmentCount;
	   }

  /** @Column(type="integer") */
      private $offlineCallCount;

      public function getOfflineCallCount(){
	        return $this->offlineCallCount;
	  }

	  public function setOfflineCallCount($offlineCallCount){
	  	   $this->offlineCallCount=$offlineCallCount;
	   }

	     /** @Column(type="integer") */
	         private $offlineAppointmentCount;

	         public function getOfflineAppointmentCount(){
	   	        return $this->offlineAppointmentCount;
	   	  }

	   	  public function setOfflineAppointmentCount($offlineAppointmentCount){
	   	  	   $this->offlineAppointmentCount=$offlineAppointmentCount;
	   }






 public function toJSON(){
	// return '{"name":"'.$this->name;
	 return '{ "installationId":"'.$this->installationId.'","callCount":"'.$this->callCount.'","appointmentCount":"'.$this->appointmentCount.'","offlineCallCount":"'.$this->offlineCallCount.'","offlineAppointmentCount":"'.$this->offlineAppointmentCount.'"}';
	 }
}
?>