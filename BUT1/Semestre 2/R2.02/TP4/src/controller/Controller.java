package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;

import view.*;
import model.*;

import javafx.collections.FXCollections; // Import for creating observable lists.
import javafx.collections.ObservableList; // Import for working with JavaFX observable lists.

// Controller class implements EventHandler to respond to UI events
public class Controller implements EventHandler<ActionEvent> {
    private StudentFileAccess studentFileAccess; // Access to student file operations
    private EvalApp view; // The main view of the application
    private EvalScene scene; // The scene for displaying evaluations
    private Stage stage; // The primary stage of the application

    // Constructor initializing the controller with stage, view, and file access
    public Controller(Stage stage, EvalApp view, StudentFileAccess studentFileAccess) {
        this.stage = stage;
        this.view = view;
        this.scene = null;
        this.studentFileAccess = studentFileAccess;
    }

    // Handle method to respond to ActionEvents
    public void handle(ActionEvent e) {
        if (e.getSource() == view.getStudentScene().getStudentForm().getAddBtn()) {
            if (!view.getStudentScene().getStudentForm().getNameTF().getText().equals("")
                    && !view.getStudentScene().getStudentForm().getFirstNameTF().getText().equals("")) {
                studentFileAccess.getStudents()
                        .add(new Student(view.getStudentScene().getStudentForm().getNameTF().getText(),
                                view.getStudentScene().getStudentForm().getFirstNameTF().getText()));
                updateStudentTable();
                reinitStudent(); // Reset the student input form
            }
        } else if (e.getSource() == view.getStudentScene().getStudentForm().getCancelBtn()) {
            reinitStudent(); // Reset the student input form
        } else if (e.getSource() == view.getStudentScene().getFileToolBar().getBtnSaveText()) {
            File selected = view.getStudentScene().getFileToolBar().getChooser().showSaveDialog(null);
            if (selected != null)
                studentFileAccess.writeToTextFile(selected.getAbsolutePath());
        } else if (e.getSource() == view.getStudentScene().getFileToolBar().getBtnLoadText()) {
            File selected = view.getStudentScene().getFileToolBar().getChooser().showOpenDialog(null);
            if (selected != null) {
                studentFileAccess.readFromTextFile(selected.getAbsolutePath());
                updateStudentTable();
            }
        } else if (e.getSource() == view.getStudentScene().getFileToolBar().getBtnSaveBinary()) {
            File selected = view.getStudentScene().getFileToolBar().getChooser().showSaveDialog(null);
            if (selected != null)
                studentFileAccess.writeToBinaryFile(selected.getAbsolutePath());
        } else if (e.getSource() == view.getStudentScene().getFileToolBar().getBtnLoadBinary()) {
            File selected = view.getStudentScene().getFileToolBar().getChooser().showOpenDialog(null);
            if (selected != null) {
                studentFileAccess.readFromBinaryFile(selected.getAbsolutePath());
                updateStudentTable();
            }
        } else if (e.getSource() == view.getStudentScene().getFileToolBar().getBtnSaveObject()) {
            File selected = view.getStudentScene().getFileToolBar().getChooser().showSaveDialog(null);
            if (selected != null)
                studentFileAccess.writeObject(selected.getAbsolutePath());
        } else if (e.getSource() == view.getStudentScene().getFileToolBar().getBtnLoadObject()) {
            File selected = view.getStudentScene().getFileToolBar().getChooser().showOpenDialog(null);
            if (selected != null) {
                studentFileAccess.readObject(selected.getAbsolutePath());
                updateStudentTable();
            }
        } else if (scene != null && e.getSource() == scene.getEvalForm().getAddBtn()) {
            if (!scene.getEvalForm().getScoreTF().getText().equals("")
                    && !scene.getEvalForm().getCoeffTF().getText().equals("")) {
                scene.getStudent()
                        .add(new Evaluation(Float.parseFloat(scene.getEvalForm().getScoreTF().getText()),
                                Integer.parseInt(scene.getEvalForm().getCoeffTF().getText())));
                updateEvaluationTable();
                reinitEval(); // Reset the evaluation input form
            }
        } else if (scene != null && e.getSource() == scene.getBackButton()) {
            updateStudentTable();
            stage.getScene().setRoot(view.getStudentScene()); // Change the root scene to the student scene
            scene = null; // Set the current scene to null
        } else if (scene != null && e.getSource() == scene.getEvalForm().getCancelBtn()) {
            reinitEval(); // Reset the evaluation input form
        } else {
            // Handling buttons with dynamic IDs for student and evaluation deletion
            Button source = (Button) e.getSource();
            String[] code = (source.getId()).split("_");
            if (code[0].equals("delStudent")) {
                studentFileAccess.getStudents().remove(Integer.parseInt(code[1]));
                updateStudentTable();
            } else if (scene != null && code[0].equals("delEval")) {
                scene.getStudent().remove(Integer.parseInt(code[1]));
                updateEvaluationTable();
            } else {
                scene = new EvalScene(this,
                        studentFileAccess.getStudents().get(Integer.parseInt(code[1])));

                stage.getScene().setRoot(scene); // Change the root scene to the new evaluation scene
            }
        }
    }

    // Method to reset student form fields
    private void reinitStudent() {
        view.getStudentScene().getStudentForm().getNameTF().setText("");
        view.getStudentScene().getStudentForm().getFirstNameTF().setText("");
    }

    // Method to reset evaluation form fields
    private void reinitEval() {
        scene.getEvalForm().getScoreTF().setText("");
        scene.getEvalForm().getCoeffTF().setText("");
    }

    // Method to update coefficient
    public void updateCoefficient(int index, int value) {
        scene.getStudent().getEvaluations().get(index).setCoefficient(value);
        scene.getStudent().computeAverage();
    }

    // Method to update score
    public void updateScore(int index, float value) {
        scene.getStudent().getEvaluations().get(index).setScore(value);
        scene.getStudent().computeAverage();
    }

    // Method to update student name
    public void updateName(int index, String value) {
        studentFileAccess.getStudents().get(index).setName(value);
    }

    // Method to update student first name
    public void updateFirstName(int index, String value) {
        studentFileAccess.getStudents().get(index).setFirstName(value);
    }

    // Method to update student table
    public void updateStudentTable() {
        view.getStudentScene().getStudentTable().setItems(null);
        view.getStudentScene().getStudentTable().layout();
        view.getStudentScene().getStudentTable()
                .setItems(FXCollections.observableArrayList(studentFileAccess.getStudents()));
    }

    // Method to update evaluation table
    public void updateEvaluationTable() {
        scene.getEvalTable().setItems(null);
        scene.getEvalTable().layout();
        scene.getEvalTable().setItems(FXCollections.observableArrayList(scene.getStudent().getEvaluations()));
    }
}
