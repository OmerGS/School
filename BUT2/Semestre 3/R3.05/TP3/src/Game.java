package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Game extends Application {
    private Scene scene;
    private Pane scenePrincipale;  // Utiliser Pane pour positionner les rectangles librement
    private ArrayList<Mobile> rectangles = new ArrayList<Mobile>();

    @Override
    public void start(Stage primaryStage) {
        // Utiliser Pane pour positionner les rectangles n'importe où (tututututututuut sou moment)
        this.scenePrincipale = new Pane();

        // Créer une scène avec le layout Pane et fixer les dimensions
        this.scene = new Scene(scenePrincipale, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Rectangle Board");
        primaryStage.show();

        System.out.println("Scene and Stage initialized");

        // Démarrer le jeu
        gameEnLuiMeme();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Pane getScenePricipale() {
        return this.scenePrincipale;
    }
    
    public ArrayList<Mobile> getRectangles(){
        return this.rectangles;
    }

    public void gameEnLuiMeme() {
        int numRectangles = 4;  // Limiter à 50 carrés pour plus de fluidité et éviter l'encombrement
        for (int i = 0; i < numRectangles; i++) {
            Mobile mobile = new Mobile(this);
            this.rectangles.add(mobile);
            mobile.start();  // Démarrer chaque animation (thread)
            System.out.println("Mobile thread started " + i);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
