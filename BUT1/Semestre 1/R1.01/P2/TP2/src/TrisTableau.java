/**
* Cette classe effectue des opérations plus complexes sur un tableaux d'entiers : recherche dichotomique, tris, etc. La taille d'un tableau est
* par définition le nombre TOTAL de cases = tab.length. Un tableau d'entiers créé possède nbElem élements qui ne correspond pas
* forcément à la taille du tableau.
* 
* Il est fait usage de la classe SimplesTableau pour accéder aux méthodes de cette classe.
* 
* @author O.Gunes
*/

class TrisTableau{
	/**
	* Cette variable est a l'interieur de chaque code d'efficacite dans
	* la boucle la plus interne. Elle compte le nombre d'operations elementaire
	* effectue dans la boucle. 
	**/
	long cpt = 0;
	
	/**
	* Cette classe contient differentes methode de manipulation de tableau. 
	**/
	SimplesTableau smplTableau = new SimplesTableau();
	
	/**
	* Point d'entrée du programme 
	*/ 
	void principal(){
		testRechercheSeq();
		testRechercheSeqEfficacite();
		
		testVerifTri();
		
		testRechercheDicho();
		testRechercheDichoEfficacite();		
		
		testTriSimple();
		testTriSimpleEfficacite();
		
		testSeparer();
		
		testTriRapideRec();
		
		testTriRapide();
		testTriRapideEfficacite();
		
		testCreerTabFreq();
		
		testTriParComptageFreq();
		testTriParComptageFreqEfficacite();
		
		testTriABulles();
		testTriABullesEfficacite();
	}
	
	
	
	
	
	/* RECHERCHE SEQUENTIELLE */
	
	/**
	* Recherche séquentielle d'une valeur dans un tableau. La valeur 
	* à rechercher peut exister en plusieurs exemplaires mais la 
	* recherche s'arrête dès qu'une première valeur est trouvée.
	* 
	* @param leTab - Le tableau dans lequel on va effectuer la recherche
	* @param nbElem - Le nombre d'entier que contient le tableau
	* @param aRech - L'entier à rechercher dans le tableau
	* 
	* @return l'indice (>=0) de la position de l'entier dans le tableau ou -1 s'il n'est pas présent
	*/  
	int rechercheSeq(int[] leTab, int nbElem, int aRech){
		int valeurARetourner = -1; //2 op.
		boolean valeurTrouver = false; //2 op.
		int i = 0; //2 op.
		
		//6 en dehors
		//(8*nbt)+1

		//borne initial i = 0
		//borne fin = nbElem

		//nbT = (nbElem-0+1)/1 = nbElem+1
		//f(n) = 7+(8*nbT) = 8*n = 8n+7 on s'en fou de 7 donc -> 8n

		while((i < nbElem) && !valeurTrouver){ //2 op. + 1 à la fin (1+1)
			if(aRech == leTab[i]){ //2 op
				valeurARetourner = i; //1 op
				valeurTrouver = true; // 1 op
			}
			i++; //2 op. i=i+1
			cpt++; //ne comptabilise pas
		}
		return(valeurARetourner); //0 op. ne compte pas
	}
	
	/**
	* Methode qui calcule l'efficacite de la methode rechercheSeq();
	* Il appelle la methode testRechercheSeqEfficaciteRepetition().
	**/
	void testRechercheSeqEfficacite(){
		System.out.println(" \n\n *** testRechercheSeqEfficacite *** \n");
		/* 1er Test d'efficacité sur un tableau de taille 2^15*/
		int[] tableauGrand215 = new int [1];
		int n = (int) Math.pow(2,15);
		testRechercheSeqEfficaciteRepetition(n, tableauGrand215);
		
		/* 2eme Test d'efficacité sur un tableau de taille 2^17*/
		int[] tableauGrand217 = new int [1];
		int n2 = (int) Math.pow(2,18);
		testRechercheSeqEfficaciteRepetition(n2, tableauGrand217);
		
		/* 3eme Test d'efficacité sur un tableau de taille 2^18*/
		int[] tableauGrand218 = new int [1];
		int n3 = (int) Math.pow(2,20);
		testRechercheSeqEfficaciteRepetition(n3, tableauGrand218);
	}
	
	/**
	* Methode qui va calculer le temps en nano secondes de la rechercheSeq
	* dans le cas le plus defavorable. Il va aussi calculer le nombre
	* total d'operation elementaire (on neglige ce qu'il y a en dehors de la boucle).
	* 
	* @param n - Le nombre d'elements voulue qu'on vas tester
	* @param tableauGrand - le tableau d'entiers
	*/ 
	void testRechercheSeqEfficaciteRepetition(int n, int[] tableauGrand){
		cpt = 0;
		tableauGrand = new int[n];
		
		System.out.println("*** Tableau de Taille " + tableauGrand.length + " ***");
		
		smplTableau.remplirAleatoire(tableauGrand, n, 0, 500);
		tableauGrand[tableauGrand.length-1] = 502;
		double t1 = System.nanoTime();
		rechercheSeq(tableauGrand, n, 502);
		double t2 = System.nanoTime();
		double diffT = (t2 - t1); // en nanosecondes
		System.out.println(diffT + " ns");
		
		double cste1 = cpt/n;
		System.out.println("cpt/n = " + cste1 + "\n");
	}
	
