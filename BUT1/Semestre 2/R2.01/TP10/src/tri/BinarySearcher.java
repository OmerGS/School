package tri;

import pays.Pays;

public class BinarySearcher {

    public boolean search(Pays[] tabPays, String nom) {
        int debut = 0;
        int fin = tabPays.length - 1;
        
        while (debut <= fin) {
            int mil = (debut + fin) / 2;
            int comparison = tabPays[mil].getNom().compareTo(nom);
            
            if (comparison == 0) {
                return true; // Found the country
            } else if (comparison < 0) {
                debut = mil + 1;
            } else {
                fin = mil - 1;
            }
        }
        return false;
    }
}
