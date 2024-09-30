/**
* Programme testant 14 methodes d'operations simples sur un tableau d'entiers.
* @author O.Gunes
**/
class SimplesTableau{
	/**
	* Point d'entree du programme
	*/  
	void principal(){
		testVerifTab();
		testAfficherTab();
		testTirerAleatoire();
		testRemplirAleatoire();
		testEgalite();
		testCopier();
		testLeMax();
		testInverse();
		testEchange();
		testMelange();
		testDecalerGche();
		testSupprimerUneValeur();
		testInclusion();
		testRemplirToutesDiff();
	}
	
	/**
	* Verifie si un tableau est valide.
	* @param tab - Le tableau d'entier
	* @param nb - Le nombre d'elements contenu dans le tableau
	* @return La validité du tableau (true si c'est valide, false sinon)
	*/ 
	boolean verifTab(int[] tab, int nb){
		boolean ret = false; //2 op.
		
		if(tab != null){ //1 op.
			if(nb > 0 && nb <= tab.length){ //3 op.
				ret = true; //1 op.
			
			} else {
				System.err.println("verifTab : " + "Tableau non valide");
			}	
		} else {
			System.err.println("verifTab : " + "Tableau null");
		}
		//Total de 7 op.
		return(ret);
	}
	
	/**
	* Procedure qui permet de tester la methode verifTab()
	*/   
	void testVerifTab(){
		System.out.println("\n ------------- testVerifTab -------------\n");
		System.out.println("*** Cas Normaux : ");
		int[] tab1 = {1, 2, 3, 4};
		testCasVerifTab(tab1, 3, true);
		
		System.out.println("\n*** Cas Limites : ");
		int[] tab3 = new int[0];
		testCasVerifTab(tab3, 0, false);
	}
	
	/**
	* Procedure qui permet de verifier si la methode verifTab() 
	* renvoie une valeur correcte.
	* @param tab - Le tableau d'entier
	* @param nb - Le nombre d'elements contenu dans le tableau
	* @param res - Le resultat attendu
	*/  
	void testCasVerifTab(int[] tab, int nb, boolean res){
		boolean verif;
		
		verif = verifTab(tab, nb);
		if(verif == res){
			System.out.println("Test Reussie");
		} else {
			System.out.println("Echec du test");
		}
	}
	
	/**
	* Affiche le contenu des nbElem cases d'un tableau une par une. 
	* @param leTab : Le tableau d'entiers a afficher
	* @param nbElem : Le nombre d'entiers que contient le tableau
	*/  
	void afficherTab(int[] leTab, int nbElem){
		if(verifTab(leTab, nbElem)){
			for(int i = 0; i < nbElem; i++){
				System.out.print(leTab[i] + " | ");
			}
		} else {
			System.err.println("AfficheTab : " + "Impossible d'afficher le Tableau");
		}
	}
	
