/**
* Demande à l'utilisateur un nombre et lorsque ce nombre est égale à -1 la saisie se termine (utilisatation de la boucle do while)
* @author O.Gunes
**/
class Exercice1do{
	void principal(){
		int nb = 0;
		
		do{
			nb = SimpleInput.getInt("Saisissez un nombre : ");
			System.out.println(nb);
		} while(nb != -1);
		System.out.println("Bravo vous avez trouvé le nombre !");
	}
}

