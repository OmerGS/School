import java.lang.Math;

public class Operations{
    public int additionne(int premierNombre, int deuxiemeNombre){
        int resultat = premierNombre + deuxiemeNombre;

        return(resultat);
    }


    public double calculeRacineCarree(double val){
        double racine = Math.sqrt(val);

        if(Double.isNaN(racine) == true){
            throw new ArithmeticException("La valeur entrée est négative");
        };

        return racine;
    }
}