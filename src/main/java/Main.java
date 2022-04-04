import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 8090;
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.printf("New connection accepted, client port: %d\n", clientSocket.getPort());
                out.println("Write your name");
                String name = in.readLine();
                boolean exit = true;
                do {
                    out.println("Are you a child? (\"yes\" or \"no\")");
                    String answer = in.readLine();
                    switch (answer) {
                        case "yes":
                            out.printf("Welcome to the kids area, %s! Let's play!", name);
                            exit = false;
                            break;
                        case "no":
                            out.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name);
                            exit = false;
                            break;
                        default:
                            out.println("Invalid answer, try again");
                            break;
                    }
                } while (exit);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
