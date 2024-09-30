/**
* Le programme verifie qu'un mot est contenu dans une phrase avec des
* méthodes de test et d'execution.
* @author O.Gunes
**/
class Exercice4{
	void principal(){
		testEstSousChaine();
	}
	
	/**
	* Fonction qui Test different cas
	*/
	void testEstSousChaine(){
		System.out.println("\n*** testEstSousChaine()");
		String mot = "bonjour";
		String phrase = "bonjour monsieur";
		testCasEstSousChaine(mot, phrase, true);
		
		String mot0 = "abcdefghi";
		String phrase0 = "fed";
		testCasEstSousChaine(mot0, phrase0, false);
		
		String mot1 = "lmnopqrs";
		String phrase1 = "lpqrs";
		testCasEstSousChaine(mot1, phrase1, true);
		
		String mot2 = " ";
		String phrase2 = " ";
		testCasEstSousChaine(mot2, phrase2, true);
		
		String mot3 = "";
		String phrase3 = "";
		testCasEstSousChaine(mot3, phrase3, false);
	}
	
	/**
	* Fonction qui Test les different cas et verifie que la méthode 
	* estSousChaine fonctionne correctement
	* @param String mot : le mot qu'on cherche dans phrase.
	* @param String phrase : La phrase dans lequel on cherche mot
	* @param boolean etat : Vrai si le mot est dans la phrase faux sinon
	*/
	void testCasEstSousChaine(String mot, String phrase, boolean etat){
		System.out.print("estSousChaine(");
		System.out.print("Mot : " + mot + " , est dans " + phrase + " = " + etat);
		boolean resExec = estSousChaine(mot, phrase);
		if(resExec == etat){
			System.out.println(" : OK");
		} else {
			System.out.println(" : ERREUR");
		}
	}
	
	/**
	* teste si une chaîne est une sous-chaîne d’une autre
	* @param mot chaîne de caractères
	* @param phrase chaîne de carectères
	* @return vrai si mot est présent dans phrase
	*/
	boolean estSousChaine (String mot, String phrase){
		boolean etatRetourner = false;
		int i = 0; 
		while(i < phrase.length() && etatRetourner == false){
			int j = 0;
			while(j < mot.length()){
				if(mot.charAt(j) == phrase.charAt(i) && etatRetourner == false){
					etatRetourner = true;
				} else {
					etatRetourner = false;
				}
				 j++;
			}
			i++;
		}
		return(etatRetourner);
	}
}

