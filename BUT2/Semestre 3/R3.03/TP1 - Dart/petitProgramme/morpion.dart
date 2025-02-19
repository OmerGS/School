import 'dart:io';

class Morpion {
  late List<List<String>> board;
  late String currentPlayer;
  
  /// Taille du plateau
  int taille = 3;







  // ------- METHODE

  Morpion() {
    board = List.generate(taille, (_) => List.filled(taille, ' '));
    currentPlayer = "X";
  }

  /// Affiche le plateau de jeu
  void displayBoard() {
    print("\n  ${List.generate(taille, (i) => i.toString()).join("   ")}");
    for (int i = 0; i < taille; i++) {
      print('$i ${board[i].join(' | ')}');
      if (i < taille - 1) { 
        print("  ${'-' * (taille * 4 - 1)}");
      }
    }
    print("");
  }

  /// Permet au joueur actuel de faire un mouvement.
  void makeMove(int ligne, int colonne) {
    try {
      if (ligne < 0 || ligne >= taille || colonne < 0 || colonne >= taille) {
        print("Erreur : Votre coup est en dehors des limites du tableau. Veuillez entrer des coordonnées entre 0 et ${taille - 1}.");
        return; 
      }

      if (board[ligne][colonne] != ' ') {
        print("Erreur : Cette case est déjà occupée. Veuillez choisir une autre case.");
        return;
      }

      board[ligne][colonne] = currentPlayer;

      if (checkVictory()) {
        displayBoard();
        print("Le joueur $currentPlayer a gagné !");
        return;
      }

      currentPlayer = (currentPlayer == "X") ? "O" : "X";
    } catch (e) {
      print("Une erreur inattendue s'est produite : $e");
    }
  }

  /// Vérifie si un joueur a gagné
  bool checkVictory() {
    // Vérification des lignes
    for (int i = 0; i < taille; i++) {
      if (board[i][0] != ' ' && board[i].every((cell) => cell == board[i][0])) {
        return true;
      }
    }

    // Vérification des colonnes
    for (int j = 0; j < taille; j++) {
      if (board[0][j] != ' ' && board.every((row) => row[j] == board[0][j])) {
        return true; 
      }
    }

    // Vérification de la diagonale
    if (board[0][0] != ' ' && List.generate(taille, (i) => board[i][i]).every((cell) => cell == board[0][0])) {
      return true; 
    }

    // Vérification de la diagonale 
    if (board[0][taille - 1] != ' ' && List.generate(taille, (i) => board[i][taille - i - 1]).every((cell) => cell == board[0][taille - 1])) {
      return true;
    }
    return false;
  }



  /// Permet de gérer la logique de lancement du jeu 
  void jouer() {
    print("Bienvenue sur Morpion 3000 !");
    print("---------------------------------------");
    displayBoard();
    print("---------------------------------------");

    while (!checkVictory()) {
      print("Tour du joueur $currentPlayer");

      int? ligne = getUserInput("Entrez la ligne (entre 0 et ${taille - 1}) :");
      int? colonne = getUserInput("Entrez la colonne (entre 0 et ${taille - 1}) :");

      if (ligne != null && colonne != null) {
        makeMove(ligne, colonne);
        displayBoard();
      }
    }
  }


  /// Fonction pour obtenir l'entrée de l'utilisateur
  int? getUserInput(String prompt) {
    String? input;
    print(prompt);
    do {
      input = stdin.readLineSync();
      
      if (input == null || input.isEmpty || int.tryParse(input) == null) {
        print("Entrée invalide. Veuillez entrer un nombre entier.");
        continue;
      }

      int value = int.parse(input);
      if (value < 0 || value >= taille) {
        print("L'entrée doit être entre 0 et ${taille - 1}. Veuillez réessayer.");
        continue;
      } else {
        return value;
      }
    } while (true);
  }
}

/// Point d'entrée du programme
void main() {
  Morpion morpion = Morpion();
  morpion.jouer();
}