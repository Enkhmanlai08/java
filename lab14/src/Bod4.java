import java.util.Scanner;

public class Bod4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        double x = 0.0, y = 0.0;

        for (int i = 0; i < N; i++) {
            int direction = scanner.nextInt();
            int steps = scanner.nextInt();

            switch (direction) {
                case 1:
                    y += steps;
                    break;
                case 2:
                    x += steps / Math.sqrt(2);
                    y += steps / Math.sqrt(2);
                    break;
                case 3:
                    x += steps;
                    break;
                case 4:
                    x += steps / Math.sqrt(2);
                    y -= steps / Math.sqrt(2);
                    break;
                case 5:
                    y -= steps;
                    break;
                case 6:
                    x -= steps / Math.sqrt(2);
                    y -= steps / Math.sqrt(2);
                    break;
                case 7:
                    x -= steps;
                    break;
                case 8:
                    x -= steps / Math.sqrt(2);
                    y += steps / Math.sqrt(2);
                    break;
                default:
                    break;
            }
        }
        System.out.printf("%.3f %.3f\n", x, y);
        scanner.close();
    }
}
