public class morpionChat {
	void principal(){
		jouer();
	}
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
                System.out.println("Le joueur " + joueur + " a gagné !");
                partieTerminee = true;
            } else if (estRempli(plateau)) {
                affichePlateau(plateau);
                System.out.println("Egalité !");
                partieTerminee = true;
            }

            joueur = changeJoueur(joueur);
        }
    }

    void affichePlateau(char[][] plateau) {
        for (int e = 0; e < plateau.length; e++) {
            System.out.print("   " + e);
        }

        for (int i = 0; i < plateau.length; i++) {
            System.out.println();
            System.out.print(i);
            for (int j = 0; j < plateau[i].length; j++) {
                System.out.print(" | ");
                System.out.print(plateau[i][j]);
            }
            System.out.print(" | ");
        }
    }

    char[][] creerPlateau(int lg) {
        char[][] plateau = new char[lg][lg];

        for (int i = 0; i < lg; i++) {
            for (int j = 0; j < lg; j++) {
                plateau[i][j] = ' ';
            }
        }
        return plateau;
    }

    void ajoutePion(char[][] plateau, char joueur) {
        boolean flag = false;
        int positionX;
        int positionY;

        do {
            if (flag) {
                System.out.println("Entrez une case vide !");
            }
            positionX = SimpleInput.getInt("Position X : ");
            positionY = SimpleInput.getInt("Position Y : ");
            flag = true;
        } while (plateau[positionX][positionY] != ' ');
        plateau[positionX][positionY] = joueur;
    }

    boolean estRempli(char[][] plateau) {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == ' ') {
                    return false; // Il y a encore au moins une case vide
                }
            }
        }
        return true; // Toutes les cases sont remplies
    }

    boolean aGagne(char[][] plateau, char joueur) {
        return alignHorizontal(plateau, joueur) || alignVertical(plateau, joueur) || alignDiagonal(plateau, joueur);
    }

    boolean alignHorizontal(char[][] plateau, char joueur) {
        for (int i = 0; i < plateau.length; i++) {
            int compteur = 0;
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] == joueur) {
                    compteur++;
                }
            }
            if (compteur == plateau.length) {
                return true;
            }
        }
        return false;
    }

    boolean alignVertical(char[][] plateau, char joueur) {
        for (int j = 0; j < plateau.length; j++) {
            int compteur = 0;
            for (int i = 0; i < plateau.length; i++) {
                if (plateau[i][j] == joueur) {
                    compteur++;
                }
            }
            if (compteur == plateau.length) {
                return true;
            }
        }
        return false;
    }

    boolean alignDiagonal(char[][] plateau, char joueur) {
        int compteur1 = 0;
        int compteur2 = 0;

        for (int i = 0; i < plateau.length; i++) {
            if (plateau[i][i] == joueur) {
                compteur1++;
            }
            if (plateau[i][plateau.length - 1 - i] == joueur) {
                compteur2++;
            }
        }

        return compteur1 == plateau.length || compteur2 == plateau.length;
    }

    char changeJoueur(char joueurInitial) {
        return (joueurInitial == 'X') ? 'O' : 'X';
    }
}
