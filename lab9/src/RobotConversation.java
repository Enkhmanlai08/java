class RobotConversation {
    private static final Object lock = new Object();
    private static boolean robot1Turn = true;

    public static void main(String[] args) {
        Robot robot1 = new Robot("Robot 1");
        Robot robot2 = new Robot("Robot 2");

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        while (!robot1Turn) {
                            lock.wait();
                        }
                        robot1.speak();
                        robot1Turn = false;
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        while (robot1Turn) {
                            lock.wait();
                        }
                        robot2.speak();
                        robot1Turn = true;
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}

class Robot {
    private final String name;

    Robot(String name) {
        this.name = name;
    }

    void speak() {
        System.out.println(name + " : " + getResponse());
    }

    private String getResponse() {
        if (name.equals("Robot 1")) {
            return robot1Responses[getNextRobot1Response()];
        } else {
            return robot2Responses[getNextRobot2Response()];
        }
    }

    private int robot1Index = 0;
    private int robot2Index = 0;

    private int getNextRobot1Response() {
        return (robot1Index++) % robot1Responses.length;
    }

    private int getNextRobot2Response() {
        return (robot2Index++) % robot2Responses.length;
    }

    private final String[] robot1Responses = {
            "Сайн уу?",
            "Таны ажил сайн биз дээ?",
            "Миний ажил маш сайн байгаа."
    };

    private final String[] robot2Responses = {
            "Сайн.",
            "Тийм ээ сайн. Харин таны ажил сайн уу?",
            "За ашгүй дээ."
    };
}
