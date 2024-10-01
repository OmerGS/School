/**
* Calcul la combinaison entre deux valeurs.
* @author O.Gunes
**/
class Exercice1{
	void principal(){
		testFactoriel();
		testCombinaison();
	}
	
	/**
	* calcul de la factorielle du paramètre
	* @param n valeur de la factoriel à calculer
	* @return factorielle de n
	*/
	double factoriel (double a){
		double resultat = 1;
		for(int i = 1; i<=a; i++){
			resultat = resultat*i;
		}
		return(resultat);
	}
	
	/**
	* calcul de la combinaison k parmi n
	* @param n cardinalité de l’ensemble
	* @param k nombre d’éléments dans n avec k<=n
	* @return nombre de combinaisons de k parmi n
	*/
	double combinaison (double n, double k){
		return(factoriel(n)/(factoriel(k)*(factoriel(n-k))));
	}
	
	/**
	* Teste la méthode factoriel()
	*/
	void testFactoriel () {
		System.out.println ();
		System.out.println ("*** testFactoriel()");
		testCasFactoriel (5, 120);
		testCasFactoriel (0, 1);
		testCasFactoriel (1, 1);
		testCasFactoriel (2, 2);
	}

	/**
	* teste un appel de factoriel
	* @param n valeur de la factoriel à calculer
	* @param result resultat attendu
	**/
	void testCasFactoriel (int n, int result) {
		// Arrange
		System.out.print ("factoriel (" + n + ") \t= " + result + "\t : ");
		// Act
		double resExec = factoriel(n);
		// Assert
		if (resExec == result){
			System.out.println ("OK");
		} else {
			System.err.println ("ERREUR");
		}
	}
	
	/**
	* appel la fonction CasCombinaison
	**/
	void testCombinaison () {
		System.out.println ();
		System.out.println ("*** testCombinaison()");
		testCasCombinaison(6, 4, 15);
		testCasCombinaison(10, 8, 45);
		testCasCombinaison(0, 0, 1);
		testCasCombinaison(5, 5, 1);
	}

	/**
	* teste un appel de combinaison
	* @param valeur de n
	* @param valeur de k
	* @param result resultat attendu
	**/
	void testCasCombinaison (int n, int k, double result) {
		System.out.print("Combinaison (" + n + "," + k + ") = " + result);
		double resExec = combinaison(n, k);
		
		if(resExec == result){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
		
	}
}
