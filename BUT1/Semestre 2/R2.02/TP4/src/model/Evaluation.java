package model;

import java.io.Serializable;

// Define a class representing an evaluation
public class Evaluation implements Serializable{

    // Private attributes representing coefficient and score
    private int coefficient; // Coefficient of the evaluation
    private float score; // Score obtained in the evaluation

    // Default constructor initializing coefficient to 1 and score to 0
    public Evaluation() {
        this.coefficient = 1;
        this.score = 0;
    }

    // Parameterized constructor initializing coefficient and score
    public Evaluation(float score, int coefficient) {
        this(); // Call default constructor to initialize default values
        // Ensure coefficient is within the range [0, 10]
        if (coefficient >= 0 && coefficient <= 10) {
            this.coefficient = coefficient;
        }
        // Ensure score is within the range [0, 20]
        if (score >= 0 && score <= 20) {
            this.score = score;
        }
    }

    // Getter method for coefficient
    public int getCoefficient() {
        return coefficient;
    }

    // Setter method for coefficient, ensuring it's within the range [0, 10]
    public void setCoefficient(int coefficient) {
        if (coefficient >= 0 && coefficient <= 10) {
            this.coefficient = coefficient;
        }
    }

    // Getter method for score
    public float getScore() {
        return score;
    }

    // Setter method for score, ensuring it's within the range [0, 20]
    public void setScore(float score) {
        if (score >= 0 && score <= 20) {
            this.score = score;
        }
    }
}
