import javax.management.RuntimeErrorException;

public class Parking {
    public final int NB_PLACES;
    Voiture[] lesPlaces;

    public Parking(int nb_place){
        this.NB_PLACES = nb_place+1;

        lesPlaces = new Voiture[this.NB_PLACES];
    }

    public String toString(){
        String valeurAEnvoye = "";
        for(int i = 1; i < this.lesPlaces.length; i++){
            valeurAEnvoye += "\nPlace Numero " + i + "\n";
            if(this.lesPlaces[i] == null){
                valeurAEnvoye += "Place est libre !";
            } else {
                valeurAEnvoye += this.lesPlaces[i].toString();
            }
            valeurAEnvoye += "\n";
        }

        return(valeurAEnvoye);
    }

    
    private void numeroValide(int numPlace) throws ArrayIndexOutOfBoundsException{
        if(numPlace < 1 && numPlace > this.NB_PLACES){
            throw new ArrayIndexOutOfBoundsException("Numero de place non valide");
        }
    }

    public void garer(Voiture voit, int numPlace) throws RuntimeException{
        numeroValide(numPlace);
        if(this.lesPlaces[numPlace] != null){
            throw new RuntimeErrorException(null, "Place deja occupe");
        } else {
            this.lesPlaces[numPlace] = voit;
        }
    }

    public Voiture sortir(int numPlace) throws RuntimeException{
        Voiture voiture = null;

        numeroValide(numPlace);
        if(this.lesPlaces[numPlace] == null){
            throw new RuntimeErrorException(null, "Pas de voiture a cette place");
        } else {
            voiture = this.lesPlaces[numPlace];
            System.out.println(voiture.toString());
            this.lesPlaces[numPlace] = null;
        }

        return(voiture);
    }

}
