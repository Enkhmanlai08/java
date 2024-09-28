import java.util.Scanner;

public class Bod1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("N тоог оруул : ");
        int N = scanner.nextInt();
        int result = find(N);

        if (result == -1) {
            System.out.println("-1");
        } else {
            System.out.println("Үр дүн : " + result);
        }
        scanner.close();
    }

    private static int find(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        int result = 0;
        for (int i = 9; i >= 2; i--) {
            while (N % i == 0) {
                result = result * 10 + i;
                N /= i;
            }
        }
        if (N != 1) {
            return -1;
        }

        int Result = 0;
        while (result != 0) {
            Result = Result * 10 + result % 10;
            result /= 10;
        }
        return Result;
    }
}
