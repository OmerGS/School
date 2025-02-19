package src;

public class Vecteur2D {
    public double x;
    public double y;

    public Vecteur2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vecteur2D inverserX() {
        return new Vecteur2D(-this.x, this.y);
    }

    public Vecteur2D inverserY() {
        return new Vecteur2D(this.x, -this.y);
    }
}
