import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Item {
    private String buyer;
    private String productName;
    private int quantity;

    public Item(String buyer, String productName, int quantity) {
        this.buyer = buyer;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
public class SortByProduct {
    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();
        try {
            String filePath = "src/product.txt";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String buyer = parts[0];
                    String productName = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    itemList.add(new Item(buyer, productName, quantity));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(itemList, Comparator.comparing(Item::getBuyer).thenComparing(Item::getProductName).thenComparing(Item::getQuantity));
        String currentBuyer = null;
        for (Item item : itemList) {
            if (!item.getBuyer().equals(currentBuyer)) {
                System.out.println(item.getBuyer() + ":");
                currentBuyer = item.getBuyer();
            }
            System.out.println(item.getProductName() + " " + item.getQuantity());
        }
    }
}
