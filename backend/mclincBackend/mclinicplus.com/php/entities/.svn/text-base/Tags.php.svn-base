<?php
/** @Entity
  * @Table(name="tags")
*/
class Tags
{
/** @Id @Column(type="bigint")
 * @GeneratedValue
 */
   private $tagid;

      public function getTagid(){
      return $this->tagid;
      }

	  public function setTagid($tagid){
	   $this->tagid=$tagid;
	   }
	   
	   /** @Column(type="string")
 */
   private $tagname;

      public function getTagname(){
      return $this->tagname;
      }

	  public function setTagname($tagname){
	   $this->tagname=$tagname;
	   }
	   
	public function toJSON(){
	return '{ "tagid":"'.$this->tagid.'","tagname":"'.$this->tagname.'"}';
	 }
}
?>