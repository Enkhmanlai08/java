import java.util.Arrays;
import java.util.Comparator;

public class ArraySorter {
    public static void main(String[] args) {
        String[] array1 = {"hello", "spoon", "cat"};
        String[] array2 = {"I", "love", "to", "music"};
        String[] array3 = {"world", "jobbbbbbbbb"};

        String[][] arrays = {array1, array2, array3};
        Arrays.sort(arrays, Comparator.comparingInt(arr -> Arrays.stream((String[]) arr).mapToInt(s -> s.split("\\s+").length).sum()));

        System.out.println("Үгийн тоогоор эрэмбэлэх : ");
        for (String[] arr : arrays) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("Нийт үсгийн тоогоор эрэмбэлэх : ");
        Arrays.sort(arrays, Comparator.comparingInt(ArraySorter::calculateTotalLetters));

        for (String[] array : arrays) {
            Arrays.sort(array, Comparator.comparingInt(String::length));
        }

        for (String[] array : arrays) {
            System.out.println(Arrays.toString(array) + " " + calculateTotalLetters(array));
        }
    }

    private static int calculateTotalLetters(String[] array) {
        int totalLetters = 0;
        for (String word : array) {
            totalLetters += word.length();
        }
        return totalLetters;
    }
}
