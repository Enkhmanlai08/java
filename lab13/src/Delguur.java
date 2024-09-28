import java.util.Scanner;

public class Delguur {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double total = 0.0;
        for (int i = 1; i <= 5; i++) {
            System.out.println("Бараа №" + i + " :");
            double totalPurchasePrice = calculate(scanner);
            System.out.println("Бараа №" + i + "үнэ : " + totalPurchasePrice + "₮");
            total += totalPurchasePrice;
        }
        System.out.println("Нийт үнийн дүн : ₮" + total);
        scanner.close();
    }

    private static double calculate(Scanner scanner) {
        System.out.print("Бараа : ");
        String itemName = scanner.nextLine();

        System.out.print("Үнэ : ");
        double itemPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Тоо : ");
        int itemQuantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Item discount (in percentage): ");
        double itemDiscount = scanner.nextDouble();
        scanner.nextLine();

        return calculateTotalPrice(itemPrice, itemQuantity, itemDiscount);
    }

    private static double calculateTotalPrice(double itemPrice, int itemQuantity, double itemDiscount) {
        double discountedPrice = itemPrice - (itemPrice * itemDiscount / 100);
        return discountedPrice * itemQuantity;
    }
}
