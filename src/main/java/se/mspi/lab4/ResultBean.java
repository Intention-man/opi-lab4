package se.mspi.lab4;

public class ResultBean {
    public Shot shoot(float x, float y, float r) {
        if (r < 0) throw new IllegalArgumentException("Радиус не может быть меньше 0");
        boolean res = (x >= 0 && y >= 0 && x <= r && y <= r/2)
                || (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)))
                || (x >= 0 && y <= 0 && y >= x - r/2);

        return new Shot(x, y, r, res);
    }
}
