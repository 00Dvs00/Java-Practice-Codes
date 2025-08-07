import java.io.*;
import java.net.*;
 
public class TcpClient {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",3689);){
            while (true) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter message to transfer: ");
                String data = console.readLine();
                while(!data.isEmpty()){
                    out.println(data);
                    break;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
