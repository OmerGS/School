/**
* Methode pour tester supprimerUneValeur en Java.
* @author O.Gunes
*/ 
class supprimerValeur{
	void principal(){
		testSupprimerUneValeur();
	}
	
	
	/**
	* Supprime du tableau la première case rencontrée dont le contenu 
	* est égale à "valeur". La case du tableau est supprimée par 
	* décalage à gauche des cases du tableau.
	* 
	* @param leTab - Le Tableau
	* @param nbElem - le nombre d'entiers présents dans le tableau
	* @param valeur - le contenu de la première case à supprimer
	* @return le nombre d'éléments dans le tableau (éventuellement inchangé)
	*/  
	int supprimerUneValeur(int[] leTab, int nbElem, int valeur) {
		int retournement = nbElem;
		boolean suppression = false;
		
		if(verifTab(leTab, nbElem)){
			int i = 0;
			while(i < nbElem && !suppression){
				if(leTab[i] == valeur){ //On verifie si le nombre de la case actuelle est le nombre qu'on cherche
					decalerGche(leTab, nbElem, i); //On decale a gauche
					suppression = true; //On quitte la boucle et cela permet d'acceder à la recursivité de la methode.
				}
				i++;
			}
		}
		
		if(suppression == true){
			nbElem = nbElem-1; //On décrémente le nombre d'elements
			retournement = supprimerUneValeur(leTab, nbElem, valeur); //Avec cette appelle on refait la methode donc on sait si il y a encore des nombres qui sont égaux à "valeur"
		}
		
		return(retournement);
	}
	
	/**
	* Test les differents cas de la methode supprimerUneValeur()
	* 
	* @param leTab - Le Tableau
	* @param nbElem - le nombre d'entiers présents dans le tableau
	* @param valeur - le contenu de la première case à supprimer
	*/  
	void testCasSupprimerUneValeur(int[] leTab, int nbElem, int valeur){
		int valeurRestant = supprimerUneValeur(leTab, nbElem, valeur);
		
		System.out.println("Le nombre d'elements restant : " + valeurRestant);
	}
	
	/**
	* La methode qui envoie les valeurs a tester à testCasSupprimerUneValeur(); 
	*/
	void testSupprimerUneValeur(){
		int[] leTab = {1,2,3,1,2,3,1,2,3,1};
		int nbElem = 10;
		int valeur = 3;
		testCasSupprimerUneValeur(leTab, nbElem, valeur);
		
		int[] leTab2 = {5,4,6,6,6,8,7,9,1,2,3,5,4};
		int nbElem2 = 13;
		int valeur2 = 6;
		testCasSupprimerUneValeur(leTab2, nbElem2, valeur2);
	}
	
	/**
	* Verifie si un tableau est valide.
	* @param tab : Le tableau d'entier
	* @param nb : Le nombre d'élements contenu dans le tableau
	* @return La validité du tableau (true si c'est valide, false sinon)
	*/ 
	boolean verifTab(int[] tab, int nb){
		
		boolean ret = false;
		
		if(tab != null){
			if(nb > 0 && nb <= tab.length){
				ret = true;
			
			} else {
				System.err.println("verifTab : " + "Tableau non valide");
			}	
		} else {
			System.err.println("verifTab : " + "Tableau non valide");
		}

		return(ret);
	}
	
	/**
     * Décale de une case de la droite vers la gauche toutes les cases d'un tableau
     * à partir d'un indice "ind" et jusque nbElem-1.
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param ind - l'indice à partir duquel commence le décalage à gauche
     */
    void decalerGche(int[] leTab, int nbElem, int ind) {
        if(verifTab(leTab, nbElem)){
			if (ind < 0 || ind > nbElem - 2) {
				System.out.println("Erreur : L'indice doit être compris entre 0 et " + (nbElem - 2));
			} else {
				for (int i = ind; i < nbElem-1; i++) {
					leTab[i] = leTab[i + 1];
				}
			}
		}
    }

}
