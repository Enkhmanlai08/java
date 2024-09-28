import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bod1 {
    public static void main(String[] args) {
        List<String> Str = Arrays.asList("Sain", "baina", "uu", "good", "Morning");
        String input = "Sain Baina uu? Sonin Saikhan YUU BNAA";


        System.out.println("Жишээ : "+input);

        long lowercase = countLow(input);
        System.out.println("Жижиг үсгийн тоо : " + lowercase);

        System.out.println("Жишээ : "+Str);
        List<String> lowWord = findLow(Str);
        System.out.println("Бүх жижгээр эхлэсэн үгс : " + lowWord);
    }

    private static List<String> findLow(List<String> stringList) {
        return stringList.stream()
                .flatMap(word -> Arrays.stream(word.split("\\s+")))
                .filter(word -> word.matches("[a-z]+"))
                .collect(Collectors.toList());
    }

    private static long countLow(String input) {
        return input.chars()
                .filter(Character::isLowerCase)
                .count();
    }
}
