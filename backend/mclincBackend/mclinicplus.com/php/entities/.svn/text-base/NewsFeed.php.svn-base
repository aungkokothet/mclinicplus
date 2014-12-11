<?php


/** @Entity
  * @Table(name="news")
*/
class NewsFeed
{
 /** @Id @Column(type="string") */
   private $url;

    public function getUrl(){
   return $this->url;
   }

   public function setUrl($url){
   $this->url=$url;
   }

  /** @Column(type="string") */
   private  $title;

   public function getTitle(){
   return $this->title;
   }

   public function setTitle($title){
   $this->title=$title;
   }

  /** @Column(type="string") */
   private  $snippet;

   public function getSnippet(){
   return $this->snippet;
   }

   public function setSnippet($snippet){
   $this->snippet=$snippet;
   }

   public function toJSON(){
   	 return '{ "url":"'.$this->url.'","title":"'.$this->title.'","snippet":"'.$this->snippet.'"}';
	 }
}

?>