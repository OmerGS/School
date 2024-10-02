<?php
require_once dirname(__FILE__) . "/CalculDistance.php";

class CalculDistanceImpl implements CalculDistance
{
    /**
     * Retourne la distance en mètres entre 2 points GPS exprimés en degrés.
     * @param float $lat1 Latitude du premier point GPS
     * @param float $long1 Longitude du premier point GPS
     * @param float $lat2 Latitude du second point GPS
     * @param float $long2 Longitude du second point GPS
     * @return float La distance entre les deux points GPS
     */
    public function calculDistance2PointsGPS(
        $lat1,
        $long1,
        $lat2,
        $long2
    ): float {
        $earth_radius = 6378137;
        $dlat1 = deg2rad($lat1);
        $dlong1 = deg2rad($long1);
        $dlat2 = deg2rad($lat2);
        $dlong2 = deg2rad($long2);

        $distance =
            $earth_radius *
            acos(
                sin($dlat2) * sin($dlat1) +
                    cos($dlat2) * cos($dlat1) * cos($dlong2 - $dlong1)
            );
        return $distance;
    }

    /**
     * Retourne la distance en metres du parcours passé en paramètres. Le parcours est
     * défini par un tableau ordonné de points GPS.
     * @param Array $parcours Le tableau contenant les points GPS
     * @return float La distance du parcours
     */
    public function calculDistanceTrajet(array $parcours): float
    {
        $sum = 0;
        for ($i = 0; $i < count($parcours["data"]) - 1; $i++) {
            $sum += $this->calculDistance2PointsGPS(
                $parcours["data"][$i]["latitude"],
                $parcours["data"][$i]["longitude"],
                $parcours["data"][$i + 1]["latitude"],
                $parcours["data"][$i + 1]["longitude"]
            );
        }
        return $sum;
    }

    /**
     * @param $fileName
     */
    public function readJSON(string $fileName): mixed
    {
        $json_content = file_get_contents($fileName);
        return json_decode($json_content, true);
    }
}
?>
