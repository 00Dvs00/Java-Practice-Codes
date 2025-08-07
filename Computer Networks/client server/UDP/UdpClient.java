import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UdpClient {

  public static void main(String[] args) throws IOException {
    byte[] sendData;
    DatagramSocket clientSocket = new DatagramSocket();
    Scanner input = new Scanner(System.in);
    while (true) {
      String cmd = input.nextLine();
      if (cmd.equals("QUIT")) {
        clientSocket.close();
        System.exit(1);
      }
      sendData = cmd.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 3689);
      clientSocket.send(sendPacket);
    }
  }
}