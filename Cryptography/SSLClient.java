import java.io.*;
import java.security.*;
import javax.net.ssl.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SSLClient {
    public static void main(String[] args) {
        try {
            // Load the client's truststore
            KeyStore ts = KeyStore.getInstance("JKS");
            ts.load(new FileInputStream("client.truststore"), "password".toCharArray());

            // Initialize the TrustManagerFactory with the truststore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ts);

            // Create and initialize the SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            // Create the socket factory
            SSLSocketFactory factory = sslContext.getSocketFactory();

            // Connect to the server on localhost:8443
            try (SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 8443);
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                
                // Log the connection
                String serverAddress = socket.getInetAddress().toString();
                System.out.println(getTimestamp() + " Connected to server: " + serverAddress);
                
                // Send message to server
                String message = "Hello from client";
                writer.println(message);
                System.out.println(getTimestamp() + " Sent to server: " + message);
                
                // Read response from server
                String response = reader.readLine();
                System.out.println(getTimestamp() + " Received from server: " + response);
            }
        } catch (Exception e) {
            System.err.println(getTimestamp() + " Client connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to get the current timestamp
    private static String getTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + LocalDateTime.now().format(dtf) + "]";
    }
}