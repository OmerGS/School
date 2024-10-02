<?php
class SqliteConnection{
    
    private static $connection;
    private static $_instance;

    function __construct(){
    }

    public static function getInstance(): self {
        if (!(self::$_instance instanceof self)) {
            self::$_instance = new self();
        }
        return self::$_instance;
    }
    
    
    public function getConnection(){
        try{
            $connection = new PDO('sqlite:model/SportTrack.db');            
        } catch(PDOException $e){
            print("Error ! : " . $e->getMessage() . "<br/>");
        }

        return($connection);
    }
}
?>