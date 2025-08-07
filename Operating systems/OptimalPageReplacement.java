import java.util.Scanner;

class OptimalPageReplacement {

    static boolean search(int key, int[] fr) {
        for (int i = 0; i < fr.length; i++)
            if (fr[i] == key)
                return true;
        return false;
    }

    static int predict(int[] pg, int[] fr, int pn, int index) {
        int res = -1, farthest = index;
        for (int i = 0; i < fr.length; i++) {
            int j;
            for (j = index; j < pn; j++) {
                if (fr[i] == pg[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }
            if (j == pn)
                return i;
        }
        return (res == -1) ? 0 : res;
    }

    static void optimalPage(int[] pg, int pn, int fn) {
        int[] fr = new int[fn];
        int hit = 0;
        int index = 0;

        System.out.println("\nIncoming         Pages");

        for (int i = 0; i < pn; i++) {
            System.out.printf("%-15d", pg[i]);

            if (search(pg[i], fr)) {
                hit++;
            } else {
                if (index < fn) {
                    fr[index++] = pg[i];
                } else {
                    int j = predict(pg, fr, pn, i + 1);
                    fr[j] = pg[i];
                }
            }

            System.out.print("[");
            for (int k = 0; k < fn; k++) {
                if (fr[k] != 0 || k < index) {
                    System.out.print(fr[k]);
                    if (k < fn - 1) System.out.print(", ");
                }
            }
            System.out.println("]");
        }

        System.out.println("\nNo. of hits = " + hit);
        System.out.println("No. of misses = " + (pn - hit));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        int pn = scanner.nextInt();
        int[] pg = new int[pn];

        System.out.println("Enter the page references:");
        for (int i = 0; i < pn; i++) {
            pg[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of frames: ");
        int fn = scanner.nextInt();

        optimalPage(pg, pn, fn);

        scanner.close();
    }
}
