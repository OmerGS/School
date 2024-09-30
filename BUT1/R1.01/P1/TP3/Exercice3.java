/**
 * Programme qui determine la plus longue sequence croissante dans un tableau.
 * dans un tableau.
 * @author O.Gunes
 **/
class Exercice3 {
    void principal() {
        int[] tab = {5,7,0,6,10,8,4,1};
        int longueurMax = 1;
        int tempo = 1;
        int i = 0;
        
        while(i < tab.length-1 && (tab.length-i) > longueurMax){
            if (tab[i] < tab[i + 1]) {
                tempo++;
            } else {
                if(longueurMax > tempo){
					tempo = 1;
				} else {
					longueurMax = tempo;
				}
                tempo = 1;
            }
            i++;
        }
        System.out.println("La + longue sequence = " + longueurMax);
    }
}