	/**
	* Methode qui verifie la methode rechercheSeq(); dans differents
	* cas (Normaux, Limite et Erreur), les valeurs lui sont fourni
	* par la methode testRechercheSeq();
	* 
	* @param leTab - Le tableau dans lequel on va effectuer la recherche
	* @param nbElem - Le nombre d'entier que contient le tableau*
	* @param aRech - L'entier à rechercher dans le tableau
	* @param rang - Le rang ou se trouve le nombre chercher
	*/
	void testCasRechercheSeq(int[] leTab, int nbElem, int aRech, int rang){
		int indiceValeur = rechercheSeq(leTab, nbElem, aRech);
		
		if(rang == indiceValeur){
			System.out.println("Test Reussie");
		} else {
			System.out.println("Echec du Test");
		}
	}
	
	/**
	* Methode qui fourni des valeurs a la methode testCasRecherceSeq();
	* Il y a 3 niveaux de test :
	* - Normaux
	* - Limites
	* - Erreur 
	*/
	void testRechercheSeq(){
		System.out.println("--------- testRechercheSeq ---------");
		System.out.println("*** Cas Normaux :");
		
		/* 1er Test Unitaire | Test Reussi - Tableau de 1 à 16, on cherche la valeur 11 à l'indice 10 */
		int[] leTableau = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int nbElem = 16;
		testCasRechercheSeq(leTableau, nbElem, 11, 10);
		
		/* 2eme Test Unitaire | Echec du Test - Tableau de 1 à 16, on cherche la valeur 11 à l'indice -1 
		 * Donne une erreur car le 11 n'est pas présent à l'indice 5 */
		int[] leTableau2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int nbElem2 = 16;
		testCasRechercheSeq(leTableau2, nbElem2, 11, 5);
		
		System.out.println("\n*** Cas Limites :");
		
		/* 3eme Test Unitaire | Echec du Test - Tableau rempli de 2, on cherche la valeur 2 présent à l'indice 4 
		 * Donne une erreur car le premier deux est présent à l'indice 0. */
		int[] leTableau3 = {2,2,2,2,2,2};
		int nbElem3 = 6;
		testCasRechercheSeq(leTableau3, nbElem3, 2, 4);
		
		/* 4eme Test Unitaire | Test Reussi - Tableau rempli de 2, on cherche la valeur 2 présent à l'indice 0.*/
		int[] leTableau4 = {2,2,2,2,2,2};
		int nbElem4 = 6;
		testCasRechercheSeq(leTableau4, nbElem4, 2, 0);
	}
	
	
	
	
	
	
	
	
	
	
	

	/* VERIFTRI */
	
	/**
	* Verifie que le tableau passe en parametre est trie par ordre croissant
	* des valeurs. On suppose que le tableau passé en parametre est crée et
	* possède au moins une valeur (nbElem > 0). 
	* 
	* @param leTab - Le tableau a verifier (trie en ordre croissant)
	* @param nbElem - Le nombre d'entiers presents dans le tableau
	* 
	* @return True si le tableau est trie, false sinon.
	*/ 
	boolean verifTri(int[] leTab, int nbElem){
        boolean tri = true;
        int i = 1;
        int nbVerif = leTab[0];
        while(i < nbElem-1 && tri){
            if(nbVerif <= leTab[i]){
                tri = true;
            } else {
				tri = false;
			}
			
            nbVerif = leTab[i];
            i++;
        }
        return(tri);
    }

	/**
	* Verifie que la méthode verifTri() renvoie une valeur correct
	* 
	* @param leTab - Le tableau d'entiers
	* @param nbElem - Le nombre d'entiers présent dans le tableau
	* @param trier - Variable qui verifie que le tableau est correct ou pas
	*/  
	void testCasVerifTri(int[] leTab, int nbElem, boolean trier){
		boolean tabTrier = verifTri(leTab, nbElem);
		
		if(trier == tabTrier){
			System.out.println("Test Reussie");
		} else{
			System.out.println("Pas Trier");
		}
	}
	
	/**
	* Methode qui fourni des valeurs a la methode testCasRecherceSeq();
	* Il y a le niveaux de test :
	* - Normaux
	*/
	void testVerifTri(){
		System.out.print("*** testVerifTri ***\n");
		
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = {1,2,3,3,5,6,7,8,9,10,11,12,13};
		int nbElem = 13;
		testCasVerifTri(leTab, nbElem, true);
		
		int[] leTab2 = {10,9,8,7,6,5};
		int nbElem2 = 6;
		testCasVerifTri(leTab2, nbElem2, false);
	}
	
	
	
	
	
	
	
	
	/* RECHERCHEDICHO */
	
	
	/**
	* Recherche dichotomique d'une valeur dans un tableau. La valeur à rechercher
	* peut exister en plusieur exemplaires, dans ce cas c'est la valeur à l'indice
	* la + faible qui sera trouve. 
	* 
	* @param leTab - Le tableau d'entiers
	* @param n - Le nombre d'entires présent dans le tableau
	* @param aRech - L'entiers à rechercher dans le tableau
	* 
	* @return - L'indice (>=0) de la position de l'entier dasn le tableau 
	* ou -1 si il n'est pas présent
	*/  
	int rechercheDicho(int[] leTab, int n, int aRech) {
		// variables locales
		int indD, indF, indM, ret; //4 op.

		// initialisations
		indD = 0; //1 op.
		indF = n - 1; //2 op.

		// boucle
		while (indD <= indF) { //2 op. + 1 à la fin
			indM = (indD + indF) / 2; //3 op.
			
			if (aRech > leTab[indM]) { //1 op.
				indD = indM + 1; //2 op.
			} else if (aRech < leTab[indM]) { //1 op.
				indF = indM - 1; //2 op.
			} else {
				// Terminer la recherche dès qu'on trouve l'élément
				ret = indM; //1 op/
				return ret;
			}
			cpt++;
		}

		// Si on ne trouve pas l'élément, retourner -1
		ret = -1; //1 op.
		return ret;
	}

