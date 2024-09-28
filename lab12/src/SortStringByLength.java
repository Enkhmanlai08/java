import java.util.Arrays;
import java.util.Comparator;

public class SortStringByLength {
    public static void main(String[] args) {
        String[] cities = {"Atlanta", "Savannahh", "New Yorkkk", "Dallas"};
        Arrays.sort(cities, Comparator.comparingInt(String::length));
        Arrays.stream(cities).forEach(System.out::println);
    }
}
