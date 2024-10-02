<?php

require_once("model/ActivityDAO.php");
require("model/ActivityEntryDAO.php");
require_once("controller/Controller.php");
require_once("utilities/CalculDistanceImpl.php");

use controllers\Controller;

class UploadActivityController extends Controller {

    public function get($request): void 
    {
        session_start();

        if (!isset($_SESSION['user_id'])) {
            header('Location: /connect');
            exit();
        }

        $userId = $_SESSION['user_id'];
        $user = UserDAO::getInstance()->getUserById($userId);
        if ($user) {
            $this->render("profil", ['user' => $user]);
        } else {
            echo "Erreur : utilisateur non trouvé.";
        }
    }


    
    
    public function post($request): void
    {
        session_start();

        if (!isset($_SESSION['user_id']) || !isset($_FILES['fichier']) || $_FILES['fichier']['error'] !== 0) {
            header('Location: /connect');
            exit();
        }

        $fileContent = file_get_contents($_FILES['fichier']['tmp_name']);
        $jsonData = json_decode($fileContent, true);

        if ($jsonData === null) {
            $this->render("upload", []);
            return;
        }

        // Process activity data
        $activityEntries = $jsonData['data'];
        $donnees_cardiaque = array_column($activityEntries, 'cardio_frequency');
        $donnees_longitude = array_column($activityEntries, 'longitude');
        $donnees_latitude = array_column($activityEntries, 'latitude');

        // Calculate activity metrics
        $duree = $this->calculerDureeActivite($activityEntries);
        [$heartRateMin, $heartRateAvg, $heartRateMax] = $this->determineHeartFrequency($donnees_cardiaque);
        $distanceActivite = round($this->trouverDistanceActivite($donnees_longitude, $donnees_latitude));

        // Validate date
        $dateInput = $jsonData['activity']['date'];
        $date = DateTime::createFromFormat('d/m/Y', $dateInput);
        if (!$date || $date->format('Y-m-d') > date('Y-m-d')) {
            $this->render("upload", []);
            return;
        }

        $formattedDate = $date->format('Y-m-d');

        // Create and insert new activity
        $activity = new Activity();
        $activity->init(
            null,
            $formattedDate,
            $jsonData['activity']['description'],
            $heartRateMin,
            $heartRateMax,
            $heartRateAvg,
            $distanceActivite,
            $duree,
            $_SESSION['user_id']
        );

        ActivityDAO::getInstance()->insert($activity);
        $uploadSuccess = true;

        // Insert activity entries
        foreach ($activityEntries as $compteur => $entry) {
            $activityEntry = new ActivityEntry();
            $activityEntry->init(
                $compteur + 1,
                $entry['time'],
                $entry['cardio_frequency'],
                $entry['longitude'],
                $entry['latitude'],
                $entry['altitude'],
                $activity->getId()
            );

            ActivityEntryDAO::getInstance()->insert($activityEntry);
        }

        $user = UserDAO::getInstance()->getUserById(id: $_SESSION['user_id']);
        if ($user) {
            $this->render("profil", ['user' => $user]);
            echo "<script>alert('Uploadé avec succès');</script>";
        } else {
            echo "Erreur : utilisateur non trouvé.";
        }
    }

    public function determineHeartFrequency(array $array): array
    {
        $heartMax = max($array);
        $heartMin = min($array);
        $heartAvg = $array ? round(array_sum($array) / count($array)) : 0;
        return [$heartMin, $heartAvg, $heartMax];
    }

    public function trouverDistanceActivite(array $longitude, array $latitude): float
    {
        $distance = 0;
        $calc = new CalculDistanceImpl();

        for ($i = 0; $i < count($latitude) - 1; $i++) {
            $distance += $calc->calculDistance2PointsGPS($latitude[$i], $longitude[$i], $latitude[$i + 1], $longitude[$i + 1]);
        }

        return $distance;
    }

    public function calculerDureeActivite(array $activityEntries): string
    {
        $startTime = $activityEntries[0]['time'];
        $endTime = end($activityEntries)['time'];
        $durationInSeconds = strtotime($endTime) - strtotime($startTime);
        
        return gmdate("H:i:s", $durationInSeconds);
    }

}
?>
