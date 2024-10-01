/**
* Ce programme peremet de jouer au jeu de Wythoff.
* @author O.Gunes
*/
class Wythoff1{
	int positionX;
	int positionY;
	String joueurActuelle;
	char plateau[][];
	int compteurPartie = 1;
	int compteurJoueur1 = 0;
	int compteurJoueur2 = 0;
	
	void principal(){
		jouer(); //Cette methode permet de jouer au Wythoff
		//methodeDeTest(); //Cette methode permet d'activer les methodes de test
	}
	
	/**
	* Procedure contenant tout les methodes de test 
	*/
	void methodeDeTest(){
		testCreerPlateau();
		testChangeJoueur();
		testEstPasValide();
		testPlacePion();
	}
	
	/**
	* Procedure permettant de tester la fonction creerPlateau();
	*/ 
	void testCreerPlateau() {
		System.out.println("\n***testCreerPlateau()");
		char[][] plateau = new char[5][5];
		testCasCreerPlateau(plateau, 5);
		
		char[][] plateau2 = new char[0][0];
		testCasCreerPlateau(plateau2, 0);
		
		char[][] plateau3 = new char[8][8];
		testCasCreerPlateau(plateau3, 8);
		
		char[][] plateau4 = new char[9][9];
		testCasCreerPlateau(plateau4, 9);
	}
	
	/**
	* Procedure permettant la verification de la sortie de creerPlateau()
	* @param plateau : Le plateau de jeu
	* @param longueur : Longueur du plateau saisie par utilisateur
	*/
	void testCasCreerPlateau(char[][] plateau, int longueur){
		int compteurCase = 0;
		int nbCaseTheorique;
		
		System.out.print("Plateau generer de taille : " + longueur + " , ");
		
		plateau = creerPlateau(longueur);
		for(int i = 0; i < plateau.length; i++){
			for(int j = 0; j < plateau[i].length; j++){
				compteurCase += 1;
			}
		}
		nbCaseTheorique = longueur*longueur;
		if(nbCaseTheorique == compteurCase){
			System.out.println("OK");
		} else {
			System.out.println("Erreur");
		}
		
	}	
	
	/**
	* Cette methode permet de creer le plateau de jeu
	* @param lg est la taille du plateau
	* @return le plateau de jeu.
	*/ 
	char[][] creerPlateau(int lg){
		char[][] plateau = new char[lg][lg];
		int i = 0;

		while(i != lg){
			int j = 0;
			while(j != lg){
				plateau[i][j] = ' ';
				j++;
			}
			i++;
		}
		return(plateau);
	}
	
	/**
	* Methode qui permet d'afficher le plateau de jeu
	* @param plateau : Le plateau de jeu
	*/ 
	void affichePlateau(char[][] plateau){
		for(int i=0 ; i<plateau.length ;i++){
			int decalage = (plateau.length-11);
			
			//~ Permet un affichage sans avoir de problèmes
			//~ Cette boucle permet de verifier si le nombre est inferieur à 10
			//~ Si il est inferieur on vas ajouté un 0 devant le nombre pour 
			//~ qu'il ne soit pas désequilibre
			boolean e = ((i-(decalage)) > 0);
			if(e == true){
				System.out.print("0"+(plateau.length-i-1));
				System.out.print("| ");
			} else {
				System.out.print(plateau.length-i-1);
				System.out.print("| ");
			}
			
			
			for(int j=0 ; j<plateau[i].length ;j++){
				System.out.print(plateau[i][j]);
				System.out.print(" | ");
			}
			System.out.println();
		}
		System.out.print("  ");
		for(int comptIndice = 0;comptIndice<plateau.length;comptIndice++){
			System.out.print( " " + comptIndice + "  ");
		}
		System.out.println();
	}





	/**
	* Procedure permettant de recevoir les entrées utilisateurs pour
	* pouvoir l'envoyer vers placePion();
	*/ 
	void ajoutePion(){
		int nbCase;
		String mouvement;
		boolean flag = false;
		//Demande le mouvement que l'utilisateur veut faire
		do {
			mouvement = SimpleInput.getString("\nA votre tour " + joueurActuelle + " ! Bas, gauche ou diagonal ? ");
		} while (!mouvement.equals("gauche") && !mouvement.equals("bas") && !mouvement.equals("diagonal"));

		//Verifie que le mouvement du pion est correct et redemande tant que ça soit correct
		do {
			if(flag){
				System.out.println("Entrez une valeur differentes de 0 ! ");
			}
			nbCase = SimpleInput.getInt("De combien de cases souhaitez-vous le bouger : ");
			if(nbCase == 0){
				flag = true;
			}
		} while (!estPasValide(plateau, nbCase, mouvement) || nbCase <= 0);
		
		placePion(mouvement, nbCase);
	}
	
