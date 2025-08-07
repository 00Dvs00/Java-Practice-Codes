import java.util.Scanner;

public class Bankers {

    static boolean isSafe(int[] available, int[][] allocation, int[][] need, int p, int r, int[] safeSequence) {
        boolean[] finish = new boolean[p];
        int[] work = available.clone();
        int index = 0;

        for (int count = 0; count < p; count++) {
            boolean found = false;
            for (int i = 0; i < p; i++) {
                if (!finish[i] && canAllocate(work, need[i], r)) {
                    for (int j = 0; j < r; j++) work[j] += allocation[i][j];
                    finish[i] = true;
                    safeSequence[index++] = i;
                    found = true;
                }
            }
            if (!found) break; 
        }

        for (boolean f : finish) if (!f) return false;
        return true;
    }

    static boolean canAllocate(int[] work, int[] need, int r) {
        for (int i = 0; i < r; i++) if (need[i] > work[i]) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes and resources: ");
        int p = sc.nextInt(), r = sc.nextInt();

        int[] available = new int[r]; 
        int[][] max = new int[p][r]; 
        int[][] allocation = new int[p][r]; 
        int[][] need = new int[p][r];        
        int[] safeSequence = new int[p]; 

        System.out.println("Enter available resources: ");
        for (int i = 0; i < r; i++) {
            available[i] = sc.nextInt();
        }

        for (int i = 0; i < p; i++) {
            System.out.println("Enter max and allocation for P" + i + ": ");
            for (int j = 0; j < r; j++) {
                max[i][j] = sc.nextInt();
                allocation[i][j] = sc.nextInt();
                need[i][j] = max[i][j] - allocation[i][j]; 
            }
        }

        if (isSafe(available, allocation, need, p, r, safeSequence)) {
            System.out.println("System is in a safe state.");
            System.out.print("Safe sequence: ");
            for (int i = 0; i < p; i++) {
                System.out.print("P" + (safeSequence[i]+1) + (i == p - 1 ? "" : " -> "));
            }
        } else {
            System.out.println("System is in a deadlock state.");
        }
        sc.close();
    }
}