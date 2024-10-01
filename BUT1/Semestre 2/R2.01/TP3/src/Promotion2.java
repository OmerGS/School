/**
 * The Promotion2 class represents a group of students.
 * @author O.Gunes
 */
public class Promotion2 {
    /**
    * The name of the promotion 
    */
    private String nom; // The name of the promotion

    /**
     * The list of the Student
     */
    private Etudiant[] listeEtudiants; // The list of enrolled students

    /**
     * The number of the student who are registered
     */
    private int nbInscrits; // The number of enrolled students in the promotion

    /**
     * Constructor to initialize a promotion with a name and a list of students.
     *
     * @param nom            The name of the promotion.
     * @param listeEtudiants The list of students in the promotion.
     */
    public Promotion2(String nom, Etudiant[] listeEtudiants) {
        if (nom == null) {
            System.out.println("Constructor: Error in name");
        } else {
            this.nom = nom;
        }

        if (listeEtudiants == null) {
            System.out.println("Constructor: Empty List");
        } else {
            this.listeEtudiants = listeEtudiants;
            this.nbInscrits = listeEtudiants.length;
        }
    }

    /**
     * Constructor to initialize a promotion with a name and a maximum size for the students' array.
     *
     * @param nom       The name of the promotion.
     * @param tailleMax The maximum size for the students' array.
     */
    public Promotion2(String nom, int tailleMax) {
        if (nom == null) {
            System.out.println("Constructor: Error in name");
        } else {
            this.nom = nom;
            this.listeEtudiants = new Etudiant[tailleMax]; // Initialize the array with the maximum size
            this.nbInscrits = 0; // No students enrolled initially
        }
    }

    /**
     * Method to enroll a new student in the promotion.
     *
     * @param etudiant The student to enroll.
     */
    public void inscrire(Etudiant etudiant) {
        if (this.nbInscrits < this.listeEtudiants.length) {
            this.listeEtudiants[this.nbInscrits] = etudiant;
            this.nbInscrits++;
        } else {
            System.out.println("Cannot enroll more students, the array is full.");
        }
    }

    /**
     * Method to get the name of the promotion.
     *
     * @return The name of the promotion.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Method to set the name of the promotion.
     *
     * @param nom The new name of the promotion.
     */
    public void setNom(String nom) {
        if (nom == null) {
            System.out.println("setNom: Name Error");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Method to calculate the overall average of the promotion.
     *
     * @return The overall average of the promotion.
     */
    public double moyenne() {
        double sumAverage = 0;
        double average = 0;

        for (int i = 0; i < this.nbInscrits; i++) {
            sumAverage += listeEtudiants[i].moyenneGenerale();
        }
        if (this.nbInscrits > 0) {
            average = sumAverage / this.nbInscrits;
        }
        return average;
    }

    /**
     * Method to get the maximum average among the enrolled students.
     *
     * @return The maximum average among the enrolled students.
     */
    public double moyenneMax() {
        double maxAverage = 0;
        if (this.nbInscrits != 0) {
            for (int i = 0; i < this.nbInscrits; i++) {
                if (listeEtudiants[i].moyenneGenerale() > maxAverage) {
                    maxAverage = listeEtudiants[i].moyenneGenerale();
                }
            }
        } else {
            System.out.println("moyenneMax: The array contains no values!");
        }
        return maxAverage;
    }

    /**
     * Method to get the minimum average among the enrolled students.
     *
     * @return The minimum average among the enrolled students.
     */
    public double moyenneMin() {
        double minAverage = 20;
        if (this.nbInscrits != 0) {
            for (int i = 0; i < this.nbInscrits; i++) {
                if (listeEtudiants[i].moyenneGenerale() < minAverage) {
                    minAverage = listeEtudiants[i].moyenneGenerale();
                }
            }
        } else {
            System.out.println("moyenneMin: The array contains no values!");
        }
        return minAverage;
    }

    /**
     * Method to get the student with the highest average in the promotion.
     *
     * @return The student with the highest average.
     */
    public Etudiant getMajor() {
        Etudiant major = null;
        if (this.nbInscrits != 0) {
            major = listeEtudiants[0];
            for (int i = 0; i < this.nbInscrits; i++) {
                if (major.moyenneGenerale() < listeEtudiants[i].moyenneGenerale()) {
                    major = this.listeEtudiants[i];
                }
            }
        } else {
            System.out.println("Major: Array contains no values");
        }

        return major;
    }

    /**
     * Method to get a student by their name.
     *
     * @param nom The name of the student to search for.
     * @return The student corresponding to the specified name, or null if no student matches.
     */
    public Etudiant getEtudiant(String nom) {
        Etudiant studentToFind = null;
        boolean found = false;
        if (this.nbInscrits != 0) {
            for (int i = 0; i < this.nbInscrits; i++) {
                if (listeEtudiants[i].getNom().equals(nom)) {
                    studentToFind = listeEtudiants[i];
                    found = true;
                    break; // Exit the loop as soon as the student is found
                }
            }
        }

        if (!found) {
            System.out.println("No student!");
        }

        return studentToFind;
    }

    /**
     * Method to calculate the average in a specific subject of the promotion.
     *
     * @param i The index of the subject for which to calculate the average.
     * @return The average in the specified subject.
     */
    public double moyenneMatiere(int i) {
        double sumAverage = 0;
        double average = 0;

        if (this.nbInscrits != 0) {
            if (i > 0 && i < this.listeEtudiants[0].getMATIERES().length) {
                for (int j = 0; j < this.nbInscrits; j++) {
                    sumAverage += this.listeEtudiants[j].moyenneMatiere(i);
                }
                average = sumAverage / this.nbInscrits;
            } else {
                System.out.println("The specified subject is not available!");
            }
        } else {
            System.out.println("The array is empty!");
        }
        return average;
    }

    /**
     * Method to get a string representation of the promotion.
     *
     * @return A string representation of the promotion.
     */
    public String toString() {
        String finalString;

        finalString = ("Promo: " + this.getNom() + "\nNumber of Students: " + this.nbInscrits + "\nAverage: " + this.moyenne());

        return finalString;
    }
}
