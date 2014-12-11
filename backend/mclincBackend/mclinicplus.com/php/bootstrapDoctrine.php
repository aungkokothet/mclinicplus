<?php
// test.php
use Doctrine\ORM\Tools\Setup;
use Doctrine\ORM\EntityManager;

use Doctrine\ORM\Configuration;
require_once './lib/DoctrineORM/Doctrine/ORM/Tools/Setup.php';
require_once 'entities/User.php';
require_once 'entities/SMEUser.php';
require_once 'entities/IndustrialZone.php';
require_once 'entities/SMECompany.php';
require_once 'entities/NewsFeed.php';
require_once 'entities/Tags.php';
require_once 'entities/UserDirectory.php';
require_once 'entities/DirectoryTag.php';
require_once 'entities/DirectoryCompany.php';
require_once './lib/klogger/KLogger.php';
date_default_timezone_set('Asia/Singapore');

$lib = "./lib/DoctrineORM/";
$log = new KLogger ( "../logs/log.txt" , KLogger::DEBUG );
Setup::registerAutoloadDirectory($lib);




    $cache = new \Doctrine\Common\Cache\ArrayCache;


$config = new Configuration;
$config->setMetadataCacheImpl($cache);
$driverImpl = $config->newDefaultAnnotationDriver('./');
$config->setMetadataDriverImpl($driverImpl);
$config->setQueryCacheImpl($cache);
$config->setProxyDir('./');
$config->setProxyNamespace('MyProject\Proxies');


    $config->setAutoGenerateProxyClasses(true);


$dbParams = array(
    'dbname' => 'mclinicplus',
    'user' => 'mclinicplus',
    'password' => 'Pass1234@myauk',
    'host' => '68.178.143.155',
    'driver' => 'pdo_mysql',
);


$em = EntityManager::create($dbParams, $config);

//$user =$em->find('User','yenaingaung@gmail.com');
//echo  $user->getPassword();
?>