import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

public class TestOperations {
    private Operations op;
    // méthode exécutée systématiquement AVANT chaque cas de test
    @Before
    public void instancier() {
        this.op = new Operations();
    }
    






    // ------------------ Additionne ----------------


    // Premier cas de test : test de la méthode additionne
    @Test
    public void testAdditionneNormal() {
        System.out.println ( "Methode Additione - Cas Normal" );
        int res = this.op.additionne( 1, 2 );
        try {
            assertEquals("Echec du test", 3, res);
            System.out.println ( "Test réussi" );
        } catch (AssertionError e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    // Premier cas de test : test de la méthode additionne
    @Test
    public void testAdditionneErreur() {
        System.out.println ( "Methode Additione - Cas Erreur" );
        int res = this.op.additionne( 5, 5 );
        try {
            assertNotEquals("Echec du test", 10, res);
            System.out.println ( "Test réussi" );
        } catch (AssertionError e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }








    // ------------------ Racine Carré ----------------


    @Test
    public void testCalculeRacineCarree() {
        System.out.println("\n\n*** testCalculeRacineCaree ***\n");
        
        System.out.println("---- 1er Test ----"); //Fonctionne Normalement
        testCasCalculeRacineCarree(4, 2);

        System.out.println("---- 2eme Test ----"); //Declenche AssertionError
        testCasCalculeRacineCarree(10, 5);

        System.out.println("---- 3eme Test ----"); //Declenche ArithmeticError
        testCasCalculeRacineCarree(-4, -5);
    }

    private void testCasCalculeRacineCarree(double valeur, double resultatAttendu){
        double res = 0;
        try {
            res = this.op.calculeRacineCarree (valeur);
            
            try {
                assertEquals ( "ECHEC du test : racine carrée incorrecte : ", resultatAttendu, res, 0d );
                System.out.println ( "Test réussi" );
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
            }

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }


    // lanceur
    public static void main ( String args[]) {
        JUnitCore.main("TestOperations");
    }
}