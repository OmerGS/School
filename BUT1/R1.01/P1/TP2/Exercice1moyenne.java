/**
* Demande à l'utilisateur un nombre et lorsque ce nombre est égale à -1 la saisie se termine en affichant la moyenne des nombres saisies.
* @author O.Gunes
**/
class Exercice1moyenne{
	void principal(){
		int nb = 0;
		int i = 0;
		double compte = 0;
		double moyenne;
		
		while(nb != -1){
			nb = SimpleInput.getInt("Saisissez un nombre : ");
			System.out.println(nb);
			if(nb == -1){
				compte=compte;
			} else {
				compte=compte+nb;
				i++;
			}
		}
		System.out.println("La moyenne des nombres est : "+compte/i);
		System.out.println("Bravo vous avez trouvé le nombre !");
	}
}
