import java.io.*;
import java.net.*;

public class SWserver {
    private static final int PORT = 2004;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket connection = serverSocket.accept();
             ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(connection.getInputStream())) {

            System.out.println("Connection established.");
            out.writeObject("connected.");

            String packet, data = "";
            int sequence = 0;

            while (!(packet = (String) in.readObject()).equals("end")) {
                if (Integer.parseInt(packet.substring(0, 1)) == sequence) {
                    data += packet.substring(1);
                    sequence = 1 - sequence;  
                    System.out.println("Received: " + packet);
                } else {
                    System.out.println("Received duplicate data: " + packet);
                }
                out.writeObject(String.valueOf(sequence));
            }
            System.out.println("Data received: " + data);
            out.writeObject("connection ended.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
