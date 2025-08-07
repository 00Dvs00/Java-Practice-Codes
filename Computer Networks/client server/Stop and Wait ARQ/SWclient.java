import java.io.*;
import java.net.*;

public class SWclient {
    private static final int PORT = 2004;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream(),true);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to receiver.");

            System.out.println("Enter data:");
            String packet = br.readLine();
            int sequence = 0;

            for (int i = 0; i <= packet.length(); i++) {
                String msg = (i == packet.length()) ? "end" : sequence + "" + packet.charAt(i);
                out.writeObject(msg);
                System.out.println("Sent: " + msg);
                String ack = (String) in.readObject();
                System.out.println("waiting for ack...");
                System.out.println("packet received by receiver");
                sequence = 1 - sequence;
            }
            System.out.println("All data sent.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
