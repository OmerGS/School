import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;

enum Races {
    Elfe, Hobbit, Homme, Nain
}

enum Classes {
    Barde, Mage, Paladin, Ranger
}

public class CharacterSheetModel {

    private String characterName;
    private String characterGender;
    private Races characterRace;
    private Classes characterClass;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private ArrayList<CharacterSheetStats> personnage;

    private int availablePoints;

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public CharacterSheetModel() {
        this.initValues();
    }

    private void initValues() {
        this.characterName = "";
        this.characterGender = "";
        this.characterRace = null;
        this.characterClass = null;

        this.personnage = new ArrayList<CharacterSheetStats>();

        this.strength = 8;
        this.dexterity = 8;
        this.constitution = 8;
        this.intelligence = 8;
        this.wisdom = 8;
        this.charisma = 8;
    }

    // Getters and setters for character attributes

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterGender() {
        return characterGender;
    }

    public void setCharacterGender(String characterGender) {
        this.characterGender = characterGender;
    }

    public Races getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(Races characterRace) {
        this.characterRace = characterRace;
    }

    public Classes getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Classes characterClass) {
        this.characterClass = characterClass;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        int oldValue = this.strength;
        this.strength = strength;
        changeSupport.firePropertyChange("strength", oldValue, strength);
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        int oldValue = this.dexterity;
        this.dexterity = dexterity;
        changeSupport.firePropertyChange("dexterity", oldValue, dexterity);
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        int oldValue = this.constitution;
        this.constitution = constitution;
        changeSupport.firePropertyChange("constitution", oldValue, constitution);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        int oldValue = this.intelligence;
        this.intelligence = intelligence;
        changeSupport.firePropertyChange("intelligence", oldValue, intelligence);
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        int oldValue = this.wisdom;
        this.wisdom = wisdom;
        changeSupport.firePropertyChange("wisdom", oldValue, wisdom);
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        int oldValue = this.charisma;
        this.charisma = charisma;
        changeSupport.firePropertyChange("charisma", oldValue, charisma);
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public void addPersonnage(CharacterSheetStats personnage){
        this.personnage.add(personnage);
    }

    public CharacterSheetStats getPersonnage(int index){
        CharacterSheetStats persoARetourner = null;
        
        if(index > this.personnage.size()){
            System.out.println("Pas de personnage");
        } else {
            this.personnage.get(index);
        }
        
        return(persoARetourner);
    }
}