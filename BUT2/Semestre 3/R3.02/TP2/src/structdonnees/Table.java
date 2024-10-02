package structdonnees;

import java.rmi.NotBoundException;

public interface Table {
	public Object obtenir(String cle);
	public boolean inserer(String cle, Object donnee) throws NotBoundException;
	public boolean supprimer(String cle) throws Exception;
}
