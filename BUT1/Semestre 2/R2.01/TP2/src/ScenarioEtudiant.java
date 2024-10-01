import java.util.ArrayList;
import java.util.List;

public class ScenarioEtudiant {
    static double[] coefficients = {3, 2, 1, 2, 3};
    static String[] matiere = {"Math", "Chimie", "Histoire", "Philo", "Francais"};

    /** 
     * @param args
     */
    public static void main(String[] args) {
        bulletinClasse();

        Etudiant etudiant;

        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);

        // ! Si dessus on va avoir les methodes de test unitaire pour chaque methode de la classe Etudiant. Il sont sous commentaires.
        
        testConstructeurEtudiant();
        testSetNom();
        testGetNom();
        testGetNbMatiere();


        System.out.println("====== testGetUneNote ======");
        double note = etudiant.getUneNote(0,1);
        System.out.println(note);

        note = etudiant.getUneNote(1,2);
        System.out.println(note);

        note = etudiant.getUneNote(2,0);
        System.out.println(note);


        System.out.println("====== testMeilleurNote ======");
        String meilleurNote = etudiant.meilleurNote();
        System.out.println(meilleurNote);


        System.out.println("====== testMoyenneMatiere ======");
        double moyenneMatiere = etudiant.moyenneMatiere(0);
        System.out.println(moyenneMatiere);

        moyenneMatiere = etudiant.moyenneMatiere(1);
        System.out.println(moyenneMatiere);

        moyenneMatiere = etudiant.moyenneMatiere(2);
        System.out.println(moyenneMatiere);


        System.out.println("====== testToString ======");
        System.out.println(etudiant.toString());


