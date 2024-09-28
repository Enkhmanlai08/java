import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelArray {
    public static void parallelAssignValues(double[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        RandomArrayAssignmentTask task = new RandomArrayAssignmentTask(list, 0, list.length);
        pool.invoke(task);
    }
    private static class RandomArrayAssignmentTask extends RecursiveAction {
        private static final int THRESHOLD = 1000;
        private double[] list;
        private int start;
        private int end;

        public RandomArrayAssignmentTask(double[] list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (int i = start; i < end; i++) {
                    list[i] = random.nextDouble();
                }
            } else {
                int mid = (start + end) / 2;
                RandomArrayAssignmentTask leftTask = new RandomArrayAssignmentTask(list, start, mid);
                RandomArrayAssignmentTask rightTask = new RandomArrayAssignmentTask(list, mid, end);
                invokeAll(leftTask, rightTask);
            }
        }
    }

    public static void main(String[] args) {
        int arraySize = 9000000;
        double[] list = new double[arraySize];

        long startTime = System.nanoTime();
        parallelAssignValues(list);
        long parallelExecutionTime = System.nanoTime() - startTime;

        startTime = System.currentTimeMillis();
        sequentialAssignValues(list);
        long sequentialExecutionTime = System.nanoTime() - startTime;

        System.out.println("Parallel Утга оноох хугацаа : " + parallelExecutionTime + " ms");
        System.out.println("Sequential Утга оноох хугацаа : " + sequentialExecutionTime + " ms");
    }

    public static void sequentialAssignValues(double[] list) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextDouble();
        }
    }
}
