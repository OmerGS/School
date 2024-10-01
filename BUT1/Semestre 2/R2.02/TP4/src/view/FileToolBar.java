package view;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.ToolBar;
import controller.Controller;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;

/**
 * The FileToolBar class represents a toolbar for file-related actions.
 * It contains buttons for various file operations and provides a file chooser.
 */
public class FileToolBar extends ToolBar {
    // Buttons for different file operations
    private Button btnLoadText;
    private Button btnSaveText;
    private Button btnLoadBinary;
    private Button btnSaveBinary;
    private Button btnLoadObject;
    private Button btnSaveObject;

    // File chooser for selecting files
    private FileChooser chooser;

    /**
     * Constructor for FileToolBar.
     * @param controller The controller responsible for handling button actions.
     */
    public FileToolBar(Controller controller) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        HBox.setHgrow(hbox, Priority.ALWAYS);

        // Initialize buttons and set their action event handlers
        chooser = new FileChooser();
        btnLoadText = new Button("Charger en texte");
        btnLoadText.setOnAction(controller);
        btnSaveText = new Button("Sauvegarder en texte");
        btnSaveText.setOnAction(controller);
        btnLoadBinary = new Button("Charger en binaire");
        btnLoadBinary.setOnAction(controller);
        btnSaveBinary = new Button("Sauvegarder en binaire");
        btnSaveBinary.setOnAction(controller);
        btnLoadObject = new Button("Charger en objets");
        btnLoadObject.setOnAction(controller);
        btnSaveObject = new Button("Sauvegarder en objets");
        btnSaveObject.setOnAction(controller);

        // Add buttons to the hbox
        hbox.getChildren().addAll(btnLoadText, btnSaveText, btnLoadBinary, btnSaveBinary, btnLoadObject, btnSaveObject);
        
        // Add the hbox to the FileToolBar
        this.getItems().add(hbox);
    }

    /**
     * Getter for the FileChooser instance.
     * @return The FileChooser instance.
     */
    public FileChooser getChooser() {
        return chooser;
    }

    // Other getters for the buttons 

    public Button getBtnLoadText() {
        return btnLoadText;
    }

    public Button getBtnSaveText() {
        return btnSaveText;
    }

    public Button getBtnLoadBinary() {
        return btnLoadBinary;
    }

    public Button getBtnSaveBinary() {
        return btnSaveBinary;
    }

    public Button getBtnLoadObject() {
        return btnLoadObject;
    }

    public Button getBtnSaveObject() {
        return btnSaveObject;
    }
}
