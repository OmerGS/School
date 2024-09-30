/**
* Utilisateur devine le nombre entre 0 et 100 inclus avec l’aide de la machines. Le programme lui retourne son nombre d’essai 
* @author O.Gunes
 **/
class Exercice5 {
    void principal() {
		int random = (int) (Math.random() * 101);
		int user;
		int compteur = 1;
		
		do{
			user = SimpleInput.getInt("Saisissez une valeur entre 0 et 100 (inclus) : ");
			if(user > random){
				System.out.println("Trop Grand !");
				compteur++;
			}
			if(user < random){
				System.out.println("Trop Petit !");
				compteur++;
			}
		}while(user != random);
		System.out.println("\nBravo vous avez trouvé le nombre qui etait : "+ random);
		System.out.println("Vous avez trouve en "+compteur+" essai !");
	}
}
