package pays;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Population class represents a collection of countries with their populations and areas.
 * @author R.PÃ©ron
 */
public class Population {

    /**
     * List of countries with their populations and areas.
     */
    private ArrayList<Pays> listePays;

    /**
     * Map of populations per country.
     */
    private HashMap<String, Double> popMap;

    /**
     * Map of areas per country.
     */
    private HashMap<String, Double> areaMap;

    /**
     * Constructor for the Population class.
     *
     * @param popArray List of populations per country
     * @param areaArray List of areas per country
     */
    public Population(ArrayList<String> popArray, ArrayList<String> areaArray){
        if(popArray != null && areaArray != null){
            this.popMap = asMap(popArray);
            this.areaMap = asMap(areaArray);
            this.listePays = new ArrayList<>();
            this.initializeListePays();
        } else {
            System.out.println("Population: invalid parameter");
        }
    }

    /**
     * Extracts the country name from a given line.
     *
     * @param line Line containing the country name
     * @return Country name
     */
    private String extractCountry(String line) {
        String ret = null;
        int i = 0;
        if (line != null) {
            while (i < line.length() && !Character.isDigit(line.charAt(i))) {
                i++;
            }
            ret = line.substring(0, i).trim();
        } else {
            System.out.println("extractCountry: invalid parameter");
        }
        return ret;
    }

    /**
     * Extracts the numerical value from a given line.
     *
     * @param line Line containing the numerical value
     * @return Numerical value
     */
    private double extractValue(String line) {
        double ret = -1;
        int i = 0;
        if (line != null) {
            while (i < line.length() && !Character.isDigit(line.charAt(i))) {
                i++;
            }
            ret = Double.parseDouble(line.substring(i).trim());
        } else {
            System.out.println("extractValue: invalid parameter");
        }
        return ret;
    }

    /**
     * Returns the map of populations per country.
     *
     * @return Map of populations per country
     */
    public HashMap<String, Double> getPopMap() {
        return this.popMap;
    }

    /**
     * Returns the map of areas per country.
     *
     * @return Map of areas per country
     */
    public HashMap<String, Double> getAreaMap() {
        return this.areaMap;
    }

    /**
     * Returns the list of countries.
     *
     * @return List of countries
     */
    public ArrayList<Pays> getListePays() {
        return this.listePays;
    }

    /**
     * Converts a list of strings into a map of strings and double values.
     *
     * @param liste List of strings to convert
     * @return Map of strings and double values
     */
    private HashMap<String,Double> asMap(ArrayList<String> liste){
        HashMap<String,Double> ret = new HashMap<String, Double>();
        if(liste != null){
            for (String string : liste) {
                ret.put(extractCountry(string), extractValue(string));
            }
        } else {
            System.out.println("asMap: invalid parameter");
        }
        return ret;
    }

    /**
     * Initializes the map of populations per country from a list of strings.
     * 
     * @param popArray List of populations per country
     */
    private void initializePopMap(ArrayList<String> popArray){
        if(popArray != null){
            this.popMap = asMap(popArray);
        } else {
            System.out.println("initializePopMap: invalid parameter");
        }
    }

    /**
     * Initializes the map of areas per country from a list of strings.
     * 
     * @param areaArray List of areas per country
     */
    private void initializeAreaMap(ArrayList<String> areaArray){
        if(areaArray != null){
            this.areaMap = asMap(areaArray);
        } else {
            System.out.println("initializeAreaMap: invalid parameter");
        }
    }

    /**
     * Initializes the list of countries using the maps of populations and areas.
     */
    private void initializeListePays() {
        if (this.getAreaMap().size() == this.getPopMap().size()) {
            for (String i : this.getAreaMap().keySet()) {
                this.listePays.add(new Pays(i, this.getPopMap().get(i), this.getAreaMap().get(i)));
            }
        }
    }
}