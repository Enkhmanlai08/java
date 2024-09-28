package geometry;

import java.util.Scanner;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point getPointFromUserInput(Scanner scanner, String pointName) {
        System.out.println("Координат " + pointName + " (x y):");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        return new Point(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
