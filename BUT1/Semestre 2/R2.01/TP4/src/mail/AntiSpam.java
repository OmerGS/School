package mail;
import java.util.ArrayList;
import java.util.Iterator;

/**
* Class for verify the content of the mail, and detect if it is a spam or not.
* @author O.Gunes
*/
public class AntiSpam {
    /**
    * ArrayList of the word at filters. 
    */
    private ArrayList <String> filtreSpam;
    
    /**
    * Constructor of the class
    * @param filtreSpam The spam filter 
    */
    public AntiSpam(ArrayList filtreSpam){
        if(filtreSpam == null){
            System.out.println("Liste de mots filtré est vide !");
        } else {
            this.filtreSpam = filtreSpam;
        }
    }

    /**
    * Add a word into the filter
    * @param mot Word we want to add
    */
    public void add(String mot){
        if(mot != null){
            this.filtreSpam.add(mot);
        } else {
            System.out.println("Le mot est nulle");
        }
    }

    /**
    * The scanner of the class
    * @param message The message who will scan
    * @return If it is a spam or not
    */
    public boolean scan(String message){
        boolean spam = false;
        if(message != null){
            Iterator <String> it = this.filtreSpam.iterator();

            while ((it.hasNext()) && (!spam)) {
                if(message.indexOf(it.next()) >= 0){
                    spam = true;
                }
            }
        } else {
            System.out.println("Message nulle !");
        }

        return(spam);
    }
}
