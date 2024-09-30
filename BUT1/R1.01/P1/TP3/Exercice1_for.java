/**
* Programme qui decale tout les nombres d'une case vers la fin avec la fonction for().
* @author O.Gunes
**/
class Exercice1_for{
	void principal(){
	
	int[] tab = {5, 7, 0, 6, 10, 8, 4, 1};
	int tmp = tab[tab.length-1];
	
	System.out.print("Avant : tab = ");
	displayTab(tab);
	
	for(int i = tab.length-1; i > 0; i--){
		tab[i] = tab[i-1];
	}
	
	tab[0] = tmp;
	
	System.out.print("Apres : tab = ");
	displayTab(tab);
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
