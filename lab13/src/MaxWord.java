import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class MaxWord {
    public static void main(String[] args) {
        String filePath = "src/test.txt";
        try {
            String lineWithMostWords = Files.lines(Paths.get(filePath))
                    .map(line -> line.split("\\s+"))
                    .max(Comparator.comparingInt(words -> words.length))
                    .map(words -> words.length > 0 ? String.join(" ", words) : "")
                    .orElse("");
            System.out.println("Хамгийн олон үг агуулдаг мөр : " + lineWithMostWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
