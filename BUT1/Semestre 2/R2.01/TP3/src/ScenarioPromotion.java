import java.util.Random;

public class ScenarioPromotion {
    static double[] coefficients = {3, 2, 1, 2, 3};
    static String[] matieres = {"Math", "Chimie", "Histoire", "Philo", "Francais"};
    
    static Promotion but1;
    static Promotion but2;
    static Promotion but3;

    public static void main(String[] args) {
        but1 = new Promotion("but1info", creationTableauEtudiant());
        but2 = new Promotion("but2info", creationTableauEtudiant());
        but3 = new Promotion("but3info", creationTableauEtudiant());

        testConstructeur();
        separationMethode();
        testGetMajor();
        separationMethode();
        testGetNom();
        separationMethode();
        testSetNom();
        separationMethode();
        testMoyenne();
        separationMethode();
        moyenneMax();
        separationMethode();
        moyenneMin();
        separationMethode();
        testGetEtudiant();
        separationMethode();
        testMoyenneMatiere();
        separationMethode();
        testToString();
    }

    private static void separationMethode(){
        System.out.println(" ------------------------- ");
    }

    private static void separationTest(){
        System.out.println(" ********************** ");
    }


// ! ----------------------------------------------------------


    public static void testConstructeur(){
        System.out.println("*** testConstructeur ***");
        Promotion promo;
        Etudiant[] liste = null;

        separationTest();
        System.out.println("Test n1 : Cas Erreur");
        //Cas Erreur
        promo = new Promotion("nom", liste);
        testCasConstructeur(promo, liste);

        separationTest();
        liste = creationTableauEtudiant();

        System.out.println("Test n2 : Cas Normal");
        //Cas Normal
        promo = new Promotion("nom", liste);
        testCasConstructeur(promo, liste);

        separationTest();

        System.out.println("Test n3 : Cas Erreur");
        //Cas Erreur
        promo = new Promotion(null, liste);
        testCasConstructeur(promo, liste);

        separationTest();

        System.out.println("Test n4 : Cas Normal");
        //Cas Normal
        promo = new Promotion("but1", liste);
        testCasConstructeur(promo, liste);
    }

    private static void testCasConstructeur(Promotion promo, Etudiant[] liste) {
        boolean toutOk = true;
        if (liste == null) {
            System.out.println("Tableau Null");
            toutOk = false;
        } else if (liste.length == 0) {
            System.out.println("Tableau Vide");
            toutOk = false;
        }

        if(promo.getNom() == null){
            System.out.println("Nom Null");
            toutOk = false;
        }

        if(toutOk){
            System.out.println("Reussi");
        } else {
            System.out.println("Echec");
        }
    }
    
    public static void testGetMajor(){
        System.out.println(" *** testGetMajor ***");
        System.out.println("Major de BUT 1 Info : " + but1.getMajor().getNom());
        System.out.println("Major de BUT 2 Info : " + but2.getMajor().getNom());
        System.out.println("Major de BUT 3 Info : " + but3.getMajor().getNom());
    }
    
    public static void testGetNom(){
        System.out.println(" *** testGetNom *** ");
        Promotion promo;
        Etudiant[] liste = creationTableauEtudiant();

        System.out.println("Test n1 : Cas Normal !");
        String nom = "BUT1Info";
        promo = new Promotion(nom, liste);
        testCasGetNom(promo, nom);

        separationTest();

        System.out.println("Test n2 : Cas Normal !");
        nom = "BUT2Info";
        promo = new Promotion(nom, liste);
        testCasGetNom(promo, nom);

        separationTest();

        System.out.println("Test n3 : Cas Erreur !");
        nom = "BUT3Info";
        promo = new Promotion(nom, liste);
        testCasGetNom(promo, "IUT3Info");
    }

    private static void testCasGetNom(Promotion promo, String nom){
        if(nom.equals(promo.getNom())){
            System.out.println("Reussi");
        } else {
            System.out.println("Echoue");
        }
    }

    public static void testSetNom(){
        System.out.println(" *** testSetNom *** ");
        Promotion promo;
        Etudiant[] liste = creationTableauEtudiant();
        String ancienNom = "DUT";
        String nouveauNom = "BUT";

        System.out.println("Test n1 : Cas Normal !");
        promo = new Promotion(ancienNom, liste);
        promo.setNom(nouveauNom);
        testCasSetNom(promo, ancienNom, nouveauNom);

        System.out.println("Test n2 : Cas Limite !");
        promo = new Promotion(ancienNom, liste);
        promo.setNom(ancienNom);
        testCasSetNom(promo, ancienNom, ancienNom);

        System.out.println("Test n2 : Cas Erreur !");
        promo = new Promotion(ancienNom, liste);
        promo.setNom("A");
        testCasSetNom(promo, ancienNom, "B");
    }

