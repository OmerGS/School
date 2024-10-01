/**
* vrai si les 2 tableaux n’ont aucune valeur commune et rend faux s’il existe au moins une valeur
* présente dans les deux tableaux.
* @author O.Gunes
**/
class Exercice1{
	void principal(){
		testSontTousDiff();
	}
	
	/**
	* Fonction qui Test different cas
	*/
	void testSontTousDiff(){
		System.out.println("\n*** testSontTousDiff()");
		int[] tab1 = {2, 5, 8, 6, 12};
		int[] tab2 = {3, 1, 7, 6, 13, 16};
        testCasSontTousDiff(tab1, tab2, true );
        
        int[] tab3 = {1, 2, 3, 4, 5};
		int[] tab4 = new int[5];
        testCasSontTousDiff(tab3, tab4, false);
        
        int[] tab5 = {51, 68, 0, 1, 2};
		int[] tab6 = new int[5];
        testCasSontTousDiff(tab5, tab6, true);
        
        int[] tab7 = new int[0];
		int[] tab8 = new int[0];
        testCasSontTousDiff(tab7, tab8, false);
	}
	
	/**
	* vérifie si deux tableaux n’ont aucune valeur commune
	* @param tab1 premier tableau
	* @param etat si les deux 
	*/
	void testCasSontTousDiff(int[] tab1, int[] tab2, boolean etat){
		System.out.print("sontTousDiff(");
		displayTab(tab1);
		System.out.print(",");
		displayTab(tab2);
		System.out.print(", " + etat + ")");
		boolean resExec = sontTousDiff(tab1, tab2);
		if(resExec == etat){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
	}
	
	
	/**
	* vérifie si deux tableaux n’ont aucune valeur 
	* commune
	* @param tab1 premier tableau
	* @param tab2 deuxième tableau
	* @return vrai si les tableaux tab1 et tab2 n’ont 
	* aucune valeur commune, faux sinon
	*/
	boolean sontTousDiff (int[] tab1, int[] tab2){
		boolean valeurCommune = false;

		int i = 0;
		
		while(i < tab1.length && !valeurCommune){
			int j = 0;
			while(j < tab2.length && !valeurCommune){
				if(tab1[i] != tab2[j]){
					valeurCommune = false;
				} else {
					valeurCommune = true;
				}
				j++;
			}
			i++;
		}
		return (valeurCommune);
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
