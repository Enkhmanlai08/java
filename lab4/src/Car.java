import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Car implements Serializable {
    String name;
    String mark;
    String number;
    static File file = new File("src\\lug.dat");
    public Car(String name, String mark, String number) {
        this.name = name;
        this.mark = mark;
        this.number = number;
    }
    public String getMark() {
        return mark;
    }
    static ArrayList<Car> cars = new ArrayList<>();
    static void workLug(){
        cars.add(new Car("Enkhmanlai","toyota","8888uba"));
        cars.add(new Car("Bataa","porche","4444uba"));
        cars.add(new Car("Galaa","nissan","2222uba"));
        cars.add(new Car("Boldoo","audi","1111uba"));
        cars.add(new Car("Huygaa","toyota","2453uba"));
    }
    static void addfile() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        for (Car lug:cars){
            out.writeObject(lug);
        }
        out.close();
    }
    public String getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }
    static void find2() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        String mark;
        Car temp;
        String tempmark;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mark oruul : ");
        mark = scanner.next();
        while (true) {
            try {
                temp = ((Car)in.readObject());
                tempmark = temp.getMark();
                if (mark.equals(tempmark)) {
                    System.out.println(temp.getName()+" "+temp.getNumber());
                }
            }
            catch (EOFException e){
                break;
            }
        }
        in.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        workLug();
        addfile();
        find2();
    }
}
