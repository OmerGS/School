/**
* Ce programme donne le nombre le plus grand entre 3 nombres entiers entrer par l'utilisateur.
* @author R.Péron
*/
class Exercice2{
	void principal(){
	int val1, val2, val3;
	
	val1 = SimpleInput.getInt("Premier Entier : ");
	val2 = SimpleInput.getInt("Deuxieme Entier : ");
	val3 = SimpleInput.getInt("Troisieme Entier : ");
	
	if(val1<val3&&val2<val3){
		System.out.println("Le troisieme entier " + val3 + " est le plus grand");
	} 
	else if(val1<val2&&val3<val2){
		System.out.println("Le deuxieme entier " + val2 + " est le plus grand");
	} 
	else if(val2<val1&&val3<val1){
		System.out.println("Le premier entier " + val1 + " est le plus grand");
	}
	if (val1==val2 && val2==val3){
		System.out.println("Tous les nombres sont egaux, veuillez entrez des nombres differents");
 }	
 }
}

