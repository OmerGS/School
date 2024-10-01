/**
 * The Promotion class represents a group of students.
 * @author O.Gunes
 */
public class Promotion {
    /** 
    * Name of the Promotion 
    */
    private String nom; // The name of the promotion

    /**
     * List of Student
     */
    private Etudiant[] listeEtudiants; // The list of students in the promotion

    /**
     * Constructor of the Promotion class.
     *
     * @param nom             The name of the promotion.
     * @param listeEtudiants  The list of students in the promotion.
     */
    public Promotion(String nom, Etudiant[] listeEtudiants) {
        if (nom == null) {
            System.out.println("Constructor: Error in name");
        } else {
            this.nom = nom;
        }

        if (listeEtudiants == null) {
            System.out.println("Constructor: Empty List");
        } else {
            this.listeEtudiants = listeEtudiants;
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

        for (int i = 0; i < this.listeEtudiants.length; i++) {
            sumAverage += listeEtudiants[i].moyenneGenerale();
            average = sumAverage / listeEtudiants.length - 1;
        }

        return average;
    }

    /**
     * Method to find the maximum average among the students in the promotion.
     *
     * @return The maximum average among the students in the promotion.
     */
    public double moyenneMax() {
        double maxAverage = 0;
        if (this.listeEtudiants.length != 0) {
            for (int i = 0; i < listeEtudiants.length; i++) {
                if (listeEtudiants[i].moyenneGenerale() > maxAverage) {
                    maxAverage = listeEtudiants[i].moyenneGenerale();
                }
            }
        } else {
            System.out.println("moyenneMin: The array contains no values!");
        }
        return maxAverage;
    }

    /**
     * Method to find the minimum average among the students in the promotion.
     *
     * @return The minimum average among the students in the promotion.
     */
    public double moyenneMin() {
        double minAverage = 20;
        if (this.listeEtudiants.length != 0) {
            for (int i = 0; i < listeEtudiants.length; i++) {
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
     * Method to find the student with the highest average in the promotion.
     *
     * @return The student with the highest average in the promotion.
     */
    public Etudiant getMajor() {
        Etudiant major = null;
        if (this.listeEtudiants.length != 0) {
            major = listeEtudiants[0];
            for (int i = 0; i < listeEtudiants.length; i++) {
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
     * Method to find a student in the promotion based on their name.
     *
     * @param nom The name of the student to search for.
     * @return The student corresponding to the specified name, or null if no student is found.
     */
    public Etudiant getEtudiant(String nom) {
        Etudiant studentToFind = null;
        boolean found = false;
        if (this.listeEtudiants.length != 0) {
            int i = 0;
            while (i < this.listeEtudiants.length) {
                if (listeEtudiants[i].getNom().equals(nom)) {
                    studentToFind = listeEtudiants[i];
                    found = true;
                    break;
                } else {
                    i++;
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

        if (this.listeEtudiants.length != 0) {
            if (i > 0 && i < this.listeEtudiants[0].getMATIERES().length) {
                for (int j = 0; j < this.listeEtudiants.length; j++) {
                    sumAverage += this.listeEtudiants[j].moyenneMatiere(i);
                }
                average = sumAverage / this.listeEtudiants.length;
            } else {
                System.out.println("The specified subject is not available!");
            }
        } else {
            System.out.println("The array is empty!");
        }
        return average;
    }

    /**
     * Method to represent the promotion as a string.
     *
     * @return A string representing the promotion.
     */
    public String toString() {
        String finalString;

        finalString = ("Promo: " + this.getNom() + "\nNumber of Students: " + this.listeEtudiants.length + "\nAverage: " + this.moyenne());

        return finalString;
    }
}
