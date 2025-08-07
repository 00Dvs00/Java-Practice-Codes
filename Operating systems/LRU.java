import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LRU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int tableSize = scanner.nextInt();

        System.out.print("Enter the length of the incoming page stream: ");
        int n = scanner.nextInt();

        int[] referenceString = new int[n];

        System.out.println("Enter the incoming page stream:");
        for (int i = 0; i < n; i++) {
            referenceString[i] = scanner.nextInt();
        }

        int faults = 0;
        List<Integer> frames = new ArrayList<>();

        System.out.println("\nIncoming         Pages");

        for (int page : referenceString) {
            System.out.printf("%-15d", page);

            if (frames.contains(page)) {
                frames.remove(Integer.valueOf(page));
                frames.add(page);
            } else {
                if (frames.size() < tableSize) {
                    frames.add(page);
                } else {
                    frames.remove(0);
                    frames.add(page);
                }
                faults++;
            }
            System.out.println(frames);
        }

        int hits = n - faults;
        System.out.println("\nTotal page faults = " + faults);
        System.out.println("Total page hits = " + hits);
        scanner.close();
    }
}
