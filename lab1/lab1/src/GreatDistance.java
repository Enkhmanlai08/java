import java.util.Scanner;
public class GreatDistance{
    int a,b;
    double per = 5.00;
    public GreatDistance(){
        Cal();
        CompLoan();
    }
    void Cal(){
        Scanner in = new Scanner(System.in);
        System.out.println("Loan Amount : ");
        a = in.nextInt();
        System.out.println("Number of Years : ");
        b = in.nextInt();
    }
    void CompLoan(){
        System.out.printf("%-20s%-20s%s", "Interest rate", "Monthly Payment", "TotalPayment\n");
        while (per <= 8.0){
            double montlyrate = per/1200;
            double monthlypay = a*montlyrate/(1-1/Math.pow(1+montlyrate,b*12));
            double total = monthlypay * b * 12;
            System.out.printf("%-20.3f%-20.2f%-20.2f\n", per, monthlypay, total);
            per+=0.125;
        }
    }
    public static void main(String args[]){
        new GreatDistance();
    }
}