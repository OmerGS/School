package structdonnees;

public interface Liste {
	public void inserer(Object data);
    public boolean supprimer();
    public boolean contient(Object data);
    public void ajouter(int index, Object data);
    public Object obtenirValeur();
    public void changerValeur(Object newData);
    public boolean estVide();
    public int obtenirTaille();
}
