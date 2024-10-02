package structdonnees;
import java.rmi.NotBoundException;

/**
 * A hash table implementation that uses open addressing and circular traversal
 * for collision resolution.
 * 
 * @author O.GUNES
 */
public class TableHachage implements Table {
    /**
    * Table which we will store the data 
    */
    private Tuple[] table;

    /**
    * The number of data we stored in the table.
    */
    private int nbTuple;

    /**
    * The length of the table 
    */
    private final int TAILLE = 10;

    /**
     * Constructor to initialize the hash table with a default size.
     */
    public TableHachage() {
        this.table = new Tuple[this.TAILLE];
        this.nbTuple = 0;
    }

    /* IMPLEMENTATION OF INTERFACE METHODS */

    /**
     * Retrieves the data associated with the given key.
     * 
     * @param cle The key to search for.
     * @return The data associated with the key or null if not found.
     * @throws NullPointerException if the key is null.
     */
    public Object obtenir(String cle) {
        if (cle == null) {
            throw new NullPointerException("The key is null");
        }

        int index = calculerIndex(cle);
        int placement = rechercheCirculaire(cle, index);

        if (placement != -1 && this.table[placement] != null) {
            return this.table[placement].donnee;
        }

        return null;
    }

    /**
     * Inserts data into the table with a specified key.
     * 
     * @param cle    The key for the data.
     * @param donnee The data to insert.
     * @return true if the data is successfully inserted, false otherwise.
     * @throws NotBoundException if the table is full.
     * @throws NullPointerException if the key is null.
     */
    public boolean inserer(String cle, Object donnee) throws NotBoundException {
        if (nbTuple >= this.TAILLE) {
            throw new NotBoundException("No more space available in the table");
        } else if (cle == null) {
            throw new NullPointerException("The key is null");
        }

        int index = calculerIndex(cle);
        int placement = rechercheCirculaire(cle, index); 
        if (placement != -1 && this.table[placement] == null) {  // Only insert if slot is empty
            this.table[placement] = new Tuple(cle, donnee);
            nbTuple++;
            return true;
        }

        return false;
    }

    /**
     * Deletes the data associated with the given key from the table.
     * 
     * @param cle The key to delete.
     * @return true if the key is successfully deleted, false otherwise.
     * @throws Exception if the table is empty or the key is null.
     */
    public boolean supprimer(String cle) throws Exception {
        if (nbTuple == 0) {
            throw new Exception("The table is empty");
        } else if (cle == null) {
            throw new NullPointerException("The key is null");
        }

        int index = calculerIndex(cle);
        int placement = rechercheCirculaire(cle, index); 

        if (placement != -1 && this.table[placement] != null) {
            this.table[placement] = null;
            nbTuple--;
            return true;
        }

        return false;
    }

    /* INTERNAL METHODS IMPLEMENTATION */

    /**
     * Gets the current number of tuples in the table.
     * 
     * @return The number of tuples.
     */
    public int obtenirNbTuples() {
        return this.nbTuple;
    }

    /**
     * Calculates the index for the given key based on its hash value.
     * 
     * @param cle The key to calculate the index for.
     * @return The index in the table.
     */
    private int calculerIndex(String cle) {
        char[] charArray = cle.toCharArray();
        int totalValue = 0;

        for (char c : charArray) {
            totalValue += (int) c;
        }

        return totalValue % TAILLE;
    }

    /**
     * Performs a circular search starting from a given index to find either
     * an empty slot or a matching key.
     * 
     * @param cle    The key to search for.
     * @param indice The initial index to start the search from.
     * @return The index where the key is found or should be inserted,
     *         or -1 if no valid position is found.
     */
    private int rechercheCirculaire(String cle, int indice) {
        int compteur = 1;
        int placement = indice;

        while (compteur < this.TAILLE * 2) {
            if (this.table[placement] == null || this.table[placement].memeCle(cle)) {
                return placement;
            } else {
                compteur++;
                placement = (placement + compteur) % this.TAILLE;
            }
        }

        return -1;
    }

    /**
     * Returns the string representation of the table, showing each tuple's key and data.
     * 
     * @return A string representation of the hash table.
     */
    public String toString() {
        StringBuilder tableau = new StringBuilder();
        for (Tuple tuple : table) {
            if (tuple != null) {
                tableau.append(tuple.toString()).append("\n");
            }
        }
        return tableau.toString();
    }

    /**
     * Private class to represent key-value pairs (tuples) in the hash table.
     */
    private class Tuple {
        /**
         * The key of the tuple.
         */
        String cle;

        /**
         * The data contained in the tuple.
         */
        Object donnee;

        /**
         * Constructor for a tuple.
         * 
         * @param cle    The key for this tuple.
         * @param donnee The data for this tuple.
         */
        Tuple(String cle, Object donnee) {
            this.cle = cle;
            this.donnee = donnee;
        }

        /**
         * Checks if this tuple has the same key as another.
         * 
         * @param autreCle The other key to compare with.
         * @return True if the keys match, false otherwise.
         */
        boolean memeCle(String autreCle) {
            return this.cle.equals(autreCle);
        }

        /**
         * Returns a string representation of the tuple.
         * 
         * @return A string containing the key and data of the tuple.
         */
        public String toString() {
            return "Key: " + this.cle + "\nData: " + this.donnee;
        }
    }
}