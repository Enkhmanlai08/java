import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
    public static void main(String[] args) {
        int port = 11111;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            int num1 = Integer.parseInt(reader.readLine());
            int num2 = Integer.parseInt(reader.readLine());
            char operation = reader.readLine().charAt(0);
            int result = calculate(num1, num2, operation);
            writer.println(result);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculate(int num1, int num2, char operation) {
        switch (operation) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num2 != 0 ? num1 / num2 : 0;
            case '%': return num2 != 0 ? num1 % num2 : 0;
            default:
                System.err.println("Буруу үйлдэл : " + operation);
                return 0;
        }
    }
}
