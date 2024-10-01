package pays;

/**
* Class allow to create country with 3 parameters 
*
* @author O.Gunes
*/
public class Pays implements Comparable<Pays>{
    
    /**
    *  Name of country
    */
    private String nom;

    /**
     * Area of country
     */
    private double surface;

    /**
     * Population of country
     */
    private double population;

    /**
     * Empty Constructor
     */
    public Pays(){
        this.nom = "";
        this.population = 0;
        this.surface = 0;
    }

    /**
     * Constructor of Pays
     * 
     * @param nom - Name of country
     * @param population - Population of country
     * @param surface - Area of country
     */
    public Pays(String nom, double population, double surface){
        if(nom != null){
            this.nom = nom;
        } else {
            System.out.println("Pays Constructeur : Nom Null");
        }

        if(population > 0){
            this.population = population;
        } else {
            System.out.println("Pays Constructeur : La population doit être superieur a 0");
        }

        if(surface > 0){
            this.surface = surface;
        } else {
            System.out.println("Pays Constructeur : Surface Doit etre positive");
        }
    }


    /**
     * Allow to get name of country
     * @return Name of country
     */
    public String getNom(){
        return(this.nom);
    }

    /**
     * Allow to get Surface
     * @return Surface of country
     */
    public double getSurface(){
        return(this.surface);
    }

    /**
    * Allow to set area of country 
    * @param surface area of country
    */
    public void setSurface(double surface){
        if(surface > 0){
            this.surface = surface;
        } else {
            System.out.println("setSurface : La surface doit etre positive");
        }
    }

    /**
     * Allow to get population
     * @return population of country
     */
    public double getPopulation(){
        return(this.population);
    }

    /**
     * Allow to set population of country
     * @param population Population of country
     */
    public void setPopulation(double population){
        if(population > 0){
            this.population = population;
        } else {
            System.out.println("setPopulation : doit etre superieur a 0");
        }
    }

    /**
     * Allow to had all information about country
     * @return String of information about country
     */
    public String toString(){
        String aEnvoye = "\n\nNom : " + this.nom + "\nPopulation : " + this.population + "\nSurface : " + this.surface;        
        return(aEnvoye);
    }

    /**
     * Si this > pays : 1
     * Si this < pays : -1
     * Si egaux : 0
     * @param o Another country
     * @return int indicate the comparator.
     */
    public int compareTo(Pays o) {
        int valeurARetourner = 0;

        if(o.surface > this.surface){
            valeurARetourner = -1;
        } else if(o.surface < this.surface){
            valeurARetourner = 1;
        } else if(o.surface == this.surface){
            valeurARetourner = 0;
        }

        return(valeurARetourner);        
    }
}
