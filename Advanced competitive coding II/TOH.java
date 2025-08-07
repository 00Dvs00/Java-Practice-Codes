import java.util.Scanner;

public class TOH {

    static void moveDisc(int n, char source, char destination) {
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
    }

    static void solveTowerOfHanoi(int n, char source, char auxiliary, char destination) {
        if (n == 1) {
            moveDisc(1, source, destination);
            return;
        }
        solveTowerOfHanoi(n - 1, source, destination, auxiliary);
        moveDisc(n, source, destination);
        solveTowerOfHanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int disks = input.nextInt();
        solveTowerOfHanoi(disks, 'S', 'A', 'D');
        input.close();
    }
}