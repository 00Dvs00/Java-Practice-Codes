import java.util.Scanner;

public class MemoryManagementPaging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int ms, ps, nop, np, rempages;
        int[] s = new int[11]; 
        int[][] fno = new int[11][20];

        System.out.print("Enter memory size: ");
        ms = scanner.nextInt();

        System.out.print("Enter page size: ");
        ps = scanner.nextInt();

        nop = ms / ps;
        System.out.printf("Total pages available: %d\n", nop);

        System.out.print("Enter number of processes: ");
        np = scanner.nextInt();
        rempages = nop;

        for (int i = 1; i <= np; i++) {
            System.out.printf("Pages required for process %d: ", i);
            s[i] = scanner.nextInt();

            if (s[i] > rempages) {
                System.out.println("Insufficient memory for this process.");
                break;
            }
            rempages -= s[i];

            System.out.printf("Enter page table for process %d:\n", i);
            for (int j = 0; j < s[i]; j++) {
                fno[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter logical address details:");
        System.out.print("Process number, page number, offset: ");
        
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int offset = scanner.nextInt();

        if (x > np || y >= s[x] || offset >= ps) {
            System.out.println("Invalid process, page number, or offset.");
        } else {
            int pa = fno[x][y] * ps + offset;
            System.out.printf("Physical Address: %d\n", pa);
        }

        scanner.close();
    }
}
