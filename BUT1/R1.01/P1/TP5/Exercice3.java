/**
 * Le programme test et verifie le nombre de valeurs differentes dans un tableau. 
 * @author : O.Gunes
 **/
class Exercice3 {
    void principal() {
        testCompteDiffVal();
    }
	
	/**
	* Fonction qui Test different cas
	*/
	void testCompteDiffVal(){
		System.out.println("*** testSontTousDiff()");
		int[] tableau = {1, 2, 2, 3, 3, 5, 4, 4, 8};
		testCasCompteDiffVal(tableau, 6);
		int[] tableau2 = {-5, 5, -10, 10};
		testCasCompteDiffVal(tableau2, 4);
		int[] tableau3 = new int[5];
		testCasCompteDiffVal(tableau3, 1);
		int[] tableau4 = {5};
		testCasCompteDiffVal(tableau4, 1);
		
	}
	
	/**
	* Fonction qui Test les different cas et verifie que la méthode 
	* compteDiffVal fonctionne correctement
	* @param int[] tab : tableau que l'on verifie.
	* @param int valeur : Nb calculé à l'avance des nombres differents 
	* contenu dans le tableau
	*/
	void testCasCompteDiffVal(int[] tab, int valeur){
		System.out.print("sontTousDiff(");
		displayTab(tab);
		System.out.print(",Nb Diff = " + valeur + ")");
		int resExec = compteDiffVal(tab);
		if(resExec == valeur){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
	}
	
	/**
	* Fonction qui compte le nb de chiffre differents dans un tableau
	* @param int[] tab : tableau que l'on verifie.
	* @return : Le nb de valeurs differentes
	*/
    int compteDiffVal(int[] tab) {
        int compteur = 0;
        boolean[] nbRencontrer = new boolean[tab.length];

        for(int i = 0; i < tab.length; i++) {
            if(nbRencontrer[i] == false) {
				compteur++;
				nbRencontrer[i] = true;
                for(int j = i; j < tab.length; j++) {
					if(tab[i] == tab[j]) {
						nbRencontrer[j] = true;
                    }
                }
            }
        }
        return(compteur);
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
			System.out.print(t[i]+"}");
		}
		if(t.length == 0){
			System.out.print("}");
		}
	}
    
}
