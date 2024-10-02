/**
* Ce programme peremet de jouer au jeu de Wythoff.
* @author O.Gunes
*/
class Wythoff{
	final double PHI = (1+Math.sqrt(5))/2; //Constante mathématique utilisé pour deduire les positions gagnantes
	int difficulte; //Difficulté du jeu
	
	char plateau[][]; //Le plateau de jeux
	int positionX; //Coordonée X du pion
	int positionY; //Coordonée Y du pion
	
	String joueurActuelle; //Nom du joueur qui joue actuellement
	String joueur1; //Joueur humain
	String ordinateur; //La pseudo-IA
	
	int compteurIA = 0; //Compteur de point de l'IA
	int compteurJoueur = 0; //Compteur de point Joueur
	int compteurPartie = 1; //Compteur de partie
	
	
	void principal(){
		jouer(); //Cette methode permet de jouer au Wythoff
		//methodeDeTest(); //Cette methode permet d'activer les methodes de test
	}
	
	/**
	* Procedure contenant toutes les methodes de test 
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
		int compteurCase = 0; //Nombre de case du tableau après la fonction
		int nbCaseTheorique; //Nombre de case du tableau calculé à la main
		
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
			System.out.print(" " + comptIndice + " ");
		}
		System.out.println();
	}

	/**
	* Procedure permettant de recevoir les entrées utilisateurs pour
	* pouvoir l'envoyer vers placePion();
	* @param plateau : Le plateau de jeu
	*/  
	void ajoutePion(char[][] plateau) {
		int nbCase; //Nombre de case que le joueur souhaite avancée
		String mouvement; //Mouvement (gauche, diagonal, bas)
		boolean flag = false;
		
		do {
			System.out.println("\nA votre tour " + joueurActuelle + " ! bas, gauche ou diagonal ? ");
			mouvement = SimpleInput.getString(" ");
		} while (!mouvement.equals("gauche") && !mouvement.equals("bas") && !mouvement.equals("diagonal"));

		do {
			if(flag){
				System.out.println("Entrez une valeur differentes de 0 ! ");
			}
			nbCase = SimpleInput.getInt("De combien de cases souhaitez-vous le bouger : ");
			if(nbCase == 0){
				flag = true;
			}
		} while (!estPasValide(plateau, nbCase, mouvement) || nbCase < 0);

		placePion(mouvement, nbCase);
	}
	
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
	* Procedure permettant à "l'IA" de fonctionner correctement en 
	* se deplaçant de façon intelligent selon le degrès de difficulté
	* 
	*/ 
	void ajoutePionOrdinateur() {
		if(difficulte == 2){
			System.out.println("L'ordinateur joue....\n");
			int xGagnant = -1;
			int yGagnant = -1;

			//Trouve les cases gagnantes
			for (int i = 0; i < plateau.length; i++) {
				for (int j = 0; j < plateau[i].length; j++) {
					if (plateau[i][j] == 'o') {
						xGagnant = i;
						yGagnant = j;
					}
				}
			}

			if (xGagnant != -1 && yGagnant != -1) {
				int deltaX = xGagnant - positionX; //Difference de cases entre le pion et une case gagnante dans l'axe X
				int deltaY = yGagnant - positionY; //Difference de cases entre le pion et une case gagnante dans l'axe Y

				
				if (deltaX > 0 && positionY > 0) {
					placePion("bas", 1);
					placePion("gauche", 1);
				} else if (deltaY > 0) {
					placePion("gauche", deltaY-1);
				} else if (positionX < plateau.length - 1) {
					placePion("bas", deltaX-1);
				} else {
					placePion("bas", 1);
					placePion("gauche", 1);
				}
			} else {
				if (positionX < plateau.length - 1 || positionY > 0) {
					if (positionX < plateau.length - 1) {
						placePion("bas", 1);
					} else {
						placePion("gauche", 1);
					}
				} else {
					placePion("bas", 1); 
				}
			}
		}
			
		if(difficulte == 1){
			
			String mouvement = " ";
			int nbCaseRandom;
			
			do{
				int mouvementRandom = (int) (Math.random() * 3);
				
				if(mouvementRandom == 0){
					mouvement = "gauche";
				}
				
				if(mouvementRandom == 1){
					mouvement = "bas";
					
				}
				
				if(mouvementRandom == 2){
					mouvement = "diagonal";
				}
			
				nbCaseRandom = (int) (Math.random() * (plateau.length - (plateau.length/2)-2) + 1);
			}while(!estPasValide(plateau, nbCaseRandom, mouvement) || nbCaseRandom == 0);
			
			placePion(mouvement, nbCaseRandom);
		}
	}

	/**
	* Procedure de test de la methode estPasValide();
	*/  
	void testEstPasValide(){
		System.out.println("\n*** testEstPasValide()");
		testCasEstPasValideAvecMouvementBas();
		testCasEstPasValideAvecMouvementGauche();
	}
	
