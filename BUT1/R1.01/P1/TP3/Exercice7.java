/**
 * Programme qui demande deux entiers A et B et les verifient si ils sont dans l'ordre dans un tableau.
 * @author O.Gunes
 **/
class Exercice7 {
	void principal(){
		int[] tableau = new int[50];
		int i = 0;	
		int a, b;	
		int indiceA = 0;
		int indiceB = 0;
		boolean aTrouve = false;
		boolean bTrouve = false;
		
		for(int e = 0; e < tableau.length; e++){
			tableau[e] = (int) (Math.random() * 50);
		}
		
		do{
			a = SimpleInput.getInt("Entrez valeur de A : ");
			b = SimpleInput.getInt("Entrez valeur de B : ");
			if(a==b){
				System.out.println("Entrez des valeurs differentes ! \n");
			}			
		}while(a==b);
		
		while(i < tableau.length && (aTrouve == false || bTrouve == false)){
			if(tableau[i] == a){
				aTrouve = true;
				indiceA = i;
			} 
			
			if(tableau[i] == b){
				bTrouve = true;
				indiceB = i;
			} 
			i++;
		}
		
		if(aTrouve == true && bTrouve == true){
			if(indiceA < indiceB+1){
				System.out.println("Les deux valeurs sont dans le tableau dans l'ordre saisie ! ");
			} else {
				System.out.println("Les valeurs A et B sont present dans le tableau mais pas dans l'ordre ! ");
			}
		} else if(aTrouve){
			System.out.println("A est present dans le tableau mais pas B !");
		} else if(bTrouve){
			System.out.println("B est present dans le tableau mais pas A !");
		} else {
			System.out.println("A et B ne sont pas present dans le tableau !");
		}
		System.out.println("\nIndice de A : " + indiceA + "\nIndice de B : " + indiceB);
		System.out.println();
		displayTab(tableau);
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
