<?php
class User {
    private int $id;
    private string $lastname;
    private string $firstname;
    private string $birthdate;
    private string $gender;
    private int $height;
    private int $weight;
    private string $email;
    private string $password;

    public function __construct() { }

    public function init
    (
        $id, 
        $lastname, 
        $firstname, 
        $birthdate, 
        $gender, 
        $height, 
        $weight, 
        $email, 
        $password
    ) 
    {
        $this->id = $id;
        $this->lastname = $lastname;
        $this->firstname = $firstname;
        $this->birthdate = $birthdate;
        $this->gender = $gender;
        $this->height = $height;
        $this->weight = $weight;
        $this->email = $email;
        $this->password = $password;
    }

    // Getter for ID
    public function getId(): int { return $this->id; }
    public function getLastname(): string { return $this->lastname; }
    public function getFirstname(): string { return $this->firstname; }
    public function getBirthdate(): string { return $this->birthdate; }
    public function getGender(): string { return $this->gender; }
    public function getHeight(): int { return $this->height; }
    public function getWeight(): int { return $this->weight; }
    public function getEmail(): string { return $this->email; }
    public function getPassword(): string { return $this->password; }

    //Setter for ID
    public function setId(int $id): void { $this->id = $id; }
    public function setLastname(string $lastname): void { $this->lastname = $lastname; }
    public function setFirstName(string $firstname): void { $this->firstname = $firstname; }
    public function setBirthdate(string $birthdate) : void { $this->birthdate = $birthdate; }
    public function setGender(string $gender): void { $this->gender = $gender; }
    public function setHeight(int $height): void { $this->height = $height; }
    public function setWeight(int $weight): void { $this->weight = $weight; }
    public function setEmail(string $email): void { $this->email = $email; }
    public function setPassword(string $password): void { $this->password = $password; }



    public function __toString(): string {
        return $this->lastname . " " . $this->firstname;
    }
}

?>