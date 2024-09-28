import java.util.Scanner;
import java.util.stream.Collectors;

public class Bod2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Тэмдэгт мөрийг оруул : ");
        String originalString = scanner.nextLine();

        String compressedString = compress(originalString);
        System.out.println("Хураангуйлсан : " + compressedString);

        scanner.close();
    }

    public static String compress(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> entry.getValue() > 1 ?
                        entry.getValue() + "" + entry.getKey() :
                        entry.getKey() + "")
                .collect(Collectors.joining());
    }
}
