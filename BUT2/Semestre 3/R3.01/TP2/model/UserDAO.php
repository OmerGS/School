<?php
require_once('SqliteConnection.php');
require_once('User.php');
class UserDAO {
    private static UserDAO $dao;

    private function __construct() {}

    public static function getInstance(): UserDAO {
        if(!isset(self::$dao)) {
            self::$dao= new UserDAO();
        }
        return self::$dao;
    }
    
    public final function findAll(): array {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $query = "SELECT * FROM User ORDER BY id";
        $stmt = $dbc->query($query);
        
        $results = $stmt->fetchAll(PDO::FETCH_CLASS, 'User');
    
        return $results;
    }




    public final function insert(User $u): void{
        if($u instanceof User){
            $dbc = SqliteConnection::getInstance()->getConnection();
            // prepare the SQL statement
            $query = "insert into user(lastname, firstname, birthdate, gender, height, weight, email, password) values (:n,:p, :b, :g, :h, :w, :e, :m)";
            $stmt = $dbc->prepare($query);

            // bind the paramaters
            $stmt->bindValue(':n',$u->getLastname(), PDO::PARAM_STR);
            $stmt->bindValue(':p',$u->getFirstname(), PDO::PARAM_STR);
            $stmt->bindValue(':b',$u->getBirthdate(), PDO::PARAM_STR);
            $stmt->bindValue(':g',$u->getGender(), PDO::PARAM_STR);
            $stmt->bindValue(':h',$u->getHeight(), PDO::PARAM_STR);
            $stmt->bindValue(':w',$u->getWeight(), PDO::PARAM_STR);
            $stmt->bindValue(':e',$u->getEmail(), PDO::PARAM_STR);
            $stmt->bindValue(':m',$u->getPassword(), PDO::PARAM_STR);

            // execute the prepared statement
            $stmt->execute();

            // Retrieve the last inserted ID
            $lastInsertId = $dbc->lastInsertId();

            // Set the ID of the Activity object
            $u->setId($lastInsertId);
        }
    }

    
    public function delete(User $u): void {
        if ($u instanceof User) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            // prepare the SQL statement
            $query = "DELETE FROM user WHERE email = :e";
            $stmt = $dbc->prepare($query);
    
            // bind the parameter
            $stmt->bindValue(':e', $u->getEmail(), PDO::PARAM_STR);
    
            // execute the prepared statement
            $stmt->execute();
        }
    }
    

    public function update(User $u): void {
        if ($u instanceof User) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            
            // Check if the email is being used by another user
            $query = "SELECT COUNT(*) FROM user WHERE email = :e AND id != :id";
            $stmt = $dbc->prepare($query);
            $stmt->bindValue(':e', $u->getEmail(), PDO::PARAM_STR);
            $stmt->bindValue(':id', $u->getId(), PDO::PARAM_INT);
            $stmt->execute();
            $count = $stmt->fetchColumn();
    
            if ($count > 0) {
                echo "Email already in use by another user: " . $u->getEmail() . "</br>";
            } else {
                // Prepare the SQL statement
                $query = "UPDATE user 
                          SET lastname = :n, firstname = :p, birthdate = :b, gender = :g, height = :h, weight = :w, email = :e, password = :m
                          WHERE id = :id";
                $stmt = $dbc->prepare($query);
    
                // Bind the parameters
                $stmt->bindValue(':n', $u->getLastname(), PDO::PARAM_STR);
                $stmt->bindValue(':p', $u->getFirstname(), PDO::PARAM_STR);
                $stmt->bindValue(':b', $u->getBirthdate(), PDO::PARAM_STR);
                $stmt->bindValue(':g', $u->getGender(), PDO::PARAM_STR);
                $stmt->bindValue(':h', $u->getHeight(), PDO::PARAM_INT);
                $stmt->bindValue(':w', $u->getWeight(), PDO::PARAM_INT);
                $stmt->bindValue(':e', $u->getEmail(), PDO::PARAM_STR);
                $stmt->bindValue(':m', $u->getPassword(), PDO::PARAM_STR);
                $stmt->bindValue(':id', $u->getId(), PDO::PARAM_INT);
    
                // Execute the prepared statement
                $stmt->execute();
            }
        }
    }



    public function emailExist(string $email){
        $users = $this->findAll();
        foreach ($users as $u) {
            if($u->getEmail() == $email){
                return true;
            }
        }

        return false;
    }

    public function getPasswordForEmail(string $email): string{
        $users = $this->findAll();
        foreach ($users as $u) {
            if($u->getEmail() == $email){
                return $u->getPassword();
            }
        }

        return 'nopassword';
    }


    public function getUserByEmail(string $email): ?User {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $query = "SELECT * FROM user WHERE email = :email";
        $stmt = $dbc->prepare($query);
        $stmt->bindValue(':email', $email, PDO::PARAM_STR);
        $stmt->execute();

        $stmt->setFetchMode(PDO::FETCH_CLASS, 'User');
        $user = $stmt->fetch();

        return $user ?: null; // Retourne l'utilisateur ou null s'il n'existe pas
    }

    public function getUserById(int $id): ?User {
        $dbc = SqliteConnection::getInstance()->getConnection();
    
        // Préparer la requête SQL pour obtenir un utilisateur par son ID
        $query = "SELECT * FROM user WHERE id = :id";
        $stmt = $dbc->prepare($query);
    
        // Liaison de l'ID de l'utilisateur
        $stmt->bindValue(':id', $id, PDO::PARAM_INT);
    
        // Exécution de la requête
        $stmt->execute();
    
        // Récupérer le résultat sous forme d'objet User
        $stmt->setFetchMode(PDO::FETCH_CLASS, 'User');
        $user = $stmt->fetch();
    
        // Si un utilisateur est trouvé, le retourner, sinon retourner null
        return $user ?: null;
    }
    
    
    
}
?>