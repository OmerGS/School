package view;

import controller.CalendarController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CalendarSheet extends Application {
    private CalendarController control;

    private Label jour;
    private Label numeroJour;
    private Label annee;

    private ComboBox<String> monthComboBox;

    public CalendarSheet(){
        this.jour = new Label();
        this.numeroJour = new Label();
        this.annee = new Label();

    }

    @Override
    public void start(Stage primaryStage) {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(5);
        pane.setVgap(5);

        Scene scene = new Scene(pane, 300, 120);

        this.control = new CalendarController(scene, this);


        this.monthComboBox = new ComboBox<>();
        this.monthComboBox.getItems().addAll(this.control.getMonthsNames());
        this.monthComboBox.setValue(this.control.getCurrentMonthName());

        
        this.control.setupMonthComboBox(monthComboBox);



        pane.getChildren().addAll(this.jour, this.numeroJour, monthComboBox, this.annee);

        // Mettre à jour les labels avec la date actuelle
        updateDate();

        primaryStage.setTitle("Calendrier");
        primaryStage.setScene(scene);
        primaryStage.show(); // Afficher le stage
    }

    // Méthode pour mettre à jour les labels avec la date actuelle
    public void updateDate() {
        this.jour.setText(this.control.getCurrentDayName());
        this.numeroJour.setText(Integer.toString(this.control.getCurrentDayNumber()));
        this.annee.setText(Integer.toString(this.control.getCurrentYear()));
        this.monthComboBox.setValue(this.control.getCurrentMonthName());
    } 

    public static void main(String[] args) {
        launch(args);
    }
}