import java.io.*;
import java.net.*;

public class EClient {
    public static void main(String[] args) {
        Socket socket = null;
        String line;
        PrintWriter os;
        BufferedReader is;

        try {
            socket = new Socket("localhost", 8081);
            os = new PrintWriter(socket.getOutputStream(), true);
            is = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.print("Client: ");
                line = is.readLine();
                os.println(line);
                if (!line.equals("exit")) {
                    BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Server: " + serverInput.readLine());
                }
            } while (!line.equals("exit"));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
