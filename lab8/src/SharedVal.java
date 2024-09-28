import java.util.concurrent.*;
public class SharedVal {
    private static Acc acc = new Acc();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            executor.execute(new Add());
        }
        executor.shutdown();
        while (!executor.isTerminated()){
        }
        System.out.println("Sum " + acc.getsum());
    }
    private static class Add implements Runnable {
        public void run() {
            acc.plus();
            //System.out.println(acc.getsum());
        }
    }
    private static class Acc {
        private int sum = 0;
        public int getsum() {
            return sum;
        }
        public void plus() {
            synchronized (this){
                sum++;
                //System.out.println(sum);
            };
        }
    }
}