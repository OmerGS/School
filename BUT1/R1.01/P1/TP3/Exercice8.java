/**
 * Creer un 3ème tableau contenant les tableaux 1 et 2 dans l'ordre croissant
 * @author O.Gunes
 **/
class Exercice8 {
	void principal(){
		
}
	
	void displayTab(int[] t){
		int i = 0;
		System.out.print("{");
		while(i<t.length-1){
			System.out.print(t[i] + ",");
			i=i+1;
		}
		System.out.println(t[i]+"}");
	}
	
}

