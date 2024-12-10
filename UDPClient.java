import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
        byte[] sendBuffer = new byte[1024];
        byte[] receiveBuffer = new byte[1024];

        System.out.println("Connected to UDP Server.");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Send message to server
            System.out.print("Client: ");
            String clientMessage = br.readLine();
            sendBuffer = clientMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);
            clientSocket.send(sendPacket);

            // Exit condition
            if (clientMessage.equalsIgnoreCase("exit")) {
                System.out.println("You have exited the chat.");
                break;
            }

            // Receive message from server
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);
            String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server: " + serverMessage);

            // Server exit condition
            if (serverMessage.equalsIgnoreCase("exit")) {
                System.out.println("Server has exited the chat.");
                break;
            }
        }

        clientSocket.close();
    }
}
