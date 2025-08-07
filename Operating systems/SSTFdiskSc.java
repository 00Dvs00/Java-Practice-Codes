import java.util.Scanner;

public class SSTFdiskSc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of requests: ");
        int n = sc.nextInt(), totalMovement = 0;
        System.out.print("Enter current head position: ");
        int head = sc.nextInt();
        int[] requests = new int[n]; boolean[] visited = new boolean[n];
        System.out.print("Enter requests: ");
        for (int i = 0; i < n; i++) requests[i] = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            int minDist = Integer.MAX_VALUE, idx = -1;
            for (int j = 0; j < n; j++)
                if (!visited[j] && Math.abs(head - requests[j]) < minDist) {
                    minDist = Math.abs(head - requests[j]);
                    idx = j;
                }
            totalMovement += minDist;
            head = requests[idx];
            visited[idx] = true;
        }
        System.out.println("Total head movements: " + totalMovement);
        sc.close();
    }
}
