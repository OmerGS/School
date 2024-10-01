package view;
import javafx.scene.layout.BorderPane;
import model.Student;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EvalScene extends BorderPane {

   // Attributes
   private EvalForm evalForm; // Evaluation form
   private EvalTable evalTable; // Evaluation table

   private Button backButton; // Button to navigate back

   private Label studentNameLabel; // Label to display student name

   private Student student; // Associated student

   // Constructor
   public EvalScene(Controller controller, Student student) {
      // Initialize attributes
      this.student = student;
      this.evalTable = new EvalTable(controller, this.student);
      this.evalForm = new EvalForm(controller);
      this.backButton = new Button("Retour");
      this.studentNameLabel = new Label(student.getFirstName() + " " + student.getName());
      
      // Set margins and alignment for UI elements
      BorderPane.setMargin(evalTable, new Insets(50, 50, 50, 50));
      BorderPane.setMargin(evalForm, new Insets(50, 50, 50, 50));
      BorderPane.setMargin(backButton, new Insets(50, 50, 50, 50));
      BorderPane.setMargin(studentNameLabel, new Insets(50, 50, 50, 50));
      BorderPane.setAlignment(studentNameLabel, javafx.geometry.Pos.CENTER);

      // Set layout
      this.setCenter(evalTable);
      this.setRight(evalForm);
      this.setBottom(backButton);
      this.setTop(studentNameLabel);

      // Set event handler for back button
      backButton.setOnAction(controller);
   }

   // Getter methods
   public EvalTable getEvalTable() {
      return evalTable;
   }

   public EvalForm getEvalForm() {
      return evalForm;
   }

   public Student getStudent() {
      return student;
   }

   public Button getBackButton() {
      return backButton;
   }
}
