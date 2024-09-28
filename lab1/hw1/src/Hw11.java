import java.util.Scanner;
public class Hw11 {
    double dis5 = 0.05;
    double dis10 = 0.10;
    double ticketPrice = 0.0;
    Scanner scanner = new Scanner(System.in);
    public Hw11(){
        Cal();
    }
    void Comptotal(){
        System.out.println("Тасалбарын тоогоо оруулна уу.");
        int numberOfTickets = scanner.nextInt();
        double totalPrice = ticketPrice * numberOfTickets;
        if (numberOfTickets > 10)
            totalPrice *= 1-dis10;
        if (numberOfTickets >= 5 && numberOfTickets < 10)
            totalPrice *= 1-dis5;
        System.out.println("Нийт үнэ : $" + totalPrice);
    }
    void Cal(){
        System.out.println("Кино үзэх танхимаа сонгоно уу.\n");
        System.out.println("1. Улаан\n");
        System.out.println("2. Хөх\n");
        System.out.println("3. Ногоон\n");
        int movieChoice = scanner.nextInt();
        if (movieChoice > 3){
            System.out.println("Буруу сонголт,Дахин оруулна уу.");
            return;
        }
        System.out.println("Үзэх цагаа сонгоно уу.");
        // "Улаан" танхим
        if (movieChoice == 1) {
            System.out.println("1. 12\n");
            System.out.println("2. 16\n");
            System.out.println("3. 20\n");
            int hours = scanner.nextInt();
            switch (hours){
                case 1:
                    ticketPrice = 2500;
                    break;
                case 2:
                    ticketPrice = 3500;
                    break;
                case 3:
                    ticketPrice = 4500;
                    break;
                default:
                    System.out.println("Буруу сонголт,Дахин оруулна уу.");
                    return;
            }
            Comptotal();
            return;
        }
        // "Хөх" танхим
        if (movieChoice == 2) {
            System.out.println("1. 10\n");
            System.out.println("2. 13\n");
            int hours = scanner.nextInt();
            switch (hours){
                case 1:
                    ticketPrice = 2500;
                    break;
                case 2:
                    ticketPrice = 3500;
                    break;
                default:
                    System.out.println("Буруу сонголт,Дахин оруулна уу.");
                    return;
            }
            Comptotal();
            return;
        }
        // "Ногоон" танхим
        System.out.println("1. 10\n");
        System.out.println("2. 14\n");
        System.out.println("3. 18\n");
        int hours = scanner.nextInt();
        switch (hours){
            case 1:
                ticketPrice = 3500;
                break;
            case 2:
                ticketPrice = 4500;
                break;
            case 3:
                ticketPrice = 4500;
                break;
            default:
                System.out.println("Буруу сонголт,Дахин оруулна уу.");
                return;
        }
        Comptotal();
        return;
    }
    public static void main(String[] args) {
        new Hw11();
    }
}
