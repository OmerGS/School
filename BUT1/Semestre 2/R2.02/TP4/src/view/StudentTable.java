package view;
import javafx.geometry.*;
import javafx.scene.layout.FlowPane;
import model.Student;
import javafx.scene.control.*;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import controller.Controller;
import controller.StudentFileAccess;
import javafx.collections.FXCollections; // Import for creating observable lists.
import javafx.collections.ObservableList; // Import for working with JavaFX observable lists.
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * The StudentTable class represents a TableView of students with editable cells.
 */
public class StudentTable extends TableView<Student> {
    private StudentFileAccess studentFileAccess; // Access to student file operations
    private Controller controller; // Reference to the controller for event handling

    /**
     * Constructor to initialize the StudentTable with data and event handling.
     * @param studentFileAccess The access object for student file operations.
     * @param controller The controller object for event handling.
     */
    public StudentTable(StudentFileAccess studentFileAccess, Controller controller) {
        this.controller = controller;
        ObservableList<Student> data = FXCollections.observableArrayList(studentFileAccess.getStudents());
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);

        // Creating columns
        TableColumn<Student, String> nameCol = new TableColumn<>("Nom");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            if (!t.getNewValue().equals(""))
                controller.updateName(t.getTablePosition().getRow(), t.getNewValue());
        });
        nameCol.setMinWidth(150);

        TableColumn<Student, String> firstNameCol = new TableColumn<>("Prénom");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            if (!t.getNewValue().equals(""))
                controller.updateFirstName(t.getTablePosition().getRow(), t.getNewValue());
        });
        firstNameCol.setMinWidth(150);

        TableColumn<Student, Float> avgCol = new TableColumn<>("Moyenne");
        avgCol.setCellValueFactory(new PropertyValueFactory<>("average"));
        avgCol.setMinWidth(150);

        TableColumn<Student, String> otherCol = new TableColumn<>("Actions");
        otherCol.setCellFactory(i -> new TableCell<Student, String>() {
            Button evalButton = new Button("Notes");
            Button deleteButton = new Button("Supprimer");
            FlowPane pane = new FlowPane(1, 0, evalButton, deleteButton);

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    pane.setAlignment(Pos.CENTER);
                    deleteButton.setId("delStudent_" + getIndex());
                    deleteButton.setOnAction(controller);
                    evalButton.setId("eval_" + getIndex());
                    evalButton.setOnAction(controller);
                    setGraphic(pane);
                    setText(null);
                    setAlignment(Pos.CENTER);
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        });
        otherCol.setMinWidth(150);

        // Adding data to the table
        this.setItems(data);
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getColumns().add(nameCol);
        this.getColumns().add(firstNameCol);
        this.getColumns().add(avgCol);
        this.getColumns().add(otherCol);

        // Setting the size of the table
        this.setMaxSize(600, 800);
    }
}
