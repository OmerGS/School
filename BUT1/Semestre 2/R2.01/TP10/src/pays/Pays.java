package pays;

public class Pays implements Comparable<Pays> {
    private String nom;
    private double surface;
    private double population;

    public Pays() {
        this.nom = "";
        this.population = 0;
        this.surface = 0;
    }

    public Pays(String nom, double population, double surface) {
        if (nom != null) {
            this.nom = nom;
        } else {
            System.out.println("Pays Constructeur : Nom Null");
        }

        if (population > 0) {
            this.population = population;
        } else {
            System.out.println("Pays Constructeur : La population doit être supérieure à 0");
        }

        if (surface > 0) {
            this.surface = surface;
        } else {
            System.out.println("Pays Constructeur : Surface Doit être positive");
        }
    }

    public String getNom() {
        return this.nom;
    }

    public double getSurface() {
        return this.surface;
    }

    public void setSurface(double surface) {
        if (surface > 0) {
            this.surface = surface;
        } else {
            System.out.println("setSurface : La surface doit être positive");
        }
    }

    public double getPopulation() {
        return this.population;
    }

    public void setPopulation(double population) {
        if (population > 0) {
            this.population = population;
        } else {
            System.out.println("setPopulation : doit être supérieure à 0");
        }
    }

    @Override
    public String toString() {
        return "\n\nNom : " + this.nom + "\nPopulation : " + this.population + "\nSurface : " + this.surface;
    }

    @Override
    public int compareTo(Pays o) {
        return this.nom.compareTo(o.nom);
    }
}
