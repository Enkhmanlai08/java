public class ComplexNumber<T extends Number> {
    private T real;
    private T imaginary;
    public ComplexNumber(T real, T imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public T getReal() {
        return real;
    }
    public void setReal(T real) {
        this.real = real;
    }
    public T getImaginary() {
        return imaginary;
    }
    public void setImaginary(T imaginary) {
        this.imaginary = imaginary;
    }
    public ComplexNumber<?> add(ComplexNumber<?> other) {
        double newReal = this.real.doubleValue() + other.real.doubleValue();
        double newImaginary = this.imaginary.doubleValue() + other.imaginary.doubleValue();
        return new ComplexNumber<>(newReal, newImaginary);
    }
    public ComplexNumber<?> subtract(ComplexNumber<?> other) {
        double newReal = this.real.doubleValue() - other.real.doubleValue();
        double newImaginary = this.imaginary.doubleValue() - other.imaginary.doubleValue();
        return new ComplexNumber<>(newReal, newImaginary);
    }
    public void print() {
        System.out.println(this.real + " + " + this.imaginary + "i");
    }
    public static void main(String[] args) {
        ComplexNumber<Double> complex1 = new ComplexNumber<>(3.0, 4.0);
        ComplexNumber<Double> complex2 = new ComplexNumber<>(1.0, 2.0);
        System.out.println("Комплекс тоо 1 : ");
        complex1.print();
        System.out.println("Комплекс тоо 2 : ");
        complex2.print();
        ComplexNumber<?> sum = complex1.add(complex2);
        ComplexNumber<?> difference = complex1.subtract(complex2);
        System.out.println("Нийлбэр : ");
        sum.print();
        System.out.println("Ялгавар : ");
        difference.print();
    }
}
