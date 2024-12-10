import java.io.*;
import java.net.*;

public class EServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        String line;
        PrintWriter ps;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(8081);
            System.out.println("Server is listening on port 8081");
            clientSocket = serverSocket.accept();

            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ps = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                line = is.readLine();
                if (line == null || line.equals("exit")) {
                    break; // Exit if client disconnects
                }
                System.out.println("Received: " + line);
                ps.println(line); // Echo the received line back to the client
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing sockets: " + e.getMessage());
            }
        }
    }
}
