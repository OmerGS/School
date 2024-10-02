package projSac;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import structdonnees.Sac;
import java.util.Iterator;

public class TestSac {

    private Sac<Integer> sac;

    @Before
    public void setUp() throws Exception {
        sac = new Sac<>();
    }

    // Test the constructor Sac()
    @Test
    public final void testSacConstructor() {
        System.out.println("\n*** Testing Constructor ***");
        assertNotNull("Sac should be initialized", sac);
        assertEquals("Sac should be initialized with 0 elements", 0, sac.size());
        System.out.println("Constructor test passed: Sac is correctly initialized.");
    }

    // Test the add() method
    @Test
    public final void testAdd() {
        System.out.println("\n*** Testing Add Method ***");

        // Normal case: Add an element
        assertTrue("Should successfully add an element", sac.add(10));
        assertEquals("Sac should contain 1 element", 1, sac.size());
        System.out.println("Test passed: Element 10 added.");

        // Add a second element
        sac.add(20);
        assertEquals("Sac should contain 2 elements", 2, sac.size());
        System.out.println("Test passed: Element 20 added.");
    }

    // Test the size() method
    @Test
    public final void testSize() {
        System.out.println("\n*** Testing Size Method ***");

        // Empty Sac
        assertEquals("Size should be 0 for an empty sac", 0, sac.size());

        // After adding elements
        sac.add(5);
        sac.add(15);
        assertEquals("Size should be 2 after adding two elements", 2, sac.size());
        System.out.println("Test passed: Size method works correctly.");
    }

    // Test the iterator functionality
    @Test
    public final void testIterator() {
        System.out.println("\n*** Testing Iterator Method ***");

        sac.add(10);
        sac.add(20);
        sac.add(30);

        Iterator<Integer> it = sac.iterator();

        assertTrue("Iterator should have next", it.hasNext());
        System.out.println("First element: " + it.next());

        assertTrue("Iterator should have next", it.hasNext());
        System.out.println("Second element: " + it.next());

        assertTrue("Iterator should have next", it.hasNext());
        System.out.println("Third element: " + it.next());


        assertFalse("Iterator should not have next", it.hasNext());
        System.out.println("Test passed: Iterator works correctly.");
    }
}
