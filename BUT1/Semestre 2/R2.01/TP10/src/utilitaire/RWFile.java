package utilitaire;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class RWFile {

    public static ArrayList<String> readFile (String fileName){
        ArrayList<String> paysLue = new ArrayList<>();

        try(Scanner in = new Scanner(new FileReader (fileName))){
            while(in.hasNextLine()){
                paysLue.add(in.nextLine());
            }
        } catch (FileNotFoundException _Exception) {
            System.out.println("Fichier non trouve ! ");
        }

        return(paysLue);
    }

    public static void writeFile (ArrayList<String> liste, String fileName){
        try( PrintWriter out = new PrintWriter (fileName)){
            for(String ligne : liste){
                out.println(ligne);
            }
        }catch(Exception _Exception){
            System.out.println("Fichier non trouve ! ");
        }
    }
    
}
