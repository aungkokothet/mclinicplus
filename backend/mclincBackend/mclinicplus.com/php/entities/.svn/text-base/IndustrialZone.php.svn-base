<?php

/** @Entity
  * @Table(name="industrialZones")
*/
class IndustrialZone
{

 /** @Id @Column(type="bigint")
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
      private $lat;

      public function getLat(){
	  return $this->lat;
	  }

	  public function setLat($lat){
	  $this->lat=$lat;
	   }

   /** @Column(type="string") */
	  private $lng;

      public function getLng(){
	  return $this->lng;
	  }

	  public function setLng($lng){
	  $this->lng=$lng;
	   }


   /** @Column(type="string") */
      private $address;

      public function getAddress(){
	  return $this->address;
	  }

	  public function setAddress($address){
	  $this->address=$address;
	   }


   /** @Column(type="string") */
      private $contactNo;

      public function getContactNo(){
	  return $this->contactNo;
	  }

	  public function setContactNo($contactNo){
	  $this->contactNo=$contactNo;
	   }


   /** @Column(type="string") */
      private $contactNo2;

       public function getContactNo2(){
	  return $this->contactNo2;
	  }

	  public function setContactNo2($contactNo2){
	  $this->contactNo2=$contactNo2;
	   }


   /** @Column(type="string") */
      private $contactNo3;

      public function getContactNo3(){
	  return $this->contactNo3;
	  }

	  public function setContactNo3($contactNo3){
	  $this->contactNo3=$contactNo3;
	   }


   /** @Column(type="string") */
      private $area;

      public function getArea(){
	  return $this->area;
	  }

	  public function setArea($area){
	  $this->area=$area;
	   }


   /** @Column(type="string") */
      private $stateDvision;

      public function getStateDvision(){
	  return $this->stateDvision;
	  }

	  public function setStateDvision($stateDvision){
	  $this->stateDvision=$stateDvision;
	   }

  /** @Column(type="string") */
      private $midcZone;

      public function getMidcZone(){
	  return $this->midcZone;
	  }

	  public function setMidcZone($midcZone){
	  $this->midcZone=$midcZone;
	   }

  /** @Column(type="string") */
      private $establishmentYear;

      public function getEstablishmentYear(){
	  return $this->establishmentYear;
	  }

	  public function setEstablishmentYear($establishmentYear){
	  $this->establishmentYear=$establishmentYear;
	   }


    /** @Column(type="string") */
      private $industryCount;

      public function getIndustryCount(){
	  return $this->industryCount;
	  }

	  public function setIndustryCount($industryCount){
	  $this->industryCount=$industryCount;
	   }

	 public function toJSON(){
	// return '{"name":"'.$this->name;
	 return '{ "id":"'.$this->id.'","name":"'.$this->name.'","lat":"'.$this->lat.'","lng":"'.$this->lng.'","address":"'.$this->address.'","contactNo":"'.$this->contactNo.'","contactNo2":"'.$this->contactNo2.'","contactNo3":"'.$this->contactNo3.'","area":"'.$this->area.'","stateDivision":"'.$this->stateDvision.'","midcZone":"'.$this->midcZone.'","establishmentYear":"'.$this->establishmentYear.'","industryCount":"'.$this->industryCount.'"}';
	 }

}
?>