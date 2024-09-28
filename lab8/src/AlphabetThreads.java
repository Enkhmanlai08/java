public class AlphabetThreads {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintChar('A', 1));
        Thread thread2 = new Thread(new PrintChar('B', 2));
        Thread thread3 = new Thread(new PrintChar('C', 3));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class PrintChar implements Runnable {
    private static final int PRINT_COUNT = 50;
    private char charToPrint;
    private int threadNumber;

    public PrintChar(char charToPrint, int threadNumber) {
        this.charToPrint = charToPrint;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            System.out.print(charToPrint+" ");
            charToPrint = (char) (charToPrint + 1);
            if (charToPrint > 'Z') {
                charToPrint = (char) (charToPrint - 26);
            }
        }
    }
}
