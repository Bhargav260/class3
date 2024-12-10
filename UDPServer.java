import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveBuffer = new byte[1024];
        byte[] sendBuffer = new byte[1024];

        System.out.println("UDP Server is running and waiting for clients...");

        while (true) {
            // Receive data from client
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            serverSocket.receive(receivePacket);

            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Client: " + clientMessage);

            // Exit condition
            if (clientMessage.equalsIgnoreCase("exit")) {
                System.out.println("Client has exited the chat.");
                break;
            }

            // Send response to client
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            System.out.print("Server: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage = br.readLine();

            sendBuffer = serverMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);

            // Server exit condition
            if (serverMessage.equalsIgnoreCase("exit")) {
                System.out.println("Server has exited the chat.");
                break;
            }
        }

        serverSocket.close();
    }
}
