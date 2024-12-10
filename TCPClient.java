import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 7888);
        System.out.println("Connected to server.");

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader msg = new BufferedReader(new InputStreamReader(System.in));

        String str;

        // Initial message
        str = "Start Chat...";
        dout.writeUTF(str);
        System.out.println("Client: " + str);

        while (true) {
            // Send message to server
            System.out.print("Client: ");
            str = msg.readLine();
            dout.writeUTF(str);

            // Check for client exit condition
            if (str.equalsIgnoreCase("exit")) {
                System.out.println("You have exited the chat.");
                break;
            }

            // Read message from server
            str = din.readUTF();
            System.out.println("Server: " + str);

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
    }
}
