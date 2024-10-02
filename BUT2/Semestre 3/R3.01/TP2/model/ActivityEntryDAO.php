<?php
require_once('SqliteConnection.php');
require_once('ActivityEntry.php');

class ActivityEntryDAO {
    private static ActivityEntryDAO $dao;

    private function __construct() {}

    public static function getInstance(): ActivityEntryDAO {
        if (!isset(self::$dao)) {
            self::$dao = new ActivityEntryDAO();
        }
        return self::$dao;
    }

    // Fetch all activity entries
    public final function findAll(): array {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $query = "SELECT * FROM ActivityEntry ORDER BY id";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchAll(PDO::FETCH_ASSOC);

        $activityEntries = [];
        foreach ($results as $row) {
            $entry = new ActivityEntry();
            $entry->setId($row['id']);
            $entry->setMeasureTime($row['measure_time']); 
            $entry->setHeartRate($row['heart_rate']);
            $entry->setLongitude($row['longitude']);
            $entry->setLatitude($row['latitude']);
            $entry->setAltitude($row['altitude']);
            $entry->setIdActivity($row['id_activity']);
            $activityEntries[] = $entry;
        }

        return $activityEntries;

    }
    

    // Insert a new activity entry
    public function insert(ActivityEntry $entry): void {
        if ($entry instanceof ActivityEntry) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $query = "INSERT INTO ActivityEntry (measure_time, heart_rate, longitude, latitude, altitude, id_activity)
                      VALUES (:time, :heartRate, :longitude, :latitude, :altitude, :idActivity)";
            $stmt = $dbc->prepare($query);

            // Bind values
            $stmt->bindValue(':time', $entry->getMeasureTime(), PDO::PARAM_STR);
            $stmt->bindValue(':heartRate', $entry->getHeartRate(), PDO::PARAM_INT);
            $stmt->bindValue(':longitude', $entry->getLongitude(), PDO::PARAM_STR);
            $stmt->bindValue(':latitude', $entry->getLatitude(), PDO::PARAM_STR);
            $stmt->bindValue(':altitude', $entry->getAltitude(), PDO::PARAM_INT);
            $stmt->bindValue(':idActivity', $entry->getIdActivity(), PDO::PARAM_INT);

            // Execute and set the new ID
            $stmt->execute();
            $lastInsertId = $dbc->lastInsertId();
            $entry->setId($lastInsertId);
        }
    }

    // Delete an activity entry
    public function delete(ActivityEntry $entry): void {
        if ($entry instanceof ActivityEntry) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $query = "DELETE FROM ActivityEntry WHERE id = :id";
            $stmt = $dbc->prepare($query);
            $stmt->bindValue(':id', $entry->getId(), PDO::PARAM_INT);
            $stmt->execute();
        }
    }

    // Update an existing activity entry
    public function update(ActivityEntry $entry): void {
        if ($entry instanceof ActivityEntry) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $query = "UPDATE ActivityEntry 
                      SET measure_time = :time, heart_rate = :heartRate, longitude = :longitude, latitude = :latitude, altitude = :altitude, id_activity = :idActivity
                      WHERE id = :id";
            $stmt = $dbc->prepare($query);

            // Bind values
            $stmt->bindValue(':time', $entry->getMeasureTime(), PDO::PARAM_STR);
            $stmt->bindValue(':heartRate', $entry->getHeartRate(), PDO::PARAM_INT);
            $stmt->bindValue(':longitude', $entry->getLongitude(), PDO::PARAM_STR);
            $stmt->bindValue(':latitude', $entry->getLatitude(), PDO::PARAM_STR);
            $stmt->bindValue(':altitude', $entry->getAltitude(), PDO::PARAM_INT);
            $stmt->bindValue(':idActivity', $entry->getIdActivity(), PDO::PARAM_INT);
            $stmt->bindValue(':id', $entry->getId(), PDO::PARAM_INT);

            // Execute the statement
            $stmt->execute();
        }
    }






    public final function findByActivityId(int $activityId): array {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $query = "SELECT * FROM ActivityEntry WHERE id_activity = :id_activity ORDER BY id";
        $stmt = $dbc->prepare($query);
        $stmt->bindValue(':id_activity', $activityId, PDO::PARAM_INT);
        $stmt->execute();
        
        $results = $stmt->fetchAll(PDO::FETCH_CLASS, 'ActivityEntry');
        return $results;
    }
    
}
?>
