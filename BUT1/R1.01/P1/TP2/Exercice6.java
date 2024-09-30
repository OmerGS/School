/**
* L’objectif de cet exercice est de coder et de tester un programme qui devine un chiffre entre 0 et 1000 choisi par l’utilisateur avec deduction logique.
*  @author O.Gunes
**/

class Exercice6 {
    void principal() {
		char proposition = 'b';
		int nb;
		int random = 0;
		int min = 0;
		int max = 1000;
		
		do{
			nb = SimpleInput.getInt("Choisissez un nombre entre "+ min+" et "+ max + " (inclus) : ");
			if(nb < min || nb > max){
				System.out.println("Valeur non valide");
			} 		
		} while(nb < min || nb > max);
	
		do{
			random = (int) (Math.random() * (max+1 - min)) + min;
			System.out.println("Le nombre proposé : "+random);
			
			if((max-min) <= 2){
				System.out.println("Deduction Logique !");
				proposition = '=';
			} else {
			
				do{
					proposition = SimpleInput.getChar("La valeur est + ou - ou = ? ");
				}while(proposition != '+' && proposition != '-' && proposition != '=');
			
				if (proposition == '+') {
						min = random;
				}
			
				if (proposition == '-') {
						max = random;
				}
				
				if (proposition == '=') {
						System.out.println("Bravo !");
				}
			}
			
		}while(proposition != '=');
	}
}
