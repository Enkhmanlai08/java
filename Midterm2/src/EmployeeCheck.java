import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//Ажилтан класс
class Employee {
    private String name;
    private String email;
    private Double salary;
    private Boolean gender;
//Байгуулагч функц
    public Employee(String name, String email, Double salary, Boolean gender) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.gender = gender;
    }
    public String getName(){
        return name;
    }
    public String getEmail() {
        return email;
    }

    public Double getSalary() {
        return salary;
    }

    public Boolean getGender() {
        return gender;
    }
}
//Үндсэн мэдээлэл хайх класс
public class EmployeeCheck {

    public static void main(String[] args) {
        //Файлын зам
        String FilePath = "src/example.csv";
        try {
            //Ажилтны мэдээлэл хадгалах жагсаалт
            List<Employee> employees = read(FilePath);
            //Хамгийн бага цалинтай ажилчдыг олж хадгалах
            List<String> lowest = getLow(employees);
            System.out.println("Хамгийн бага цалин авдаг ажилчдын мэйл хаягууд : \t" + lowest);
            //Бага цалинтай ажилчдаас зөвхөн эмэгтэйг нь олох
            double totalSalaryOfWomen = getTotal(employees);
            System.out.println("Хамгийн бага цалин авдаг эмэгтэй ажилчдын нийт цалин : \t" + totalSalaryOfWomen);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    //csv файлаас мэдээллийг уншиж хадгалах функц
    private static List<Employee> read(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(line -> {
                        //Мөр мөрөөр нь уншиж ажилтан object үүсгэнэ
                        String[] parts = line.split(",");
                        if (parts.length == 4) {
                            return new Employee(parts[0], parts[1], Double.parseDouble(parts[2]), Boolean.parseBoolean(parts[3]));
                        } else {
                            return null;
                        }
                    })
                    //Зөвхөн бүх мэдээлэл нь бүрэн ажилчдыг цуглуулж авна
                    .filter(employee -> employee != null)
                    .collect(Collectors.toList());
        }
    }
    //Хамгийн бага цалинтай ажилчдын мэйл хаяг олох функц
    private static List<String> getLow(List<Employee> employees) {
        //Хамгийн бага цалинг олсон
        double lowestSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
        //Бага цалинтай хүмүүсийн мэйл хаяг буцаана
        return employees.stream()
                .filter(employee -> employee.getSalary().equals(lowestSalary))
                .map(Employee::getEmail)
                .collect(Collectors.toList());
    }
    //Бага цалинтай эмэгтэй ажилчдын нийт цалинг олох функц
    private static double getTotal(List<Employee> employees) {
        //Бага цалинг олох
        double lowestSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
        //Зөвхөн эмэгтэй ажилчдыг олон нийлбэрийг нь буцаана
        return employees.stream()
                .filter(Employee::getGender).filter(employee -> employee.getSalary().equals(lowestSalary))
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}