	/**
	* Procedure de test utilisant testCasPlacePion();
	*/ 
	void testPlacePion(){
		System.out.println("\n***testPlacePion()");
		plateau = creerPlateau(20);
		
		//Test n°1
		positionX = 5;
		positionY = 7;
		testCasPlacePion("bas", 3, 8, 7);
		
		//Test n°2
		positionX = 15;
		positionY = 12;
		testCasPlacePion("diagonal", 1, 16, 11);
		
		//Test n°3
		positionX = 10;
		positionY = 1;
		testCasPlacePion("gauche", 1, 10, 0);
	}
	
	
	/**
	* Procedure de verification de la méthode placePion.
	* @param mouvement : Le mouvement test
	* @param nbCase : Distance de test
	* @param positionXTheorique : Position X du pion theorique après l'execution du programme
	* @param positionYTheorique : Position Y du pion theorique après l'execution du programme 
	* 
	*/ 
	void testCasPlacePion(String mouvement, int nbCase, int positionXTheorique, int positionYTheorique) {

		/*int initPosX = positionX;
		int initPosY = positionY;

		System.out.print("Init Pos : (" + initPosX + ", " + initPosY + ")");
		System.out.print(" | ");*/
		
		placePion(mouvement, nbCase);

		int finalPosX = positionX;
		int finalPosY = positionY;

		System.out.print("Final Pos : (" + finalPosX + ", " + finalPosY + ")");
		System.out.print(" | ");
		
		if(finalPosX == positionXTheorique && finalPosY == positionYTheorique){
			System.out.println("OK");
		} else {
			System.out.println("ERREUR");
		}
	}
	
	/**
	* Procedure permettant de deplacer le pion sur le plateau de jeu
	* @param mouvement : Mouvement voulue
	* @param nbCase : Nombre de case de deplacement voulue
	*/ 
	void placePion(String mouvement, int nbCase){
		plateau[positionX][positionY] = ' '; //Supprime le pion de l'ancienne case


		if (mouvement.equals("gauche")) {
			positionY = positionY - nbCase;
		} else if (mouvement.equals("diagonal")) {
			positionX = positionX + nbCase;
			positionY = positionY - nbCase;
		} else if (mouvement.equals("bas")) {
			positionX = positionX + nbCase;
		}

		plateau[positionX][positionY] = 'X'; //Ajoute le pion à sa nouvelle case
	}

	/**
	* Procedure de test de la methode estPasValide();
	*/  
	void testEstPasValide(){
		System.out.println("\n*** testEstPasValide()");
		testEstPasValideAvecMouvementBas();
		testEstPasValideAvecMouvementGauche();
	}
	
	
	/**
	* Procedure de test de la methode estPasValide lorsque le mouvement
	* est vers le bas.
	*/
	void testEstPasValideAvecMouvementBas() {
        char[][] plateau = creerPlateau(5);
        boolean result = estPasValide(plateau, 3, "bas");
        if (result) {
            System.out.println("Test OK : estPasValide avec mouvement bas");
        } else {
            System.out.println("Test ERREUR : estPasValide avec mouvement bas");
        }
    }

	/**
	* Procedure de test de la methode estPasValide lorsque le mouvement
	* est vers la gauche.
	*/ 
    void testEstPasValideAvecMouvementGauche() {
        char[][] plateau = creerPlateau(5);
        boolean result = estPasValide(plateau, 3, "droite");
        if (!result) {
            System.out.println("Test OK : estPasValide avec mouvement droite");
        } else {
            System.out.println("Test ERREUR : estPasValide avec mouvement droite");
        }
    }
	
	/**
	* Methode qui verifie que le pion ne sort pas du plateau de jeu.
	* @param plateau : Le plateu de jeu
	* @param nbCase : Le nombre de case que le pion doit bouger
	* @param mouvement : La direction dans laquellele pion doit aller
	*/  
	boolean estPasValide(char[][] plateau, int nbCase, String mouvement) {
    boolean estValide = false;

		if (mouvement.equals("bas")) {
			estValide = ((positionX+nbCase) < plateau.length);
		} else if (mouvement.equals("gauche")){
			estValide = ((positionY-nbCase) >= 0);
		} else{
			estValide = (((positionY-nbCase) > 0) || ((positionX+nbCase) < plateau.length));
		}

		if(!estValide){
			System.out.println("Saisissez une valeur plus petite !");
		}
		return(estValide);
	}

	/**
	* Procedure de test sur la methode de changeJoueur.
	*/ 
	void testChangeJoueur(){
		System.out.println("\n***testChangeJoueur()");
		testCasChangeJoueur("AB", "AB", "DE","DE");
		testCasChangeJoueur("EF", "AE", "EF","AE");
		testCasChangeJoueur("ABC", "DEF", "ABC","DEF");
	}
	
	/**
	* Procedure permettant la verification de la sortie de changeJoueur()
	* @param joueurActuelle : Le joueur qui vient de jouer
	* @param joueur1 : Le 1er joueur
	* @param joueur2 : Le 2eme joueur
	* @param resultatAttendu : Le resultat attendu à la sortie.
	*/
	void testCasChangeJoueur(String joueurActuelle, String joueur1, String joueur2, String resultatAttendu){
		System.out.print("Joueur Actuelle : " + joueurActuelle + " | J1 : " + joueur1 + " | J2 " + joueur2 + " | JRetourne : ");
		String joueurAvantMethode = joueurActuelle;
		String joueurApresMethode = changeJoueur(joueurActuelle, joueur1, joueur2);
		System.out.print(joueurApresMethode);

		if(resultatAttendu == joueurApresMethode){
			System.out.println(" | OK");
		} else {
			System.out.println(" | ERREUR");
		}
	}

