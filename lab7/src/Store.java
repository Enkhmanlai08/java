import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Store {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> buyerInfo = new HashMap<>();
        try {
            String filePath = "src/store.txt";
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String buyer = parts[0];
                    String item = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    buyerInfo.putIfAbsent(buyer, new HashMap<>());
                    Map<String, Integer> items = buyerInfo.get(buyer);
                    items.put(item, items.getOrDefault(item, 0) + quantity);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> sortedBuyers = new ArrayList<>(buyerInfo.keySet());
        Collections.sort(sortedBuyers);
        for (String buyer : sortedBuyers) {
            System.out.println(buyer + ":");
            Map<String, Integer> items = buyerInfo.get(buyer);
            List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(items.entrySet());
            sortedItems.sort(Comparator.comparing(Map.Entry::getKey));
            for (Map.Entry<String, Integer> itemEntry : sortedItems) {
                System.out.println(itemEntry.getKey() + " " + itemEntry.getValue());
            }
        }
    }
}
