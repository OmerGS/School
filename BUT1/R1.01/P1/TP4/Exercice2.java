/**
* Le programme rend vrai lorsque q divise p, et rend faux lorsqu'il ne
* ne divise pas.
* @author O.Gunes
**/
class Exercice2{
	void principal(){
		testEstDiviseur();
	}
	
	/**
	* teste la divisibilité de deux entiers
	* @param p entier positif à tester pour la divisibilité
	* @param q diviseur strictement positif
	* @return vrai si q divise p
	*/
	boolean estDiviseur (int p, int q){
		boolean resultat = false;
		if(q%p == 0){
			resultat = true;
		} else {
			resultat = false;
		}
		return(resultat);
	}
	
	/**
	* teste la divisibilité de deux entiers
	* @param p entier positif à tester pour la divisibilité
	* @param q diviseur strictement positif
	* @return vrai si q divise p
	*/
	void testEstDiviseur () {
		System.out.println ();
		System.out.println ("*** testEstDiviseur()");
		testCasEstDiviseur(2, 10, true);
		testCasEstDiviseur(3, 10, false);
		testCasEstDiviseur(2, 2, true);
		testCasEstDiviseur(2, 2, true);
		testCasEstDiviseur(10, 100, true);
		testCasEstDiviseur(3, 82, false);
	}
	
	/**
	* Verifie le fonctionement des deux fonctions
	* @param p valeur defini
	* @param q valeur defini
	* @param flag condition
	* @return vrai si q divise p
	*/
	void testCasEstDiviseur (int p, int q, boolean flag) {
		System.out.print ("Division (" + p + "," + q + ") = " + flag);
		boolean resExec = estDiviseur(p,q);
		if(resExec == flag){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
	}
}