	/**
	* Procedure de test de la methode estPasValide lorsque le mouvement
	* est vers le bas.
	*/  
	void testCasEstPasValideAvecMouvementBas() {
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
    void testCasEstPasValideAvecMouvementGauche() {
        char[][] plateau = creerPlateau(5);
        boolean result = estPasValide(plateau, 3, "gauche");
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
		} else {
			estValide = ((positionY-nbCase) >= 0);
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
		System.out.print("J1 : " + joueur1 + " | J2 " + joueur2 + " | JRetourne : ");
		String joueurAvantMethode = joueurActuelle;
		String joueurApresMethode = changeJoueur(joueurActuelle, joueur1, joueur2);
		System.out.print(joueurApresMethode);

		if(resultatAttendu == joueurApresMethode){
			System.out.println("OK");
		} else {
			System.out.println("ERREUR");
		}
	}

	/**
	* Change le joueur courant
	* @param joueur1 : Le joueur n°1
	* @param joueur2 : Le joueur n°2
	* @return Le joueur qui doit jouer.
	*/
	String changeJoueur(String joueurActuelle, String joueur1, String ordinateur){
		if(joueurActuelle == joueur1){
			joueurActuelle = ordinateur;
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
			positionX = 0;
			positionY = (int) (Math.random() * (plateau.length - 1) + 0);
		}
		
		if(positionRandom == 0){
			positionX = (int) (Math.random() * ((plateau.length - 2)+1));
			positionY = plateau.length-1;
		}
			plateau[positionX][positionY] = 'X';
	}
	
	/**
	* Procedure qui est executer une seule fois en debut de partie
	* pour pouvoir creer un plateau de jeu et placer le pion au hasard
	* en haut ou à droite du plateau grâce à la procedure positionnementDepart() 
	*/
	void demarrage(){
		int longueur;
		boolean flag = false;
		do{
			if(flag){
				System.out.println("\nLe nombre doit etre superieur a 5 ! ");
			}
			longueur = SimpleInput.getInt("\nEntrez la taille du tableau (doit etre superieur a 5) : ");
			flag = true;
		}while(longueur < 5);
		plateau = creerPlateau(longueur);
		affichePositionGagnante(plateau);
		positionnementDepart();
		
		affichePlateau(plateau);
	}
	
	/**
	* Procedure affichant les positions gagnantes du plateau grace
	* à la methode positionGagnante()
	* @param plateau : Le plateau de jeu.
	*/
	void affichePositionGagnante(char[][] plateau){
        int[][] result = positionGagnante(plateau);
        int i = 0;
        while (i < plateau.length){
            int x = result[i][0];
            int y = result[i][1];
            if (x < plateau.length && y < plateau.length ){
                plateau[plateau.length-1-x][y] = 'o';
                plateau[plateau.length-1-y][x] = 'o';
            }
            i++;
        }
    }

	/**
     * Les positions gagnantes sont stockées dans un tableau
     * @param plateau : Le plateau de jeu
     * @return resultat : Tableau double entré avec [rang][coordonées]
     */
    int[][] positionGagnante(char[][] plateau) {
        int[][] resultat = new int[plateau.length][2];
        for (int rank = 0; rank < resultat.length; rank++) {
            resultat[rank][0] = (int) Math.floor(rank * PHI); // x = [rank*φ]
            resultat[rank][1] = resultat[rank][0] + rank; // y = x + rank
        }
        return(resultat);
    }

	/**
	* Procedure qui permet l'execution du jeu et verifie que le jeu 
	* n'est pas terminer.
	*/
	void jouer(){
		int nbPartie; //Variable contenant le nombre de partie terminer
		int choix;
		
		//Recuperation du nom du joueur
		String joueur1 = SimpleInput.getString("Nom du joueur : ");
		String ordinateur = "IA";
		System.out.println("----------------------");
		
		//Niveau de difficulté
		System.out.println("1. Facile\n2. Difficile");
		do{
			difficulte = SimpleInput.getInt("Choisissez votre difficulte: ");
		}while(difficulte != 2 && difficulte != 1);
		System.out.println("---------------");
		
		//Choix du joueur qui va jouer en premier
		System.out.println("1. Joueur\n2. IA");
		System.out.println("---------------");
		do{
			choix = SimpleInput.getInt("Qui joue en premier ? : ");
		}while(choix != 1 && choix != 2);
		
		if(choix == 1){
			joueurActuelle = ordinateur;
		}
		if(choix == 2){
			joueurActuelle = joueur1;
		}
		
		//Recuperation du nombre de partie
		do{
			nbPartie = SimpleInput.getInt("\nCombien de partie voulez-vous faire ? ");
		} while(nbPartie < 1);
		
		
		//Boucle de la partie
		while(compteurPartie < nbPartie+1){
			System.out.println("\nPartie Numero " + compteurPartie + "/"+nbPartie);
			System.out.println("Joueur : " + compteurJoueur);
			System.out.println("Ordinateur : " + compteurIA);
			boolean partieTerminer = false;
			demarrage();
		
		
			//Boucle d'un seul tour
			while(partieTerminer == false){
				positionGagnante(plateau);
				joueurActuelle = changeJoueur(joueurActuelle, joueur1, ordinateur);
				
				if(joueurActuelle == joueur1){
					ajoutePion(plateau);
					affichePlateau(plateau);
				}else if(joueurActuelle == ordinateur){
					ajoutePionOrdinateur();
					affichePlateau(plateau);
				} else {
					ajoutePion(plateau);
					affichePlateau(plateau);
				}
				
				if (positionY == 0 && positionX == plateau.length-1) {
					System.out.println("\n");
					System.out.println(joueurActuelle + " a gagne !");
					
					if(joueurActuelle == joueur1){
						compteurJoueur++;
					}
					if(joueurActuelle == ordinateur){
						compteurIA++;
					}
					
					compteurPartie++;
					
					System.out.println("\033[H\033[2J");
					partieTerminer = true;
					
				} 
			}
		}
		
		if(compteurIA > compteurJoueur){
				System.out.println("L'ordinateur a gagne avec " + compteurIA + " points");
			}else if(compteurIA < compteurJoueur){
				System.out.println("Le joueur a gagne avec " + compteurJoueur + " points");
			}
		 if(compteurIA == compteurJoueur){
				System.out.println("Egalite");
				System.out.println("Le joueur a gagne avec " + compteurJoueur + " points");
				System.out.println("L'ordinateur a gagne avec " + compteurIA + " points");
			}
			
			System.out.println("Merci d'avoir joue ! ");
	}
	
}

