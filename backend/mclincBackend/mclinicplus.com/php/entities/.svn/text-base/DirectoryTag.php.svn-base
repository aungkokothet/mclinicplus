<?php
//require_once './entities/Tags.php';

/** @Entity
  * @Table(name="directorytag")
*/
class DirectoryTag
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
	   
	  public function getTagname(){
      return $this->tags->getTagname();
      }

	  public function setTagname($tagname){
	   $this->tags->setTagname($tagname);
	   }
	   
	  public function getTTagid(){
      return $this->tags->getTagid();
      }

	  public function setTTagid($tagid){
	   $this->tags->setTagid($tagid);
	   }
	 
	/**
     * @OneToOne(targetEntity="Tags",cascade={"all"})
     * @JoinColumn(name="tagid", referencedColumnName="tagid")
     **/
	protected $tags;
	   
	function __construct() {
          $this->tags = new Tags();         
	}
	   
	public function toJSON(){
	return '{ "directorytagid":"'.$this->directorytagid.'","directoryid":"'.$this->directoryid.'","tagid":"'.$this->tagid.'"}';
	 }
}
?>