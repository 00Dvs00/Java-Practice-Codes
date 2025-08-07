import java.io.*;
import java.security.*;
import javax.net.ssl.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SSLServer {
    public static void main(String[] args) {
        try {
            // Load the server's keystore
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("server.jks"), "password".toCharArray());

            // Initialize the KeyManagerFactory with the keystore
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, "password".toCharArray());

            // Create and initialize the SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);

            // Create the server socket factory
            SSLServerSocketFactory factory = sslContext.getServerSocketFactory();

            // Create the server socket on port 8443
            try (SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(8443)) {
                System.out.println(getTimestamp() + " Server is listening on port 8443...");

                // Continuously accept client connections
                while (true) {
                    try (SSLSocket socket = (SSLSocket) serverSocket.accept();
                         BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                        
                        // Log the connection
                        String clientAddress = socket.getInetAddress().toString();
                        System.out.println(getTimestamp() + " Accepted connection from " + clientAddress);
                        
                        // Read message from client
                        String line = reader.readLine();
                        System.out.println(getTimestamp() + " Received from client (" + clientAddress + "): " + line);
                        
                        // Send response to client
                        String response = "Hello from server";
                        writer.println(response);
                        System.out.println(getTimestamp() + " Sent to client (" + clientAddress + "): " + response);
                    } catch (IOException e) {
                        System.err.println(getTimestamp() + " Error handling client connection: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(getTimestamp() + " Server setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to get the current timestamp
    private static String getTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + LocalDateTime.now().format(dtf) + "]";
    }
}