import pays.Pays;
import pays.Population;
import tri.BinarySearcher;
import tri.TriParSelectionAlpha;

import java.util.ArrayList;
import java.util.Arrays;

public class TriAlphaScenario {

    public static void main(String[] args) {
        Population population = new Population("../data/worldarea.txt", "../data/worldpop.txt");
        ArrayList<Pays> listePays = population.getListePays();

        // Sorting the list of countries by name
        TriParSelectionAlpha tri = new TriParSelectionAlpha();
        tri.trier(listePays);

        // Printing the sorted list
        for (Pays pays : listePays) {
            System.out.println(pays.getNom());
        }






        System.out.println("\n -----------------------");


        Pays[] tabPays = listePays.toArray(new Pays[0]);

        // Sort the array
        Arrays.sort(tabPays);

        // Create an instance of BinarySearcher
        BinarySearcher searcher = new BinarySearcher();

        // Search for a specific country
        ArrayList<String> countriesToSearch = new ArrayList<>();
        countriesToSearch.add("France");
        countriesToSearch.add("Argentina");
        countriesToSearch.add("Turkiye");
        countriesToSearch.add("Japan");
        countriesToSearch.add("Georgia");
        countriesToSearch.add("Malta");
        countriesToSearch.add("Pays de Galle");


        // Search for each country in the sorted array and print the result
        for (String country : countriesToSearch) {
            boolean found = searcher.search(tabPays, country);
            if (found) {
                System.out.println("La valeur \"" + country + "\" est dans le tableau");
            } else {
                System.out.println("La valeur \"" + country + "\" n'est pas dans le tableau");
            }
        }
    }
}
