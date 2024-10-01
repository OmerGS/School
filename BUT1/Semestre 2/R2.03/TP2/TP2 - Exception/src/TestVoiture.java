public class TestVoiture {
    public static void main(String[] args) {
        testConstructeurEtToString();
    }

    public static void testConstructeurEtToString(){
        System.out.println("***** TestConstructeyrEtToString *****");
        System.out.println("*** Cas Normaux ***");

        Voiture voitureFrancaise = new Voiture("Renault", "Scenic", 103);
        Voiture voitureAllemande = new Voiture("BMW", "M5", 600);
        Voiture voitureJaponaise = new Voiture("Toyota", "Yaris", 50);
        Voiture voiture = new Voiture("Fiat", "Multipla", -50);

        System.out.println(voitureFrancaise.toString() + "\n");
        System.out.println(voitureAllemande.toString() + "\n");
        System.out.println(voitureJaponaise.toString() + "\n");
        System.out.println(voiture.toString() + "\n");
    }
}