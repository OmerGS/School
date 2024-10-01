import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class TestScanner {

    public static void main(String[] args) {
        testConstructeur();
        testClose();
        testHasNextLine();
        testNextLine();
    }

    public static void testConstructeur() {
        System.out.println("=== Test du constructeur ===");
        System.out.println("Cas Normal");
        try {
            File file = new File("test.txt"); // Chemin vers votre fichier de test
            MyScanner scanner = new MyScanner(file);
            System.out.println("Constructeur : OK");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        // Cas d'erreur
        try {
            File file = new File("fichier_inexistant.txt");
            System.out.println("Cas d'Erreur");
            MyScanner scanner = new MyScanner(file);
            scanner.close(); // Fermeture pour éviter les fuites de ressources
            System.out.println("Erreur : Le constructeur n'a pas levé d'exception pour un fichier inexistant.");
        } catch (RuntimeException e) {
            System.out.println("Constructeur : OK (Fichier introuvable)");
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void testClose() {
        System.out.println("=== Test de la méthode close ===");
        // Cas normal
        try {
            File file = new File("test.txt"); // Chemin vers votre fichier de test
            MyScanner scanner = new MyScanner(file);
            scanner.close();
            System.out.println("close() : OK");
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void testHasNextLine() {
        System.out.println("=== Test de la méthode hasNextLine ===");
        // Cas normal
        try {
            File file = new File("test.txt"); // Chemin vers votre fichier de test
            MyScanner scanner = new MyScanner(file);
            System.out.println("hasNextLine() : " + scanner.hasNextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }

    public static void testNextLine() {
        System.out.println("=== Test de la méthode nextLine ===");
        // Cas normal
        try {
            File file = new File("test.txt"); // Chemin vers votre fichier de test
            MyScanner scanner = new MyScanner(file);
            System.out.println("nextLine() : " + scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}