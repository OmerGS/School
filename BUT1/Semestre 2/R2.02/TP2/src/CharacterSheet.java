import java.awt.BorderLayout;
import javax.swing.*;

public class CharacterSheet extends JFrame {

    private JPanel panel;

    private CharacterSheetStateBar characterSheetStateBar;
    private CharacterSheetStats characterSheetStats;
    private CharacterSheetToolBar characterSheetToolBar;
    private CharacterSheetListener characterSheetListener;

    public CharacterSheet() {
        this.initComponents();
    }

    private void initComponents() {
        characterSheetListener = new CharacterSheetListener(this, new CharacterSheetModel());

        characterSheetToolBar = new CharacterSheetToolBar(characterSheetListener);
        characterSheetStats = new CharacterSheetStats(characterSheetListener);
        characterSheetStateBar = new CharacterSheetStateBar("0.0.1");

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(characterSheetToolBar, BorderLayout.WEST);
        panel.add(characterSheetStats, BorderLayout.CENTER);
        panel.add(characterSheetStateBar, BorderLayout.SOUTH);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public CharacterSheetStateBar getCharacterSheetStateBar() {
        return characterSheetStateBar;
    }

    public CharacterSheetStats getCharacterSheetStats() {
        return characterSheetStats;
    }

    public CharacterSheetToolBar getCharacterSheetToolBar() {
        return characterSheetToolBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CharacterSheet characterSheet = new CharacterSheet();
                characterSheet.setVisible(true);
            }
        });
    }
}