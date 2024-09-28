import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IceCream {
    private static final String file = "src/icecream.dat";
    private static final double Zairmag_une = 2.25;
    private static final double Nemelt_une = 0.50;
    private static final double Torh_une = 2.25;
    private static final double TAX = 0.06;

    public static void main(String[] args) {
        IceCream app = new IceCream();
        while (true) {
            System.out.println("Зайрмаг захиалах програм");
            System.out.println("1. Сүүлийн захиалга");
            System.out.println("2. Зайрмаг нэмэх");
            System.out.println("3. Үнэ тооцоолох");
            System.out.println("4. Гарах");
            System.out.print("1-4 Сонгоно уу : ");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1:
                        app.restoreOrder();
                        break;
                    case 2:
                        app.saveOrder();
                        break;
                    case 3:
                        app.calculateCost();
                        break;
                    case 4:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                    default:
                        System.out.println("Буруу сонголт.");
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    //Сүүлийн захиалга харах функц
    private void restoreOrder() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String orderDate = null;
            StringBuilder lastOrder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Захиалгын огноо : ")) {
                    orderDate = line;
                    lastOrder.setLength(0);
                } else {
                    lastOrder.append(line).append("\n");
                }
            }
            if (orderDate != null) {
                System.out.println("Сүүлийн Захиалга");
                System.out.println(orderDate);
                System.out.println(lastOrder.toString().trim());
            } else {
                System.out.println("Error opening data file");
            }
        } catch (IOException e) {
            System.out.println("Error opening data file");
        }
    }

    //Зайрмаг нэмж захиалан хадгалах функц
    private void saveOrder() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            writer.write("Захиалгын огноо : " + sdf.format(date));
            writer.newLine();

            System.out.println("Амт (Vanilla/Chocolate/Strawberry): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String flavor = br.readLine();
            writer.write(flavor);
            writer.newLine();

            System.out.println("Самар (With_Nuts/Without_Nuts): ");
            String nuts = br.readLine();
            writer.write(nuts);
            writer.newLine();

            System.out.println("Интоор (With_Cherries/Without_Cherries): ");
            String cherries = br.readLine();
            writer.write(cherries);
            writer.newLine();

            System.out.println("Амжилттай хадгаллаа.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Нийт үнийн дүнг бодох функц
    private void calculateCost() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String flavor = null;
            String nuts = null;
            String cherries = null;
            String orderDate = null;
            double allCost = 0.0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Захиалгын огноо : ")) {
                    if (orderDate != null) {
                        double cost = Zairmag_une + Torh_une;
                        if (nuts.equals("With_Nuts")) {
                            cost += Nemelt_une;
                        }
                        if (cherries.equals("With_Cherries")) {
                            cost += Nemelt_une;
                        }
                        allCost += cost;
                    }
                    orderDate = line;
                    flavor = null;
                    nuts = null;
                    cherries = null;
                } else if (flavor == null) {
                    flavor = line;
                } else if (nuts == null) {
                    nuts = line;
                } else if (cherries == null) {
                    cherries = line;
                }
            }
            if (orderDate != null) {
                double cost = Zairmag_une + Torh_une;
                if (nuts.equals("With_Nuts")) {
                    cost += Nemelt_une;
                }
                if (cherries.equals("With_Cherries")) {
                    cost += Nemelt_une;
                }
                allCost += cost;
            }

            if (orderDate != null) {
                double tax = allCost * TAX;
                double total = allCost + tax;

                displayOrder(orderDate, allCost, tax, total);
            } else {
                //Файл хоосон гэсэн үг
                System.out.println("Захиалга олдсонгүй.");
            }
        } catch (IOException e) {
            System.out.println("Error opening data file");
        }
    }
    //Нийт үнийн дүнг хэвлэх функц
    private void displayOrder(String orderDate, double totalCost, double tax, double grandTotal) {
        System.out.println(orderDate);
        System.out.println("Нийт үнэ : " + totalCost);
        System.out.println("Татвар : " + tax);
        System.out.println("Нийт : " + grandTotal);
    }
}
