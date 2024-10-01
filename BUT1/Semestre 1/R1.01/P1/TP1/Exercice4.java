/**
*	Ce programme simule les règles du bowling.
* @author O.Gunes & R.Peron
*/ 


class Exercice4{
	void principal(){
		int lance1 = SimpleInput.getInt("Saisissez le nombre de quille renversé au lance 1: ");
		int lance2, quilleManquant;
		int nbrQuille = 10;
		
		if(lance1 == nbrQuille){
			System.out.println("Strike !");
		}
		else if(lance1 < nbrQuille && lance1 >=0){
			System.out.println("Vous avez renverse " + lance1 + " au premier lance \n");
			
			lance2 = SimpleInput.getInt("Vous avez une second lance combien de quille avez-vous renverser : ");
			quilleManquant = nbrQuille-lance1;	
			
			if (lance2<=quilleManquant && lance2>=0){
				if (lance1+lance2>nbrQuille){
					System.out.println("Vous ne pouvez pas renverse plus de quille que le nombre de quille restante");
				} else if (lance1+lance2==nbrQuille) {
					System.out.println("Spare !");
				} else if (lance1+lance2<nbrQuille) {
					System.out.println("Dommage !");
				}
			} else{
				System.out.println("Vous ne pouvez pas saisir cette valeur pour le lance 2");
			}
		
		} else {
			System.out.println("Vous ne pouvez pas saisir cette valeur pour le lance 1");
		}
			
				
			}
		}

