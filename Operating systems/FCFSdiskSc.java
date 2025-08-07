import java.util.Scanner;

public class FCFSdiskSc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of requests: ");
        int n = sc.nextInt(), totalMovement = 0;
        
        System.out.print("Enter current head position: ");
        int head = sc.nextInt(), requests[] = new int[n];
        
        System.out.print("Enter requests: ");
        for (int i = 0; i < n; i++) requests[i] = sc.nextInt();
        
        for (int req : requests) {
            totalMovement += Math.abs(head - req);
            head = req;
        }
        
        System.out.println("Total head movement: " + totalMovement);
        sc.close();
    }
}
