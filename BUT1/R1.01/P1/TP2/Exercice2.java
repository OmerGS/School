/**
* 
* @author O.Gunes
**/
class Exercice2{
	void principal(){
		int val1;
		int val2;
		int total=0;
		
		val1 = SimpleInput.getInt ("Première valeur : ");
		val2 = SimpleInput.getInt ("Deuxième valeur : "); 
		
		while(val1 < 0 || val2 < 0){
			System.out.println("Entrer un nombre superieur à 0");
			val1 = SimpleInput.getInt ("Première valeur : ");
			val2 = SimpleInput.getInt ("Deuxième valeur : "); 
		}
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



