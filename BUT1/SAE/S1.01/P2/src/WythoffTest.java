/**
* Ce programme peremet de jouer au jeu de Wythoff.
* @author O.Gunes
*/
class Wythoff2{
	int positionX; //Coordonée X du pion
	int positionY; //Coordonée Y du pion
	final double PHI = (1+Math.sqrt(5))/2; //Constante mathématique utilisé pour deduire les positions gagnantes
	String joueurActuelle; //Nom du joueur qui joue actuellement
	char plateau[][]; //Le plateau de jeux
	
	
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
			
			//~ Permet un affichage sans avoir de problèmes
			//~ Cette boucle permet de verifier si le nombre est inferieur à 10
			//~ Si il est inferieur on vas ajouté un 0 devant le nombre pour 
			//~ qu'il ne soit pas désequilibre
			boolean e = ((i-4) > 0);
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
		} while (!estPasValide(plateau, nbCase, mouvement) || nbCase == 0);

		placePion(mouvement, nbCase);
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
	* Procedure permettant à "l'ia" de fonctionner correctement
	*/ 
	void ajoutePionOrdinateur() {
		String mouvement = " ";
		int mouvementRandom = (int) (Math.random() * 3);
		int nbCaseRandom;
		
		if(mouvementRandom == 0){
			mouvement = "gauche";
		}
		if(mouvementRandom == 1){
			mouvement = "bas";
			
		}
		if(mouvementRandom == 2){
			mouvement = "diagonal";
		}
		
		do{
			nbCaseRandom = (int) (Math.random() * (plateau.length - 2) + 1);
		}while(!estPasValide(plateau, nbCaseRandom, mouvement) || nbCaseRandom == 0);
		
		placePion(mouvement, nbCaseRandom);
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
            resultat[rank][0] = (int) Math.floor(rank * PHI); // x = [rang*φ]
            resultat[rank][1] = resultat[rank][0] + rank; // y = x + rang
        }
        return resultat;
    }

	
	/**
	* Procedure qui permet l'execution du jeu et verifie que le jeu 
	* n'est pas terminer.
	*/
	void jouer(){
		String joueur1 = SimpleInput.getString("Nom du joueur : ");
		String ordinateur = "IA";
		
		//Choix du joueur qui va jouer en premier
		System.out.println("1. Joueur\n2. IA");
		int choix = SimpleInput.getInt("Qui joue en premier ? : ");
		if(choix == 1){
			joueurActuelle = ordinateur;
		}
		if(choix == 2){
			joueurActuelle = joueur1;
		}
		
		demarrage();
		boolean partieTerminer = false;
		
		//Boucle de la partie
		while(partieTerminer == false){
			positionGagnante(plateau);
			joueurActuelle = changeJoueur(joueurActuelle, joueur1, ordinateur);
			if(joueurActuelle == joueur1){
				ajoutePion(plateau);
				affichePlateau(plateau);
			}else if(joueurActuelle == ordinateur){
				ajoutePionOrdinateur();
				affichePlateau(plateau);
			}
            
            if (positionY == 0 && positionX == plateau.length-1) {
				System.out.println("\n");
                System.out.println(joueurActuelle + " a gagne !");
                partieTerminer = true;
            } 
		}
	}
	
}

