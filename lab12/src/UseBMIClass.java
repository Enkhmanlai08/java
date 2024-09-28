import java.util.function.Supplier;
public class UseBMIClass {
    public static void main(String[] args) {
        BMIFactory bmiFactory = BMI::new;
        BMI bmi1 = bmiFactory.create("Kim Yang", 18, 145, 70);
        BMI bmi2 = bmiFactory.create("Susan Kim", 20, 150, 80);
        printBMI(bmi1, BMI::getBMI, BMI::getStatus);
        printBMI(bmi2, BMI::getBMI, BMI::getStatus);
    }
    private static void printBMI(BMI bmi, Supplier<Double> bmiSupplier, Supplier<String> statusSupplier) {
        System.out.println("The BMI for " + bmi.getName() + " is " + bmiSupplier.get() + " " + statusSupplier.get());
    }
}
interface BMIFactory {
    BMI create(String name, int age, double weight, double height);
}