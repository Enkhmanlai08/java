class LeapYearException extends Exception {
    public LeapYearException(String message) {
        super(message);
    }
}

public class lab3 {
    public static void main(String[] args) {
        for (int i = 1900; i < 2023; i++) {
            try {
                if (isLeapYear(i)) {
                    throw new LeapYearException(i + " Өндөр жил.");
                }
            } catch (LeapYearException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
}
