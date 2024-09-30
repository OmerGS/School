/**
* Programme qui calcule l'aire et le perimetre d'un cercle grace au rayon donnée par l'utilisateur.
* @author O.Gunes & R.Peron
*/ 


class Exercice3{
	void principal(){
		double rayon = SimpleInput.getDouble("Saisissez un rayon : ");
		double PI = Math.PI;
		double perimetre, aire;
		
		perimetre = 2*rayon*PI;
		aire = PI*Math.pow(rayon,2);
			
		if (rayon > 0){	
		System.out.println("L'aire d'un cercle de rayon " + rayon + " est " + aire);
		System.out.println("Le perimetre d'un cercle de rayon " + rayon + " est " + perimetre);
	} else {
		System.out.println("Un cercle de rayon " + rayon + " ne peut pas exister");
	}	
	
		
	}
}

