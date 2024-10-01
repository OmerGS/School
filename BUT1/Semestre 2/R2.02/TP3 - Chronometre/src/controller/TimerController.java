package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import model.TimerModel;

public class TimerController {
    private TimerModel model;
    private Button startStopButton;
    private Button resetButton;
    private Label timerLabel;
    private Canvas canvas;
    private boolean isRunning;
    private Timeline timeline;

    public TimerController() {
        this.model = new TimerModel();
        this.isRunning = false;
    }

    public void initialize(Button startStopButton, Button resetButton, Label timerLabel, Canvas canvas) {
        this.startStopButton = startStopButton;
        this.resetButton = resetButton;
        this.timerLabel = timerLabel;
        this.canvas = canvas;

        // Event handlers
        this.startStopButton.setOnAction(event -> {
            if (isRunning()) {
                stopTimer();
                startStopButton.setText("Start");
                resetButton.setDisable(false);
            } else {
                startTimer();
                startStopButton.setText("Stop");
                resetButton.setDisable(true);
            }
        });

        this.resetButton.setOnAction(event -> {
            model.res();
            updateUI();
        });
    }

    public boolean isRunning(){
        return(isRunning);
    }

    public void startTimer() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            this.model.next();
            updateUI();
        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.isRunning = true;
        this.timeline.play();
    }

    public void stopTimer() {
        this.isRunning = false;
        this.timeline.stop();
    }

    public void toggleTimer(ActionEvent event) {
        if (isRunning) {
            stopTimer();
        } else {
            startTimer();
        }
        updateUI();
    }

    public void resetTimer(ActionEvent event) {
        model.res();
        updateUI();
    }

    private void updateUI() {
        String time = String.format("%02d:%02d", model.getMin(), model.getSec());
        timerLabel.setText(time);
        updateButtons();
        drawArc();
    }

    private void updateButtons() {
        if (isRunning) {
            startStopButton.setText("Stop");
        } else {
            startStopButton.setText("Start");
        }

        resetButton.setDisable(isRunning);
    }

    private void drawArc() {
        int seconds = model.getSec();
        double angle = (seconds / 60.0) * 360.0;

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw black circle
        canvas.getGraphicsContext2D().setStroke(Color.BLACK);
        canvas.getGraphicsContext2D().setLineWidth(10);
        canvas.getGraphicsContext2D().strokeArc(20, 20, 160, 160, 90, 360, ArcType.OPEN);

        // Draw red circle for seconds
        canvas.getGraphicsContext2D().setStroke(Color.RED);
        canvas.getGraphicsContext2D().setLineWidth(10);
        canvas.getGraphicsContext2D().strokeArc(20, 20, 160, 160, 90, -angle, ArcType.OPEN);
    }
}