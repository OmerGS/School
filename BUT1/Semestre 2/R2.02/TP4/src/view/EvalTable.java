package view;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Evaluation;
import model.Student;
import javafx.util.converter.FloatStringConverter;
import controller.Controller;
import javafx.collections.FXCollections; 

/**
 * The EvalTable class represents a TableView for displaying evaluations.
 */
public class EvalTable extends TableView<Evaluation> {
    private Controller controller;

    /**
     * Constructor for EvalTable.
     * @param controller The controller managing the interactions with the table.
     * @param student The student for whom evaluations are displayed.
     */
    public EvalTable(Controller controller, Student student) {
        this.controller = controller;
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);

        // Creating columns
        TableColumn<Evaluation, Float> noteCol = new TableColumn<Evaluation, Float>("Note");
        noteCol.setCellValueFactory(new PropertyValueFactory<Evaluation, Float>("score"));
        noteCol.setCellFactory(TextFieldTableCell.<Evaluation, Float>forTableColumn(new FloatStringConverter() {
            @Override
            public Float fromString(String value) {
                Float floatValue;
                try {
                    floatValue = super.fromString(value);
                } catch (NumberFormatException e) {
                    return null;
                }
                return floatValue;
            }
        }));
        noteCol.setOnEditCommit((CellEditEvent<Evaluation, Float> t) -> {
            if (t.getNewValue() != null) {
                controller.updateScore(t.getTablePosition().getRow(), t.getNewValue());
            }
        });
        noteCol.setMinWidth(200);

        TableColumn<Evaluation, Integer> coeffCol = new TableColumn<Evaluation, Integer>("Coefficient");
        coeffCol.setCellValueFactory(new PropertyValueFactory<Evaluation, Integer>("coefficient"));
        coeffCol.setCellFactory(TextFieldTableCell.<Evaluation, Integer>forTableColumn(new IntegerStringConverter() {
            @Override
            public Integer fromString(String value) {
                Integer intValue;
                try {
                    intValue = super.fromString(value);
                } catch (NumberFormatException e) {
                    return null;
                }
                return intValue;
            }
        }));
        coeffCol.setOnEditCommit((CellEditEvent<Evaluation, Integer> t) -> {
            if (t.getNewValue() != null) {
                controller.updateCoefficient(t.getTablePosition().getRow(), t.getNewValue());
            }
        });
        coeffCol.setMinWidth(200);

        TableColumn<Evaluation, String> suppCol = new TableColumn<Evaluation, String>("Actions");
        suppCol.setCellFactory(i -> new TableCell<Evaluation, String>() {
            Button btnSupp = new Button("Supprimer");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    btnSupp.setId("delEval_" + getIndex());
                    btnSupp.setOnAction(controller);
                    setGraphic(btnSupp);
                    setText(null);
                    setAlignment(Pos.CENTER);
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        });
        suppCol.setMinWidth(200);

        // Adding data to the table
        this.setItems(FXCollections.observableArrayList(student.getEvaluations()));
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getColumns().add(noteCol);
        this.getColumns().add(coeffCol);
        this.getColumns().add(suppCol);

        // Setting the size of the table
        this.setMaxSize(600, 800);
    }
}