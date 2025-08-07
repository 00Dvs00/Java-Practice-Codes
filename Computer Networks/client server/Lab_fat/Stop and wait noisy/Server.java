import java.net.*;  
import java.io.*;
public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(6969);
            server.setReuseAddress(true);
            Socket client = server.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String str;
            int count = 1;
            while (!(str = input.readLine()).equals("End")) {
                if(str.equals("2")){
                    System.out.println("--Timeout");
                }
                if(str.equals("7")){
                    System.out.println("Received Frame "+ count);
                    System.out.println("--Timeout");
                    System.out.println("Received Frame "+ count);
                    System.out.println("Duplicate, Discard Acknowledgement: "+ count);
                }
                else{
                    System.out.println("Received Frame "+ count);
                    System.out.println("Acknowledgement: "+ count);
                    count++;
                }
            }
        } 
        catch(Exception e){
            e.getStackTrace();
        } 
    }
}
