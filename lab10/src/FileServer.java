import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    private final int port;
    private final String filePath;
    //Байгуулагч нь файл нэр, порт дугаар хүлээн авна
    public FileServer(int port, String filePath) {
        this.port = port;
        this.filePath = filePath;
    }
    //server-ийг эхлүүлэн тус тусд нв тред үүсгэх функц
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Холболт амжилттай. " + clientSocket.getInetAddress());
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    //Файлыг stream ашиглан 1000 байтаар салган 200мл секунд хүлээнэ.
    private void handleClient(Socket clientSocket) {
        try (InputStream inputStream = new BufferedInputStream(clientSocket.getInputStream());
             OutputStream outputStream = new BufferedOutputStream(clientSocket.getOutputStream())) {
            byte[] fileNameBytes = new byte[1024];
            int bytesRead = inputStream.read(fileNameBytes);
            String fileName = new String(fileNameBytes, 0, bytesRead);
            File file = new File(filePath + File.separator + fileName);
            if (file.exists()) {
                long fileSize = file.length();
                byte[] fileSizeBytes = String.valueOf(fileSize).getBytes();
                outputStream.write(fileSizeBytes);
                outputStream.flush();
                byte[] buffer = new byte[1000];
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    int count;
                    while ((count = fileInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, count);
                        outputStream.flush();
                        Thread.sleep(200);
                    }
                }
            } else {
                String errorMessage = "Файл олдсонгүй.";
                byte[] errorBytes = errorMessage.getBytes();
                outputStream.write(errorBytes);
            }
            clientSocket.close();

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        int port = 11111;
        String filePath = "Homework-10-Networking.pdf";
        FileServer fileServer = new FileServer(port, filePath);
        fileServer.startServer();
    }
}
