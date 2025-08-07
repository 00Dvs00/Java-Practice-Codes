import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client Started");
            Socket socket = new Socket("LocalHost",6969);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));    
            PrintWriter send = new PrintWriter(socket.getOutputStream(), true);
            String str = input.readLine();
            for(int i=0; i<str.length();i++){
                send.println(str.charAt(i));
                System.out.println("Sent Frame "+(i+1));
                if(i == str.length() - 1){
                    send.println("End");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
