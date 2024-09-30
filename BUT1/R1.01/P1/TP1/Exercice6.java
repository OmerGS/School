/**
* Calcul de la moyenne des differentes competences, pour le passage en BUT2.
* @author O.Gunes & R.Peron
*/ 


class Exercice6{
	void principal(){
		double c1 = SimpleInput.getDouble("Votre Moyenne dans la 1ere Competences : ");
		double c2 = SimpleInput.getDouble("Votre Moyenne dans la 2e Competences : ");
		double c3 = SimpleInput.getDouble("Votre Moyenne dans la 2e Competences : ");
		double c4 = SimpleInput.getDouble("Votre Moyenne dans la 3e Competences : ");
		double c5 = SimpleInput.getDouble("Votre Moyenne dans la 4e Competences : ");
		double c6 = SimpleInput.getDouble("Votre Moyenne dans la 5e Competences : ");
		int i,var;
		int nbrEliminatoire=0;
		
		for(i=1;i<=6;i++){
			if('c'+'i' < 8){
				nbrEliminatoire = nbrEliminatoire+1;
		}
	}
	System.out.println(nbrEliminatoire);
}
}

