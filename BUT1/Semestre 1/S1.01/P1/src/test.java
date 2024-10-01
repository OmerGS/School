/**
* Ce programme peremet de jouer au jeu de Wythoff.
* @author O.Gunes
*/
class test{
	int positionX;
	int positionY;
	String joueurActuelle;
	char plateau[][];
	
	
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
	}
	
	/**
	*
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
			System.out.print(plateau.length-i-1);
			System.out.print("| ");
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

	void ajoutePion(char[][] plateau) {
		int nbCase;
		String mouvement;
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
		} while (!estPasValide(plateau, nbCase, mouvement) || nbCase == 0);

		placePion(mouvement, nbCase);
	}
	
	void placePion(String mouvement, int nbCase){
		plateau[positionX][positionY] = ' ';


		if (mouvement.equals("gauche")) {
			positionY = positionY - nbCase;
		} else if (mouvement.equals("diagonal")) {
			positionX = positionX + nbCase;
			positionY = positionY - nbCase;
		} else if (mouvement.equals("bas")) {
			positionX = positionX + nbCase;
		}

		plateau[positionX][positionY] = 'X';
	}

	void testEstPasValide(){
		System.out.println("\n*** testEstPasValide()");
		testEstPasValideAvecMouvementBas();
		testEstPasValideAvecMouvementDroite();
	}
	
	void testEstPasValideAvecMouvementBas() {
        char[][] plateau = creerPlateau(5);
        boolean result = estPasValide(plateau, 3, "bas");
        if (result) {
            System.out.println("Test OK : estPasValide avec mouvement bas");
        } else {
            System.out.println("Test ERREUR : estPasValide avec mouvement bas");
        }
    }

    void testEstPasValideAvecMouvementDroite() {
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
		} else {
			estValide = ((positionY-nbCase) >= 0);
		}

		return(estValide);
	}

	void testChangeJoueur(){
		System.out.println("\n***testChangeJoueur()");
		testCasChangeJoueur("AB", "AB", "DE","DE");
		testCasChangeJoueur("EF", "AE", "EF","AE");
		testCasChangeJoueur("ABC", "DEF", "ABC","DEF");
	}
	
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
		affichePositionGagnante(plateau);
		positionnementDepart();
		
		affichePlateau(plateau);
	}
	
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

    int[][] positionGagnante(char[][] plateau) {
        int[][] result = new int[plateau.length][2];
        for (int rang = 0; rang < result.length; rang++) {
            result[rang][0] = (int) Math.floor(rang * ((1 + Math. sqrt(5)) / 2)); // x = [rang*φ]
            result[rang][1] = result[rang][0] + rang; // y = x + rang
        }
        return result;
    }

	
	/**
	* Procedure qui permet l'execution du jeu et verifie que le jeu 
	* n'est pas terminer.
	*/
	void jouer(){
		String joueur1 = SimpleInput.getString("Nom du premier joueur : ");
		String joueur2 = SimpleInput.getString("Nom du deuxieme joueur : ");
		demarrage();
		boolean partieTerminer = false;
		
		while(partieTerminer == false){
			positionGagnante(plateau);
			joueurActuelle = changeJoueur(joueurActuelle, joueur1, joueur2);
			ajoutePion(plateau);
			affichePlateau(plateau);
            
            
            if (positionY == 0 && positionX == plateau.length-1) {
				System.out.println("\n");
                System.out.println(joueurActuelle + " a gagne !");
                partieTerminer = true;
            } 
		}
	}
	
}
