package model;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

// Define a class representing a student
public class Student implements Serializable{

    // Attributes to store the student's name and first name
    private String name;
    private String firstName;

    // A list of evaluations for the student, managed as an observable list
    private List<Evaluation> evaluations;

    // Stores the average of the student's evaluations
    private float average = 0;

    // Constructor with parameters for name, first name, and evaluations
    public Student(String name, String firstName, List<Evaluation> evaluations) {
        this.name = name;
        this.firstName = firstName;
        this.evaluations = evaluations;
        this.computeAverage(); // Compute the average based on existing evaluations
    }

    // Constructor with parameters for name and first name (evaluations initialized
    // as empty list)
    public Student(String name, String firstName) {
        this(name, firstName, new ArrayList<Evaluation>());
    }

    // Getter for name
    public String getName() {
        return this.name;
    }

    // Getter for first name
    public String getFirstName() {
        return this.firstName;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter for evaluations
    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    // Getter for average
    public float getAverage() {
        return average;
    }

    // Method to compute the average of evaluations
    public void computeAverage() {
        float weightedSum = 0;
        float sum = 0;
        for (Evaluation e : evaluations) {
            weightedSum += e.getScore() * e.getCoefficient();
            sum += e.getCoefficient();
        }
        average = (sum == 0) ? 0 : weightedSum / sum;
    }

    // Method to add a new evaluation
    public void add(Evaluation e) {
        evaluations.add(e);
        computeAverage(); // Recompute average after adding evaluation
    }

    // Method to remove an evaluation by index
    public void remove(int i) {
        evaluations.remove(i);
        computeAverage(); // Recompute average after removing evaluation
    }

}
