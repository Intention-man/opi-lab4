package se.mspi.lab4;

public class Shot {
    private float x;
    private float y;
    private float r;
    private boolean isInside;

    public Shot(float x, float y, float r, boolean isInside) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInside = isInside;
    }

    public boolean isInside() {
        return isInside;
    }

    public String fancyResult() {
        return String.format("x = %s, y = %s, r = %s, результат: %s", x, y, r, isInside ? "попадание" : "промах");
    }

    public void setInside(boolean inside) {
        this.isInside = inside;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
