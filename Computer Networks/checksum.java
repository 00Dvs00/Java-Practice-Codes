import java.util.Scanner;

public class checksum {
    public static byte calculateChecksum(int[] data, int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += data[i];
        }
        sum = (sum & 0xFF) + ((sum >> 8) & 0xFF);
        sum = (sum & 0xFF) + (sum >> 8);
        return (byte) ~sum;
    }

    public static String calculateParity(String input) {
        int parityCount = 0;

        for (char ch : input.toCharArray()) {
            parityCount += Integer.bitCount(ch);
        }

        return (parityCount % 2 == 0) ? "Even Parity" : "Odd Parity";
    }

    public static void main(String[] args) {
        final int size = 8; 
        int[] data = new int[size];
        Scanner scanner = new Scanner(System.in);

        System.out.println("22BCE3689");
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        for (int i = 0; i < size && i < input.length(); i++) {
            data[i] = input.charAt(i);
        }

        for (int i = input.length(); i < size; i++) {
            data[i] = 0;
        }

        byte checksum = calculateChecksum(data, size);
        System.out.println("Checksum: " + (checksum & 0xFF));

        String parity = calculateParity(input);
        System.out.println("Parity: " + parity);

        scanner.close();
    }
}
