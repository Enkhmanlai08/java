import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentGenerator {
    public static void main(String[] args) {
        List<Student> students = generateTestData();
        saveStudentsToFile(students, "src//student.dat");
    }

    private static List<Student> generateTestData() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", "A001", "123 Main St", 3.8));
        students.add(new Student("Bob", "B002", "456 Oak St", 3.5));
        students.add(new Student("Charlie", "C003", "789 Pine St", 4.0));

        return students;
    }

    private static void saveStudentsToFile(List<Student> students, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Student student : students) {
                oos.writeObject(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
