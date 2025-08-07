import java.util.Scanner;
import java.util.Arrays;

public class LOOK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), head = sc.nextInt(), dir = sc.nextInt(), total = 0;
        int[] req = new int[n];
        for (int i = 0; i < n; i++) req[i] = sc.nextInt();
        Arrays.sort(req);

        int i = Arrays.binarySearch(req, head); if (i < 0) i = -i - 1;

        if (dir == 1) {
            for (int j = i; j < n; j++) { total += Math.abs(head - req[j]); head = req[j]; }
            for (int j = i - 1; j >= 0; j--) { total += Math.abs(head - req[j]); head = req[j]; }
        } else {
            for (int j = i - 1; j >= 0; j--) { total += Math.abs(head - req[j]); head = req[j]; }
            for (int j = i; j < n; j++) { total += Math.abs(head - req[j]); head = req[j]; }
        }
        System.out.println(total);
        sc.close();
    }
}
