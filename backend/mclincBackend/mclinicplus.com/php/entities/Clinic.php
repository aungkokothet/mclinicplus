<?php
/** @Entity
  * @Table(name="CP_CLINIC")
*/
class Clinic
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
      private $address;

      public function getAddress(){
	        return $this->address;
	  }

	  public function setAddress($address){
	  	   $this->address=$address;
	   } 
	   
  /** @Column(type="decimal") */
      private $lat;

      public function getLat(){
	        return $this->lat;
	  }

	  public function setLat($lat){
	  	   $this->lat=$lat;
	   } 
	   
	     /** @Column(type="decimal") */
	         private $lang;
	   
	         public function getLang(){
	   	        return $this->lang;
	   	  }
	   
	   	  public function setlang($lang){
	   	  	   $this->lang=$lang;
	   } 
	   
   /** @Column(type="string") */
      private $contactNo;

      public function getContactNo(){
	  return $this->contactNo;
	  }

	  public function setContactNo($contactNo){
	  $this->contactNo=$contactNo;
	   }

    private $doctors;
    
    public function addDoctor($doctor){
        $this->doctors[$doctor->getId()]=$doctor;
    }

public function __construct()
  {
    $this->$doctors=array(); 
  }
	   
 public function toJSON(){
	// return '{"name":"'.$this->name;
	 $ret ='{ "name":"'.$this->name.'","address":"'.$this->address.'","lat":"'.$this->lat.'","lang":"'.$this->lang.'","contactNo":"'.$this->contactNo.'","doctors":[{}';
 if(count($this->doctors)!=0){
 foreach($this->doctors as $doctor){
   $ret=$ret.','.$doctor->toJSON();
 }
 }
 $ret=$ret.']}';
     //'"}';
	 return $ret;
     }	      	   	     	      	     	     	       	    
}
?>