import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

public class TestMoneyBag {
    private MoneyBag portefeuille;
    private MoneyBag otherPortefeuille;

    @Before
    public void instancier() {
        this.portefeuille = new MoneyBag();
        this.otherPortefeuille = new MoneyBag();
    }

    // -------- Test de append --------

    @Test
    public void testAppend() {
        System.out.println("\n\n*** Cas Normaux - testAppend() ***");

        System.out.println("\n1er Test : ajouter 50 EUR");
        Money EUR50 = new Money(50, "EUR");
        testCasAppend(EUR50, "50 EUR");

        System.out.println("\n2eme Test : ajouter 50 USD");
        Money USD50 = new Money(50, "USD");
        testCasAppend(USD50, "50 USD");

        System.out.println("\n3eme Test : ajouter 19 EUR");
        Money EUR19 = new Money(19, "EUR");
        testCasAppend(EUR19, "69 EUR"); // 50 EUR + 19 EUR
    }

    private void testCasAppend(Money theM, String expectedContent) {
        try {
            this.portefeuille.appendMoney(theM);

            boolean found = false;
            for (Money money : portefeuille.monies) {
                if (money.toString().equals(expectedContent)) {
                    found = true;
                    break;
                }
            }
            assertTrue("Erreur : Le montant attendu n'est pas présent dans le portefeuille", found);
            System.out.println("Test reussi");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTheSame() {
        System.out.println("*** Cas Normaux - testTheSame() ***");

        Money EUR50 = new Money(50, "EUR");
        Money USD50 = new Money(10, "USD");

        System.out.println("1er Test");
        this.otherPortefeuille.appendMoney(EUR50);
        this.otherPortefeuille.appendMoney(USD50);
        this.portefeuille.appendMoney(EUR50);

        try {
            this.portefeuille.theSame(this.otherPortefeuille);
            System.out.println("Test échoué : les deux MoneyBag devraient être différents");
        } catch (NotTheSameException e) {
            System.out.println("Test réussi : " + e.getMessage());
        }

        System.out.println("2eme Test");
        this.portefeuille.appendMoney(USD50);

        try {
            this.portefeuille.theSame(this.otherPortefeuille);
            System.out.println("Test réussi");
        } catch (NotTheSameException e) {
            System.out.println("Test échoué : les deux MoneyBag devraient être les mêmes");
        }
    }

    @Test
    public void testAppendNullMoney() {
        System.out.println("\n*** Cas d'Erreur - testAppendNullMoney() ***");
        try {
            this.portefeuille.appendMoney(null);
            System.out.println("Test échoué : une exception aurait dû être lancée");
        } catch (IllegalArgumentException e) {
            System.out.println("Test réussi");
        }
    }

    @Test
    public void testTheSameNull() {
        System.out.println("\n*** Cas d'Erreur - testTheSameNull() ***");
        try {
            this.portefeuille.theSame(null);
            System.out.println("Test échoué : une exception aurait dû être lancée");
        } catch (IllegalArgumentException e) {
            System.out.println("Test réussi");
        } catch (NotTheSameException e) {
            System.out.println("Test échoué : une IllegalArgumentException aurait dû être lancée");
        }
    }


    @Test
    public void testAppendDifferentCurrency() {
        System.out.println("\n*** Cas Limites - testAppendDifferentCurrency() ***");

        Money EUR50 = new Money(50, "EUR");
        Money USD50 = new Money(50, "USD");

        this.portefeuille.appendMoney(EUR50);
        this.portefeuille.appendMoney(USD50);

        assertEquals(2, this.portefeuille.monies.size());
        System.out.println("Test réussi : deux devises différentes ajoutées correctement");
    }

    // lanceur
    public static void main(String args[]) {
        JUnitCore.main("TestMoneyBag");
    }
}