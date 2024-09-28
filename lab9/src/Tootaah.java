import java.util.Random;

class Tootaah {
    public static final int MAX = 100;
    private static int guessedNumber;
    private static boolean numberGuessed = false;

    public static void main(String[] args) {
        Random random = new Random();
        guessedNumber = random.nextInt(MAX);
        int numThreads = 5;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new Trader(i + 1));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Тоог таалаа. тоо : " + guessedNumber);
    }

    static synchronized boolean isNumberGuessed() {
        return numberGuessed;
    }

    static synchronized void setNumberGuessed() {
        numberGuessed = true;
    }

    static synchronized int getGuessedNumber() {
        return guessedNumber;
    }
}

class Trader implements Runnable {
    private final int traderNumber;
    private int[] guesses;
    private int attempts;

    Trader(int traderNumber) {
        this.traderNumber = traderNumber;
        this.guesses = new int[Tootaah.MAX];
        this.attempts = 0;
    }

    @Override
    public void run() {
        while (!Tootaah.isNumberGuessed()) {
            int guess = generateGuess();
            guesses[attempts++] = guess;

            if (guess == Tootaah.getGuessedNumber()) {
                System.out.println("Thread " + traderNumber + ": " + printGuesses() + " таасан ба " +
                        attempts + " удаа таах оролдлого хийв.");
                Tootaah.setNumberGuessed();
            } else {
                System.out.println("Thread " + traderNumber + ": " + guess + " таагаагүй ба " +
                        attempts + " удаа таах оролдлого хийв.");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateGuess() {
        Random random = new Random();
        return random.nextInt(Tootaah.MAX);
    }

    private String printGuesses() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < attempts; i++) {
            result.append(guesses[i]);
            if (i < attempts - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
