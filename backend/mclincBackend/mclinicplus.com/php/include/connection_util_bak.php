<?php
// test.php
use Doctrine\ORM\Tools\Setup;
use Doctrine\ORM\EntityManager;
use Doctrine\ORM\Configuration;

require_once 'commonutil.php';
require_once 'entities/User.php';
require_once 'entities/SMEUser.php';
require_once 'entities/IndustrialZone.php';
require_once 'entities/SMECompany.php';
require_once 'entities/NewsFeed.php';
require_once 'entities/Tags.php';
require_once 'entities/UserDirectory.php';
require_once 'entities/UserDirectory1.php';
require_once 'entities/DirectoryTag.php';
require_once 'entities/DirectoryTag1.php';

?>

<?php
/**
 *
 * @author thanhtetaung
 *
 */
class ConnectionUtil{
	private static $em=NULL;
	private static $lib = "lib/DoctrineORM/";

	private static $dbParams = array(
    'dbname' => 'msmeadminuat',
    'user' => 'msmeadminuat',
    'password' => 'Myanmarsme@123',
    'host' => 'msmeadminuat.db.6631622.hostedresource.com',
    'driver' => 'pdo_mysql',
	);

	/*
	private static $dbParams = array(
    'dbname' => 'msmeadmiin',
    'user' => 'root',
    'password' => 'root',
    'host' => 'localhost',
    'driver' => 'pdo_mysql',
	);*/
	private function _construct(){

	}

	/**
	 * getting EntityManger
	 * @return  EntityManager
	 */
	public static function getEntityManager(){
		if(is_null(ConnectionUtil::$em)){
			Setup::registerAutoloadDirectory(ConnectionUtil::$lib);
			$cache = new \Doctrine\Common\Cache\ArrayCache;
			$config = new Configuration;
			$config->setMetadataCacheImpl($cache);
			$driverImpl = $config->newDefaultAnnotationDriver('./');
			$config->setMetadataDriverImpl($driverImpl);
			$config->setQueryCacheImpl($cache);
			$config->setProxyDir('./');
			$config->setProxyNamespace('MyProject\Proxies');
			$config->setAutoGenerateProxyClasses(true);
			ConnectionUtil::$em = EntityManager::create(ConnectionUtil::$dbParams, $config);
		}
		return ConnectionUtil::$em;
	}

	/**
	 * beginning transaction
	 */
	public static function beginTransaction(){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->getConnection()->beginTransaction();
	}

	/**
	 * rollback current transaction to save point
	 */
	public static function rollback(){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->getConnection()->rollback();
	}

	/**
	 * committing current transaction
	 */
	public static function commit(){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->getConnection()->commit();
	}

	/**
	 * saving entity to database
	 * @param $entity entity object
	 */
	public static function save($entity){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->persist($entity);
		ConnectionUtil::$em->flush();
	}

	/**
	 * updating entity to database by id of entity
	 * @param $entity entity object
	 */
	public static function update($entity){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->merge($entity);
		ConnectionUtil::$em->flush();
	}

	/**
	 * delete from data base by id of entity
	 * @param  $entity entity object
	 */
	public static function delete($entity){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->remove($entity);
		ConnectionUtil::$em->flush();
	}

	/**
	 * excuting doctrine query language
	 * @param $dql doctrine query language
	 */
	public static function execute($dql){
		ConnectionUtil::getEntityManager();
		ConnectionUtil::$em->createQuery($dql)->execute();
		ConnectionUtil::$em->flush();
	}

	/**
	 * executing doctrine query language
	 * @param $dql doctrine query language
	 * @return query result
	 */
	public static function executeQuery($dql){
		ConnectionUtil::getEntityManager();
		$query=ConnectionUtil::$em->createQuery($dql);
		return $query->getResult();
	}

	/**
	 * executing doctrine query language by limitation
	 * @param $dql doctrine query language
	 * @param $limit limit
	 * @param $offset offset
	 * @return limited query result
	 */
	public static function executeQueryWithLimit($dql,$limit,$offset){
		ConnectionUtil::getEntityManager();
		$query=ConnectionUtil::$em->createQuery($dql);
		$query->setFirstResult($offset);
		$query->setMaxResults($limit);
		return $query->getResult();
	}

	/**
	 * finding by entity name with limitation
	 * @param $name entity name
	 * @param $order order by clasuse
	 * @param $limit limit
	 * @param $offset offset
	 * @return limited query result
	 */
	public static function findAllWithLimit($name,$order,$limit,$offset){
		ConnectionUtil::getEntityManager();
		return ConnectionUtil::$em->getRepository($name)->findBy(array(),$order,$limit,$offset);
	}

	/**
	 * finding by entity name
	 * @param $name  entity name
	 * @return query result
	 */
	public static function findAll($name){
		ConnectionUtil::getEntityManager();
		return ConnectionUtil::$em->getRepository($name)->findAll();
	}

	/**
	 * finding by id
	 * @param $name
	 * @param $id
	 * @return query result
	 */
	public static function find($name,$id){
		ConnectionUtil::getEntityManager();
		return ConnectionUtil::$em->find($name,$id);
	}

	/**
	 * finding unique by criteria
	 * @param $name
	 * @param $criteria
	 * @return unique query result
	 */
	public static function findOneBy($name,$criteria){
		ConnectionUtil::getEntityManager();
	 	return ConnectionUtil::$em->getRepository($name)
	 							  ->findOneBy($criteria);
	}

	/**
	 * finding by criteria
	 * @param $name
	 * @param $criteria
	 * @return query result
	 */
	public static function findByCriteria($name,$criteria){
		ConnectionUtil::getEntityManager();
		return ConnectionUtil::$em->getRepository($name)
	 							  ->findBy($criteria);
	}

	/**
	 * finding by criteria with limitation
	 * @param $name entity name
	 * @param $criteria criteria to filter
	 * @param $order order by clause
	 * @param $limit limit
	 * @param $offset offset
	 * @return limited query result
	 */
	public static function findByCriteriaWithLimit($name,$criteria,$order,$limit,$offset){
		ConnectionUtil::getEntityManager();
		return ConnectionUtil::$em->getRepository($name)
	 							  ->findBy($criteria,$order,$limit,$offset);
	}
}



?>