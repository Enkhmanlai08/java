import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

// Тоглоом класс
class Game {
    private String name;
    private String category;
    private int price;
    private String ageCategory;

    public Game(String name, String category, int price, String ageCategory) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.ageCategory = ageCategory;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    // toString() method-ийг өөрийнхөөрөө дахин тодорхойлов.
    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", age='" + ageCategory + '\'' +
                '}';
    }
}

public class KidToy {

    public static void main(String[] args) {
        //Тоглоомуудын мэдээлэл унших файлын зам.
        List<Game> games = readData("src/Data.txt");

        if (games != null) {
            //Эхлээд тоглоомын мэдээллээ хэвлэнэ.
            System.out.println("\tТоглоомууд : ");
            games.forEach(System.out::println);
            System.out.println("");

            // 1. 40000 төгрөгөөс хэтрэхгүй үнэтэй, 5 настай хүүхдэд зориулсан тоглоомуудын нэрс олох
            List<String> Toy5 = Method1(games);
            System.out.println("1. 40000 төгрөгөөс хэтрэхгүй үнэтэй, 5 настай хүүхдэд зориулсан тоглоомуудын нэрс : " + Toy5);

            // 2. Өгсөн загварын дагуу угсардаг хамгийн үнэтэй тоглоомын үнэ олох
            int max = Method2(games);
            System.out.println("2. Хамгийн үнэтэй Угсардаг тоглоомын үнэ : " + max + " MNT");

            // 3. Хамгийн үнэтэй тоглоомуудын нэрс олох (хамгийн үнэтэй тоглоомоос 1000 төгрөгийн л зөрүүтэй)
            List<String> Expensive = Method3(games);
            System.out.println("3. Хамгийн үнэтэй тоглоомуудын нэрс : " + Expensive);

            // 4. 6-аас 10 насны хоорондох хүүхдүүдэд тохирсон тоглоомуудын нэрс олох
            List<String> game6to10 = Method4(games);
            System.out.println("4. 6-аас 10 насны хүүхдүүдэд тохирсон тоглоомуудын нэрс : " + game6to10);

            // 5. 3 настай хүүхдэд зориулж бөмбөгнөөс өөр тоглоом сонгож болох уу?
            boolean toyFor3 = Method5(games);
            if (toyFor3)
                System.out.println("5. 3 настай хүүхдэд зориулж бөмбөгнөөс өөр тоглоом сонгож болох уу? \t Болно.");
            else
                System.out.println("5. 3 настай хүүхдэд зориулж бөмбөгнөөс өөр тоглоом сонгож болох уу? \t Болохгүй.");
        }
    }

    // Method 1
    private static List<String> Method1(List<Game> games) {
        return games.stream()
                .filter(game -> game.getAgeCategory().equals("2-5") && game.getPrice() <= 40000)
                .map(Game::getName)
                .collect(Collectors.toList());
    }

    // Method 2
    private static int Method2(List<Game> games) {
        return games.stream()
                .filter(game -> game.getCategory().equals("Lego"))
                .mapToInt(Game::getPrice)
                .max()
                .orElse(0);
    }

    // Method 3
    private static List<String> Method3(List<Game> games) {
        int maxPrice = games.stream()
                .mapToInt(Game::getPrice)
                .max()
                .orElse(0);

        return games.stream()
                .filter(game -> Math.abs(game.getPrice() - maxPrice) <= 1000)
                .map(Game::getName)
                .collect(Collectors.toList());
    }

    // Method 4
    private static List<String> Method4(List<Game> games) {
        return games.stream()
                .filter(game -> game.getAgeCategory().equals("6-10"))
                .map(Game::getName)
                .collect(Collectors.toList());
    }

    // Method 5
    private static boolean Method5(List<Game> games) {
        return games.stream()
                .anyMatch(game -> game.getAgeCategory().equals("2-5") && !game.getCategory().equals("Ball"));
    }

    private static List<Game> readData(String fileName) {
        try {
            return Files.lines(Paths.get(fileName))
                    .map(line -> {
                        String[] parts = line.split(",");
                        return new Game(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()), parts[3].trim());
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
