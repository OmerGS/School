/**
 * Jeu de morpion
 * @author O.Gunes
 */
class morpion {
	void principal(){
		jouer();
	}
	
	/**
	* Affichage du plateau de morpion avec les indices de lignes
	* et de colonnes
	* @param plateau : le tableau a afficher 
	*/
	void affichePlateau(char[][] plateau){
		for(int e = 0; e < plateau.length; e++){
			System.out.print("   " + e);
		}
		
		for(int i = 0; i < plateau.length; i++){
				System.out.println();
				System.out.print(i);
			for(int j = 0; j < plateau[i].length; j++){
				System.out.print(" | ");
				System.out.print(plateau[i][j]);
			}
			System.out.print(" | ");
		}
	}
	
	/**
	* Creer un plateau de jeu carré rempli de caractere espace
	* @param lg : taille du plateau.
	* @return tableau de caractère en deux dimensions.
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
	* Demande deux coordonnees à l'utilisateur. Si les coordonnees sont
	* correcte (dans le plateau et pas de pion déjà mis à cet endroit
	* le caractère du joueur est ajouté au plateau sinon, on redemande
	* des coordonees en expliquant l'erreur.
	* @param plateau : le plateau de jeu
	* @param joueur : le caractère representant le joueur : X ou O.
	*/
	void ajoutePion(char[][] plateau, char joueur){
		boolean flag = false;
		int positionX;
		int positionY;
		
		do{
			if(flag){
				System.out.println("Entrez une case vide !");
			}
			System.out.println("\nTour du joueur " + joueur);
			positionX = SimpleInput.getInt("Position X : ");
			positionY = SimpleInput.getInt("Position Y : ");
			flag = true;
		} while(plateau[positionX][positionY] != ' ');
		plateau[positionX][positionY] = joueur;
	}
	
	/**
	* Verifie si toutes les cases du tableau sont remplies
	* (differente de ' ')
	* @param plateau : le plateau de jeu.
	* @return : True si tout le plateau est rempli, false sinon.
	*/
	 boolean estRempli(char[][] plateau) {
        boolean flag = true;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == ' ') {
                    flag = false;
                }
            }
        }
        return flag;
    }

	
	/**
	* Verifie s'il y a un alignement de n même caractère horizontalement
	* (n étant la longueur ou la largeur du plateau)
	* @param plateau : le plateau de jeu.
	* @return True si il existe un alignement, false sinon.
	*/
	boolean alignHorizontal(char[][] plateau, char joueur) {
        boolean flag = false;
        for (int i = 0; i < plateau.length; i++) {
            int compteur = 0;
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == joueur) {
                    compteur++;
                }
            }
            if (compteur == plateau.length) {
                flag = true;
            }
        }
        return flag;
    }

    boolean alignVertical(char[][] plateau, char joueur) {
        boolean flag = false;
        for (int j = 0; j < plateau.length; j++) {
            int compteur = 0;
            for (int i = 0; i < plateau.length; i++) {
                if (plateau[i][j] == joueur) {
                    compteur++;
                }
            }
            if (compteur == plateau.length) {
                flag = true;
            }
        }
        return flag;
    }

	
	/**
	* Change le joueur courant
	* @param joueurInitial : caractère representant le joueur : X ou O.
	* @return : Si le joueur est X renvoie O sinon renvoie X.
	*/
	char changeJoueur(char joueurInitial){
		char joueur;
		if(joueurInitial == 'X'){
			joueur = 'O';
		} else {
			joueur = 'X';
		}
		return(joueur);
	}
	
	boolean aGagne(char[][] plateau, char joueur) {
        return alignHorizontal(plateau, joueur) || alignVertical(plateau, joueur);
    }
	
	/**
	* Lance une partie de morpion
	* @param plateau de jeu a afficher
	*/
	void jouer() {
        int taillePlateau = SimpleInput.getInt("Entrez la taille du plateau : ");
        char[][] plateau = creerPlateau(taillePlateau);
        char joueur = 'X';
        boolean partieTerminee = false;

        while (!partieTerminee) {
            affichePlateau(plateau);
            ajoutePion(plateau, joueur);

            if (aGagne(plateau, joueur)) {
                affichePlateau(plateau);
                System.out.println("Le joueur " + joueur + " a gagne !");
                partieTerminee = true;
            } else if (estRempli(plateau)) {
                affichePlateau(plateau);
                System.out.println("Egalite !");
                partieTerminee = true;
            }

            joueur = changeJoueur(joueur);
        }
    }
}
