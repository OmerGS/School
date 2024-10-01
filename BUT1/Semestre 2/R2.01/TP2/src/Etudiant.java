/**
* This class is used for create etudiant object
* @author O.Gunes
*/
public class Etudiant {
    /**
    * This private variable stores the student's name.
    */
    private String nom;

    /**
    * This tables stores de report of each notes of the student
    */
    private double[][] bulletin;

    /**
    * This tables stores de report of eace subject of the student
    */
    private String[] MATIERES;

    /**
    * This tables stores de report of each coefficient of the subject
    */
    private double[] COEFFICIANTS;

     /**
     * Constructor for the Etudiant class.
     * @param nom The name of the student.
     * @param matiere The array of subject names.
     * @param coeff The array of coefficients corresponding to each subject.
     * @param nbNotes The number of notes to be stored for each subject.
     */
    public Etudiant(String nom, String[] matiere, double[] coeff, int nbNotes){
        
        if(nom != null && matiere != null && coeff != null && nbNotes >= 0){
            this.nom = nom;
            if(matiere.length == coeff.length){
                this.MATIERES = matiere;
                this.COEFFICIANTS = coeff;
                this.bulletin = new double[this.MATIERES.length][nbNotes];
                initialisation();
            } else {
                System.out.println("Constructeur : Erreur");
            } 
        } else {
            System.out.println("Constructeur : Erreur");
        }
    }

    
    /**
     * Sets the name of the student.
     * @param nom The new name of the student.
     */
    public void setNom(String nom){
        if(nom != null){
            this.nom = nom;
        }
    }

    
    /**
     * Gets the name of the student.
     * @return String The name of the student.
     */
    public String getNom(){
        return this.nom;
    }

    
    /**
     * Gets the number of subjects.
     * @return int The number of subjects.
     */
    public int getNbMatiere(){
        int nbMatiere = 0;

        nbMatiere = this.bulletin.length;

        return(nbMatiere);
    }

    
    /**
     * Gets a specific note for a subject.
     * @param indiceMatiere The index of the subject.
     * @param indiceNote The index of the note.
     * @return double The requested note.
     */
    public double getUneNote(int indiceMatiere, int indiceNote){
        double noteToReturn = 0;

        if(indiceMatiere >= 0 && indiceMatiere < this.bulletin.length){
            if(indiceNote > 0 && indiceNote < this.bulletin[indiceMatiere].length){
                noteToReturn = this.bulletin[indiceMatiere][indiceNote];
            }
        } else {
            System.out.println("getUneNote : Erreur");
        }

        return(noteToReturn);
    }
    
    
    /**
     * Gets the subject with the highest grade and the corresponding grade.
     * @return String The subject with the highest grade and the grade itself.
     */
    public String meilleurNote() {
        double meilleureNote = 0;  
        String matiereMeilleureNote = "";
    
        for (int i = 0; i < this.bulletin.length; i++) {
            for (int j = 0; j < this.bulletin[i].length; j++) {
                if (this.bulletin[i][j] > meilleureNote) {
                    meilleureNote = this.bulletin[i][j];
                    matiereMeilleureNote = this.MATIERES[i];
                }
            }
        }
        
        return(matiereMeilleureNote + " " + meilleureNote);
    }
    
    /**
     * Generates a string representation of the student's information.
     * @return String The string representation of the student.
     */
    public String toString() {
        String result = "Nom de l'etudiant: " + this.nom + "\n";

        for (int i = 0; i < this.MATIERES.length; i++) {
            result += "Matiere: " + this.MATIERES[i] + "\n";
            result += "Notes: ";
            for (int j = 0; j < this.bulletin[i].length; j++) {
                result += this.bulletin[i][j] + " ";
            }
            result += "\n";
            result += "Moyenne: " + moyenneMatiere(i) + "\n\n";  // Ajout d'une ligne vide entre chaque matière
        }

        result += "Moyenne generale: " + moyenneGenerale() + "\n";

        return result;
    }

    /**
    * Initializes the 'bulletin' array with random values between 0 (inclusive) and 20 (exclusive).
    * The 'bulletin' array represents the report of each note for the student in different subjects.
    * This method is typically called during the construction of an Etudiant object.
    */
    private void initialisation(){
        for(int i = 0; i < this.bulletin.length; i++){
            for(int j = 0; j < this.bulletin[i].length; j++){
                this.bulletin[i][j] = (Math.random())*20;
            }
        }
    }

    /**
     * Calculates and returns the average of the notes for a specific subject.
     *
     * @param indiceMatiere The index of the subject for which the average is calculated.
     * @return double The average note for the specified subject.
     * If the index is invalid (less than 0 or greater than or equal to the number of subjects),
     * an error message is printed, and the method returns 0.
     */
    public double moyenneMatiere(int indiceMatiere){
        double sommeNote = 0;
        double moyenneNote = 0;
        if(indiceMatiere > this.bulletin.length || indiceMatiere < 0){
            System.out.println("moyenneMatiere : Erreur indice");
        } else {
            for(int i = 0; i < this.bulletin[indiceMatiere].length; i++){
                sommeNote += this.bulletin[indiceMatiere][i];
            }
        }
        moyenneNote = sommeNote/this.bulletin[indiceMatiere].length;
        return (moyenneNote);
    }

    /**
     * Calculates and returns the overall average for the student based on weighted averages of individual subjects.
     *
     * @return double The overall average of the student's grades.
     * If the student has not been graded in any subject, an error message is printed, and the method returns 0.
     */
    public double moyenneGenerale() {
        double somme = 0;
        double sommeCoeff = 0;
        double resultat = 0;
    
        if (this.bulletin.length != 0) {
            for (int i = 0; i < this.bulletin.length; i++) {
                double moyenneMatiere = moyenneMatiere(i);  // Utilise la méthode moyenneMatiere ici
                somme += moyenneMatiere * this.COEFFICIANTS[i];
            }
    
            for (int j = 0; j < this.COEFFICIANTS.length; j++) {
                sommeCoeff += this.COEFFICIANTS[j];
            }
    
            resultat = somme / sommeCoeff;
        } else {
            System.out.println("Etudiant pas note !");
        }
        return resultat;
    }
    
    /**
     * Prints the student's bulletin, including the average grades for each subject and the overall average.
     * The information is displayed in the console.
     */
    public void bulletinEtudiant() {
        System.out.println("Bulletin de l'etudiant: " + this.nom);

        for (int i = 0; i < this.MATIERES.length; i++) {
            double moyenneMatiere = moyenneMatiere(i);
            System.out.println(this.MATIERES[i] + ": " + moyenneMatiere);
        }

        double moyenneGenerale = moyenneGenerale();
        System.out.println("Moyenne generale: " + moyenneGenerale);
        System.out.println("\n\n");
    }
}
