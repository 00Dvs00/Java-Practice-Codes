import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class SRclient {
    private static Socket connection;
    
    public static void main(String[] args) {
        DataInputStream in = null;
        DataOutputStream out = null;
        
        try {
            int[] v = new int[10];
            int p = 0;
            Random rands = new Random();
            int rand;

            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("Connecting to " + addr);
            connection = new Socket(addr, 8011);
            
            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(connection.getOutputStream());
            
            p = in.read();
            System.out.println("Number of frames: " + p);

            if (p > v.length) {
                System.out.println("Error: Number of frames exceeds array size.");
                return;
            }
            
            for (int i = 0; i < p; i++) {
                v[i] = in.read();
                System.out.println("Received frame: " + v[i]);
            }
            
            rand = rands.nextInt(p);
            v[rand] = -1;
            
            for (int i = 0; i < p; i++) {
                System.out.println("Frame status: " + (v[i] == -1 ? "Lost" : v[i]));
            }
            
            for (int i = 0; i < p; i++) {
                if (v[i] == -1) {
                    System.out.println("Requesting retransmission for frame number " + (i + 1) + "!");
                    out.write(i);
                    out.flush();
                    
                    int retransmittedFrame = in.read();
                    System.out.println("Received retransmitted frame: " + retransmittedFrame);
                    v[i] = retransmittedFrame;
                }
            }
            System.out.println("Quitting");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (connection != null) connection.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
