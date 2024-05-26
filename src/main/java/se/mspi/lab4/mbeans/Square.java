package se.mspi.lab4.mbeans;

public class Square implements SquareMBean {
    private final double unitSquare = 0.5 + 0.125 + Math.PI / 4;

    @Override
    public double calcSquareOfFigure(float r) {
        return unitSquare * r * r;
    }
}
