package structdonnees;

import static org.junit.Assert.*;

import java.rmi.NotBoundException;

import org.junit.Before;
import org.junit.Test;

public class TableHachageTest {
    
    private TableHachage tableHachage;

    @Before
    public void setUp() throws Exception {
        tableHachage = new TableHachage();
    }

    // Test de la méthode TableHachage()
    @Test
    public final void testTableHachage() {
        assertNotNull("Table should be initialized", tableHachage);
        assertEquals("Table should be initialized with 0 tuples", 0, tableHachage.obtenirNbTuples());
        System.out.println("Test réussi : La table est bien initialisée.");
    }

    // Test de la méthode obtenir()
    @Test
    public final void testObtenir() {
        try {
        	System.out.println("*** testObtenir ***");
        	
            // Cas Normal : Obtenir une valeur pour une clé existante
            tableHachage.inserer("key1", "value1");
            assertEquals("Should return 'value1'", "value1", tableHachage.obtenir("key1"));
            System.out.println("Test réussi : Obtenir avec une clé existante.");

            // Cas Limite : Obtenir une valeur pour une clé inexistante
            assertNull("Should return null for a non-existing key", tableHachage.obtenir("nonExistingKey"));
            System.out.println("Test réussi : Obtenir avec une clé inexistante retourne null.");

            // Cas d'erreur : clé null
            try {
                tableHachage.obtenir(null);
                fail("Should throw NullPointerException for null key");
            } catch (NullPointerException e) {
                System.out.println("Test réussi : NullPointerException capturé pour une clé null.");
            }

        } catch (NotBoundException e) {
            fail("Unexpected NotBoundException");
        }
    }

    // Test de la méthode inserer()
    @Test
    public final void testInserer() {
        System.out.println("\n*** testInserer ***");

        try {
            // Cas Normal : Ins�rer une paire cl�-valeur
            assertTrue("Should successfully insert key-value pair", tableHachage.inserer("key1", "value1"));
            System.out.println("Test r�ussi : Insertion r�ussie.");
            assertEquals("Table should contain 1 tuple", 1, tableHachage.obtenirNbTuples());

            // Cas Limite : Insertion d'une cl� d�j� pr�sente (devrait mettre � jour la valeur)
            assertFalse("Should return false for inserting an already existing key", tableHachage.inserer("key1", "newValue1"));
            System.out.println("Test r�ussi : Mise � jour de la cl� existante.");
            assertEquals("Table should still contain 1 tuple", 1, tableHachage.obtenirNbTuples());

            // Cas d'erreur : cl� null
            try {
                tableHachage.inserer(null, "value2");
                fail("Should throw NullPointerException for null key");
            } catch (NullPointerException e) {
                System.out.println("Test r�ussi : NullPointerException captur� pour une cl� null.");
            }

        } catch (NotBoundException e) {
            fail("Unexpected NotBoundException: " + e.getMessage());
        }
    }


    // Test de la méthode obtenirNbTuples()
    @Test
    public final void testObtenirNbTuples() {
    	System.out.println("\n*** testObtenirNbTuple ***");

        try {
            // Cas Limite : Table vide
            assertEquals("Should return 0 for an empty table", 0, tableHachage.obtenirNbTuples());
            System.out.println("Test réussi : La table vide retourne 0 tuples.");

            // Cas Normal : Ajout de tuples
            tableHachage.inserer("key1", "value1");
            assertEquals("Should return 1 after insertion", 1, tableHachage.obtenirNbTuples());
            System.out.println("Test réussi : Insertion d'un tuple, 1 tuple dans la table.");

            tableHachage.inserer("key2", "value2");
            assertEquals("Should return 2 after second insertion", 2, tableHachage.obtenirNbTuples());
            System.out.println("Test réussi : Insertion de 2 tuples, 2 tuples dans la table.");

        } catch (NotBoundException e) {
            fail("Unexpected NotBoundException");
        }
    }

    // Test de la méthode toString()
    @Test
    public final void testToString() {
    	System.out.println("\n*** testToString ***");

        try {
            // Cas Limite : Table vide
            String expectedEmptyString = ""; // Ajuster selon l'implémentation réelle de toString
            assertEquals("Should return an empty string for an empty table", expectedEmptyString, tableHachage.toString());
            System.out.println("Test réussi : Table vide retourne une chaîne vide.");

            // Cas Normal : Table avec des tuples
            tableHachage.inserer("key1", "value1");
            String expectedString = "Key: key1\nData: value1\n";
            assertEquals("Should return correct string representation of the table", expectedString, tableHachage.toString());
            System.out.println("Test réussi : Table avec un tuple retourne la chaîne correcte.");

        } catch (NotBoundException e) {
            fail("Unexpected NotBoundException");
        }
    }
    
    
    
    // Test de la méthode supprimer()
    @Test
    public final void testSupprimer() {
    	System.out.println("\n*** testSupprimer ***");

        	try {
                tableHachage.inserer("key1", "value1");
                assertTrue("Should successfully delete key-value pair", tableHachage.supprimer("key1"));
        	} catch(Exception e) {
        		
        	}
            // Cas Normal : Supprimer une clé existante

            System.out.println("Test réussi : Suppression réussie.");
            assertEquals("Table should have 0 tuples", 0, tableHachage.obtenirNbTuples());

            // Cas d'erreur : Suppression dans une table vide
            try {
                tableHachage.supprimer("key1");
                fail("Should throw Exception for deleting from an empty table");
            } catch (Exception e) {
                System.out.println("Test réussi : Exception capturée pour suppression dans une table vide.");
            }

            // Cas d'erreur : clé null
            try {
                tableHachage.supprimer(null);
                fail("Should throw NullPointerException for null key");
            } catch (Exception e) {
                System.out.println("Test réussi : NullPointerException capturé pour une clé null.");
            }
    }
}