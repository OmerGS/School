/**
 * Programme qui doit afficher le nombre d'étapes pour arriver à la valeur 1 d'après la saisie d'un utilisateur.
 * @author O.Gunes
 **/
class Exercice6 {
    void principal() {
		int valeurUser = 0;
		int valeurMax = 0;
		int compteur = 0;
		do{
			valeurUser = SimpleInput.getInt("Veuillez entrez un nombre entier : ");
		}while(valeurUser < 0);
		
		while(valeurUser != 1){
			
			if(valeurUser %2 == 0){
				valeurUser/=2;
			} else {
				valeurUser = (3*valeurUser)+1;
			}
			
			if(valeurMax < valeurUser){
					valeurMax = valeurUser;
				}
			compteur++;
		}
		System.out.println("Le nombre de tour : " + compteur);
		System.out.println("La valeur max : " + valeurMax);		
		
	}
}


