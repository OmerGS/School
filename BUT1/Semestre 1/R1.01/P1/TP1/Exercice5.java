/**
* Calcul du salaire net à partir du salaire brut
* @author O.Gunes & R.Peron
*/ 


class Exercice5{
	void principal(){
		float salaireBrut = SimpleInput.getFloat("Saisissez un salaire Brut : ");
		float assuranceMaladie, assuranceVieillesseD, assuranceVieillesseP, fraisPro, contributionSocialeG, crds, chomage, salaireNet, totalPrelev;
		
		if(salaireBrut<=0){
			System.out.println("Vous n'avez pas de salaire donc vous ne cotisez pas.");
		} 
			else 
			{
				System.out.println("Le salaire brut est de "+ salaireBrut+" Euros");
		
				assuranceMaladie = salaireBrut*0.0075F;
				System.out.println("L'assurance Maladie prend "+assuranceMaladie+" Euros");
				
				assuranceVieillesseD = salaireBrut*0.001F;
				System.out.println("L'assurance Vieillesse Deplafonne prend "+assuranceVieillesseD+" Euros");
				
				assuranceVieillesseP = salaireBrut*0.0675F;
				System.out.println("L'assurance Vieillesse Plafonne prend "+assuranceVieillesseP+" Euros");
				
				fraisPro = salaireBrut*0.0175F;
				System.out.println("Les Frais Professionnels prend "+fraisPro+" Euros");
				
				contributionSocialeG = (salaireBrut-fraisPro)*0.075F;
				System.out.println("Les Contributions Sociales Generalisees prend "+contributionSocialeG+" Euros");
				
				crds = (salaireBrut-fraisPro)*0.005F;
				System.out.println("Les CRDS prend "+crds+" Euros");
				
				chomage = salaireBrut*0.024F;
				System.out.println("Le chomage prend "+chomage+" Euros\n");
				
				totalPrelev = assuranceMaladie+assuranceVieillesseD+assuranceVieillesseP+fraisPro+contributionSocialeG+crds+chomage;
				System.out.println("Le total des prelevements est de "+totalPrelev+" Euros\n");
				
				salaireNet = salaireBrut-totalPrelev; 
				System.out.println("Le salaire Net est de "+salaireNet+" Euros");
			}


	}
}

