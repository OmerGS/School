/**
 * Le programme teste la premiere occurence d'une valeur dans un tableau.
 * @author : O.Gunes
 **/
class Exercice2_3 {
    void principal() {
        int[] tableau = {8,6,1,5,9,4,02,25,41};
        System.out.print("1ere occurance de 36 :");
        System.out.println(indiceTab(tableau, 36));
        
        int[] tableau2 = {8,6,1, 27, 48, 96};
        System.out.print("1ere occurance de 27 :");
        System.out.println(indiceTab(tableau2, 27));
        
        System.out.print("1ere occurance de 5 :");
        int[] tableau3 = {5, 8, 9, 5, 8, 8, 5};
        System.out.println(indiceTab(tableau3, 5));
    }

	/**
	* cherche l’indice de la premiere occurrence d’une valeur dans un tableau
	* @param tab tableau d’entiers
	* @param v valeur à chercher
	* @return l’indice de la première valeur v dans tab si v est dans tab, -1 sinon
	*/
    int indiceTab(int[] tab, int v) {
        int valeur = -1;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == v) {
                valeur = i; 
            }
        }
        return valeur;
    }

}
