import java.io.*;
import java.util.Scanner;
import java.util.concurrent.*;

class ArrayMinMaxTask implements Callable<int[]> {
    private int[] arr;

    public ArrayMinMaxTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int[] call() throws Exception {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        return new int[]{min, max};
    }
}

public class FourIntArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array1 = new int[4];
        int[] array2 = new int[4];

        System.out.println("Enter Array 1:");
        for (int i = 0; i < 4; i++) {
            array1[i] = scanner.nextInt();
        }

        System.out.println("Enter Array 2:");
        for (int i = 0; i < 4; i++) {
            array2[i] = scanner.nextInt();
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<int[]> result1 = executor.submit(new ArrayMinMaxTask(array1));
        Future<int[]> result2 = executor.submit(new ArrayMinMaxTask(array2));

        try {
            int[] minMax1 = result1.get();
            int[] minMax2 = result2.get();

            writeArrayToBinaryFile("array1.bin", minMax1);
            writeArrayToBinaryFile("array2.bin", minMax2);

            int[] sumArray1 = readArrayFromBinaryFile("array1.bin");
            int[] sumArray2 = readArrayFromBinaryFile("array2.bin");

            int sum1 = sumArray1[0] + sumArray2[0];
            int sum2 = sumArray1[1] + sumArray2[1];

            System.out.println("Array 1: Min = " + minMax1[0] + ", Max=" + minMax1[1]);
            System.out.println("Array 2: Min = " + minMax2[0] + ", Max=" + minMax2[1]);

            int totalSum = array1[0] + array1[1] + array1[2] + array1[3];
            int totalSum1 = array2[0] + array2[1] + array2[2] + array2[3];
            System.out.println("Sum of array1: " + totalSum);
            System.out.println("Sum of array2: " + totalSum1);
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void writeArrayToBinaryFile(String fileName, int[] array) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readArrayFromBinaryFile(String fileName) {
        int[] array = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            array = (int[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }
}
