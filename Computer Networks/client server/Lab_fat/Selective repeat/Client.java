import java.io.*;
import java.net.*;

public class Client{
    public static void main(String[] args) {
        try{
            Socket client = new Socket("localHost",1207);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String send = input.readLine();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}