public class TestParking {
    public static void main(String[] args) {
        testConstructeurEtToString();
        testGarer();
        testSortir();
    }

    private static void testConstructeurEtToString(){
        System.out.println("\n\n=== TEST CONSTRUCTEUR ET TOSTRING() ===");
        System.out.println("*** Cas Normaux ***");
        
        Parking centreVille = new Parking(4);

        System.out.println(centreVille.toString());
        
    }


    private static void testGarer(){
        System.out.println("\n\n=== TEST GARER() ===");
        try {
            Parking IUT = new Parking(4);
            Voiture voitureFrancaise = new Voiture("Renault", "Scenic", 103);
            IUT.garer(voitureFrancaise, 2);

            System.out.println(IUT.toString());
        } catch (Exception e) {
            System.out.println("Echec du test");
        }



        System.out.println("*** Cas d'Erreur ***");
        try {
            Parking IUT = new Parking(4);
            Voiture voitureFrancaise = new Voiture("Renault", "Scenic", 103);
            IUT.garer(voitureFrancaise, 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n******** 2eme Test ********");
        try {
            Parking IUT = new Parking(4);
            Voiture voitureFrancaise = new Voiture("Renault", "Scenic", 103);
            Voiture voitureAllemande = new Voiture("BMW", "M5", 600);
            IUT.garer(voitureFrancaise, 4);
            IUT.garer(voitureAllemande, 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testSortir(){
        System.out.println("\n\n=== TEST SORTIR() ===");
        try {
            Parking IUT = new Parking(4);
            Voiture voitureFrancaise = new Voiture("Renault", "Scenic", 103);
            IUT.garer(voitureFrancaise, 2);
            IUT.sortir(2);

            System.out.println(IUT.toString());
        } catch (Exception e) {
            System.out.println("Echec du test");
        }



        System.out.println("*** Cas d'Erreur ***");
        try {
            Parking IUT = new Parking(4);
            IUT.sortir(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
