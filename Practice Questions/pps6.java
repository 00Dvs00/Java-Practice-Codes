import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

class OtherExceptions extends IOException{
    public OtherExceptions(String exception){
        super(exception);
    }
}

public class pps6 {
    public static void main(String...args) throws OtherExceptions{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file name: ");
        String name = input.nextLine();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(name));
            System.out.println(reader.readLine());
            reader.close();
        }
        catch(IOException e){
            while(true){
                System.out.println("Error Occured: " + e.getMessage());
                throw new OtherExceptions("The File does not exist in the directory");
            }

        }
    }
}