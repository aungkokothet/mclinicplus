<?php


/** @Entity 
  * @Table(name="user") 
*/
class User
{
   /** @Id @Column(type="string") */
   private $email;
   
   /** @Column(type="string") */
   private $password;
   
   public function getEmail(){
   return $this->email;
   }
   
   public function getPassword(){
   return $this->password;
   }
   
   public function setEmail($email){
   $this->email=$email;
   }
   
   public function setPassword($password){
   $this->password=$password;
   }
}
?>
