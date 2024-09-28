import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bod3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("n тоо : ");
        int n = scanner.nextInt();

        List<Integer> sequenceA = constructSequenceA(n);
        List<Integer> sequenceB = constructSequenceB(n);

        System.out.println("A : " + sequenceA);
        System.out.println("B : " + sequenceB);

        scanner.close();
    }

    private static List<Integer> constructSequenceA(int n) {
        List<Integer> sequenceA = new ArrayList<>();
        sequenceA.add(2);
        sequenceA.add(3);
        sequenceA.add(4);
        sequenceA.add(7);
        sequenceA.add(13);

        for (int i = 6; i <= n; i++) {
            int element = sequenceA.get(i - 2) + sequenceA.get(i - 4);
            sequenceA.add(element);
        }

        return sequenceA.subList(0, n);
    }

    private static List<Integer> constructSequenceB(int n) {
        List<Integer> sequenceA = constructSequenceA(n);
        List<Integer> sequenceB = new ArrayList<>();
        int value = 1;

        for (int i = 0; i < n; i++) {
            while (sequenceA.contains(value)) {
                value++;
            }
            sequenceB.add(value);
            value++;
        }

        return sequenceB;
    }
}
