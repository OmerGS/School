import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CharacterSheetListener implements ActionListener, ChangeListener {

    private CharacterSheet characterSheet;
    private CharacterSheetModel model;

    public CharacterSheetListener(CharacterSheet characterSheet, CharacterSheetModel model) {
        this.characterSheet = characterSheet;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        // Handle actions for buttons in the CharacterSheetToolBar
        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            String buttonText = sourceButton.getText();
            
            if(buttonText.equals("Enregistrer") || buttonText.equals("Enregistrer Sous")){
                if(characterSheet.getCharacterSheetStats().getPointTotal() < 0 ){
                    characterSheet.getCharacterSheetStateBar().updateStatus("Vous avez depasse le nombre de points disponible !");
                } else if(characterSheet.getCharacterSheetStats().getPointTotal() > 0){
                    characterSheet.getCharacterSheetStateBar().updateStatus("Vous n'avez pas utilisez tout vos points !");
                } else {
                    characterSheet.getCharacterSheetStateBar().updateStatus(buttonText);
                    model.addPersonnage(characterSheet.getCharacterSheetStats());
                    model.getPersonnage(0).getName();
                }
            } else {
                characterSheet.getCharacterSheetStateBar().updateStatus(buttonText);
            }

        }
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            int newValue = ((Double) spinner.getValue()).intValue();


            if (spinner == characterSheet.getCharacterSheetStats().getJSpinnerForce()) {
                model.setStrength(newValue);
            } else if (spinner == characterSheet.getCharacterSheetStats().getJSpinnerDexterite()) {
                model.setDexterity(newValue);
            } else if (spinner == characterSheet.getCharacterSheetStats().getJSpinnerConstitution()) {
                model.setConstitution(newValue);
            } else if (spinner == characterSheet.getCharacterSheetStats().getJSpinnerIntelligence()) {
                model.setIntelligence(newValue);
            } else if (spinner == characterSheet.getCharacterSheetStats().getJSpinnerSagesse()) {
                model.setWisdom(newValue);
            } else if (spinner == characterSheet.getCharacterSheetStats().getJSpinnerCharisme()) {
                model.setCharisma(newValue);
            }

            totalPointUpdate();
        }
    }

    private void totalPointUpdate(){
        int strength = model.getStrength();
        int dexterity = model.getDexterity();
        int constitution = model.getConstitution();
        int intelligence = model.getIntelligence();
        int wisdom = model.getWisdom();
        int charisma = model.getCharisma();

        int allPoints = strength + dexterity + constitution + intelligence + wisdom + charisma;
        int reelPoint = allPoints - 48;
        int totalPoint = 27 - reelPoint;

        characterSheet.getCharacterSheetStats().updateStatus(totalPoint);   
    }
}