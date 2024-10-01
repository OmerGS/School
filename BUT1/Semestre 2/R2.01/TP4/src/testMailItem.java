import java.util.ArrayList;
import mail.MailItem;

public class testMailItem {
    public static void main(String[] args) {
        testConstructor();
    }

    public static void separateurTest(int i){
        System.out.println(" -------------- ");
        System.out.println("Test n" + i);
    }

    public static void testConstructor(){
        System.out.println("*** testConstructor() ***");

        //Test n°1
        separateurTest(1);

        String from = "Omer";
        String to = "Secretariat";
        String message = "Absence le 14/02/2024 a 13h00";
        MailItem mail = new MailItem(from, to, message);
        testCasConstructor(mail, from, to, message);


        //Test n°2
        separateurTest(2);

        from = null;
        to = "Secretariat";
        message = "Absence le 14/02/2024 a 13h00";
        mail = new MailItem(from, to, message);
        testCasConstructor(mail, from, to, message);

        //Test n°3
        separateurTest(3);

        from = "Omer";
        to = null;
        message = "Absence le 14/02/2024 a 13h00";
        mail = new MailItem(from, to, message);
        testCasConstructor(mail, from, to, message);

        //Test n°4
        separateurTest(4);

        from = "Omer";
        to = "Secretariat";
        message = null;
        mail = new MailItem(from, to, message);
        testCasConstructor(mail, from, to, message);

        //Test n°5
        separateurTest(5);

        from = "Omer";
        to = "Secretariat";
        message = "Absence";
        mail = new MailItem(from, to, message);
        testCasConstructor(mail, from, to, null);
        
    }

    public static void testCasConstructor(MailItem mail, String from, String to, String message){
        boolean erreur = false;
        int position = 0;
        int positionExacte = 0;

        String[] tableau = {"null", "from", "to", "message"};
        ArrayList <String> composants = new ArrayList<>();
        composants.add(from);
        composants.add(to);
        composants.add(message);


        for (String composant : composants) {
            positionExacte = position+1;
            if(composant == null)
            {
                if(mail == null)
                {
                    erreur = false; //Pas d'erreur car le constructeur a bien travaillé
                } else if(mail != null) //Mais si le composant est null et que la mail existe, il y a une erreur !
                {
                    erreur = true;
                    System.out.println(tableau[positionExacte] + " : est null");
                }
            }
            position++;
        }

        if(!erreur){
            System.out.println("Reussi");
        } else {
            System.out.println("Echoue");
        }
    }




}
