package src;

import javafx.application.Platform;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Mobile extends Thread {
    private Game game;
    private Rectangle rectangle;
    private int posX;
    private int posY;
    private Vecteur2D vitesse;
    private Random randomNumbers = new Random();
    private boolean running = true;
    private int boing = 0;
    private String nom;

    private static final int RECT_SIZE = 50; // Taille des carrés

    public Mobile(Game game) {
        this.game = game;

        // Initialisation du rectangle
        this.rectangle = new Rectangle(RECT_SIZE, RECT_SIZE);
        Platform.runLater(() -> game.getScenePricipale().getChildren().add(rectangle));

        // Initialisation des positions aléatoires à l'intérieur des limites
        this.posX = randomNumbers.nextInt(800 - RECT_SIZE);  // Garder dans les limites de la scène
        this.posY = randomNumbers.nextInt(800 - RECT_SIZE);

        // Création du vecteur vitesse aléatoire
        this.vitesse = new Vecteur2D(10, 5);

        // Initialisation de la position graphique du rectangle
        Platform.runLater(() -> {
            this.rectangle.setTranslateX(this.posX);
            this.rectangle.setTranslateY(this.posY);
        });

        System.out.println("Initial Position X: " + this.posX + ", Y: " + this.posY);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Mettre à jour la position

            updatePosition();
            checkCollision();
        }
    }

    private void updatePosition() {
        // Déplacer selon la vitesse
        this.posX += this.vitesse.x;
        this.posY += this.vitesse.y;

        // Rebondir sur les bords
        if (this.posX <= 0 || this.posX >= (800 - RECT_SIZE)) {  // Limite sur X
            this.vitesse = this.vitesse.inverserX();
            changeColor();
        }
        if (this.posY <= 0 || this.posY >= (800 - RECT_SIZE)) {  // Limite sur Y
            this.vitesse = this.vitesse.inverserY();
            changeColor();
        }

        // Mettre à jour la position sur l'interface graphique
        Platform.runLater(() -> {
            this.rectangle.setTranslateX(posX);
            this.rectangle.setTranslateY(posY);
        });
    }

    // Changer la couleur du rectangle
    private void changeColor() {
        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.PURPLE};
        Color randomColor = colors[randomNumbers.nextInt(colors.length)];
        Platform.runLater(() -> this.rectangle.setFill(randomColor));
    }

    // Pour arrêter le thread proprement
    public void stopMobile() {
        running = false;
    }


    private void checkCollision() {
        for (Mobile m1 : this.game.getRectangles()) {
            for (Mobile m2 : this.game.getRectangles()) {
                if (m1 == m2) continue; // Ne pas comparer un rectangle avec lui-même
    
                // Détection de collision en tenant compte de la taille des rectangles
                boolean collisionX = m1.posX < m2.posX + RECT_SIZE && m1.posX + RECT_SIZE > m2.posX;
                boolean collisionY = m1.posY < m2.posY + RECT_SIZE && m1.posY + RECT_SIZE > m2.posY;
    
                if (collisionX && collisionY) {
                    // Inverser les directions des deux rectangles
                    m1.vitesse = m1.vitesse.inverserX().inverserY();
                    m2.vitesse = m2.vitesse.inverserX().inverserY();
    
                    // Déplacer légèrement les rectangles pour les séparer après la collision
                    m1.posX += m1.vitesse.x;
                    m1.posY += m1.vitesse.y;
                    m2.posX += m2.vitesse.x;
                    m2.posY += m2.vitesse.y;

                    m1.boing++;
                    m2.boing++;

                    System.out.println("\033[2J");
                    for (Mobile boing : this.game.getRectangles()) {
                        System.out.println(boing.getId() + " : " + boing.boing);
                    }
    
                    // Mettre à jour la position graphique des rectangles
                    Platform.runLater(() -> {
                        m1.rectangle.setTranslateX(m1.posX);
                        m1.rectangle.setTranslateY(m1.posY);
                        m2.rectangle.setTranslateX(m2.posX);
                        m2.rectangle.setTranslateY(m2.posY);
                    });
    
                    // Optionnel: Changer la couleur des rectangles après collision
                    m1.changeColor();
                    m2.changeColor();
                }
            }
        }
    }
    
    
}