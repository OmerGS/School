import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

public class TestMoney {
    private Money m1;
    private Money m2;
    private Money m3;
    private Money m4;
    private Money m5;

    @Before
    public void instancier() {
        this.m1 = new Money(20, "USD");
        this.m2 = new Money(20, "USD");
        this.m3 = new Money(20, "CHF");
        this.m4 = new Money(10, "USD");

        this.m5 = new Money(3, "CHF");
    }
    






    // ------------------ Test Des Getters ----------------


    // Premier cas de test : test de la méthode additionne
    @Test
    public void testGetters(){
        System.out.println("\n\n*** TestGetters() ***");

        System.out.println("----- 1er Test avec M1 -----");
        testCasGetters(this.m1);

        System.out.println("----- 2eme Test avec M1 -----");
        testCasGetters(this.m4);

        System.out.println("----- 3eme Test avec M1 -----");
        testCasGetters(this.m3);

    }

    private void testCasGetters(Money argent){
        try {
            assertEquals(20, argent.getAmount());
            assertEquals("USD", argent.getCurrency());

            System.out.println(argent.toString());
            System.out.println("Test Reussi");

        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }









    // ------------------ add(AnotherMoney) ----------------

    @Test
    public void testAdd(){
        System.out.println("*** testAdd() ***");

        System.out.println("----- 1er Test avec M1 + M2 -----");
        testCasAdd(this.m1, this.m2);

        System.out.println("----- 2eme Test avec M2 + M3 -----");
        testCasAdd(this.m2, this.m3);

        System.out.println("----- 3eme Test avec M3 + M4 -----");
        testCasAdd(this.m3, this.m4);

        System.out.println("----- 4eme Test avec M1 + M4 -----");
        testCasAdd(this.m1, this.m4);

    }

    private void testCasAdd(Money firstMoney, Money secondMoney) {
        try {
            IMoney result = firstMoney.add(secondMoney);
    
            if (result instanceof Money) {
                Money finalMoney = (Money) result;
                System.out.println("Test Réussi, Somme des montants = " + finalMoney.toString());
            } else if (result instanceof MoneyBag) {
                MoneyBag finalBag = (MoneyBag) result;
                System.out.println("Test Réussi, MoneyBag créé = " + finalBag.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    
    
    
    
    
    // ------------------ equals(Money AnotherMoney) ----------------
    
    @Test
    public void testEquals(){
        System.out.println("*** Cas Normaux : testEquals() ***");

        System.out.println("----- 1er Test avec :\n1ere Valeur : " + this.m1.toString() + "\n2eme Valeur : " + this.m2.toString());
        testCasEquals(this.m1, this.m2);

        System.out.println("\n----- 2eme Test avec :\n1ere Valeur : " + this.m5.toString() + "\n2eme Valeur : " + this.m5.toString());
        testCasEquals(this.m5, this.m5);


        System.out.println("\n*** Cas Erreur : testEquals() ***");

        System.out.println("----- 1er Test avec :\n1ere Valeur : " + this.m2.toString() + "\n2eme Valeur : " + this.m3.toString());
        testCasEquals(this.m2, this.m3);

        System.out.println("\n----- 2eme Test avec :\n1ere Valeur : " + this.m3.toString() + "\n2eme Valeur : " + this.m4.toString());
        testCasEquals(this.m3, this.m4);
    
        System.out.println("\n----- 3eme Test avec :\n1ere Valeur : " + this.m1.toString() + "\n2eme Valeur : " + this.m4.toString());
        testCasEquals(this.m1, this.m4);

        System.out.println("\n----- 4eme Test avec :\n1ere Valeur : " + this.m2.toString() + "\n2eme Valeur : " + this.m4.toString());
        testCasEquals(this.m2, this.m4);
    }

    private void testCasEquals(Money firstMoney, Money secondMoney){
        try{
            assertEquals(true, firstMoney.equals(secondMoney));
            System.out.println("Test Reussi les deux monnaie sont egaux !");
        }catch(AssertionError e){
            System.out.println(e.getMessage());
        }
    }



    // lanceur
    public static void main ( String args[]) {
        JUnitCore.main("TestMoney");
    }
}