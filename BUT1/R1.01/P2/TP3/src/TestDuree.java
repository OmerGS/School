/**
* Classe qui a pour but de tester les differentes methodes présent dans la classe
* Duree
* 
* @author O.Gunes
**/
class TestDuree{
	
	/**
	* Creation de la variable d1 pour faire appel a la methode duree 
	**/
	Duree d1 = new Duree(5000);

	/**
	* Creation de la variable d2 pour faire appel a la methode duree 
	**/
	Duree d2 = new Duree(10,5,56);

	/**
	* Point d'entrée du programme 
	**/
	void principal(){
		testConstructeur1EtGetLeTemps();
		testConstructeur2EtGetLeTemps();
		testAjouter();
		testSoustraire();
		testCompareA();
		testEnTexte();
	}
	
	/**
	* Methode qui permet de faire une separation entre deux methodes de test
	* pour un meilleur confort de lecture lors de l'éxecution de plusieurs test 
	**/
	void separationTest(){
		System.out.println("\n ===================== \n");
	}
	
	/**
	* Methode qui test qui envoie la valeur a tester dans testCasConstructeur1EtGetLeTemps() 
	**/
	void testConstructeur1EtGetLeTemps(){
		System.out.println(" ------ testConstructeur1EtGetLeTemps ------ ");
		System.out.print("*** Cas Normaux : \n");
		testCasConstructeur1EtGetLeTemps(5000, 5000, false);
		/* Renvoie "Reussi" (ça fonctionne car les valeurs sont les mêmes) */
		
		testCasConstructeur1EtGetLeTemps(15000, 100, false);
		/* Renvoie "Echec" (ça fonctionne correctement car les valeurs sont differentes) */
		
		System.out.println("\n*** Cas Limites : ");
		testCasConstructeur1EtGetLeTemps(0,0, false);
		/* Renvoie "Reussi" (ça fonctionne car les valeurs sont les mêmes) */
		
		System.out.println("\n*** Cas D'Erreur : ");
		testCasConstructeur1EtGetLeTemps(-100, 0, true);
		/* Erreur car une des deux valeurs est négative */
		
		separationTest();
	}

