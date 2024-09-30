/**
* Programme qui creer un nouveau tableau en cumulant les valeurs d'un tableau déjà existant avec la fonction for().
* @author O.Gunes
**/
class Exercice2_for{
	void principal(){
		int[] cumul;
		int[] tab = {5, 7, 0, 6, 10, 8, 4, 1};
		int tempo = 0;
		
		System.out.print("Avant : Tab = ");
		displayTab(tab);
		cumul = new int[tab.length];
		
		for(int i = 0; i < tab.length; i++){
			cumul[i] = tab[i]+tempo;
			tempo = cumul[i];
		}
		System.out.print("Apres : Cumul = ");
		displayTab(cumul);
	
	
	
	
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


