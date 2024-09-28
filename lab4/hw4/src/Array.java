public class Array {
    private int[] arr;
    private int size;
    private int capacity;
    public Array() {
        this.capacity = 5;
        this.arr = new int[capacity];
        this.size = 0;
    }
    public void add(int num1, int num2) {
        if (size == capacity) {
            capacity+=capacity;
            int[] newArr = new int[capacity];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
        if (Overflow(num1, num2)) {
            throw new ArithmeticException("Overflow.");
        } else {
            arr[size] = num1 + num2;
            size++;
        }
    }
    public int at(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс хэтэрсэн.");
        }
        return arr[index];
    }
    private boolean Overflow(int a, int b) {
        if ((a > 0 && b > Integer.MAX_VALUE - a) || (a < 0 && b < Integer.MIN_VALUE - a)) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Array array = new Array();
        array.add(1, 2);
        try {
            array.add(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            //Албаар overflow болгосон жишээ
            System.out.println("Exception: " + e.getMessage());
        }
        array.add(3, 4);
        System.out.println("Индекс 0 : " + array.at(0));
        System.out.println("Индекс 1 : " + array.at(1));
        //Индекс хэтрүүлсэн жишээ
        System.out.println("Индекс 2 : " + array.at(2));
    }
}
