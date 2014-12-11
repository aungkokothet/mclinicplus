<?php

/** @Entity
  * @Table(name="directorytag")
*/
class DirectoryTag1
{
/** @Id @Column(type="bigint")
 * @GeneratedValue
 */
   private $directorytagid;

      public function getdirectorytagid(){
      return $this->directorytagid;
      }

	  public function setdirectorytagid($directorytagid){
	   $this->directorytagid=$directorytagid;
	   }
	   
	   /** @Column(type="bigint")
 */
   private $directoryid;

      public function getdirectoryid(){
      return $this->directoryid;
      }

	  public function setdirectoryid($directoryid){
	   $this->directoryid=$directoryid;
	   }
	   
	   /** @Column(type="bigint")
 */
   private $tagid;

      public function gettagid(){
      return $this->tagid;
      }

	  public function settagid($tagid){
	   $this->tagid=$tagid;
	   }
	   
	public function toJSON(){
	return '{ "directorytagid":"'.$this->directorytagid.'","directoryid":"'.$this->directoryid.'","tagid":"'.$this->tagid.'"}';
	 }
}
?>