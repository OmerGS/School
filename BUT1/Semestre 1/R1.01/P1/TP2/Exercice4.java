/**
 * Programme qui a pour but de calculer des exposants sans utiliser la librairie Maths.Row()
 * @author O.Gunes
 **/
class Exercice4 {
    void principal() {
        double x = SimpleInput.getInt("Valeur de X (telle que X^n) : "); 
        int n = SimpleInput.getInt("Valeur de N (telle que x^N) : ");
        double resultat = 1.0;
		int i = 0;

        if (n >= 0) {
            while(i < n){
                resultat = resultat * x;
                i++;
            }
            
        } else {
            while(i > n) {
                resultat = resultat/x;
                i--;
            }
        }
        System.out.println("Resultat : " + resultat);
    }
}
