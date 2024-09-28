import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String id;
    String address;
    double cgpa;

    public Student(String alice, String a001, String s, double v) {
    }


    @Override
    public String toString() {
        return "Student{" +
                "Нэр = '" + name + '\'' +
                ", id = '" + id + '\'' +
                ", хаяг = '" + address + '\'' +
                ", голч = " + cgpa +
                '}';
    }
}

class SplitFileTask extends RecursiveAction {
    private static final int CHUNK_SIZE = 1000;
    private List<Student> students;
    private int start;
    private int end;
    private String outputFile;

    public SplitFileTask(List<Student> students, int start, int end, String outputFile) {
        this.students = students;
        this.start = start;
        this.end = end;
        this.outputFile = outputFile;
    }

    @Override
    protected void compute() {
        if (end - start <= CHUNK_SIZE) {
            writeToFile();
        } else {
            int mid = start + (end - start) / 2;
            invokeAll(
                    new SplitFileTask(students, start, mid, outputFile),
                    new SplitFileTask(students, mid, end, outputFile)
            );
        }
    }

    private void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            for (int i = start; i < end; i++) {
                oos.writeObject(students.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class filehuvaah {
    public static void main(String[] args) {
        String inputFile = "src//student.dat";
        String outputPrefix = "output";
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        List<Student> students = readStudentsFromFile(inputFile);
        ForkJoinPool forkJoinPool = new ForkJoinPool(numOfThreads);
        SplitFileTask splitFileTask = new SplitFileTask(students, 0, students.size(), outputPrefix);
        forkJoinPool.invoke(splitFileTask);
    }

    private static List<Student> readStudentsFromFile(String inputFile) {
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile))) {
            while (true) {
                try {
                    Student student = (Student) ois.readObject();
                    students.add(student);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }
}
