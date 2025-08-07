import java.util.Scanner;

public class two_D_parity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("22BCE3689");
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter the matrix values (0 or 1):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Choose parity type: ");
        String parityType = scanner.next();

        boolean isEvenParity = parityType.equalsIgnoreCase("even");

        System.out.println("Horizontal parity bits:");
        for (int i = 0; i < rows; i++) {
            int rowParity = 0;
            for (int j = 0; j < cols; j++) {
                rowParity ^= matrix[i][j];
            }
            if (!isEvenParity) {
                rowParity ^= 1;
            }
            System.out.print(rowParity + " ");
        }
        System.out.println();

        System.out.println("Vertical parity bits:");
        for (int j = 0; j < cols; j++) {
            int colParity = 0;
            for (int i = 0; i < rows; i++) {
                colParity ^= matrix[i][j];
            }
            if (!isEvenParity) {
                colParity ^= 1;
            }
            System.out.print(colParity + " ");
        }
        System.out.println();

        int overallParity = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                overallParity ^= matrix[i][j];
            }
        }
        if (!isEvenParity) {
            overallParity ^= 1;
        }

        System.out.println("Overall parity bit: " + overallParity);

        scanner.close();
    }
}