/**
* Demande un nb à l'utilisateur tant que celui ci soit inferieur au dernier nombre choisi
* @author O.Gunes
**/
class Exercice3{
	void principal(){
		int nb = 0;
		int nbP = 0;
		int i = 0;
		
		do{
			if(i>1){
				nbP = nb; 
			}
			nb = SimpleInput.getInt("Saisir un nombre : ");
			if(i==0){
				nbP = nb; 
				i=i+10;
			}
		}while(nb>=nbP);
		
		System.out.println("Le nombre actuelle : " + nb);
		System.out.println("Le nombre precedent : "+  nbP);
		System.out.println("Bravo vous avez ecrit un nombre plus petit");
	} 
} 