	/**
	* Methode qui calcule l'efficacite de la methode rechercheSeq();
	* Il appelle la methode testRechercheSeqEfficaciteRepetition().
	**/
	void testRechercheDichoEfficacite(){
		System.out.println(" *** testRechercheDichoEfficacite ***\n");
		/* 1er Test d'efficacité sur un tableau de taille 2^15*/
		int[] tableauGrand215 = new int [1];
		int n = (int) Math.pow(2,15);
		testRechercheDichoEfficaciteRepetition(n, tableauGrand215);
		
		/* 2eme Test d'efficacité sur un tableau de taille 2^17*/
		int[] tableauGrand217 = new int [1];
		int n2 = (int) Math.pow(2,18);
		testRechercheDichoEfficaciteRepetition(n2, tableauGrand217);
		
		/* 3eme Test d'efficacité sur un tableau de taille 2^18*/
		int[] tableauGrand218 = new int [1];
		int n3 = (int) Math.pow(2,20);
		testRechercheDichoEfficaciteRepetition(n3, tableauGrand218);
	}
	
	/**
	* Methode qui va calculer le temps en nano secondes de la rechercheSeq
	* dans le cas le plus defavorable. Il va aussi calculer le nombre
	* total d'operation elementaire (on neglige ce qu'il y a en dehors de la boucle).
	* 
	* @param n - le nombre d'elements du tableau
	* @param tableauGrand - le tableau d'entiers
	*/ 
	void testRechercheDichoEfficaciteRepetition(int n, int[] tableauGrand){
		cpt = 0;
		tableauGrand = new int[n];
		
		System.out.println("*** Tableau de Taille " + tableauGrand.length + " ***");
		
		smplTableau.remplirAleatoire(tableauGrand, n, 0, 500);
		tableauGrand[tableauGrand.length-1] = 502;
		double t1 = System.nanoTime();
		rechercheSeq(tableauGrand, n, 502);
		double t2 = System.nanoTime();
		double diffT = (t2 - t1); // en nanosecondes
		System.out.println(diffT + " ns");
		
		double cste1 = cpt/n;
		System.out.println("cpt/n = " + cste1 + "\n");
	}

