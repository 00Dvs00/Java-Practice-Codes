import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(1207);
            server.setReuseAddress(true);
            Socket client = server.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}