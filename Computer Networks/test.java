import java.util.Scanner;

public class test {

    public static void AckLost(int index, int frames,int count,int windowindex, int size){
        for (int i = index-1; i < frames; i++) {
            System.out.println("Frame "+(i+1)+" Sent"+"\t\t"+"Received frame "+(i+1)+" ,ACK sent ");
            windowindex++;
            count++;
            if( count == size){
                System.out.print("\n");
                count = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter frame size : ");
        int frames = input.nextInt();
        System.out.println("Enter window size: ");
        int size = input.nextInt();
        int windowindex = 0; int count = 0;
        System.out.print("\nServer\t\t\tClient\n");
        for (int i = 0; i < frames; i++) {
            System.out.println("Frame "+(i+1)+" Sent"+"\t\t"+"Received frame "+(i+1)+" ,ACK sent ");
            windowindex++;
            count++;
            if( count == size){
                System.out.print("\n");
                count = 0;
            }
            if(i==4){
                System.out.println("***Acklost for frame "+(i+1));
                count = 0;
                AckLost(windowindex,frames,count,windowindex,size);
                break;  
            }
        }
    }
}
