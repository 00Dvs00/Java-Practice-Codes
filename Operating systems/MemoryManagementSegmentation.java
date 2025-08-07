import java.util.Scanner;

public class MemoryManagementSegmentation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ms, np;
        int[][] segmentTable = new int[11][2];  
        int[] segments = new int[11];

        System.out.print("Enter memory size: ");
        ms = scanner.nextInt();

        System.out.print("Enter number of processes: ");
        np = scanner.nextInt();

        int remainingMemory = ms;

        for (int i = 1; i <= np; i++) {
            System.out.printf("Number of segments for process %d: ", i);
            segments[i] = scanner.nextInt();

            for (int j = 0; j < segments[i]; j++) {
                System.out.printf("Enter base address and length for segment %d of process %d: ", j, i);
                int base = scanner.nextInt();
                int length = scanner.nextInt();

                if (length > remainingMemory) {
                    System.out.println("Insufficient memory for this segment.");
                    break;
                }
                
                remainingMemory -= length;
                segmentTable[j][0] = base;
                segmentTable[j][1] = length;
            }
        }

        System.out.println("Enter logical address details:");
        System.out.print("Process number, segment number, offset: ");
        
        int procNum = scanner.nextInt();
        int segmentNum = scanner.nextInt();
        int offset = scanner.nextInt();
        if (procNum > np || segmentNum >= segments[procNum] || offset >= segmentTable[segmentNum][1]) {
            System.out.println("Invalid process, segment number, or offset.");
        } else {
            int pa = segmentTable[segmentNum][0] + offset;
            System.out.printf("Physical Address: %d\n", pa);
        }

        scanner.close();
    }
}
