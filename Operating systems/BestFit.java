import java.util.Scanner;

class BestFit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter the size of each memory block:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter the size of each process:");
        for (int i = 0; i < n; i++) {
            processSize[i] = scanner.nextInt();
        }

        implementBestFit(blockSize, m, processSize, n);

        scanner.close();
    }

    static void implementBestFit(int[] blockSize, int blocks, int[] processSize, int processes) {
        int[] allocate = new int[processes];
        
        for (int i = 0; i < allocate.length; i++) {
            allocate[i] = -1;
        }

        for (int i = 0; i < processes; i++) {
            int bestIdx = -1;
            for (int j = 0; j < blocks; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocate[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < processes; i++) {
            System.out.print(i + 1 + "\t\t\t" + processSize[i] + "\t\t\t");
            if (allocate[i] != -1)
                System.out.println(allocate[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }
}