	/**
	* Methode de test qui vas envoyer des valeurs a tester a la methode
	* testCasRechercheDicho(); 
	**/
	void testRechercheDicho() {
		System.out.println("*** testVerifRechercheDicho ***");

		System.out.println("*** Cas Normaux : ");
		int[] leTab = {1, 2, 3, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int nbElem = 13;
		int aRech1 = 3;
		testCasRechercheDicho(leTab, nbElem, aRech1, 2); // La valeur 3 se trouve à l'indice 2

		int[] leTab2 = {10, 9, 8, 7, 6, 5};
		int nbElem2 = 6;
		int aRech2 = 4;
		testCasRechercheDicho(leTab2, nbElem2, aRech2, -1); // La valeur 4 ne se trouve pas dans le tableau

		System.out.println("*** Cas Limites : ");
		int[] tableauPremierElem = {1, 2, 3, 4, 5};
		int nbElemPremierElem = 5;
		int aRechPremierElem = 1;
		testCasRechercheDicho(tableauPremierElem, nbElemPremierElem, aRechPremierElem, 0); // La valeur est au début du tableau

		int[] tableauDernierElem = {1, 2, 3, 4, 5};
		int nbElemDernierElem = 5;
		int aRechDernierElem = 5;
		testCasRechercheDicho(tableauDernierElem, nbElemDernierElem, aRechDernierElem, 4); // La valeur est à la fin du tableau

		int[] tableauValeurMilieu = {1, 2, 3, 4, 5};
		int nbElemValeurMilieu = 5;
		int aRechValeurMilieu = 3;
		testCasRechercheDicho(tableauValeurMilieu, nbElemValeurMilieu, aRechValeurMilieu, 2); // La valeur est au milieu du tableau
	}

	/**
	* Methode de test cas qui va tester la methode rechercheDicho();
	* 
	* @param leTab - Le tableau d'entier
	* @param nbElem - le nombre d'element
	* @param aRech - L'entiers à rechercher dans le tableau
	* @param expectedResult - le resulat attendu
	**/ 
	void testCasRechercheDicho(int[] leTab, int nbElem, int aRech, int expectedResult) {
		int result = rechercheDicho(leTab, nbElem, aRech);

		if (result == expectedResult) {
			System.out.println("Test Reussie");
		} else {
			System.out.println("Erreur");
		}
		
		System.out.println("La valeur a chercher : " + aRech);
		System.out.println("Le tableau dans laquel on doit le chercher : ");
		smplTableau.afficherTab(leTab, nbElem);
		System.out.println("Son indice : " + result);
		
		System.out.println("");
		System.out.println("----------------------------------------");
	}






	/* TriSimple */
	
	/**
	* Cette méthode trie un tableau d’entiers par ordre
	* croissant des valeurs. La méthode est celle du tri
	* simple (ramener les + petites valeurs en début de
	* tableau).
	* 
	* @param leTab - le tableau des valeurs
	* @param n - le nombre de valeurs à trier
	*/
	void triSimple(int[] leTab, int n) {
		int i, k, min, tmp; //4 op.
		
		// première boucle = celle qui parcourt le tableau de 0 à (n – 2) y compris
		for (i = 0; i <= (n - 2); i++) { //1 + 2 + 2 op.
			// sélectionner la plus petite valeur sur le sous-tableau allant de i à (n-1) : on identifie une case « k »
			min = leTab[i]; //1 op.
			k = i; //1 op.
			
			for (int p = (i + 1); p < n; p++) { //2 + 1 + 1 + 2 op.
				if (leTab[p] < min) { //1 op.
					min = leTab[p]; //1 op.
					k = p; //1 op.
				}
				cpt++; //ne compte pas
			}
			// ensuite échanger cette case « k » avec « i »
			tmp = leTab[k]; //1 op.
			leTab[k] = leTab[i]; //1 op.
			leTab[i] = tmp; //1 op.
		}
	}
	
	/**
	* Methode de test qui a pour but d'envoyer des valeurs a tester
	* dans testCasTriSimple(); 
	*/ 
	void testTriSimple() {
		System.out.print("*** Cas Normaux : \n");
		
		System.out.println("---------- 1er Test Unitaire ---------");
		int[] leTab = {5,4,8,45,150,1,28,9504,6405};
		int nbElem = 9;
		testCasTriSimple(leTab, nbElem);
		
		System.out.println("---------- 2eme Test Unitaire ---------");
		int[] leTab2 = {150,2,6,2561,25698,210,3654,785,1,0,458};
		int nbElem2 = 11;
		testCasTriSimple(leTab2, nbElem2);
    }

	/**
	* Methode qui a pour but de tester les differentes combinaison qui lui
	* ont été donne par testTriSimple().
	* 
	* @param leTab - Le tableau d'entiers
	* @param n - le nombre d'element que contient le tableau
	*/ 
    void testCasTriSimple(int[] leTab, int n) {
		System.out.println("Le tableau avant la methode : ");
		smplTableau.afficherTab(leTab, n);
		triSimple(leTab, n);
		System.out.println("Le tableau apres la methode : ");
		smplTableau.afficherTab(leTab, n);
		
		if(verifTri(leTab, n)){
			System.out.println("Test Reussie : Tableau Trier");
		} else {
			System.out.println("Tableau Non Trier");
		}
    }
	
	/**
	* Methode qui permet de mesurer l'efficacite de la methode triSimple
	* en comptant le nombre d'operations elementaires effectue dans la boucle
	* la plus imbrique et calculant le temps d'execution de la methode.
	**/
	void testTriSimpleEfficacite(){
		System.out.println("\n*** testTriSimpleEfficacite *** ");
		int n = 15000;
		for(int i = 1; i <= 5; i++){
			System.out.println("\nEfficacite pour : " + n + " valeurs");
			
			int[] leTab = triSimpleEfficaciteCreationTableau(n);
			
			cpt = 0;
			double t1 = System.currentTimeMillis();
			triSimple(leTab, n);
			double t2 = System.currentTimeMillis();
			double duree = t2-t1;
			
			System.out.println("temps : " + duree + " ms");
			System.out.println("cpt : " + cpt);
			
			double cste2 = cpt/(Math.pow(n, 2));
			System.out.println("cpt/n² : " + cste2);
			
			n*=2;
		}
	}
	
	/**
	* Methode qui effectue une inversion de tableau.
	* @param n - Le nombre d'element du tableau
	* 
	* @return un tableau desordonne
	**/
	int[] triSimpleEfficaciteCreationTableau(int n){
		int[] tableauDesordone = new int[n];
		for(int i = 0; i < n; i++){
			tableauDesordone[i] = n-i;
		}
		return(tableauDesordone);
	}
	
	
	
	
	
	
	
	
	
	/* SEPARER */
	/**
	* Cette méthode renvoie l'indice de separation du tableau en 2 parties
	* par placement du pivot à la bonne case.
	* 
	* @param tab - Le tableau des valeurs
	* @param indL - indice Left de debut du tableau
	* @param indR - indice Right de fin de tableau
	* 
	* @return l'indice de separation du tableau.
	*/ 
	int separer(int[] tab, int indL, int indR){
        int indP = indL;
        int pivot = tab[indL];
        int nbElem = indR+1;
        int i = 0;
        while(indL < indR){ //1 operation
			cpt++; 
            if(i%2 == 0){ //2 op.
                if(pivot > tab[indR]){ //1 op.
                    smplTableau.echange(tab, nbElem, indP, indR);
                    i++; //2 op.
                    indP = indR;
                    indL++;
                }else{
                    indR--;
                }
            }else{
                if(pivot < tab[indL]){
                    smplTableau.echange(tab, nbElem, indP, indL);
                    i++;
                    indP = indL;
                    indR--;
                }else{
                    indL++;
                }

            }
        }
        return indP;
    }

	/**
	* Methode de test qui a pour but de d'envoyer des données dans testCasSeparer();
	**/
	void testSeparer(){
		System.out.println("--------- testSeparer --------- ");
		System.out.print("*** Cas Normaux : \n");
		int[] leTabMelanger = {5,4,8,9,6,2,1,3,4,7};
		int indLMelanger = 0;
		int indRMelanger = leTabMelanger.length-1;
		testCasSeparer(leTabMelanger, indLMelanger, indRMelanger, 5);
		
		int[] leTabMelanger2 = {10,5,8,7,9,6,3,1,21};
		int indLMelanger2 = 0;
		int indRMelanger2 = leTabMelanger2.length-1;
		testCasSeparer(leTabMelanger2, indLMelanger2, indRMelanger2, 7);
		
		System.out.println("\n*** Cas Limites : ");
		int[] leTabMemeValeur = {1,1,1,1,1};
		int indLMemeValeur = 0;
		int indRMemeValeur = leTabMemeValeur.length-1;
		testCasSeparer(leTabMemeValeur, indLMemeValeur, indRMemeValeur, 0);
	}
	
	/**
	* Methode de test qui a pour but de tester les valeurs qui lui sont envoyer en verifiant
	* l'exactitude des valeurs de sortis de separer();
	* 
	* @param tab - Le tableau des valeurs
	* @param indL - indice Left de debut du tableau
	* @param indR - indice Right de fin de tableau
	* @param pivot - l'indice de sepation du tableau qu'on veut trouver on envoyant ces valeurs
	**/
	void testCasSeparer(int[] tab, int indL, int indR, int pivot){
		int verif = separer(tab, indL, indR);
		
		
		System.out.print("Indice Pivot : " + verif + " | ");
		if(pivot == verif){
			System.out.println("Test Reussie");
		} else {
			System.out.println("Echec du test");
		}
	}
	
	
	
	
	
	
	
	
	
	/* TRIRAPIDE et TRIRAPIDEREC */
	/**
	* Methode de tri recursive selon le principe de separation. La methode
	* s'appelle elle meme sur les tableaux gauche et droite par rapprt a un pivot.
	* 
	* @param tab - Le tableau d'entiers
	* @param indL - L'indice gauche de debut de tableau
	* @param indR - L'indice droite de fin de tableau
	**/
	void triRapideRec (int [] tab, int indL, int indR) {
		// variable locale
		int indS; //1 op.
		indS = separer(tab, indL, indR); //1 op. + separer
		if ( (indS-1) > indL ) { //1 op.
			triRapideRec(tab, indL, (indS-1) );
		}
		if ( (indS+1) < indR ) {
			triRapideRec ( tab, (indS+1), indR );
		}
	}
	
	/**
	* Methode de test qui a pour but d'envoyant des valeurs a triRapideTestCas()
	**/
	void testTriRapideRec(){
		System.out.println("--------- testTriRapideRec --------- ");
		System.out.print("*** Cas Normaux : \n");
		//1er Test
		int n = (int) Math.pow(2,5);
		int[] leTab = new int[n];
		smplTableau.remplirAleatoire(leTab, n, 0, 25);
		testCasTriRapideRec(leTab, 0, n-1);
		
		//2eme Test
		int n2 = 13;
		int[] leTab2 = {5,4,7,9,8,20,4,5,54,21,3,6,89};
		testCasTriRapideRec(leTab2, 0, n2-1);
		
		System.out.println("\n*** Cas Limites : ");
		int n3 = 14;
		int[] leTab3 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		testCasTriRapideRec(leTab3, 0, n3-1);
	}
	
	/**
	* Methode qui pour but de tester la methode triRapideRec en lui envoyant des valeurs.
	* Les valeurs a envoyer a triRapideRec(); lui son fournit par testTriRapide();
	* 
	* @param tab - Le tableau d'entiers
	* @param indL - L'indice gauche de debut de tableau
	* @param indR - L'indice droite de fin de tableau
	**/
	void testCasTriRapideRec(int[] tab, int indL, int indR){
		System.out.println("Avant le tri : ");
		smplTableau.afficherTab(tab, tab.length-1);
		
		triRapideRec(tab, 0, tab.length-1);
		
		System.out.println("Apres le tri : ");
		smplTableau.afficherTab(tab, tab.length-1);
		
		if(verifTri(tab, tab.length)){
			System.out.println("Test Reussie");
		} else {
			System.out.println("Echec du test");
		}
		
		System.out.println("\n---------------------\n");
	}
	
	/**
	* Tri par ordre croissant d'un tableau selon la méthode du tri rapide (QuickSort)
	* On suppose que le tableau passe en parametre est cree et possede au moins
	* une valeur (nbElem > 0). Ceci ne doit donc pas etre verifi. Cette Methode
	* appelle triRapideRec() qui elle effectue reellement le tri rapide selon la methode
	* de separation recursive.
	* 
	* @param tab - le tableau d'entier a trier par ordre croissant
	* @param nbElem - Le nombre d'entiers que contient le tableau 
	**/
	void triRapide (int [] tab, int nbElem) {
		triRapideRec (tab, 0, (nbElem-1)); //1 op.
	}
	
	/**
	* Methode de test qui envoie des valeurs a tester a la methode testCasTriRapide();
	**/
	void testTriRapide(){
		System.out.println("--------- testTriRapide --------- ");
		System.out.print("*** Cas Normaux : \n");
		//1er Test
		int n = (int) Math.pow(2,5);
		int[] leTab = new int[n];
		smplTableau.remplirAleatoire(leTab, n, 0, 25);
		testCasTriRapide(leTab, n);
		
		//2eme Test
		int n2 = 13;
		int[] leTab2 = {5,4,7,9,8,20,4,5,54,21,3,6,89};
		testCasTriRapide(leTab2, n2);
	}
	
	/**
	* Methode de test qui recoit les valeurs de testTriRapide() pour les tester
	* sur la methode triRapide(). 
	* 
	* @param tab - le tableau d'entier a trier par ordre croissant
	* @param n - Le nombre d'entiers que contient le tableau 
	**/
	void testCasTriRapide(int[] tab, int n){
		System.out.println("Avant le tri : ");
		smplTableau.afficherTab(tab, n);
		
		triRapide(tab, n);
		
		System.out.println("Après le tri : ");
		smplTableau.afficherTab(tab, n);
		
		if(verifTri(tab, tab.length)){
			System.out.println("Test Reussie");
		} else {
			System.out.println("Echec du test");
		}
		
		System.out.println("------------------");
	}
	
	/**
	* Methode qui permet de mesurer l'efficacite de la methode triRapide
	* en comptant le nombre d'operations elementaires effectue dans la boucle
	* la plus imbrique et en calculant le temps d'execution de la 
	* methode triRapide.
	**/
	void testTriRapideEfficacite(){
		System.out.println("\n*** testTriRapideEfficacite *** ");
		System.out.println("\n ------ Tableau Aleatoire ------ \n");
		int exposant = 18;
		for(int i = 1; i <= 3; i++){
			int n = (int) Math.pow(2, exposant);
			System.out.println("\nEfficacite pour : " + n + " valeurs");
			
			int[] leTab = new int [n];
			smplTableau.remplirAleatoire(leTab, n, 0, n);
			
			cpt = 0;
			double t1 = System.currentTimeMillis();
			triRapide(leTab, n);
			double t2 = System.currentTimeMillis();
			double duree = t2-t1;
			
			System.out.println("temps : " + duree + " ms");
			System.out.println("cpt : " + cpt);
			
			double cste3 = cpt/(n*(Math.log10(n) / Math.log10(2)));
			System.out.println("cpt/nlog2n : " + cste3);
			
			exposant++;
		}
		
		System.out.println("\n ------ Pire Des Cas ------");
		//Ici je crée un tableau aleatoire pour le trier une premiere fois
		int n = (int) Math.pow(2, 13);
		System.out.println("\nEfficacite pour : " + n + " valeurs");
		
		int[] leTab = new int [n];
		smplTableau.remplirAleatoire(leTab, n, 0, n);
		
		//Le tableau va être trie une premiere fois
		triRapide(leTab, n);
		
		/*puis ici le tableau va etre trie une seconde fois mais cette 
		 * fois ci le tableau sera deja trie donc triRapide sera dans 
		 * le pire cas possible. */
		cpt = 0;
		double t1 = System.currentTimeMillis();
		triRapide(leTab, n);
		double t2 = System.currentTimeMillis();
		double duree = t2-t1;
		
		System.out.println("temps : " + duree + " ms");
		System.out.println("cpt : " + cpt);
		
		double cste4 = cpt/(Math.pow(n,2));
		System.out.println("cpt/n^2 : " + cste4);
	}
	
	
	
	
	
	
	
	
	
	/* creerTabFreq */
	
	/**
	*  A partir d'un tableau initial passé en paramètre "leTab", 
	* cette méthode renvoie un nouveau tableau "tabFreq" d'entiers où chaque
	* case contient la fréquence d'apparition des valeurs dans le tableau initial.
	* On suppose que le tableau passé en paramètre est créé et possède au moins
	* une valeur (nbElem > 0). Ceci ne doit donc pas être vérifié. Par contre, 
	* on vérifiera que le max du tableau initial est >= min et que le min 
	* est >= 0. Dans le cas contraire, renvoyer un tableau "null"
	* 
	* @param leTab - le tableau initial
	* @param nbElem - le nombre d'entiers presents dans le tableau
	* 
	* @return le tableau frequences de taille (max+1) ou null si la 
	* methode ne s'applique pas
	**/
	int[] creerTabFreq(int[] leTab, int nbElem) {
		int[] tabFreq; //1 op.

		// Correction 1: Utiliser la fonction leMin
		int min = leMin(leTab, nbElem); //1 op. + leMin (9op.) donc 10 op.

		if (min >= 0) { //1 op.
			// Correction 2: Utiliser la fonction leMax
			int max = smplTableau.leMax(leTab, nbElem); //1 op + leMax (17 op.) donc 18.
			tabFreq = new int[max + 1]; //2 op.

			// Correction 3: Correction de la boucle pour calculer les fréquences
			for (int i = 0; i < nbElem; i++) { //2 + 1 + 2 op.
				// Correction 4: Utiliser leTab[i] pour accéder à la valeur actuelle
				int valeurCourante = leTab[i]; //2 op.
				tabFreq[valeurCourante]++; //2 op.
			}
		} else {
			tabFreq = null; //1 op.
		}
		return tabFreq; //ne compte pas
	}
	
	/**
	* Methode de test qui doit envoyer les valeurs a tester dans la
	* methode testCasCreerTabFreq();
	**/
	void testCreerTabFreq(){
		System.out.println(" ------- testCreerTabFreq ------- ");
		System.out.print("*** Cas Normaux : \n");
		int[] leTab = {5,4,7,8,14,25,12,36,36,36,12,5,7,4,4,4};
		int n = 16;
		testCasCreerTabFreq(leTab, n);
		
		int[] leTab2 = {1};
		int n2 = 1;
		testCasCreerTabFreq(leTab2, n2);
		
		int[] leTab3 = {5,5,5,5,5,5,5,5,5,5,5,5,5};
		int n3 = 13;
		testCasCreerTabFreq(leTab3, n3);
		
		int[] leTab4 = {-5,12,14,15,13,10,5,7,8};
		int n4 = 9;
		testCasCreerTabFreq(leTab4, n4);
	}
	
	/**
	* Methode de test qui teste la methode creerTabFreq() pour verifier
	* le tableau.
	* 
	* @param leTab - le tableau d'entiers
	* @param nbElem - le nombre d'element present dans tableau. 
	**/
	void testCasCreerTabFreq(int[] leTab, int nbElem){
		int[] tab = creerTabFreq(leTab, nbElem);
		int max = smplTableau.leMax(leTab, nbElem);
		smplTableau.afficherTab(tab, max+1);;
	}
	
	/**
	* Renvoie le minimum parmi les elements du tableau.
	* 
	* @param leTab - Le tableau
	* @param nbElem - Le nombre d'entiers présents dans le tableau
	* 
	* @return Le minimum des elements du tableau
	*/  
	int leMin(int[] leTab, int nbElem) {
		int min = leTab[0]; //2 op.
		for (int i = 1; i < nbElem; i++) { //2 + 1 + 2 op.
			if (leTab[i] < min) { //1 op.
				min = leTab[i]; //1 op.
			} //Total de 9 op.
		}
		return(min);
	}









	/* triParComptageFreq */
	/**
	* Tri par ordre croissant d'un tableau selon la méthode du tri par 
	* comptage de fréquences. On suppose que le tableau passé en
	* paramètre est créé et possède au moins une valeur (nbElem > 0). 
	* Ceci ne doit donc pas être vérifié.
	* 
	* @param leTab - le tableau a trier par ordre croissant.
	* @param nbElem - le nombre d'entier que contient le tableau.
	**/
	void triParComptageFreq(int[] leTab, int nbElem) {
		int[] tabFreq = creerTabFreq(leTab, nbElem); //2 op + creerTabFreq (43) donc 45op.
		if (tabFreq != null) { //1 op.
			int indice = 0; //2 op.
			for (int j = 0; j < tabFreq.length; j++) { //2 + 1+ 2 op.
				for (int i = 0; i < tabFreq[j]; i++) { //2 + 1 + 2 op. 
					leTab[indice] = j; //1 op.
					indice++; //2 op.
					cpt++; //ne compte pas
				}
			}
		}
	}
	
	/**
	* Methode de test qui envoie les valeurs a la methode de test de cas
	* testCasTriParComptageFreq();.
	*/ 
	void testTriParComptageFreq(){
		System.out.println("------- testTriParComptageFreq ------");
		System.out.print("*** Cas Normaux : \n");
		System.out.println("\n****** Cas Avec Tableau Aleatoire *******\n");
		int[] tab = {1,2,6,8,8,95,4,1,2,36,5,12,56,5,6,98,1,5};
		int nbElem = 18;
		testCasTriParComptageFreq(tab, nbElem);
		
		System.out.println("\n****** Cas Avec Tableau de Meme Valeur *******\n");
		int[] tab2 = {5,5,5,5,5,5,5,5,5,5,5,5,5};
		int nbElem2 = 13;
		testCasTriParComptageFreq(tab2, nbElem2);
		
		System.out.println("\n****** Cas Avec Tableau de Une seule Valeur *******\n");
		int[] tab3 = {1};
		int nbElem3 = 1;
		testCasTriParComptageFreq(tab3, nbElem3);
	}
	
	/**
	* Methode de test de cas qui recoit les valeurs de la methode
	* testTriParComptageFreq(); et les verifie en les affichant.
	* 
	* @param leTab - Le tableau d'entier
	* @param nbElem - Le nombre d'element du tableau
	**/
	void testCasTriParComptageFreq(int[] leTab, int nbElem){
		System.out.println("Le tableau avant la methode : ");
		smplTableau.afficherTab(leTab, nbElem);
		
		triParComptageFreq(leTab, nbElem);
		
		System.out.println("Le tableau apres la methode : ");
		smplTableau.afficherTab(leTab, nbElem);
	}
	
	/**
	* Methode de test qui permet de tester l'efficacite de la methode
	* triParComptageFreq(); en mesurant nottament la vitesse d'execution,
	* le nombre operation elementaire du programme. 
	**/
	void testTriParComptageFreqEfficacite(){
		System.out.println("\n*** testTriParComptageFreqEfficacite *** ");
		System.out.println("\n ------ Tableau Aleatoire ------ \n");
		int exposant = 18;
		
		for(int i = 0; i < 4; i++){
			int n = (int) Math.pow(2, exposant);
			System.out.println("\nEfficacite pour : " + n + " valeurs");
				
			int[] leTab = new int [n];
			smplTableau.remplirAleatoire(leTab, n, 0, n);
				
			cpt = 0;
			double t1 = System.currentTimeMillis();
			triParComptageFreq(leTab, n);
			double t2 = System.currentTimeMillis();
			double duree = t2-t1;
				
			System.out.println("temps : " + duree + " ms");
			System.out.println("cpt : " + cpt);
				
			double cste3 = cpt/(n);
			System.out.println("cpt/n : " + cste3);
				
			exposant++;
		}
	}
	
	
	
	
	
	
	
	
	/* triABulles */
	/**
	* Tri par ordre croissant d'un tableau selon la méthode du tri à
	* bulles : tant que le tableau n'est pas trié, permuter le contenu de 2
	* cases successives si leTab[i] > leTab[i+1]. On suppose que le tableau 
	* passé en paramètre est créé et possède au moins une valeur (nbElem > 0).
	* Ceci ne doit donc pas être vérifié.
	* 
	* @param leTab - le tableau a trier par ordre croissant
	* @param nbElem - le nombre d'entier que contient le tableau
	**/
	void triABulles(int[] leTab, int nbElem){
		int i = 0; //2 op.
		while(!verifTri(leTab, nbElem) && i < nbElem){ //7 op. + 1 + 1
			for(int j = 0; j < nbElem-1; j++){ //2 + 1 + 2
				if(leTab[j] > leTab[j+1]){ //1
					smplTableau.echange(leTab, nbElem, j, j+1); //17 op
				}
				cpt++;
			}
			i++; //2 op.
		}
	}
	
	/**
	* Methode de test qui permet d'envoyer des valeurs a tester a 
	* testCasTriABulles();
	**/
	void testTriABulles(){
		System.out.println(" ------- testTriABulles ------ ");
		System.out.print("*** Cas Normaux : \n");
		
		System.out.println("\n****** Cas Avec Tableau Desordones *******\n");
		int[] leTab = {5,4,6,9,7,8,1,2,3,54,5,6,1,25,20,236};
		int nbElem = 16;
		testCasTriABulles(leTab, nbElem);
		
		System.out.println("\n****** 2e Cas Avec Tableau Desordones *******\n");
		int[] tab4 = {1,2,6,8,8,95,4,1,2,36,5,12,56,5,6,98,1,5};
		int nbElem4 = 18;
		testCasTriABulles(tab4, nbElem4);
		
		System.out.println("\n****** Cas Avec Tableau de Meme Valeur *******\n");
		int[] tab2 = {5,5,5,5,5,5,5,5,5,5,5,5,5};
		int nbElem2 = 13;
		testCasTriABulles(tab2, nbElem2);
		
		System.out.println("\n****** Cas Avec Tableau de Une seule Valeur *******\n");
		int[] tab3 = {1};
		int nbElem3 = 1;
		testCasTriABulles(tab3, nbElem3);
	}
	
	/**
	* Methode de test de cas qui permet de tester la methode triABulles de
	* façon optimale avec les valeurs qui lui sont fournis par la methode
	* testTriABulles();
	* 
	* @param leTab - Le tableau d'entiers
	* @param nbElem - Le nombre d'elements
	**/
	void testCasTriABulles(int[] leTab, int nbElem){
		System.out.println("Le tableau avant la methode : ");
		smplTableau.afficherTab(leTab, nbElem);
		
		triABulles(leTab, nbElem);
		
		System.out.println("Le tableau apres la methode : ");
		smplTableau.afficherTab(leTab, nbElem);
	}
	
	/**
	* Methode de test qui permet de tester l'efficacite de la methode
	* triABulles();
	**/
	void testTriABullesEfficacite(){
        System.out.println("\n*** testTriABullesEfficacite *** ");
		System.out.println("\n ------ Tableau Aleatoire ------ \n");
		int exposant = 14;
		
		for(int i = 0; i < 4; i++){
			int n = (int) Math.pow(2, exposant);
			System.out.println("\nEfficacite pour : " + n + " valeurs");
				
			int[] leTab = new int [n];
			smplTableau.remplirAleatoire(leTab, n, 0, n);
				
			cpt = 0;
			double t1 = System.currentTimeMillis();
			triABulles(leTab, n);
			double t2 = System.currentTimeMillis();
			double duree = t2-t1;
				
			System.out.println("temps : " + duree + " ms");
			System.out.println("cpt : " + cpt);
				
			double cste7 = cpt/(Math.pow(n,2));
			System.out.println("cpt/n^2 : " + cste7);
				
			exposant++;
		}
	}
}