	/**
	* Procedure qui permet de tester la methode afficherTab()
	*/  
	void testAfficherTab(){
		System.out.println("\n ------------- testAfficherTab -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab1 = {0,5,8,10,15,20};
		afficherTab(leTab1, 3);
		System.out.println();
		int[] leTab3 = null;
		afficherTab(leTab1, 3);
		System.out.println();
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTab2 = new int[5];
		afficherTab(leTab2, 4);
		System.out.println();
		
		System.out.println("\n*** Cas D'Erreur : ");
		int[] leTab4 = {0,5,8,10,15,20};
		afficherTab(leTab4, 10);
	}
	
	/**
	* Renvoie un entier aleatoire compris entre min et max. Verifie que 
	* min est superieur a zero et max > min, sinon affiche -1.
	* @param min - La valeur de l'entier minimum
	* @param max - La valeur de l'entier maximum
	* @return L'entier aleatoire
	*/  
	int tirerAleatoire(int min, int max){ //A Voir
		int valeurRetourner = -1;
		
		if(min <= max){
			if(min >= 0){
				valeurRetourner = (int) (min + (Math.random() * (max - min)+0));
			} else {
				System.err.println("tirerAleatoire : " + "min inferieur a 0");
			}
		} else {
			System.err.println("tirerAleatoire : " + "Valeur de min superieur a max");
		}
		return(valeurRetourner);
	}
	
	/**
	* Procedure qui permet de verifier le bon fonctionnement de tirerAleatoire()
	*/   
	void testCasTirerAleatoire(int min, int max){
		
		int valeurComparer = tirerAleatoire(min, max);
		
		if(valeurComparer != -1){
			if(valeurComparer >= min && valeurComparer <= max){
				System.out.println("Min : " + min + " | Max : " + max + " | Valeur Obtenu : " + valeurComparer + " : OK");
			}
		}
	}
	
	/**
	* Procedure de test qui envoie des valeurs à tester à testCasTirerAleatoire
	*/ 
	void testTirerAleatoire(){
		System.out.println("\n ------------- testTirerAleatoire -------------\n");
		System.out.print("*** Cas Normaux : \n");
		testCasTirerAleatoire(1, 5);
		testCasTirerAleatoire(10, 15);
		
		System.out.println("\n*** Cas Limites : ");
		testCasTirerAleatoire(0, 0);
		
		System.out.println("\n*** Cas D'Erreur : ");
		testCasTirerAleatoire(12, 10);
		testCasTirerAleatoire(-5, -2);
	}
	
	/**
	 * A partir d'un tableau deja crée, rempli aleatoirement le tableau 
	 * de nbElem comprises entre min et max
	* @param leTab - le tableau à remplir de valeurs tirées aléatoirement
	* @param nbElem - le nombres d'entiers que contiendra le tableau
	* @param min - la valeur de l'entier minimum
	* @param max - la valeur de l'entier maximum
	* 
	*/  
	void remplirAleatoire(int[] leTab, int nbElem, int min, int max){
		if(verifTab(leTab, nbElem)){
			if(tirerAleatoire(min,max) != -1){
				for(int i = 0; i < nbElem; i++){
					leTab[i] = tirerAleatoire(min,max);
				}
				afficherTab(leTab, nbElem);
			}
		}
	}
	
	/**
	* Procedure de test qui envoie des valeurs à tester à remplirAleatoire();
	*/  
	void testRemplirAleatoire(){
		System.out.println("\n ------------- testRemplirAleatoire -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = new int [10];
		int nbElem = 7;
		int min = 5;
		int max = 25;
		remplirAleatoire(leTab, nbElem, min, max);
		
		int[] leTab2 = new int [18];
		int nbElem2 = 7;
		int min2 = 10;
		int max2 = 18;
		remplirAleatoire(leTab2, nbElem2, min2, max2);
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTab3 = new int [1];
		int nbElem3 = 1;
		int min3 = 0;
		int max3 = 0;
		remplirAleatoire(leTab3, nbElem3, min3, max3);
		
		System.out.println("\n*** Cas D'Erreur : ");
		int[] leTab4 = new int [10];
		int nbElem4 = -5;
		int min4 = 100;
		int max4 = 200;
		remplirAleatoire(leTab4, nbElem4, min4, max4);
		
		int[] leTab5 = new int [30];
		int nbElem5 = 15;
		int min5 = 12;
		int max5 = 10;
		remplirAleatoire(leTab5, nbElem5, min5, max5);
		
		int[] leTab6 = new int [30];
		int nbElem6 = 15;
		int min6 = -5;
		int max6 = 10;
		remplirAleatoire(leTab6, nbElem6, min6, max6);
	}
	
	/**
	* Verifie si les deux tableaux passé en paramètre sont egaux ou pas.
	* @param leTab1 - Le 1er tableau à comparer
	* @param leTab2 - Le 2eme tableau à comparer
	* @param nbElem1 - Le nombre d'entiers present sur le 1er tableau
	* @param nbElem2 - Le nombre d'entiers present sur le 2eme tableau
	* @return true si egalité parfaite, false sinon.
	*/  
	boolean egalite(int[] leTab1, int[] leTab2, int nbElem1, int nbElem2){
		boolean continuation = false;
		int i = 0;
		
		if(verifTab(leTab1, nbElem1) == true){
			if(verifTab(leTab2, nbElem2) == true){
				if(nbElem1 == nbElem2){
					
					while(i < nbElem1-1 || !continuation){
						continuation = (leTab1[i] == leTab2[i]);
						i++; 
					}
					
				}
			}
		}
		return(continuation);
	}
	
	/**
	* Test la methode qui erifie si les deux tableaux passé en parametre 
	* sont egaux ou pas.
	* @param leTab1 - Le 1er tableau à comaparer
	* @param leTab2 - Le 2eme tableau à comparer
	* @param nbElem1 - Le nombre d'entiers present sur le 1er tableau
	* @param nbElem2 - Le nombre d'entiers present sur le 2eme tableau
	* @param res - Valeur déjà connu sur l'égalité des tableaux, true si egaux, false sinon
	*/
	void testCasEgalite(int[] leTab1, int[] leTab2, int nbElem1, int nbElem2, boolean res){
		boolean valeurMethode = egalite(leTab1, leTab2, nbElem1, nbElem2);
		
		if(verifTab(leTab1, nbElem1) && verifTab(leTab2, nbElem2)){
			if(res == valeurMethode){
				System.out.println("OK");
			} else {
				System.out.println("Tableaux pas egaux");
			}
		}
	}
	
	/**
	* Procedure de test qui envoie des valeurs à tester à testCasEgalite();
	*/  
	void testEgalite(){
		System.out.println("\n ------------- testEgalite -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab1 = {1,2,3,4,5,6};
		int[] leTab2 = {1,2,3,4,5,6};
		int nbElem1 = 6;
		int nbElem2 = 6;
		testCasEgalite(leTab1, leTab2, nbElem1, nbElem2, true); 
		
		int[] leTab3 = {1,2,3};
		int[] leTab4 = {1,2,3,4,5,6};
		int nbElem3 = 3;
		int nbElem4 = 6;
		testCasEgalite(leTab3, leTab4, nbElem3, nbElem4, true);
		
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTab5 = {};
		int[] leTab6 = {};
		int nbElem5 = 0;
		int nbElem6 = 0;
		testCasEgalite(leTab5, leTab6, nbElem5, nbElem6, false);
	}
	
	/**
	* Renvoie la copie exacte (clone) du tableau passe au parametre.
	* @param tabToCopy - Le tableau à copier
	* @param nbElem - Le nombre d'entier presents dans le tableau
	* @return Le nouveau tableau qui est la copie du tableau passé en parametre
	*/  
	int[] copier(int[] tabToCopy, int nbElem){
		int[] tabRetourner = new int[0];
		
		if(verifTab(tabToCopy, nbElem)){
			tabRetourner = new int[nbElem];
			for(int i = 0; i < nbElem; i++){
				tabRetourner[i] = tabToCopy[i];
			}
		}
		return(tabRetourner);
	}
	
	/**
	* Test la methode qui verifie si les deux tableaux passe en paramètre
	* @param tabToCopier - Le tableau à verifier
	* @param nbElem - Le nombre d'elements présents dans le tableau
	*/  
	void testCasCopier(int[] tabToCopier, int nbElem){
		int[] tableauCopier = copier(tabToCopier, nbElem);
		
		afficherTab(tableauCopier, nbElem);
		
		if(egalite(tableauCopier, tabToCopier, nbElem, nbElem) == true){
			System.out.println("\nOK");
		} else {
			System.out.println("ERREUR");
		}
	}
	/**
	* Methodes qui test differents tableau sur la methode testCasCopier();
	*/  
	void testCopier(){
		System.out.println("\n ------------- testCopier -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = {1,5,3,4,5,6};
		int nbElem = 6;
		testCasCopier(leTab, nbElem);
		
		int[] leTab2 = new int[5];
		int nbElem2 = 5;
		testCasCopier(leTab2, nbElem2);
		
		int[] leTab3 = {0};
		int nbElem3 = 1;
		testCasCopier(leTab3, nbElem3);
	}
	
	/**
	* Renvoie le maximum parmi les elements du tableau.
	* @param leTab - Le tableau
	* @param nbElem - Le nombre d'entiers présents dans le tableau
	* @return Le maximum des elements du tableau
	*/  
	int leMax(int[] leTab, int nbElem){
		int retourneValeur = -1; //2 op.
		if(verifTab(leTab, nbElem)){ //1 + verifTri (7 op.) donc 8 op.
			for(int i = 0; i < nbElem; i++){ //2 + 1 + 2 op.
				if(retourneValeur < leTab[i]){ //1 op.
					retourneValeur = leTab[i]; // 1 op.
				}
			}
		} //Total de 17 op.
		return(retourneValeur);
	}
	
	/**
	* Test la methodes qui renvoie la valeur maximum d'un tableau.
	* @param leTab - Le tableau
	* @param nbElem - Le nombre d'entiers présents dans le tableau
	* @param nbMax - La valeur maximum contenu dans le tableau
	*/ 
	void testCasLeMax(int[] leTab, int nbElem, int nbMax){
		int valeurMaxMethode = leMax(leTab, nbElem);
			
		if(valeurMaxMethode != -1){	
			if(nbMax == valeurMaxMethode){
				System.out.println(valeurMaxMethode + " | " + "OK");
			} else {
				System.out.println("ERREUR");
			}
		}
	}
	/**
	* Methode qui envoie les valeurs à tester à la methode testCasLeMax(); 
	*/
	void testLeMax(){
		System.out.println("\n ------------- testLeMax -------------\n");
		System.out.print("*** Cas Normaux : \n");
		
		int[] leTab = {5, 7, 8, 1, 2, 5, 6};
		int nbElem = 5;
		testCasLeMax(leTab, nbElem, 8);
		
		int[] leTab2 = {-1, -2, -3, -5, 5, 2};
		int nbElem2 = 6;
		testCasLeMax(leTab2, nbElem2, 5);
		
		int[] leTab3 = {8, 12, 15, 52, 98, 100, -5, 12};
		int nbElem3 = 7;
		testCasLeMax(leTab3, nbElem3,100);
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTab4 = new int[5];
		int nbElem4 = 5;
		testCasLeMax(leTab4, nbElem4, 0);
	}
	
	/**
	* Renvoie un nouveau tableau qui est l'inverse de celui passe en parametre.
	* @param leTab - Le tableau
	* @param nbElem - Le nombre d'entiers presents dans le tableau
	* @return Le nouveau tableau qui est l'inverse de leTab sur la plage (0... nbElem-1) 
	*/ 
	int[] inverse(int[] leTab, int nbElem){
		int[] tabInverse = new int [0];
		if(verifTab(leTab, nbElem)){
			tabInverse = new int [nbElem];
			for(int i = 0; i < nbElem; i++){
				tabInverse[i] = leTab[nbElem-1-i];
			}
		}
		return(tabInverse);
	}
	
	void testCasInverse(int[] leTab, int nbElem, int[] tableauSortie){
		int[] tableauInverse = inverse(leTab, nbElem);
		
		System.out.print("Tableau : ");
		afficherTab(leTab, nbElem);
		System.out.print("\nLe Tableau Inverse : ");
		afficherTab(tableauInverse, nbElem);
		
		if(egalite(tableauSortie, tableauInverse, nbElem, nbElem)){
			System.out.println("\nOK");
		} else {
			System.out.println("\nERREUR");
		}
	}
	
	void testInverse(){
		System.out.println("\n ------------- testInverse -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = {1,2,3,4,5,6,7,8,9};
		int[] leTabSortie = {9,8,7,6,5,4,3,2,1};
		int nbElem = 9;
		testCasInverse(leTab, nbElem, leTabSortie);
		
		System.out.println();
		
		int[] leTab2 = {50,40,30,20,10,5,4};
		int[] leTabSortie2 = {4,5,10,20,30,40,50};
		int nbElem2 = 7;
		testCasInverse(leTab2, nbElem2, leTabSortie2);
		
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTab3 = new int [5];
		int[] leTabSortie3 = new int [5];
		int nbElem3 = 5;
		testCasInverse(leTab3, nbElem3, leTabSortie3);
	}
	
	/**
	* Echange les contenues des cases du tableau passe en parametre, 
	* cases identifiees par les indices ind1 et ind2. 
	* @param leTab - le tableau
	* @param nbElem - le nombre d'entiers présents dans le tableau
	* @param ind1 - numero de la premieres cases à echanger
	* @param ind2 - numero de la deuxieme cases à echanger
	*/ 
	void echange(int[] leTab, int nbElem, int ind1, int ind2){
		if(verifTab(leTab, nbElem)){ //7 op.
			if(ind1 >= 0 && ind2 >= 0){ // 3 op
				if(ind2 <= nbElem && ind2 <= nbElem){ //3 op
					int varTemporaire = leTab[ind1]; //2 op
					leTab[ind1] = leTab[2]; //1 
					leTab[ind2] = varTemporaire; //1 op
				} else {
					System.err.println("echange : " + "Les indices doient etre < nbElem");
				}
			} else {
				System.err.println("echange : " + "Les indices doient etre > 0");
			}
		}
	}
	
	/**
	* Test le bon fonctionnement de la methode echange(); 
	* @param leTab - le tableau
	* @param nbElem - le nombre d'entiers présents dans le tableau
	* @param ind1 - numero de la premieres cases à echanger
	* @param ind2 - numero de la deuxieme cases à echanger
	* @param tableauCorrect - Le tableau correct
	*/ 
	void testCasEchange(int[] leTab, int nbElem, int ind1, int ind2, int[] tableauCorrect){
		int[] leTabOriginal = leTab;
		echange(leTab, nbElem, ind1, ind2);
		int[] leTabAVerifier = leTab;
		
		if(verifTab(leTab, nbElem) && verifTab(tableauCorrect, nbElem)){
			if(egalite(leTabAVerifier, tableauCorrect, nbElem, nbElem)){
				System.out.println("\nOK");
			} else {
				System.out.println("\nERREUR");
			}
		}
		System.out.print("Le Tableau Original : "); afficherTab(leTabOriginal, nbElem); System.out.println();
		System.out.print("Le Tableau Apres Methode : "); afficherTab(leTabAVerifier, nbElem); System.out.println();
		System.out.print("Le Tableau Correct : "); afficherTab(tableauCorrect, nbElem); System.out.println();
	}
	
	/**
	* La methode qui envoie les valeurs a tester à testCasEchange(); 
	*/
	void testEchange(){
		System.out.println("\n ------------- testEchange -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTabATester = {0,1,2};
		int[] leTabCorrect = {2,1,0};
		int nbElem = 3;
		int ind1 = 0;
		int ind2 = 2;
		testCasEchange(leTabATester, nbElem, ind1, ind2, leTabCorrect);
		
		int[] leTabATester2 = {0,1,2,3,4,5,6,7,8,9};
		int[] leTabCorrect2 = {0,1,2,3,7,5,6,4,8,9};
		int nbElem2 = 10;
		int ind3 = 5;
		int ind4 = 8;
		testCasEchange(leTabATester2, nbElem2, ind3, ind4, leTabCorrect2);
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTabATester3 = {0,1,2,3,4,5,6,7,8,9};
		int[] leTabCorrect3 = {0,1,2,3,4,5,6,7,8,9};
		int nbElem3 = 10;
		int ind5 = 5;
		int ind6 = 5;
		testCasEchange(leTabATester3, nbElem3, ind5, ind6, leTabCorrect3);
		
		int[] leTabATester4 = new int[5];
		int[] leTabCorrect4 = new int[5];
		int nbElem4 = 3;
		int ind7 = 4;
		int ind8 = 0;
		testCasEchange(leTabATester4, nbElem4, ind7, ind8, leTabCorrect4);
	}
	
	/**
    * Melange les elements du tableau de manière aleatoire.
    * @param leTab - le tableau à melanger
    * @param nbElem - le nombre d'entiers présents dans le tableau
    * @return le nouveau tableau mélangé
    */
    int[] melange(int[] leTab, int nbElem) {
        int[] tableauMelange = copier(leTab, nbElem);
		if(verifTab(leTab, nbElem)){
			for (int i = nbElem - 1; i > 0; i--) {
				int j = tirerAleatoire(0, i + 1);
				int temporaire = tableauMelange[i];
				tableauMelange[i] = tableauMelange[j];
				tableauMelange[j] = temporaire;
			}
		} 

        return(tableauMelange);
    }
    
    /**
    * Methodes qui test et affiche les differentes valeurs sur la methode
    * melange();
    * 
    * @param leTab - Le tableau
    * @param nbElem - Le nombre d'entiers présents dans le tableau
    */ 
    void testCasMelange(int[] leTab, int nbElem){
		int[] tableauFinal = melange(leTab, nbElem);
		
		afficherTab(tableauFinal, nbElem);
	}
    
    void testMelange(){
		System.out.println("\n ------------- testMeleange -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTabATester1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int nbElem1 = 10;
        testCasMelange(leTabATester1, nbElem1);
        
        System.out.println();
		
        int[] leTabATester2 = {5, 10, 15, 20, 25};
        int nbElem2 = 5;
        testCasMelange(leTabATester2, nbElem2);
        
        System.out.println();

        int[] leTabATester4 = {1,2,3,4,5,6,7,8,9};
        int nbElem4 = 5;
        testCasMelange(leTabATester4, nbElem4);
        
        System.out.println("\n\n*** Cas D'Erreur : ");
        int[] leTabATester5 = {1,2,3,4,5,6,7,8,9};
        int nbElem5 = 15;
        testCasMelange(leTabATester5, nbElem5);
	}
	
	/**
     * Decale de une case de la droite vers la gauche toutes les cases d'un tableau
     * a partir d'un indice "ind" et jusque nbElem-1.
     * @param leTab - le tableau
     * @param nbElem - le nombre d'entiers présents dans le tableau
     * @param ind - l'indice à partir duquel commence le décalage à gauche
     */
    void decalerGche(int[] leTab, int nbElem, int ind) {
        if(verifTab(leTab, nbElem)){
			if (ind < 0 || ind > nbElem - 2) {
				System.out.println("Erreur : L'indice doit être compris entre 0 et " + (nbElem - 2));
			} else {
				for (int i = ind; i < nbElem-1; i++) {
					leTab[i] = leTab[i + 1];
				}
			}
		}
    }
    
    /**
    * Methodes qui test et affiche les differentes valeurs sur la methode
    * decalerGche();
    * 
    * @param leTab - Le tableau
    * @param nbElem - Le nombre d'entiers presents dans le tableau
    */ 
    void testCasDecalerGche(int[] leTab, int nbElem, int ind){
		decalerGche(leTab, nbElem, ind);
		int[] tableauFinal = leTab;
		
		afficherTab(tableauFinal, nbElem);
	}
    
    /**
	* La methode qui envoie les valeurs a tester à testDecalerGauche(); 
	*/
    void testDecalerGche(){
		System.out.println("\n ------------- testDecalerGauche -------------\n");
		System.out.print("*** Cas Normaux : \n");
		
		int[] leTabATester1 = {1,2,3,4,5,6,7,8,9};
		int nbElem = 9;
		int ind = 6;
		testCasDecalerGche(leTabATester1, nbElem, ind);
		
		System.out.println();
		
		int[] leTabATester2 = {45,52,68,48,96,53,24,15,61,75,23};
		int nbElem2 = 11;
		int ind2= 3;
		testCasDecalerGche(leTabATester2, nbElem2, ind2);
		
		System.out.println();
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTabATester4 = new int[15];
		int nbElem4 = 11;
		int ind4 = 5;
		testCasDecalerGche(leTabATester4, nbElem4, ind4);
		
		System.out.println();
		
		System.out.println("\n*** Cas D'Erreur : ");
		int[] leTabATester3 = {};
		int nbElem3 = 0;
		int ind3 = 0;
		testCasDecalerGche(leTabATester3, nbElem3, ind3);
		
		System.out.println();
		
		int[] leTabATester5 = {1,5,8,9,4,3,6,7,10};
		int nbElem5 = 20;
		int ind5 = 5;
		testCasDecalerGche(leTabATester5, nbElem5, ind5);
	}
	
	/**
	* Supprime du tableau la premiere case rencontrée dont le contenu 
	* est egale à "valeur". La case du tableau est supprimee par 
	* décalage à gauche des cases du tableau.
	* 
	* @param leTab - Le Tableau
	* @param nbElem - le nombre d'entiers presents dans le tableau
	* @param valeur - le contenu de la premiere case à supprimer
	* @return le nombre d'elements dans le tableau (eventuellement inchange)
	*/  
	int supprimerUneValeur(int[] leTab, int nbElem, int valeur) {
		int retournement = nbElem;
		boolean suppression = false;
		
		if(verifTab(leTab, nbElem)){
			int i = 0;
			while(i < nbElem && !suppression){
				if(leTab[i] == valeur){
					decalerGche(leTab, nbElem, i);
					suppression = true;
				}
				i++;
			}
		}
		
		if(suppression == true){
			nbElem = nbElem-1;
			retournement = supprimerUneValeur(leTab, nbElem, valeur);
		}
		
		return(retournement);
	}
	
	/**
	* Test les differents cas de la methode supprimerUneValeur()
	* 
	* @param leTab - Le Tableau
	* @param nbElem - le nombre d'entiers présents dans le tableau
	* @param valeur - le contenu de la première case à supprimer
	*/  
	void testCasSupprimerUneValeur(int[] leTab, int nbElem, int valeur){
		int valeurRestant = supprimerUneValeur(leTab, nbElem, valeur);
		
		System.out.println("Le nombre d'elements restant : " + valeurRestant);
	}
	
	/**
	* La methode qui envoie les valeurs a tester à testCasSupprimerUneValeur(); 
	*/
	void testSupprimerUneValeur(){
		System.out.println("\n ------------- testSupprimeUneValeur -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = {1,2,3,1,2,3,1,2,3,1};
		int nbElem = 10;
		int valeur = 3;
		testCasSupprimerUneValeur(leTab, nbElem, valeur);
		
		int[] leTab2 = {5,4,6,6,6,8,7,9,1,2,3,5,4};
		int nbElem2 = 13;
		int valeur2 = 6;
		testCasSupprimerUneValeur(leTab2, nbElem2, valeur2);
		
		int[] leTab3 = {1,2,3,4,5,7,8,9,10};
		int nbElem3 = 9;
		int valeur3 = 6;
		testCasSupprimerUneValeur(leTab3, nbElem3, valeur3);
	}
	
	/**
	* Renvoie vrai si le tableau tab1 est inclus dans tab2. Autrement dit
	* , si tous les elements du tab1 se retrouvent totalement dans tab2 
	* (y compris les doublons) mais pas necessairement dans le meme
	* ordre.
	* 
	* @param tab1 - Le 1er tableau
	* @param tab2 - Le 2eme tableau
	* @param nbElem1 - Le nombre d'entiers presents dans le 1er tableau
	* @param nbElem2 - Le nombre d'entiers presents dans le 2eme tableau
	* @return vrai si tableau1 est inclus dans tableau2.
	*/  
	boolean inclusion(int[] tab1, int[] tab2, int nbElem1, int nbElem2) {
		boolean inclusion = false;
		boolean continuation = false;
		int z = 0;

		if (nbElem1 > nbElem2) {
			System.err.println("inclusion : " + "Le tableau 2 doit etre plus grand que le 1er");
			continuation = true;
		}

		while (z != 1 && verifTab(tab1, nbElem1) && verifTab(tab2, nbElem2) && !continuation) {
			boolean[] estDedans = new boolean[nbElem2];
			inclusion = true;

			for (int i = 0; i < nbElem1; i++) {
				boolean trouve = false;
				boolean pause = false;
				int j = 0;

				while (j < nbElem2 && pause == false) {
					if (!estDedans[j] && tab1[i] == tab2[j]) {
						estDedans[j] = true;
						trouve = true;
						pause = true;
					}
					j++;
				}

				if (!trouve) {
					inclusion = false;
					z = 2;
				}
			}

			z++;
		}
		return(inclusion);
	}
    
    /**
    * Effectue differents test sur la methode inclusion 
    * avec les valeurs fourni en parametre.
    * 
    * @param tab1 - Le 1er tableau
	* @param tab2 - Le 2eme tableau
	* @param nbElem1 - Le nombre d'entiers presents dans le 1er tableau
	* @param nbElem2 - Le nombre d'entiers presents dans le 2eme tableau
    */  
    void testCasInclusion(int[] tab1, int[] tab2, int nbElem1, int nbElem2, boolean inclus){
		boolean resultat = inclusion(tab1, tab2, nbElem1, nbElem2);
		
		System.out.println("Valeur Attendu : " + inclus + " | " + "Valeur Obtenu : " + resultat);
		
		if(resultat == inclus){
			System.out.println("OK");
		} else {
			System.out.println("Echec du test");
		}
	}
    
    /**
	* La methode qui envoie les valeurs a tester à testCasInclusion(); 
	*/
    void testInclusion(){
		System.out.println("\n ------------- testInclusion -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] tab1 = {3, 3, 3};
        int[] tab2 = {5, 4, 3, 2, 1, 3, 2, 3};
        int nbElem1 = 3;
        int nbElem2 = 8;
		testCasInclusion(tab1, tab2, nbElem1, nbElem2 , true);
		
		System.out.println();
		
		int[] tab3 = {};
        int[] tab4 = {};
        int nbElem3 = 0;
        int nbElem4 = 0;
		testCasInclusion(tab3, tab4, nbElem3, nbElem4 , false);
		
		System.out.println();
		
		int[] tab5 = {1,1,1};
        int[] tab6 = {1,1,1};
        int nbElem5 = 3;
        int nbElem6 = 3;
		testCasInclusion(tab5, tab6, nbElem5, nbElem6 , true);
	}
	
	/**
	* A partir d'un tableau déjà créé, remplir le tableau de nbElem 
	* valeurs saisies par l'utilisateur.
	* 
	* @param leTab - Le tableau
	* @param nbElem - Le nombre d'entiers contenu dans le tableau
	*
	*/   
	 void remplirToutesDiff(int[] leTab, int nbElem) {
        if(verifTab(leTab, nbElem)){
			for (int i = 0; i < nbElem; i++) {
				boolean valeurDejaPresente;
				do {
					leTab[i] = SimpleInput.getInt("Saisissez la valeur pour l'index " + i + " : ");
					
					valeurDejaPresente = false;
					
					int j = 0;
					while(j < i && !valeurDejaPresente) {
						if (leTab[j] == leTab[i]) {
							valeurDejaPresente = true;
							System.out.println("remplirToutesDiff : " + "Valeurs deja presentes");
						}
						j++;
					}
				} while (valeurDejaPresente);
			}
		}
    }
    
    
    /**
    * Effectue differents test sur la methode remplirToutesDiff 
    * avec les valeurs fourni en parametre.
    * 
    * @param leTab - Le tableau d'entiers
	* @param nbElem - Le nombre d'entiers contenu dans le tableau
    */
    void testCasRemplirToutesDiff(int[] leTab, int nbElem){
		remplirToutesDiff(leTab, nbElem);
		
		System.out.println("\nVoici le tableau final : ");
		afficherTab(leTab, nbElem);
	}
    
    /**
	* Methode qui envoie les valeurs a tester à testCasRemplirToutesDiff(); 
	*/
    void testRemplirToutesDiff(){
		System.out.println("\n ------------- testRemplirToutesDiff -------------\n");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = new int[6];
		int nbElem = 6;
		testCasRemplirToutesDiff(leTab, nbElem);
		
		System.out.println("\n\n*** Cas D'Erreur : ");
		int[] leTab2 = new int[6];
		int nbElem2 = 0;
		testCasRemplirToutesDiff(leTab2, nbElem2);
		
	}
}
