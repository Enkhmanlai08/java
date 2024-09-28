import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FetchUserData {

    public static void main(String[] args) {
        String baseUrl = "http://35.220.141.151:3069/api/user/";
        String outputFile = "user_data.json";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Iterate over user IDs from 1 to 200
            for (int userId = 1; userId <= 200; userId++) {
                String url = baseUrl + userId;

                // Make the HTTP request
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");

                // Check if the request was successful (status code 200)
                if (connection.getResponseCode() == 200) {
                    Scanner scanner = new Scanner(connection.getInputStream());
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                    scanner.close();

                    // Save the user data to the JSON file
                    writer.write(response.toString());
                    writer.newLine();

                    System.out.println("User " + userId + " data saved.");
                } else {
                    System.out.println("Failed to retrieve data for user " + userId +
                            ". Status code: " + connection.getResponseCode());
                }
            }

            writer.close();
            System.out.println("Finished fetching user data.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