	/**
	* Change le joueur courant
	* @param joueurActuelle : Le joueur qui vient de jouer
	* @param joueur1 : Le joueur n°1
	* @param joueur2 : Le joueur n°2
	* @return Le joueur qui doit jouer.
	*/
	String changeJoueur(String joueurActuelle, String joueur1, String joueur2){
		if(joueurActuelle == joueur1){
			joueurActuelle = joueur2;
		} else {
			joueurActuelle = joueur1;
		}
		return(joueurActuelle);
	}

	/**
	* Place le pion aleatoirement sur la premiere ligne ou la derniere colonne
	*/  
	void positionnementDepart(){
		int positionDepart;
		int positionRandom = (int) (Math.random() * 2);
		
		if(positionRandom == 1){
			do{
				positionX = 0;
				positionY = (int) (Math.random() * (plateau.length - 1) + 0);
			}while(!(positionX != 0 || positionY != 0 && positionX != 0 || positionY != plateau.length-1));
		}
		
		if(positionRandom == 0){
			do{
				positionX = (int) (Math.random() * ((plateau.length-2)+1));
				positionY = plateau.length-1;
			}while(!((positionX != 0) || (positionY != plateau.length-1) && (positionX != plateau.length-1) || (positionY != plateau.length-1)));
		}
			plateau[positionX][positionY] = 'X';
	}
	
	/**
	* Methode executer en debut de partie pour preparer le plateau. 
	*/
	void demarrage(){
		int longueur;
		boolean flag = false;
		do{
			if(flag){
				System.out.println("Le nombre doit etre superieur a 5 ! ");
			}
			longueur = SimpleInput.getInt("Entrez la taille du tableau (doit etre superieur a 5) : ");
			flag = true;
		}while(longueur < 5);
		plateau = creerPlateau(longueur);
		
		positionnementDepart();
		
		affichePlateau(plateau);
	}
	
	/**
	* Procedure qui permet l'execution du jeu et verifie que le jeu 
	* n'est pas terminer.
	*/
	void jouer(){
		int nbPartie; //Variable contenant le nombre de partie terminer
		int choix;
		
		//Recuperation du nom du joueur
		String joueur1 = SimpleInput.getString("Nom du joueur 1 : ");
		String joueur2 = SimpleInput.getString("Nom du joueur 2 : ");
		System.out.println("----------------------");
		
		//Choix du joueur qui va jouer en premier
		System.out.println("1. " + joueur1 + "\n2. " + joueur2);
		System.out.println("---------------");
		do{
			choix = SimpleInput.getInt("Qui joue en premier ? : ");
		}while(choix != 1 && choix != 2);
		
		if(choix == 1){
			joueurActuelle = joueur2;
		}
		if(choix == 2){
			joueurActuelle = joueur1;
		}
		
		//Recuperation du nombre de partie
		do{
			nbPartie = SimpleInput.getInt("\nCombien de partie voulez-vous faire ? ");
		} while(nbPartie < 1);
		
		while(compteurPartie < nbPartie+1){
			System.out.println("\nPartie Numero " + compteurPartie + "/"+nbPartie);
			System.out.println(joueur1 + " : " + compteurJoueur1);
			System.out.println(joueur2 + " : " + compteurJoueur2);
			boolean partieTerminer = false;
			demarrage();
			
			while(partieTerminer == false){
			   joueurActuelle = changeJoueur(joueurActuelle, joueur1, joueur2);
			   ajoutePion();
			   affichePlateau(plateau);
				
				if (positionY == 0 && positionX == plateau.length-1) {
					System.out.println("\n");
					System.out.println(joueurActuelle + " a gagne !");
					
					if(joueurActuelle == joueur1){
						compteurJoueur1++;
					}
					if(joueurActuelle == joueur2){
						compteurJoueur2++;
					}
					
					compteurPartie++;
					
					System.out.println("\033[H\033[2J");
					partieTerminer = true;
				} 
			}
		}
		
		if(compteurJoueur1 > compteurJoueur2){
				System.out.println(joueur1 + " " + " a gagne avec " + compteurJoueur1 + " points");
			}else if(compteurJoueur1 < compteurJoueur2){
				System.out.println(joueur2 + " " + " a gagne avec " + compteurJoueur2 + " points");
			}
		 if(compteurJoueur1 == compteurJoueur2){
				System.out.println("Egalite");
				System.out.println(joueur1 + " " + " a " + compteurJoueur1 + " points");
				System.out.println(joueur2 + " " + " a " + compteurJoueur2 + " points");
			}
			
			System.out.println("Merci d'avoir joue ! ");
	
	}
	
}
