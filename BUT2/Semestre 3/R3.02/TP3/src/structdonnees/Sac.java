package structdonnees;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* The Sac class represents a collection of items (a bag), where
* elements can be added at random positions. The Sac allows iteration
* over its elements, but it does not guarantee any particular order.
*
* @param <E> The type of elements held in this collection.
*
* @author  O.Gunes
*/
public class Sac<E> {

    /**
    * Internal class representing an element in the Sac.
    * Each element stores data and a reference to the next element.
    */
    private class Element {
        Element suiv;  // Pointer to the next element
        E data;        // Data stored in the element

        /**
        * Constructs a new element with the given data and next reference.
        *
        * @param suiv  The next element in the list.
        * @param donnee The data to be stored in the element.
        */
        Element(Element suiv, E donnee) {
            this.suiv = suiv;
            this.data = donnee;
        }
    }


    /**
    * Internal class that implements the Iterator for the Sac.
    * The iterator supports the standard `next`, `hasNext`, and `remove` operations,
    * while also checking for concurrent modifications.
    */
    private class Itr implements Iterator<E> {
        private Element courant;     // The current element being iterated over
        private Element precCourant; // The element before current
        private int expectedModCount; // To handle concurrent modifications

        /**
        * Constructs an iterator for the Sac starting at the sentinel node.
        *
        * @param sentinel The sentinel node of the Sac.
        */
        public Itr(Element sentinel) {
            this.courant = sentinel;
            this.precCourant = sentinel;
            this.expectedModCount = modCount;
        }

        /**
        * Returns true if there are more elements in the Sac to iterate over.
        *
        * @return true if there is another element, false otherwise.
        */
        public boolean hasNext() {
            return courant.suiv != sentinel;  // Check if the next element is not the sentinel
        }


        /**
        * Returns the next element in the Sac.
        *
        * @return The next element in the iteration.
        * @throws NoSuchElementException If there are no more elements.
        */
        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();

            precCourant = courant;
            courant = courant.suiv;
            return courant.data;
        }

        /**
        * Removes the last element returned by the iterator from the Sac.
        *
        * @throws IllegalStateException If remove is called before next().
        * @throws java.util.ConcurrentModificationException If the Sac was modified outside the iterator.
        */
        @Override
        public void remove() {
            if (precCourant == courant) {
                throw new IllegalStateException("Call next() before remove() again");
            }
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            precCourant.suiv = courant.suiv;
            courant = precCourant;
            taille--;
            modCount++;
            expectedModCount++;
        }
    }


    /**
    * Sentinel of node 
    */
    private Element sentinel;
    
    /**
    * Size of the list 
    */
    private int taille;

    /**
     * To track modification for iterator synchronization
     */
    private int modCount;

    /**
    * Constructs an empty Sac with a sentinel node.
    */
    public Sac() {
        this.sentinel = new Element(null, null);
        this.sentinel.suiv = sentinel;  // Sentinel points to itself in an empty list
        this.taille = 0;
        this.modCount = 0;
    }

    /**
    * Constructs a Sac from another collection.
    * All elements from the given collection will be added to the Sac.
    *
    * @param c The collection from which elements are to be added.
    */
    public Sac(Collection<E> c) {
        this();
        for (E item : c) {
            this.add(item);
        }
    }

    /**
    * Returns an iterator over the elements in the Sac.
    * The order of iteration is not guaranteed to be the order of insertion.
    *
    * @return An Iterator over the elements of this Sac.
    */
    public Iterator<E> iterator() {
        return new Itr(sentinel);
    }

    /**
    * Returns the number of elements in the Sac.
    *
    * @return The size of the Sac.
    */
    public int size() {
        return this.taille;
    }

    /**
    * Adds a new element to the Sac at a random position.
    * The new element is inserted into the list at a random index.
    *
    * @param o The element to be added.
    * @return true, as specified by Collection.add(E).
    */
    public boolean add(E o) {
        Element newElement = new Element(null, o);
        int index = (int) (Math.random() * (taille + 1));
        Element current = sentinel;

        // Traverse to the random index
        for (int i = 0; i < index; i++) {
            current = current.suiv;
        }

        // Insert the new element at the random index
        newElement.suiv = current.suiv;
        current.suiv = newElement;
        taille++;
        modCount++;
        return true;
    }
}
