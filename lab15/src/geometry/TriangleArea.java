package geometry;

public class TriangleArea {

    public static double calculateTriangleArea(Point p1, Point p2, Point p3) {
        return 0.5 * Math.abs(p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX() * (p3.getY() - p1.getY()) +
                p3.getX() * (p1.getY() - p2.getY()));
    }
}

