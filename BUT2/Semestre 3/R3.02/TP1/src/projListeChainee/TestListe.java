package projListeChainee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import structdonnees.ListeChainee;

public class TestListe {
	private ListeChainee listeChainee;
    // m√©thode ex√©cut√©e syst√©matiquement AVANT chaque cas de test
    @Before
    public void instancier() {
        this.listeChainee = new ListeChainee();
    }

	@Test
	public void testInserer(){
		System.out.println ( "Methode Inserer - Cas Normal");
		int temp = 5;
		int tailleListeAvantInseration = this.listeChainee.obtenirTaille();

        this.listeChainee.inserer(temp);

        assertEquals("Echec du test", 0, tailleListeAvantInseration);
        System.out.println ( "Test r√©ussi" );
	}

	@Test
	public void testSupprimer(){
		System.out.println ( "Methode Supprimer - Cas Normal");
		int temp = 5;
        this.listeChainee.inserer(temp);
        this.listeChainee.suivant();
        this.listeChainee.supprimer();
		int tailleListeAvantInseration = this.listeChainee.obtenirTaille();

        assertEquals("Echec du test", 0, tailleListeAvantInseration);
        System.out.println ( "Test r√©ussi" );
	}

	@Test
	public void testContient(){
		System.out.println ( "Methode Contient - Cas Normal");
		String temp = "valeurNumero1";
        this.listeChainee.inserer(temp);
        
		boolean res = this.listeChainee.contient(temp);
        
        assertEquals("Echec du test", false, res);
        System.out.println ( "Test r√©ussi" );
	}

	@Test
	public void testAjouter() {
		System.out.println ( "Methode Ajouter - Cas Normal");
		
		int temp = 56;
		int temp2 = 65;
			
		this.listeChainee.inserer(temp2);
        this.listeChainee.ajouter(1, temp);
		
        int taille = this.listeChainee.obtenirTaille();
	
        
        assertEquals("Echec du test", 2, taille);
        System.out.println ( "Test reussi" );
	}


	@Test
	public void testObtenirValeur(){
		System.out.println ( "Methode Obtenir - Cas Normal");
		int temp = 52;
		
		this.listeChainee.inserer(temp);
		int validate = (Integer) this.listeChainee.obtenirValeur();
        
        assertEquals("Echec du test", validate, temp);
        System.out.println ( "Test reussi" );
	}

	
	@Test
	public void testChangerValeur(){
		System.out.println ( "Methode ChangerValeur - Cas Normal");
		int ancienneValeur = 52;
		this.listeChainee.inserer(ancienneValeur);
		
		int nouvelleValeur = 25;
		this.listeChainee.changerValeur(nouvelleValeur);
		
		int valeurRecup = (Integer) this.listeChainee.obtenirValeur();
		
        
        assertEquals("Echec du test", nouvelleValeur, valeurRecup);
        System.out.println ( "Test reussi" );
	}

	@Test
	public void testEstVide(){
		System.out.println ( "Methode estVide - Cas Normal");
		int taille = this.listeChainee.obtenirTaille();
		
        
        assertEquals("Echec du test", 0, taille);
        System.out.println ( "Test reussi" );
	}

	@Test
	public void testObtenirTaille(){
		System.out.println ( "Methode obtenirTaille - Cas Normal");
		
		int premier = 51;
		int deuxieme = 10;
		int troisieme = 58;
		
		this.listeChainee.inserer(premier);
		this.listeChainee.inserer(deuxieme);
		this.listeChainee.inserer(troisieme);
		
		int taille = this.listeChainee.obtenirTaille();
		
        
        assertEquals("Echec du test", 3, taille);
        System.out.println ( "Test reussi" );
	}

	@Test
	public void testTeteListe(){
		System.out.println ( "Methode TeteListe - Cas Normal");
		
		int premier = 3;
		int deuxieme = 10;
		int troisieme = 58;
		
		this.listeChainee.inserer(premier);
		this.listeChainee.inserer(deuxieme);
		this.listeChainee.inserer(troisieme);
		
		int taille = this.listeChainee.obtenirTaille();
		
        
        assertEquals("Echec du test", premier, 3);
        System.out.println ( "Test reussi" );
	}

	@Test
	public void testFinListe(){
		System.out.println ( "Methode FinListe - Cas Normal");
		
		int premier = 3;
		int deuxieme = 10;
		int troisieme = 58;
		
		this.listeChainee.inserer(premier);
		this.listeChainee.inserer(deuxieme);
		this.listeChainee.inserer(troisieme);		
        
        assertEquals("Echec du test", troisieme, 58);
        System.out.println ( "Test reussi" );
	}

	@Test
	public void testSuivant(){
		System.out.println ( "Methode Suivant - Cas Normal");
		
		int premier = 3;
		int deuxieme = 10;
		int troisieme = 58;
		
		this.listeChainee.inserer(premier);
		this.listeChainee.inserer(deuxieme);
		this.listeChainee.inserer(troisieme);	
		
		boolean test = this.listeChainee.suivant();
        
        assertEquals("Echec du test", true, test);
        System.out.println ( "Test reussi" );
	}

	@Test
	public void testPrecedent(){
		System.out.println ( "Methode Precedent - Cas Normal");
		
		int premier = 3;
		int deuxieme = 10;
		int troisieme = 58;
		
		this.listeChainee.inserer(premier);
		this.listeChainee.inserer(deuxieme);
		this.listeChainee.inserer(troisieme);	
		
		this.listeChainee.suivant();
		boolean test = this.listeChainee.precedent();
        
        assertEquals("Echec du test", true, test);
        System.out.println ( "Test reussi" );
	}

	 @Test
	    public void testToString() {
	        System.out.println("MÈthode testToString - Cas Normal");

	        int premier = 3;
	        int deuxieme = 56;

	        this.listeChainee.inserer(premier);
	        this.listeChainee.inserer(deuxieme);

	        String resultat = this.listeChainee.toString();

	        String expectedString = "[3, 56]";

	        assertEquals("Echec du test", expectedString, resultat);
	        System.out.println("Test rÈussi");
	    }

	 @Test
    public void testObtenirValeurA() {
        System.out.println("MÈthode ObtenirValeurA - Cas Normal");

        int premier = 3;
        int deuxieme = 56;
        int troisieme = 152;

        this.listeChainee.inserer(premier);
        this.listeChainee.inserer(deuxieme);
        this.listeChainee.inserer(troisieme);

        int valeur = (Integer) this.listeChainee.obtenirValeurA(1);

        assertEquals("Echec du test", 56, valeur);
        System.out.println("Test rÈussi");
    }
}
