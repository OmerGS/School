import pays.*;
import utilitaire.RWFile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * classe de scenario de Population
 */
public class ScenarioPopulation {
  
  public static void main (String[] args) {
    Population pop = new Population ("../data/worldarea.txt", "../data/worldpop.txt");

    HashMap<String, Double> popHash = pop.getPopMap();
    HashMap<String, Double> areaHasp = pop.getAreaMap();

    //System.out.println(popHash);
    ArrayList<String> liste = new ArrayList<String>();
    liste.add("Bonjour");
    liste.add("Ce TP est très interessant");

    RWFile.writeFile(liste, "../data/o.txt");

    if(popHash.size() == areaHasp.size()){
      System.out.println("O let's go");
    } else {
      System.out.println("Nuh uh");
    }
  }
  
}
