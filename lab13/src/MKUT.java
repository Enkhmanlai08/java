import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MKUT {

    public static void main(String[] args) {
        String[] programs = {"Компьютерийн ухаан", "Програм хангамж", "Мэдээллийн технологи", "Мэдээллийн систем"};
        Map<String, Integer> programSelections = new HashMap<>();

        for (String program : programs) {
            int studentsSelected = rand();
            programSelections.put(program, studentsSelected);
        }

        System.out.println("Хөтөлбөр бүрийн элсэгчдийн тоо : ");
        for (Map.Entry<String, Integer> entry : programSelections.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        Map<String, Map<String, Integer>> cityProvinceSelections = aimgaar(programs, programSelections);

        System.out.println("\nАймгуудийн Хөтөлбөр тус бүрийн эслсэгчид : ");
        for (String program : programs) {
            Map<String, Integer> cityProvinceMap = cityProvinceSelections.get(program);
            System.out.println("\nХөтөлбөр : " + program);
            for (Map.Entry<String, Integer> entry : cityProvinceMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        Map<String, Integer> provinceSelections = findProvinceSelections(cityProvinceSelections);
        System.out.println("\nАймаг тус бүрийн эслсэгчид : ");
        for (Map.Entry<String, Integer> entry : provinceSelections.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        String maxApplicantsProgram = ihElsegch(programSelections);
        System.out.println("\nХамгийн олон элсэгчтэй хөтөлбөр : " + maxApplicantsProgram);
    }

    private static int rand() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private static Map<String, Map<String, Integer>> aimgaar(String[] programs, Map<String, Integer> programSelections) {
        Map<String, Map<String, Integer>> cityProvinceSelections = new HashMap<>();

        for (String program : programs) {
            Map<String, Integer> cityProvinceMap = new HashMap<>();
            int remainingStudents = programSelections.get(program);
            for (String cityProvince : aimag()) {
                int studentsSelected = randBetween(0, remainingStudents);
                remainingStudents -= studentsSelected;
                cityProvinceMap.put(cityProvince, studentsSelected);
            }
            cityProvinceSelections.put(program, cityProvinceMap);
        }
        return cityProvinceSelections;
    }

    private static String[] aimag() {
        return new String[]{"Дархан", "Эрдэнэт", "Хөвсгөл"};
    }

    private static String ihElsegch(Map<String, Integer> programSelections) {
        return programSelections.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No program found");
    }

    private static Map<String, Integer> findProvinceSelections(Map<String, Map<String, Integer>> cityProvinceSelections) {
        Map<String, Integer> provinceSelections = new HashMap<>();

        for (Map<String, Integer> cityProvinceMap : cityProvinceSelections.values()) {
            for (Map.Entry<String, Integer> entry : cityProvinceMap.entrySet()) {
                String province = entry.getKey();
                int studentsSelected = entry.getValue();
                provinceSelections.merge(province, studentsSelected, Integer::sum);
            }
        }

        return provinceSelections;
    }

    private static int randBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
