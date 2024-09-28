import java.io.*;
import java.net.Socket;

public class CalculatorClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 11111;
        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Эхний тоо : ");
            writer.println(reader.readLine());
            System.out.println("2 дах тоо : ");
            writer.println(reader.readLine());
            System.out.println("Үйлдлийн тэмдэг (+, -, *, /, %) : ");
            writer.println(reader.readLine().charAt(0));
            System.out.println("Үр дүн : " + serverReader.readLine());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
