public class Voiture{
    String marque;
    String modele;
    int puissance;

    public Voiture(){
        this.marque = "";
        this.modele = "";
        this.puissance = 0;
    }

    public Voiture(String marque, String modele, int puissance){
        if(marque != null){
            this.marque = marque;
        } else {
            System.out.println("Voiture_Constructeur : Marque est null");
        } 

        if(modele != null){
            this.modele = modele;
        } else {
            System.out.println("Voiture_Constructeur : Modele est null");
        }

        this.puissance = puissance;

    }


    public String toString(){
        String valueARetourner = "Marque : " + this.marque + "\nModele : " + this.modele + "\nPuissance : " + this.puissance;

        return(valueARetourner);
    }

}