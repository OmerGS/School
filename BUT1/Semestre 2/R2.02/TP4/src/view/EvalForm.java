package view;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import controller.Controller;
import javafx.geometry.*;

/**
 * The EvalForm class represents a form for entering evaluation details.
 */
public class EvalForm extends GridPane {

    // Private attributes
    private Button addBtn;
    private Button cancelBtn;
    private TextField scoreTF;
    private TextField coeffTF;

    /**
     * Constructs an instance of EvalForm with the given controller.
     * 
     * @param controller The controller to handle button actions.
     */
    public EvalForm(Controller controller) {
        this.setAlignment(Pos.CENTER);
        this.setHgap(5.5);
        this.setVgap(5.5);

        // TextFields for score and coefficient
        scoreTF = new TextField();
        this.add(new Label("Note:"), 0, 0);
        this.add(scoreTF, 1, 0);
        coeffTF = new TextField();
        this.add(new Label("Coefficient:"), 0, 1);
        this.add(coeffTF, 1, 1);

        // Buttons for adding and canceling
        addBtn = new Button("Ajouter");
        addBtn.setOnAction(controller);
        this.add(addBtn, 1, 3);
        cancelBtn = new Button("Annuler");
        this.add(cancelBtn, 0, 3);
        cancelBtn.setOnAction(controller);

        // Set alignment for addBtn to right
        GridPane.setHalignment(addBtn, HPos.RIGHT);

        // TextFormatters for score and coefficient validation
        scoreTF.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("([0-1]?[0-9]?|20)(\\.[0-9]*)?")) {
                return change;
            }
            return null;
        }));
        coeffTF.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("0*([1-9]?|10)")) {
                return change;
            }
            return null;
        }));
    }

    // Getter methods
    public Button getAddBtn() {
        return addBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public TextField getScoreTF() {
        return scoreTF;
    }

    public TextField getCoeffTF() {
        return coeffTF;
    }
}
