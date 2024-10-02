<?php
class Activity {
    private ?int $id;
    private string $date;
    private string $description;
    private int $heart_rate_min;
    private int $heart_rate_max;
    private int $heart_rate_avg;
    private int $distance;
    private string $duration;
    private int $userId;

    // Updated init method
    public function init($id, $date, $description, $heart_rate_min, $heart_rate_max, $heart_rate_avg, $distance, $duration, $id_user) {
        $this->id = $id;
        $this->date = $date;
        $this->description = $description;
        $this->heart_rate_min = $heart_rate_min;
        $this->heart_rate_max = $heart_rate_max;
        $this->heart_rate_avg = $heart_rate_avg;
        $this->distance = $distance;
        $this->duration = $duration;
        $this->userId = $id_user; // Fixed userId property name
    }    
    
    public function getId(): int {
        return $this->id ?? 0; // Return 0 if id is null
    }

    public function setId(int $id): void {
        $this->id = $id; // Allow setting the id
    }

    public function getDate(): string { 
        return $this->date; 
    }
    
    public function getDescription(): string { 
        return $this->description; 
    }

    public function getHeartRateMin(): int { 
        return $this->heart_rate_min ?? 0; // Ensure 0 is returned if null
    }

    public function getHeartRateMax(): int { 
        return $this->heart_rate_max ?? 0; 
    }

    public function getHeartRateAvg(): int { 
        return $this->heart_rate_avg ?? 0; 
    }

    public function getDistance(): int { 
        return $this->distance; 
    }

    public function getDuration(): string { 
        return $this->duration; 
    }

    public function getIdUser(): int {
        return $this->userId; 
    }

    public function __toString(): string { 
        return $this->id . " " . $this->description . " " . $this->date; 
    }
}
?>