/**
 * Ce programme trouve les quatres premiers nombres parfaits.
 * @author O.Gunes
 **/
class Exercice3_Quatre {
	int compteur;
	
    void principal() {
        testEstParfait();
    }

	/**
	* Trouve les quatres nombres parfait
	*/
    void testEstParfait() {
        System.out.println("\n*** testEstParfait()");
        int i = 1;
        while(compteur < 4){
			estParfait(i);
			i++;
		}
    }
	
	/**
	* Verifie si le nombre est parfait
	* @param n = nombre a verifier
	*/
    void estParfait(int n) {
        int sommeDiviseurs = 0;
        boolean returnValeurs;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sommeDiviseurs += i;
            }
        }
        
        if(sommeDiviseurs == n){
			System.out.println(n + " est parfait ");
			compteur++;
		}
	}
}
