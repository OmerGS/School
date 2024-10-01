import javax.swing.*;
import java.awt.*;

public class CharacterSheetStateBar extends JPanel {
    private JLabel versionLabel;
    private JLabel statusLabel;

    public CharacterSheetStateBar(String version) {
        initComponents(version);
    }

    private void initComponents(String version) {
        setLayout(new BorderLayout());

        // Create status label and add it to the left side
        statusLabel = new JLabel("<STATUS>");
        add(statusLabel, BorderLayout.WEST);

        // Create version label and add it to the right side
        versionLabel = new JLabel(version, SwingConstants.RIGHT);
        add(versionLabel, BorderLayout.EAST);
    }

    // Method to update the status label text
    public void updateStatus(String text) {
        statusLabel.setText(text);
    }
}
