import java.util.*;

public class Parity_Checker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the data(bits): ");
        String data = input.nextLine();

        System.out.print("Choose parity type (even/odd): ");
        String parityType = input.nextLine().toLowerCase();

        int ones = 0;
        for (char bit : data.toCharArray()) {
            if (bit == '1') ones++;
        }

        boolean isEvenParity = parityType.equals("even");
        int parityBit = (ones % 2 == 0) ? (isEvenParity ? 0 : 1) : (isEvenParity ? 1 : 0);
        String dataWithParity = data + parityBit;
        System.out.println("Data with parity bit: " + dataWithParity);

        System.out.print("Enter bit position you want to change: ");
        int index = input.nextInt();
        System.out.print("Enter the bit value: ");
        char value = input.next().charAt(0);

        StringBuilder modifiedData = new StringBuilder(dataWithParity);
        modifiedData.setCharAt(index, value);

        ones = 0;
        for (char bit : modifiedData.toString().toCharArray()) {
            if (bit == '1') ones++;
        }

        boolean isValid = (isEvenParity && ones % 2 == 0) || (!isEvenParity && ones % 2 != 0);
        System.out.println(isValid ? "Data accepted" : "Data rejected");

        input.close();
    }
}
