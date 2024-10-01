package view;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import controller.Controller;
import javafx.geometry.*;

/**
 * The StudentForm class represents a form for entering student information.
 */
public class StudentForm extends GridPane {

    private Button addBtn;
    private Button cancelBtn;
    private TextField nameTF;
    private TextField firstNameTF;

    /**
     * Constructor for the StudentForm class.
     * 
     * @param controller The controller to handle button actions.
     */
    public StudentForm(Controller controller) {
        // Set alignment and gaps for the grid pane
        this.setAlignment(Pos.CENTER);
        this.setHgap(5.5);
        this.setVgap(5.5);

        // Initialize text fields for name and first name
        nameTF = new TextField();
        this.add(new Label("Nom:"), 0, 0);
        this.add(nameTF, 1, 0);

        firstNameTF = new TextField();
        this.add(new Label("Prénom:"), 0, 1);
        this.add(firstNameTF, 1, 1);

        // Initialize buttons for adding and canceling
        addBtn = new Button("Ajouter");
        addBtn.setOnAction(controller);
        this.add(addBtn, 1, 3);

        cancelBtn = new Button("Annuler");
        this.add(cancelBtn, 0, 3);
        cancelBtn.setOnAction(controller);

        // Align the add button to the right
        GridPane.setHalignment(addBtn, HPos.RIGHT);
    }

    // Getter methods for buttons and text fields
    public Button getAddBtn() {
        return addBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public TextField getNameTF() {
        return nameTF;
    }

    public void setNameTF(TextField nameTF) {
        this.nameTF = nameTF;
    }

    public TextField getFirstNameTF() {
        return firstNameTF;
    }

    public void setFirstNameTF(TextField firstNameTF) {
        this.firstNameTF = firstNameTF;
    }
}
