public class StepRobot {
    public static void main(String[] args) {
        Thread leftThread = new Thread(new LeftStep());
        Thread rightThread = new Thread(new RightStep());
        leftThread.start();
        rightThread.start();
    }
}

class LeftStep implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Left");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RightStep implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Right");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
