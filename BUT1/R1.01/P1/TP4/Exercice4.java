/**
* Le programme verifie si les valeurs d'un tableau est dans l'ordre 
* croissant.
* @author O.Gunes
**/
class Exercice4{
	void principal(){
		testEstCroissant();
	}
	
	/**
	* teste si les valeurs d’un tableau sont triées par ordre croissant
	* @param t tableau d’entiers
	* @return vrai si les valeurs du tableau sont en ordre croissant
	*/
	boolean estCroissant (int[] t){
		int compteur = 0;
		boolean tableauCroissant;
		for(int i = 0; i<t.length-1; i++){
			if(t[i] < t[i+1]){
				compteur++;
			}
		}
		if(compteur == t.length-1){
			tableauCroissant = true;
		} else {
				tableauCroissant = false;
		}
		return(tableauCroissant);
	}
	
	/**
	* teste si le tableau est croissant
	*/
	void testEstCroissant() {
		System.out.println ();
		System.out.println ("*** testEstCroissant()");
		int[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		testCasEstCroissant(tab, true);
		
		int[] tab2 = {1, 2, 3, 4, 15, 6, 7, 8, 9};
		testCasEstCroissant(tab2, false);
		
		int[] tab3 = {1, 2, 3, 4, 0, 0, 7, 8, 9};
		testCasEstCroissant(tab3, false);
	}
	
	/**
	* Verifie le fonctionement des deux fonctions
	* @param t tableau d'entiers
	* @param vrai si les valeurs du tableau sont en ordre croissant
	*/
	void testCasEstCroissant(int[] t, boolean tableauCroissant) {
		System.out.print("Tableau (" + tableauCroissant +")");
		boolean resExec = estCroissant(t);
		if(resExec == tableauCroissant){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
	}
}

