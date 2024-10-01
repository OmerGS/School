import javax.swing.*;
import java.awt.*;

public class CharacterSheetToolBar extends JToolBar {

    // Les boutons que l'on veut utiliser :
    // nouveau, charger, enregistrer, enregistrer sous
    private JButton nouveau;
    private JButton charger;
    private JButton enregistrer;
    private JButton enregistrerSous;

    private CharacterSheetListener characterSheetListener; // Listener

    public CharacterSheetToolBar(CharacterSheetListener listener) {
        this.characterSheetListener = listener; // Initialize the listener
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(0, 1)); // Single column layout

        nouveau = new JButton("Nouveau");
        charger = new JButton("Charger");
        enregistrer = new JButton("Enregistrer");
        enregistrerSous = new JButton("Enregistrer Sous");

        // Register ActionListener for each button
        nouveau.addActionListener(characterSheetListener);
        charger.addActionListener(characterSheetListener);
        enregistrer.addActionListener(characterSheetListener);
        enregistrerSous.addActionListener(characterSheetListener);

        add(Box.createVerticalGlue()); // Push buttons to top
        add(nouveau);
        add(charger);
        add(enregistrer);
        add(enregistrerSous);
        add(Box.createVerticalGlue()); // Push buttons to bottom
    }
}
