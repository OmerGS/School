<?php
require_once('SqliteConnection.php');
require_once('Activity.php');
class ActivityDAO {
    private static ActivityDAO $dao;

    private function __construct() {}

    public static function getInstance(): ActivityDAO {
        if(!isset(self::$dao)) {
            self::$dao= new ActivityDAO();
        }
        return self::$dao;
    }

    public final function findAll(): array {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $query = "SELECT * FROM Activity ORDER BY id";
        $stmt = $dbc->query($query);
        
        $results = $stmt->fetchAll(PDO::FETCH_CLASS, 'Activity');
    
        return $results;
    }

    public final function insert(Activity $a): void {
        if ($a instanceof Activity) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            // prepare the SQL statement
            $query = "INSERT INTO activity (date, description, heart_rate_min, heart_rate_max, heart_rate_avg, distance, duration, id_user) 
                      VALUES (:da, :description, :heart_min, :heart_max, :heart_avg, :dis, :dur, :uid)";
            $stmt = $dbc->prepare($query);
    
            // bind the parameters (id is auto-generated, so no need to bind it)
            $stmt->bindValue(':da', $a->getDate(), PDO::PARAM_STR);
            $stmt->bindValue(':description', $a->getDescription(), PDO::PARAM_STR);
            $stmt->bindValue(':heart_min', $a->getHeartRateMin(), PDO::PARAM_INT);
            $stmt->bindValue(':heart_max', $a->getHeartRateMax(), PDO::PARAM_INT);
            $stmt->bindValue(':heart_avg', $a->getHeartRateAvg(), PDO::PARAM_INT);
            $stmt->bindValue(':dis', $a->getDistance(), PDO::PARAM_INT);
            $stmt->bindValue(':dur', $a->getDuration(), PDO::PARAM_STR);
            $stmt->bindValue(':uid', $a->getIdUser(), PDO::PARAM_INT);
    
            // execute the prepared statement
            $stmt->execute();
    
            // Retrieve the last inserted ID and set it in the Activity object
            $lastInsertId = $dbc->lastInsertId();
            $a->setId((int)$lastInsertId);
        }
    }
    
    

    public function delete(Activity $a): void {
        if ($a instanceof Activity) {
            $dbc = SqliteConnection::getInstance()->getConnection();

            // prepare the SQL statement
            $query = "DELETE FROM activity WHERE id = :id";
            $stmt = $dbc->prepare($query);
    
            // bind the parameter
            $stmt->bindValue(':id', $a->getId(), PDO::PARAM_STR);
    
            // execute the prepared statement
            $stmt->execute();
        }
    }
    

    public final function update(Activity $a): void {
        $dbc = SqliteConnection::getInstance()->getConnection();
        $query = "UPDATE activity 
                  SET date = :da, description = :description, heart_rate_min = :heart_min, 
                      heart_rate_max = :heart_max, heart_rate_avg = :heart_avg, 
                      distance = :dis, duration = :dur, id_user = :uid 
                  WHERE id = :id";

        $stmt = $dbc->prepare($query);

        // bind the parameters
        $stmt->bindValue(':da', $a->getDate(), PDO::PARAM_STR);
        $stmt->bindValue(':description', $a->getDescription(), PDO::PARAM_STR);
        $stmt->bindValue(':heart_min', $a->getHeartRateMin(), PDO::PARAM_INT);
        $stmt->bindValue(':heart_max', $a->getHeartRateMax(), PDO::PARAM_INT);
        $stmt->bindValue(':heart_avg', $a->getHeartRateAvg(), PDO::PARAM_INT);
        $stmt->bindValue(':dis', $a->getDistance(), PDO::PARAM_INT);
        $stmt->bindValue(':dur', $a->getDuration(), PDO::PARAM_STR);
        $stmt->bindValue(':uid', $a->getIdUser(), PDO::PARAM_INT);
        $stmt->bindValue(':id', $a->getId(), PDO::PARAM_INT);

        $stmt->execute();
    }

    public final function findById($userId): array {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $query = "SELECT * FROM Activity WHERE id_user = $userId";
        $stmt = $dbc->query($query);
        
        $results = $stmt->fetchAll(PDO::FETCH_CLASS, 'Activity');
    
        return $results;
    }
}
?>