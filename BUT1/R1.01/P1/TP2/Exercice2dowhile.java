/**
* 
* @author O.Gunes
**/
class Exercice2dowhile{
	void principal(){
		int val1;
		int val2;
		int total=0;
		
		do{
			val1 = SimpleInput.getInt ("Première valeur : ");
			val2 = SimpleInput.getInt ("Deuxième valeur : ");
			if (val1 <= 0 || val2 <= 0){
				System.out.println("Entrer un nombre superieur strict à 0");
			}
		}while(val1 <= 0 || val2 <= 0);
		
		while(val1 != val2){
			if(val1 > val2){
				val1 = val1 - val2;
			} else {
				val2 = val2 - val1;	
			}
		}
		System.out.println(val1);
	} 
} 




