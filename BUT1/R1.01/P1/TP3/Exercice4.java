/**
 * Programme qui determine la plus longue sequence croissante en donnant les indices de debuts et de fin de la sequence.
 * @author O.Gunes
 **/
class Exercice4 {
    void principal() {
        int[] tab = {5, 7, 0, 6, 10, 8, 4, 1};
        int longueurMax = 1;
        int longueurTempo = 1;
        
        int indiceDebut = 0;
        int indiceFin = 0;
        int indiceTempo = 0;

        for (int i = 0; i < tab.length - 1; i++) {
            if (tab[i+1] > tab[i]) {
                longueurTempo++;
            } else {
                if (longueurMax >= longueurTempo) {
                    longueurTempo = 1;
                    indiceTempo = i + 1;
                    
                } else {
                    longueurMax = longueurTempo;
                    indiceDebut = indiceTempo;
                    indiceFin = i;
                    
                    longueurTempo = 1;
                    indiceTempo = i + 1;
                }
            }
        }
		System.out.println("Sequence + longue : " + longueurMax);
		System.out.println("Indice Debut : " + indiceDebut);
		System.out.println("Indice Fin : " + indiceFin);
    }
}
