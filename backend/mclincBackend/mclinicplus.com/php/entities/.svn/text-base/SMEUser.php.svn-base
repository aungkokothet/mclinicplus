<?php
//require_once './entities/User.php';

/** @Entity
  * @Table(name="smeuser")
*/
class SMEUser
{
  /** @Column(type="string") */
   private $fullName;

   /** @Id @Column(type="string") */
   private $email;

   /** @Column(type="string") */
   private $phoneNo;

   /**
     * @OneToOne(targetEntity="User",cascade={"all"})
     * @JoinColumn(name="email", referencedColumnName="email")
     **/
   private $user;
   
/** @ManyToMany(targetEntity="SMECompany", inversedBy="users")
 *  @JoinTable(name="smeuser2company",
 *      joinColumns={@JoinColumn(name="email", referencedColumnName="email")},
 *      inverseJoinColumns={@JoinColumn(name="companyId", referencedColumnName="id")}
 *      )
 */
   private $companies;

   function __construct() {
          $this->user= new User();
   }
   
    public function getCompanies(){
   return $this->companies;
   }

   public function getEmail(){
   return $this->email;
   }

   public function getPhoneNo(){
   return $this->phoneNo;
   }

   public function getPassword(){
   return $this->user->getPassword();
   }

   public function getFullName(){
   return $this->fullName;
   }

   public function setPhoneNo($phoneNo){
   $this->phoneNo=$phoneNo;
   }

   public function setEmail($email){
   $this->email=$email;
   $this->user->setEmail($email);

   }

   public function setPassword($psddword){
   $this->user->setPassword($psddword);

   }

   public function setFullName($fullName){
   $this->fullName=$fullName;
   }
   
   public function setCompanies($companies){
   $this->companies=$companies;
   }
   
   public function toJSON(){
	//return '{ "fullName":"'.$this->fullName.'","password":"'.$this->getPassword().'","email":"'.$this->email.'","phoneNo":"'.$this->phoneNo.'"}';
	return '{ "fullName":"'.$this->fullName.'","password":"'.$this->getPassword().'","email":"'.$this->email.'","phoneNo":"'.$this->phoneNo.'"}';
	 }
}
?>