import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8090;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            System.out.println(in.readLine());
            String name = scanner.nextLine();
            out.println(name.trim());
            String response = "";
            while (!response.contains("Welcome")) {
                System.out.println(in.readLine());
                String answer = scanner.nextLine();
                out.println(answer);
                response = in.readLine().trim();
                System.out.println(response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
