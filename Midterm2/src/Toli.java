import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Толь бичиг класс
public class Toli {

    public static void main(String[] args) {
        //файлын зам
        String FilePath = "src/example.txt";
        try {
            //Файлаас үгсийг уншиж жагсаалтанд хадгалах
            List<String> Words = read(FilePath);
            System.out.println("Толь бичгээс хайх үгcээ оруулна уу : ");
            //Толиос хайх үгээ гараас авах
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String givenSentence = reader.readLine().trim();
            //Олдсон эсэхийг илэрхийлэх хувьсагч
            boolean temp = find(Words, givenSentence);
            if (temp)
                System.out.println("Бүх үг олдлоо.");
            else
                System.out.println("Зарим үгс олдсонгүй.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Файлаас үгсүүдийг унших функц
    private static List<String> read(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .collect(Collectors.toList());
        }
    }
    //Толь бичиг буюу үгийн жагсаалтаас Гараас оруулсан үг хайх функц
    private static boolean find(List<String> dictionaryWords, String sentence) {
        List<String> sentenceWords = Arrays.asList(sentence.split("\\s+"));
        return refind(dictionaryWords, sentenceWords);
    }
    //Эхний үг олдсон хэсгээс хойш дараагийн үгийг хайх дэд функц
    private static boolean refind(List<String> source, List<String> target) {
        int index = 0;
        // олдсон индексээс цааш дараагийн үгийг хайх логик
        for (String targetWord : target) {
            boolean oldoh = false;
            while (index < source.size()) {
                if (source.get(index).equalsIgnoreCase(targetWord)) {
                    oldoh = true;
                    break;
                }
                index++;
            }
            if (!oldoh)
                return false;
        }
        return true;
    }
}
