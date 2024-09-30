/**
 * Le programme verifie si le nombre saisie est parfait ou pas.
 * @author O.Gunes
 **/
class Exercice3 {
    void principal() {
        testEstParfait();
    }

	/**
	* Trouve les quatres nombres parfait
	*/
    void testEstParfait() {
        System.out.println("\n*** testEstParfait()");
        testCasEstParfait(28, true);
        testCasEstParfait(81, false);
        testCasEstParfait(17, false);
        testCasEstParfait(496, true);
        testCasEstParfait(499, false);
        testCasEstParfait(73, false);
    }
	
	/**
	* Verifie si le nombre est parfait
	* @param n le nombre a verifier
	*/
    boolean estParfait(int n) {
        int sommeDiviseurs = 0;
        boolean a = false;
        boolean returnValeurs;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sommeDiviseurs += i;
            }
        }
        if(sommeDiviseurs == n){
			a = true;
		} else {
			a = false;
		}
		return(a);
	}
	
	void testCasEstParfait(int e, boolean parfait) {
		System.out.print("Nombre : " + e + " , " + parfait);
		boolean resExec = estParfait(e);
		if(resExec == parfait){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
	}
}	
