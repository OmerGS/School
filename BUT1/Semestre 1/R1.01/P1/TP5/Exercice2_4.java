/**
* Decale la valeur voulue vers la gauche jusqu'à l'avoir à l'indice 0.
* @author : O.Gunes
**/
class Exercice2_4 {
    void principal() {
        int[] tableau = {3, 10, 6, 20, 7};
        System.out.print("Avant : ");
        displayTab(tableau);
        decaleValeur(tableau, 6);
        System.out.print("Apres : ");
        displayTab(tableau);
    }

	/**
	* Decale les valeurs jusqu'à gauche tant 
	* que la valeur voulu soit à l'indice 0
	* @param int[] tab : tableau a manipuler
	* @param int v : la valeur qu'on veut mettre à l'indice 0.
	*/
    void decaleValeur(int[] tab, int v) {
		int indice = 0;
        for (int i = 0; i < tab.length; i++) {
			if (tab[i] == v) {
                indice = i; 
            }
        }
		while(indice != 0){
			decalerGauche(tab);
			indice--;
		}
	}
    
    /**
	* décale les entiers d’un tableau d’une position vers la gauche
	* L’élément en 0 se retrouve à la fin du tableau
	* @param tab tableau d’entiers
	*/
	void decalerGauche(int[] tab){
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
	* int[] t : tableau a afficher
	**/
    void displayTab(int[] t){
		int i = 0;
		System.out.print("{");
		while(i<t.length-1){
			System.out.print(t[i] + ",");
			i=i+1;
		}
		if(t.length > 0){
			System.out.println(t[i]+"}");
		}
		if(t.length == 0){
			System.out.println("}");
		}
	}
}

