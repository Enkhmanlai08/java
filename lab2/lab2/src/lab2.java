import java.io.IOException;
import java.util.Scanner;
public class lab2 {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a URL: ");
        //https://liveexample.pearsoncmg.com/data/Lincoln.txt
        String URLString = new Scanner(System.in).next();
        try {
            java.net.URL url = new java.net.URL(URLString);
            int count=0;
            String line = null;
            Scanner input = new Scanner(url.openStream());
            while (input.hasNext()) {
                line = input.next();
                count++;
            }
            System.out.println("niit ugiin too " + count);
            }
        catch (java.net.MalformedURLException ex) {
            System.out.println("Invalid URL");
        }
    }
}
