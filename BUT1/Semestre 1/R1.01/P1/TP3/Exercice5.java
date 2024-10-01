/**
 * Programme dans lequel on genere 1000 nombres entre 0 et 9. Le programme doit afficher le nombre de fois le nombre a été generer
 * @author O.Gunes
 **/
class Exercice5 {
    void principal() {
		int nbValeur = 10;
		int nbAlea;
		int[] tab = new int [nbValeur];

		
        for(int i=1; i < 1000; i++){
			nbAlea = (int) (Math.random() * nbValeur);
			tab[nbAlea]++;
		}
		displayTab(tab);
    }

	void displayTab(int[] t){
		int i = 0;
		System.out.print("{");
		while(i<t.length-1){
			System.out.print(t[i] + ",");
			i=i+1;
		}
		System.out.println(t[i]+"}");
	}

	


}

