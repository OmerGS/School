<?php
class ActivityEntry {
    private int $id;
    private string $measureTime; // Ensure this is a string
    private int $heartRate;
    private float $longitude;
    private float $latitude;
    private float $altitude;
    private int $id_activity;

    // Constructor for initialization
    public function __construct() { }
    
    
    public function init(
        int $id,
        string $measureTime,
        int $heartRate,
        float $longitude,
        float $latitude,
        float $altitude,
        int $id_activity
    ) {
        $this->id = $id;
        $this->measureTime = $measureTime;
        $this->heartRate = $heartRate;
        $this->longitude = $longitude;
        $this->latitude = $latitude;
        $this->altitude = $altitude;
        $this->id_activity = $id_activity;
    }

    // Getters
    public function getId(): int { return $this->id; }
    public function getMeasureTime(): string { return $this->measureTime; }
    public function getHeartRate(): int { return $this->heartRate; }
    public function getLongitude(): float { return $this->longitude; }
    public function getLatitude(): float { return $this->latitude; }
    public function getAltitude(): float { return $this->altitude; }
    public function getIdActivity(): int { return $this->id_activity; }

    // Setters
    public function setId(int $id): void { $this->id = $id; }
    public function setMeasureTime(string $measureTime): void { $this->measureTime = $measureTime; }
    public function setHeartRate(int $heartRate): void { $this->heartRate = $heartRate; }
    public function setLongitude(float $longitude): void { $this->longitude = $longitude; }
    public function setLatitude(float $latitude): void { $this->latitude = $latitude; }
    public function setAltitude(float $altitude): void { $this->altitude = $altitude; }
    public function setIdActivity(int $id_activity): void { $this->id_activity = $id_activity; }
}
?>
