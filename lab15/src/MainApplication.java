import java.util.Scanner;
import geometry.Point;
import geometry.TriangleArea;

public class MainApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point p1 = Point.getPointFromUserInput(scanner, "Цэг 1");
        Point p2 = Point.getPointFromUserInput(scanner, "Цэг 2");
        Point p3 = Point.getPointFromUserInput(scanner, "Цэг 3");
        double triangleArea = TriangleArea.calculateTriangleArea(p1, p2, p3);
        System.out.println("Гурвалжны талбай : " + triangleArea);
        scanner.close();
    }
}
