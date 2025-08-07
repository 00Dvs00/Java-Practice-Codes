import java.io.*;
import java.net.*;
import java.sql.Timestamp;

public class UdpServer {
  public static void main(String[] args) throws IOException {
    DatagramSocket serverSocket = new DatagramSocket(3689);
    System.out.println("Server Started. Listening for Clients on port 3689" + "...");
    byte[] receiveData = new byte[4096];
    DatagramPacket receivePacket;
    while (true) {
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        String clientMessage = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println("[" + "IP: " + IPAddress + " ,Port: " + port +"]  " + clientMessage);
    }
  }
}