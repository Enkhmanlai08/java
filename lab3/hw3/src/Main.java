import java.util.ArrayList;
import java.util.List;
abstract class Furniture {
    private int numLegs;
    private double height;
    private double width;
    private double depth;
    private String seating;
    private double price;
    private String design;

    public Furniture(int numLegs, double height, double width, double depth, String seating, double price, String design) {
        this.numLegs = numLegs;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.seating = seating;
        this.price = price;
        this.design = design;
    }

    public abstract void displayInfo();

    public int getNumLegs() {
        return numLegs;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public String getSeating() {
        return seating;
    }

    public double getPrice() {
        return price;
    }

    public String getDesign() {
        return design;
    }
}

class Chair extends Furniture {

    public Chair(int numLegs, double height, double width, double depth, String seating, double price, String design) {
        super(numLegs, height, width, depth, seating, price, design);

    }

    @Override
    public void displayInfo() {
        System.out.println("Сандал - Дизайн: " + getDesign() + ", Хөлийн тоо: " + getNumLegs() + ", Өндөр: " + getHeight() + " cm, Өргөн: " + getWidth() + " cm, Гүн: " + getDepth() + " cm, Суух боломжтой эсэх: " + getSeating() + ", Үнэ: ₮" + getPrice());
    }
}

class Table extends Furniture {
    public Table(int numLegs, double height, double width, double depth, String seating, double price, String design) {
        super(numLegs, height, width, depth, seating, price, design);
    }

    @Override
    public void displayInfo() {
        System.out.println("Ширээ - Дизайн: " + getDesign() + ", Хөлийн тоо: " + getNumLegs() + ", Өндөр: " + getHeight() + " cm, Өргөн: " + getWidth() + " cm, Гүн: " + getDepth() + " cm, Суух боломжтой эсэх: " + getSeating() + ", Үнэ: ₮" + getPrice());
    }
}

class Bed extends Furniture {
    private String size;

    public Bed(int numLegs, double height, double width, double depth, String seating, double price, String design, String size) {
        super(numLegs, height, width, depth, seating, price, design);
        this.size = size;
    }

    @Override
    public void displayInfo() {
        System.out.println("Ор - Дизайн: " + getDesign() + ", Хөлийн тоо: " + getNumLegs() + ", Өндөр: " + getHeight() + " cm, Өргөн: " + getWidth() + " cm, Гүн: " + getDepth() + " cm, Суух боломжтой эсэх: " + getSeating() + ", Үнэ: ₮" + getPrice() + ", Хэмжээ: " + size);
    }
}

class FurnitureStore {
    private List<Furniture> inventory;

    public FurnitureStore() {
        inventory = new ArrayList<>();
    }

    public void addFurniture(Furniture furniture) {
        inventory.add(furniture);
    }

    public void displayInventory() {
        for (Furniture item : inventory) {
            item.displayInfo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FurnitureStore store = new FurnitureStore();
        Chair chair1 = new Chair(4, 90, 60, 60, "Болно", 150, "Энгийн");
        Table table1 = new Table(4, 75, 120, 80, "Болохгүй", 250, "Тансаг");
        Bed bed1 = new Bed(4, 100, 160, 200, "Болно", 500, "Орчин үеийн", "2 хүний");
        store.addFurniture(chair1);
        store.addFurniture(table1);
        store.addFurniture(bed1);
        System.out.println("Тавилгын дэлгүүр:");
        store.displayInventory();
    }
}