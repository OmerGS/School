package view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.util.Duration;
import controller.TimerController;

public class TimerView extends Application {
    private TimerController controller;
    private Label timerLabel;
    private Button startStopButton;
    private Button resetButton;
    private VBox root;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        this.controller = new TimerController();

        // Label
        this.timerLabel = new Label();
        this.timerLabel.setFont(new Font("Arial", 48));
        this.timerLabel.setTextFill(Color.BLACK);

        // Boutons
        this.startStopButton = createStyledButton("Stop");
        this.resetButton = createStyledButton("Reset");
        this.resetButton.setDisable(true);

        this.canvas = new Canvas(200, 200);

        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().addAll(startStopButton, resetButton);
        buttonPane.setAlignment(Pos.CENTER);

        StackPane canvasPane = new StackPane();
        canvasPane.getChildren().addAll(canvas, timerLabel);
        canvasPane.setAlignment(Pos.CENTER);

        this.root = new VBox(20);
        this.root.getChildren().addAll(canvasPane, buttonPane);
        this.root.setAlignment(Pos.CENTER);
        this.root.setBackground(new Background(new BackgroundFill(Color.web("#d4f0d7"), CornerRadii.EMPTY, null)));

        StackPane stackPane = new StackPane(root);
        Scene scene = new Scene(stackPane, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chronomètre");
        primaryStage.setResizable(false);
        primaryStage.show();

        // Pass the necessary UI components to the controller
        controller.initialize(startStopButton, resetButton, timerLabel, canvas);

        // Start the timer
        controller.startTimer();

        // Add hover effect to buttons
        addHoverEffect(startStopButton);
        addHoverEffect(resetButton);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);

        String buttonStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #d3d3d3; "
                + "-fx-border-radius: 15; -fx-background-radius: 15; "
                + "-fx-padding: 10 20; -fx-font-size: 14px;";
        button.setStyle(buttonStyle);

        return button;
    }

    private void addHoverEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), button);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(100), button);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.8);

        ParallelTransition parallelTransition = new ParallelTransition(scaleIn, fadeTransition);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), button);
        scaleOut.setFromX(1.1);
        scaleOut.setFromY(1.1);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(100), button);
        fadeOut.setFromValue(0.8);
        fadeOut.setToValue(1);

        ParallelTransition parallelTransitionOut = new ParallelTransition(scaleOut, fadeOut);

        button.setOnMouseEntered(event -> {
            parallelTransition.play();
        });

        button.setOnMouseExited(event -> {
            parallelTransitionOut.play();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

