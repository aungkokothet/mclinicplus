<?php
	error_reporting(E_ALL);
	ini_set("display_errors", 1);
	header('Cache-Control: no-cache, must-revalidate');
	header('Content-type: application/json');
	error_reporting(0);
	require_once 'include/connection_util.php';


	$installationId = $_POST["installationId"];

	try{
		$em = ConnectionUtil::getEntityManager();
//		$cri_str = ' WHERE stats.installationId=' . $installationId;
		$query =$em->createQuery("SELECT stats FROM STATS stats WHERE stats.installationId = :IID");
        $query->setParameter('IID', $installationId);
		$result = $query->getResult();
		if(count($result) > 0)
		{
			for ($i=0; $i<count($result); $i++)
			{
				$stats = $result[$i];
                $stats->setInstallationId($_POST["installationId"]);
				$stats->setCallCount($_POST["callCount"]);
						$stats->setAppointmentCount($_POST["appointmentCount"]);
						$stats->setOfflineCallCount($_POST["offlineCallCount"]);
		$stats->setOfflineAppointmentCount($_POST["offlineAppointmentCount"]);
						ConnectionUtil::beginTransaction();
										ConnectionUtil::save($stats);
		ConnectionUtil::commit();
			}

		}else{
			$stats = new STATS();
		$stats->setInstallationId($_POST["installationId"]);
		$stats->setCallCount($_POST["callCount"]);
		$stats->setAppointmentCount($_POST["appointmentCount"]);
		$stats->setOfflineCallCount($_POST["offlineCallCount"]);
		$stats->setOfflineAppointmentCount($_POST["offlineAppointmentCount"]);
			ConnectionUtil::beginTransaction();
						ConnectionUtil::save($stats);
		ConnectionUtil::commit();
		}


	}
	catch (Exception $e){
		$log->LogError($e->getMessage());
		echo $e->getMessage();
	}
?>
	