package view;

import controller.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Main application class
public class EvalApp extends Application {
    private Controller controller; // Controller instance
    private StudentFileAccess studentFileAccess; // Student file access instance
    private StudentScene studentScene; // Student scene instance

    // Start method, entry point of the JavaFX application
    public void start(Stage stage) {
        // Initialize student file access
        studentFileAccess = new StudentFileAccess();
        // Initialize controller with stage, this application instance, and student file
        // access
        controller = new Controller(stage, this, studentFileAccess);
        // Initialize student scene with controller and student file access
        studentScene = new StudentScene(controller, studentFileAccess);

        // Create a new scene with the student scene as root node, set dimensions, and
        // show
        Scene scene = new Scene(studentScene, 1200, 800);
        stage.setTitle("Evaluations");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Getter for the student scene
    public StudentScene getStudentScene() {
        return studentScene;
    }

    // Main method to launch the application
    public static void main(String args[]) {
        launch(args);
    }
}