        System.out.println("====== testMoyenneGenerale ======");
        System.out.println(etudiant.moyenneGenerale());
    
    }

    // ! Affiche les bulletins perso et celui de la classe

    public static void bulletinClasse(){
        double[] coefficients = {3, 2, 1, 2, 3};
        String[] matiere = {"Math", "Chimie", "Histoire", "Philo", "Francais"};
        List<Etudiant> listeEtudiant = new ArrayList<>();

        Etudiant etudiantOmer = new Etudiant("Omer", matiere, coefficients, 3);
        Etudiant etudiantRomain = new Etudiant("Romain", matiere, coefficients, 3);
        Etudiant etudiantMael = new Etudiant("Mael", matiere, coefficients, 3);
        Etudiant etudiantRayanne = new Etudiant("Rayanne", matiere, coefficients, 3);
        Etudiant etudiantNoe = new Etudiant("Noe", matiere, coefficients, 3);
        Etudiant etudiantBrayan = new Etudiant("Brayan", matiere, coefficients, 3);

        // Afficher le bulletin de chaque étudiant
        etudiantOmer.bulletinEtudiant();
        etudiantRomain.bulletinEtudiant();
        etudiantMael.bulletinEtudiant();
        etudiantRayanne.bulletinEtudiant();
        etudiantNoe.bulletinEtudiant();
        etudiantBrayan.bulletinEtudiant();

        listeEtudiant.add(etudiantRomain);
        listeEtudiant.add(etudiantOmer);
        listeEtudiant.add(etudiantMael);
        listeEtudiant.add(etudiantRayanne);
        listeEtudiant.add(etudiantNoe);
        listeEtudiant.add(etudiantBrayan);


        // Calculer et afficher la moyenne de la classe
        double moyenneClasse = calculerMoyenneClasse(listeEtudiant);
        System.out.println("Moyenne de la classe: " + moyenneClasse);

        // Trouver l'étudiant avec la meilleure moyenne
        Etudiant meilleurEtudiant = trouverMeilleurEtudiant(listeEtudiant);
        System.out.println("Meilleur étudiant: " + meilleurEtudiant.getNom() + " avec une moyenne de " + meilleurEtudiant.moyenneGenerale());

        // Trouver l'étudiant avec la pire moyenne
        Etudiant pireEtudiant = trouverPireEtudiant(listeEtudiant);
        System.out.println("Pire étudiant: " + pireEtudiant.getNom() + " avec une moyenne de " + pireEtudiant.moyenneGenerale());
    }

    // ? Trouve la moyenne de la classe

    private static double calculerMoyenneClasse(List<Etudiant> listeEtudiant) {
        double sommeMoyennes = 0;
        double moyenneClasse = 0;
        int nombreEtudiants = listeEtudiant.size();

        for (int i = 0; i < listeEtudiant.size(); i++) {
            sommeMoyennes += listeEtudiant.get(i).moyenneGenerale();
        }

        moyenneClasse = sommeMoyennes / nombreEtudiants;
        return moyenneClasse;
    }

    // ! Trouve l'etudiant avec la meilleur moyenne generale.

    private static Etudiant trouverMeilleurEtudiant(List<Etudiant> listeEtudiants) {
        Etudiant meilleurEtudiant = listeEtudiants.get(0);

        for (int i = 0; i < listeEtudiants.size(); i++) {
            if (listeEtudiants.get(i).moyenneGenerale() > meilleurEtudiant.moyenneGenerale()) {
                meilleurEtudiant = listeEtudiants.get(i);
            }
        }

        return(meilleurEtudiant);
    }

    // ? Trouve l'etudiant avec la pire moyenne generale.
    
    private static Etudiant trouverPireEtudiant(List<Etudiant> listeEtudiants) {
        Etudiant pireEtudiant = listeEtudiants.get(0);

        for (int i = 0; i < listeEtudiants.size(); i++) {
            if (listeEtudiants.get(i).moyenneGenerale() < pireEtudiant.moyenneGenerale()) {
                pireEtudiant = listeEtudiants.get(i);
            }
        }

        return pireEtudiant;
    }







    // ! Test de Constructeur Etudiant A FINIR (Erreur)

    public static void testConstructeurEtudiant(){
        System.out.println("====== testConstructeurEtudiant ======");
        Etudiant etudiant;

        //Cas Normal
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasConstructeurEtudiant(etudiant, "etudiant");

        //Cas Normal
        etudiant = new Etudiant("omer", matiere, coefficients, 3);
        testCasConstructeurEtudiant(etudiant, "omer");

        //Cas Echoue
        etudiant = new Etudiant("mael", matiere, coefficients, 3);
        testCasConstructeurEtudiant(etudiant, "maelle");

    }

    private static void testCasConstructeurEtudiant(Etudiant etudiant, String nom){
        if(etudiant.getNom().equals(nom)){
            System.out.println("Reussi ! ");
        } else {
            System.out.println("Test echoue ! (c'est normal)");
        }
    }






    // ! Test de Set Nom FINI

    public static void testSetNom(){
        System.out.println("====== testSetNom ======");
        
        Etudiant etudiant;

        //1er Test
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasSetNom(etudiant, "etudiante");

        //2eme Test
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasSetNom(etudiant,null);

        //3eme Test
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasSetNom(etudiant, "Omer");
    }

    public static void testCasSetNom(Etudiant etudiant, String nouveauNom){
        System.out.println("L'ancien nom de cet etudiant etait : " + etudiant.getNom());

        etudiant.setNom(nouveauNom);

        System.out.println("Voici le nouveau nom de cet etudiant : " + etudiant.getNom());

        System.out.println();
    }



    // ! Test de Get Nom FINI

    public static void testGetNom(){
        System.out.println("====== testGetNom ======");
        
        Etudiant etudiant;

        //1er Test
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasGetNom(etudiant, "etudiant");

        //2eme Test
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasGetNom(etudiant, null);

        //3eme Test
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasGetNom(etudiant, "etudiante");
    }

    public static void testCasGetNom(Etudiant etudiant, String nom){
        if(etudiant.getNom() != nom){
            System.out.println("Le nom donné n'est pas egale aux nom de l'etudiant");
        } else {
            System.out.println("Le nom donné est celui de l'etudiant");
        }
    }



    // ! Test de GetNbMatiere FINI

    public static void testGetNbMatiere(){
        System.out.println("====== testGetNbMatiere ======");
        Etudiant etudiant;
        
        //1er test Erreur
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasGetNbMatiere(etudiant, 4);

        //2eme test Normal
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasGetNbMatiere(etudiant, 5);

        //3eme test Erreur
        etudiant = new Etudiant("etudiant", matiere, coefficients, 3);
        testCasGetNbMatiere(etudiant, 0);
        
    }

    public static void testCasGetNbMatiere(Etudiant etudiant, int nbMatiere){
        if(etudiant.getNbMatiere() != nbMatiere){
            System.out.println("Erreur : NbMatiere pas egaux");
        } else {
            System.out.println("Reussi : Le nombre de Matieres est la même");
        }
    }
}
