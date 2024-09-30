/**
* Le programme doit decaler chaque valeur d'un tableau d'un indice vers la gauche.
* @author O.Gunes
**/
class Exercice2{
	void principal(){
		int[] t = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.print("Avant decalerGauche :");
		displayTab(t);
		decalerGauche(t);
		System.out.println();
		System.out.print("Apres decalerGauche :");
		displayTab(t);
	}
	
	/**
	* décale les entiers d’un tableau d’une position vers la gauche
	* L’élément en 0 se retrouve à la fin du tableau
	* @param tab tableau d’entiers
	*/
	void decalerGauche (int[] tab){
		int i = 0;
		int tmp = tab[0];
		while(i < tab.length-1){
			tab[i] = tab[i+1];
			i++;
		}
		tab[tab.length-1] = tmp;
	}
	
	/**
	* Affiche le tableau rentré en paramètre
	* @param int[] t : tableau a afficher
	**/
	void displayTab(int[] t){
		int i = 0;
		System.out.print("{");
		while(i<t.length-1){
			System.out.print(t[i] + ",");
			i=i+1;
		}
		if(t.length > 0){
			System.out.print(t[i]+"}");
		}
		if(t.length == 0){
			System.out.print("}");
		}
	}
}