    private static void testCasSetNom(Promotion promo, String ancienNom, String nouveauNom){
        if(promo.getNom().equals(nouveauNom) || promo.getNom().equals(ancienNom)){
            System.out.println("Reussi");
        } else {
            System.out.println("Echec");
        }

        if(ancienNom.equals(nouveauNom)){
            System.out.println("setNom : Ancien nom est egale au nouveau nom");
        }
    }

    public static void testMoyenne(){
        System.out.println(" *** testMoyenne *** ");
        double note = 0;

        note = but1.moyenne();
        testCasMoyenne(note);
        
        separationTest();
        
        note = but2.moyenne();
        testCasMoyenne(note);

        separationTest();

        note = but3.moyenne();
        testCasMoyenne(note);
    }

    private static void testCasMoyenne(double note){
        System.out.print("Moyenne : " + note);
        if(note > 20 || note < 0){
            System.out.println(" | Erreur");
        } else {
            System.out.println(" | Reussi");
        }
    }

    public static void moyenneMax(){
        System.out.println(" *** testMoyenneMax *** ");
        double note = 0;

        note = but1.moyenneMax();
        testCasMoyenneMax(note);
        
        separationTest();
        
        note = but2.moyenneMax();
        testCasMoyenneMax(note);

        separationTest();

        note = but3.moyenneMax();
        testCasMoyenneMax(note);
    }

    private static void testCasMoyenneMax(double note){
        System.out.print("Moyenne : " + note);
        if(note > 20 || note < 0){
            System.out.println(" | Erreur");
        } else {
            System.out.println(" | Reussi");
        }
    }

    public static void moyenneMin(){
        System.out.println(" *** testMoyenneMin *** ");
        double note = 0;

        note = but1.moyenneMin();
        testCasMoyenneMin(note);
        
        separationTest();
        
        note = but2.moyenneMin();
        testCasMoyenneMin(note);

        separationTest();

        note = but3.moyenneMin();
        testCasMoyenneMin(note);
    }

    private static void testCasMoyenneMin(double note){
        System.out.print("Moyenne : " + note);
        if(note > 20 || note < 0){
            System.out.println(" | Erreur");
        } else {
            System.out.println(" | Reussi");
        }
    }

    public static void testGetEtudiant(){
        System.out.println(" *** testGetEtudiant *** ");
        
        System.out.println("On cherche Marie Lefevre : \n" + but1.getEtudiant("Marie Lefevre"));
        System.out.println("On cherche Sophie Petit : \n" + but1.getEtudiant("Sophie Petit"));
        System.out.println("On cherche Lucas Campion : \n" + but1.getEtudiant("Lucas Campion"));
    }

    public static void testMoyenneMatiere(){
        System.out.println(" *** testMoyenneMatiere *** ");
        double note = 0;

        note = but1.moyenneMatiere(1);
        testCasMoyenneMatiere(note);
        
        separationTest();
        
        note = but2.moyenneMatiere(2);
        testCasMoyenneMatiere(note);

        separationTest();

        note = but3.moyenneMatiere(3);
        testCasMoyenneMatiere(note);
    }

    private static void testCasMoyenneMatiere(double note){
        System.out.print("Moyenne : " + note);
        if(note > 20 || note < 0){
            System.out.println(" | Erreur");
        } else {
            System.out.println(" | Reussi");
        }
    }

    public static void testToString(){
        System.out.println(" *** testToString *** ");
        System.out.println(but1.toString());

        separationTest();

        System.out.println(but2.toString());

        separationTest();

        System.out.println(but3.toString());
    }
























    private static final String[] prenoms = {"Jean", "Marie", "Pierre", "Sophie", "Emma", "Louis", "Charlotte", "Lucas", "Brayan", "Romain", "Omer", "Mael", "No\u00E9", "Rayanne", "Benjamin"};
    private static final String[] nomsDeFamille = {"Dubois", "Martin", "Lefevre", "Bernard", "Durand", "Petit", "Thomas", "Leroy", "Campion", "Coignard", "Mellah", "Gunes", "Parcolet", "Saunders"};
    private static final Random random = new Random();

    private static String generateNomPersonne() {
        String prenom = prenoms[random.nextInt(prenoms.length)];
        String nomDeFamille = nomsDeFamille[random.nextInt(nomsDeFamille.length)];
        return(prenom + " " + nomDeFamille);
    }

    public static Etudiant[] creationTableauEtudiant(){
        Etudiant[] listeEtudiantBut1 = new Etudiant[50];
        
        for(int i = 0; i < listeEtudiantBut1.length; i++){
            String nom = generateNomPersonne();
            listeEtudiantBut1[i] = new Etudiant(nom, matieres, coefficients, 3);
        }
        return(listeEtudiantBut1);
    }
}
