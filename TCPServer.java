import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7888);
        System.out.println("Server started, waiting for a client...");
        Socket s = ss.accept();
        System.out.println("Client connected.");

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader msg = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while (true) {
            // Read message from client
            str = din.readUTF();
            System.out.println("Client: " + str);

            // Check for exit condition
            if (str.equalsIgnoreCase("exit")) {
                System.out.println("Client has exited the chat.");
                break;
            }

            // Send message to client
            System.out.print("Server: ");
            str = msg.readLine();
            dout.writeUTF(str);

            // Check for server exit condition
            if (str.equalsIgnoreCase("exit")) {
                System.out.println("Server has exited the chat.");
                break;
            }
        }

        // Close resources
        din.close();
        dout.close();
        s.close();
        ss.close();
    }
}
