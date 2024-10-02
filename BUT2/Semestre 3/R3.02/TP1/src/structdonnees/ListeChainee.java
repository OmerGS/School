package structdonnees;

/**
 * A doubly linked list implementation with a sentinel node.
 * @author O.Gunes
 */
public class ListeChainee {

    /**
     * Represents a node in the doubly linked list.
     */
    private class Element {
        Element prec;  // Previous node
        Element suiv;  // Next node
        Object data;   // Data stored in the node
        
        /**
         * Constructs a new node with the given previous node, next node, and data.
         *
         * @param prec the previous node
         * @param suiv the next node
         * @param donnee the data stored in the node
         */
        Element(Element prec, Element suiv, Object donnee) {
            this.prec = prec;
            this.suiv = suiv;
            this.data = donnee;
        }
    }

    private Element sentinel;  // Sentinel node
    private Element courant;   // Current node
    private int taille;        // Size of the list
    
    /**
     * Constructs an empty doubly linked list with a sentinel node.
     */
    public ListeChainee() { 
        this.sentinel = new Element(null, null, null);
        this.taille = 0;
        this.courant = new Element(null, null, null);
    }
    
    /**
     * Checks the validity of the list invariant.
     * 
     * @return true if the invariant holds, false otherwise.
     */
    private boolean invariant() {
        return this.taille >= 0;
    }

    /**
     * Inserts a new element with the given data before the current node.
     *
     * @param data the data to be inserted
     */
    public void inserer(Object data) {
        Element a = new Element(this.courant.prec, this.courant, data);
        this.courant.prec = a;
        this.courant = a;
        this.courant.suiv.prec = a;
        this.taille++;
        
        assert this.taille > 0 : "la taille doit etre superieure a 0.";
        assert (this.invariant()) : "Invariant violé !";
    }

    /**
     * Removes the current element from the list.
     *
     * @return true if the removal was successful, false if the list was empty
     */
    public boolean supprimer() {
        boolean varAReturn = true;

        if (this.taille == 0) {
            varAReturn = false;  // List is empty
        }

        if (this.courant == sentinel) {
            this.courant = this.courant.suiv;
        } else {
            this.courant.prec.suiv = this.courant.suiv;
            
            if (this.courant.suiv != null) {
                this.courant.suiv.prec = this.courant.prec;
            }
            this.courant = this.courant.prec;
        }

        this.taille--;

        assert this.taille >= 0 : "la taille doit étre non négative.";
        assert (this.invariant()) : "Invariant violé !";
        
        return varAReturn;
    }

    /**
     * Checks if the list contains an element with the given data.
     *
     * @param data the data to search for
     * @return true if the data is found, false otherwise
     */
    public boolean contient(Object data) {
        boolean variableAReturn = false;
        Element temp = this.sentinel.suiv;
        while (temp != null) {
            if (temp.data.equals(data)) {
                variableAReturn = true;
            }
            temp = temp.suiv;
        }
        return(variableAReturn);
    }

    /**
     * Inserts a new element with the given data at the specified index.
     *
     * @param index the position to insert the element
     * @param data the data to be inserted
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void ajouter(int index, Object data) {
        if (index < 0 || index > this.taille) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Element temp = this.courant;
        for (int i = 0; i < index; i++) {
            temp = temp.suiv;
        }

        Element newElement = new Element(temp, temp.suiv, data);

        if (temp.suiv != null) {
            temp.suiv.prec = newElement;
        }

        temp.suiv = newElement;
        this.courant = newElement;
        this.taille++;
        
        assert this.taille > 0 : "la taille doit etre augmentee.";
        assert (this.invariant()) : "Invariant violé !";
    }

    /**
     * Retrieves the data of the current element.
     *
     * @return the data of the current element
     * @throws IllegalStateException if there is no current element
     */
    public Object obtenirValeur() {
        if (this.courant == null || this.courant == sentinel) {
            throw new IllegalStateException("No current element.");
        }
        return(this.courant.data);
    }

    /**
     * Changes the data of the current element to the given data.
     *
     * @param newData the new data for the current element
     * @throws IllegalStateException if there is no current element
     */
    public void changerValeur(Object newData) {
        if (this.courant == null || this.courant == sentinel) {
            throw new IllegalStateException("No current element.");
        }
        this.courant.data = newData;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean estVide() {
        return(this.taille == 0);
    }

    /**
     * Retrieves the size of the list.
     *
     * @return the size of the list
     */
    public int obtenirTaille() {
        return(this.taille);
    }

    /**
     * Moves the current position to the head of the list.
     */
    public void teteListe() {
        if (taille != 0) {
            courant = sentinel.suiv;
        }
        
        assert courant == sentinel.suiv : "courant ne pointe pas sur la tete de la liste.";
    }

    /**
     * Moves the current position to the end of the list.
     */
    public void finListe() {
        if(taille != 0) {
            courant = sentinel.prec;
        }

        assert courant == sentinel.prec : "courant ne pointe pas sur la fin de la liste.";
    }

    /**
     * Moves the current position to the next element in the list.
     *
     * @return true if the move was successful, false if there is no next element
     */
    public boolean suivant() {
        boolean variableAReturn = false;
        if (courant != null && courant.suiv != null) {
            courant = courant.suiv;
            variableAReturn = true;
        }
        assert courant != sentinel : "Le pointeur courant ne doit pas etre egal a la sentinel apres l'execution.";
        assert (this.invariant()) : "Invariant violé !";
        
        return(variableAReturn);
    }

    /**
     * Moves the current position to the previous element in the list.
     *
     * @return true if the move was successful, false if there is no previous element
     */
    public boolean precedent() {
        boolean variableAReturn = false;
        if (courant != null && courant.prec != null && courant.prec != sentinel) {
            courant = courant.prec;
            variableAReturn = true;
        }
        assert courant != sentinel : "Le pointeur courant ne doit pas etre egal a la sentinel apres l'execution.";
        assert (this.invariant()) : "Invariant violé !";
        
        return(variableAReturn);
    }

    /**
     * Returns a string representation of the list.
     * 
     * @return a string representation of the list in the format [element1, element2, ...].
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Element temp = sentinel.suiv;
        while (temp != sentinel) {
            sb.append(temp.data);
            if (temp.suiv != sentinel) {
                sb.append(", ");
            }
            temp = temp.suiv;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Retrieves the data of the element at the specified index.
     * 
     * @param index the position of the element to retrieve.
     * @return the data of the element at the specified index.
     * @throws IllegalStateException if the list is empty.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Object obtenirValeurA(int index) {
        if (estVide()) {
            throw new IllegalStateException("la liste est vide.");
        }
        if (index < 0 || index >= taille) {
            throw new IndexOutOfBoundsException("index hors limites.");
        }
        Element temp = sentinel.suiv;
        for (int i = 0; i < index; i++) {
            temp = temp.suiv;
        }

        assert (this.invariant()) : "Invariant violé !";
        return temp.data;
    }
}