	/**
	* Methode de test qui permet de tester le constructeur et la methode 
	* getLeTemps()
	* 
	* @param millisec Temps en milliseconde
	* @param res Valeur attendu
	* @param casErreur Si le test est censé donné une erreur ou pas (true si erreur, false sinon) 
	* 
	**/
	void testCasConstructeur1EtGetLeTemps(int millisec, int res, boolean casErreur){
		Duree d1;
		
		if(!casErreur){
			d1 = new Duree(millisec);
			if(d1.getLeTemps() == res){
				System.out.println("Reussi");
			} else {
				System.out.println("Echec du Test");
			}
		} else {
			System.out.println("Message d'erreur attendu");
			d1 = new Duree(millisec);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	* Methode qui test qui envoie la valeur a tester dans testCasConstructeur2EtGetLeTemps() 
	**/
	void testConstructeur2EtGetLeTemps(){
		System.out.println(" ------ testConstructeur2EtGetLeTemps ------ ");
		System.out.print("*** Cas Normaux : \n");
		testCasConstructeur2EtGetLeTemps(21, 45, 23, 78323000, false);
		/* Renvoie "Reussi" (ça fonctionne car le temps en heure, minute, sec et le même que res) */
		
		testCasConstructeur2EtGetLeTemps(16, 0, 5, 57605000, false);
		/* Renvoie "Reussi" (ça fonctionne car le temps en heure, minute, sec et le même que res) */
		
		System.out.println("\n*** Cas Limites : ");
		testCasConstructeur2EtGetLeTemps(0, 0, 0, 0, false);
		/* Renvoie "Reussi" (ça fonctionne car le temps en heure, minute, sec et le même que res) */
		
		System.out.println("\n*** Cas D'Erreur : ");
		testCasConstructeur2EtGetLeTemps(-100, 0, 45, 45, true);
		/* Renvoie "Echec" (ça fonctionne pas car un des heure, minute, seconde est negative) */
		
		separationTest();
	}

	/**
	* Methode de test qui permet de tester le constructeur2 et la methode 
	* getLeTemps()
	* 
	* @param heure Temps en heure
	* @param minute Temps en minute
	* @param seconde Temps en seconde
	* @param res Valeur attendu
	* @param casErreur Si le test est censé donné une erreur ou pas (true si erreur, false sinon) 
	* 
	**/
	void testCasConstructeur2EtGetLeTemps(int heure,int minute, int seconde, int res, boolean casErreur){
		Duree d2;
		
		if(!casErreur){
			d2 = new Duree(heure, minute, seconde);
			if(d2.getLeTemps() == res){
				System.out.println("Reussi");
			} else {
				System.out.println("Echec du Test");
			}
		} else {
			System.out.println("Message d'erreur attendu");
			d2 = new Duree(heure, minute, seconde);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	* Methode qui test qui envoie la valeur a tester dans testCasAjouter() 
	**/
	void testAjouter(){
		Duree d1;
		Duree d2;
		
		System.out.println(" ------ testAjouter ------ ");
		System.out.print("*** Cas Normaux : \n");
		d1 = new Duree(4000);
		d2 = new Duree(2000);
		testCasAjouter(d2, d1, 6000);
		/* Renvoie "Reussi" (ça fonctionne car l'addition de d1 et d2 donne res) */
		
		d1 = new Duree(10000);
		d2 = new Duree(10000);
		testCasAjouter(d2, d1, 20000);
		/* Renvoie "Reussi" (ça fonctionne car l'addition de d1 et d2 donne res) */
		
		System.out.println("\n*** Cas Limites : ");
		d1 = new Duree(0);
		d2 = new Duree(0);
		testCasAjouter(d2, d1, 0);
		/* Renvoie "Reussi" (ça fonctionne car l'addition de d1 et d2 donne res) */
		
		System.out.println("\n*** Cas D'Erreur : ");
		System.out.println("Il n'y en a pas");
		/* Pas de cas d'erreur car une addition de nombre positifs est toujours positifs */
		
		separationTest();
	}
	
	/**
	* Methode de test qui permet de tester la methode ajouter()
	* 
	* @param d2 Duree qui va s'ajouter a d1
	* @param d1 Duree qui va se faire ajouter d2
	* @param res Resultat attendu
	* 
	**/
	void testCasAjouter(Duree d2, Duree d1, int res){
		d1.ajouter(d2);
		if(d1.getLeTemps() == res){
			System.out.println("Reussi");
		} else {
			System.out.println("Echec");
		}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	*  Methode qui test qui envoie la valeur a tester dans testCasSoustraire() 
	**/
	void testSoustraire(){
		Duree d1;
		Duree d2;
		
		System.out.println(" ------ testSoustraire ------ ");
		System.out.print("*** Cas Normaux : \n");
		d1 = new Duree(10000);
		d2 = new Duree(5000);
		testCasSoustraire(d2, d1, 5000, false);
		/* Renvoie "Reussi" (ça fonctionne car la soustraction de d1 et d2 donne res) */
		
		d1 = new Duree(20000);
		d2 = new Duree(20000);
		testCasSoustraire(d2, d1, 0, false);
		/* Renvoie "Reussi" (ça fonctionne car la soustraction de d1 et d2 donne res) */
		
		System.out.println("\n*** Cas Limites : ");
		d1 = new Duree(0);
		d2 = new Duree(0);
		testCasSoustraire(d2, d1, 0, false);
		/* Renvoie "Reussi" (ça fonctionne car la soustraction de d1 et d2 donne res) */
		
		System.out.println("\n*** Cas D'Erreur : ");
		d1 = new Duree(5000);
		d2 = new Duree(10000);
		testCasSoustraire(d2, d1, 5000, true);
		/* Renvoie "Message d'erreur attendu" (ça fonctionne pas car la durée après soustraction devient négative ) */
		
		separationTest();
	
	}
	
	/**
	* Methode de test qui permet de tester la methode soustraire()
	* 
	* @param d2 Duree qui va être soustrait a d1
	* @param d1 Duree qui va se faire soustraire d2
	* @param res Resultat attendu
	* @param casErreur Si le test est censé donné une erreur ou pas (true si erreur, false sinon) 
	* 
	**/
	void testCasSoustraire(Duree d2, Duree d1, int res, boolean casErreur){
		
		if(!casErreur){
			d1.soustraire(d2);
			if(d1.getLeTemps() == res){
				System.out.println("Reussi");
			} else {
				System.out.println("Echec");
			}
		} else {
			System.out.println("Message d'erreur attendu");
			d1.soustraire(d2);
		}
	}
	
	
	
	
	
	
	
	
	/**
	* Methode de test qui envoie les valeurs a la methode testCasCompareA(); 
	*/
	void testCompareA(){
		Duree d1;
		Duree d2;
		
		System.out.println(" ------ testCompareA ------ ");
		System.out.print("*** Cas Normaux : \n");
		d1 = new Duree(100);
		d2 = new Duree(100);
		testCasCompareA(d2, d1, 0, false);
		/* Renvoie 'Reussi' (ça fonctionne car les deux valeurs sont égaux) */
		
		d1 = new Duree(200);
		d2 = new Duree(100);
		testCasCompareA(d2, d1, -1, false);
		/* Renvoie 'Reussi' (ça fonctionne car la methode renvoie -1 (d1 > d2)) */
		
		d1 = new Duree(100);
		d2 = new Duree(200);
		testCasCompareA(d2, d1, 1, false);
		/* Renvoie 'Reussi' (ça fonctionne car la methode renvoie 1 (d1 < d2)) */
		
		System.out.println("\n*** Cas Limites : ");
		/* Il n'y a pas de cas limites */
		
		System.out.println("\n*** Cas D'Erreur : ");
		d1 = null;
		d2 = new Duree(100);
		testCasCompareA(d2, d1, -2, true);
		/* Renvoie 'Message d'erreur' (ça fonctionne pas car il y a une valeur null) */
		
		separationTest();
	}
	
	/**
	* Methode de test de cas qui test different cas concernant la methode compareA();
	* Il recoit les donnees de la methode testCompareA();
	* 
	* @param d1 Duree a compare avec la deuxieme duree
	* @param d2 Duree a compare avce la premiere duree
	* @param res Resultat attendu
	* @param casErreur Si il y a un cas d'erreur (True si il y a, false sinon)
	*/
	void testCasCompareA(Duree d1, Duree d2, int res, boolean casErreur){
		if(!casErreur){
			if(d1.compareA(d2) == res){
				System.out.println("Reussi");
			} else {
				System.out.println("Echec");
			}
		} else {
			System.out.println("Message d'erreur attendu");
			d1.compareA(d2);
		}
	}








	/**
	* Methode de test qui envoie les valeurs a la methode testCasEnTexte(); 
	*/
	void testEnTexte(){
		Duree d1;
		Duree d2;
		
		System.out.println(" ------ testSoustraire ------ ");
		System.out.print("*** Cas Normaux : \n");
		d1 = new Duree(25000);
		testCasEnTexte(d1, 'M', "25000 millisec", false);
		/* Renvoie "Reussi" (ça fonctionne car la methode enTexte envoie "25000 millisec") */

		d1 = new Duree(999999999);
		testCasEnTexte(d1, 'J', "11 jours 13 h", false);
		/* Renvoie "Reussi" (ça fonctionne car la methode enTexte envoie "11 jours 13 h") */

		d1 = new Duree(36870);
		testCasEnTexte(d1, 'S', "36 sec", false);
		/* Renvoie "Reussi" (ça fonctionne car la methode enTexte envoie "36 sec") */

		d1 = new Duree(87542121);
		testCasEnTexte(d1, 'H', "24:19:02", false);
		/* Renvoie "Reussi" (ça fonctionne car la methode enTexte envoie "24:19:02") */

		System.out.println("\n*** Cas Limites : ");

		System.out.println("\n*** Cas D'Erreur : ");
		d1 = new Duree(25000);
		testCasEnTexte(d1, 'Y', "", true);
		/* Renvoie "Message Erreur" (ça fonctionne pas car la methode enTexte envoie ne gère pas le cas avec la lettre 'Y' comme paramatère) */

	}

	/**
	* Methode de test qui verifie les differents cas qui lui sont envoyer par testEnTexte();
	* @param d1 Duree a transformer en caractere
	* @param mode Type de chaine de caractere qu'on veut obtenir (H, J, M ou S)
	* @param res Le resultat attendu
	* @param casErreur Si il y a un cas d'erreur (True si il y a, false sinon) 
	*/
	void testCasEnTexte(Duree d1, char mode, String res ,boolean casErreur){
		if(!casErreur){
			if(d1.enTexte(mode).equals(res)){
				System.out.println("Reussi");
			} else {
				System.out.println("Echec");
			}
		} else {
			System.out.println("Message d'erreur attendu !");
			System.out.println(d1.enTexte(mode));
		}
	}
}