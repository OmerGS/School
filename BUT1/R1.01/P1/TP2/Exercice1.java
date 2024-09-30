/**
* Demande à l'utilisateur un nombre et lorsque ce nombre est égale à -1 la saisie se termine
* @author O.Gunes
**/
class Exercice1{
	void principal(){
		int nb = 0;
		
		while(nb != -1){
			nb = SimpleInput.getInt("Saisissez un nombre : ");
			System.out.println(nb);
		} 
		System.out.println("Bravo vous avez trouvé le nombre !");
	}
}
