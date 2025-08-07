import java.util.Scanner;

public class Hammingcode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dataBits, totalBits, parityBits;
        System.out.print("Enter the number of data bits: ");    
        dataBits = scanner.nextInt();

        parityBits = 0;
        while (dataBits + parityBits + 1 > Math.pow(2, parityBits)) {
            parityBits++;
        }

        totalBits = dataBits + parityBits;
        System.out.println("Number of parity bits to be added: " + parityBits + "\n Total Bits: " + totalBits);

        int[] data = new int[dataBits];
        System.out.println("Enter the Data Bits:");
        for (int i = 0; i < dataBits; ++i) {
            data[i] = scanner.nextInt();
        }

        System.out.print("\nData bits entered: ");
        for (int i = 0; i < dataBits; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();

        int[] encodedData = new int[totalBits + 1];
        int dataIndex = 0;
        int power = 0;

        for (int i = 1; i <= totalBits; ++i) {
            if (i == Math.pow(2, power)) {
                encodedData[i] = 0;
                ++power;
            } else {
                encodedData[i] = data[dataIndex++];
            }
        }

        System.out.print("Data bits are encoded with parity bits(0): ");
        for (int i = 1; i <= totalBits; ++i) {
            System.out.print(encodedData[i] + " ");
        }
        System.out.println();

        System.out.print("Choose parity (even/odd): ");
        String parityChoice = scanner.next().trim().toLowerCase();

        for (int i = 0; i < parityBits; ++i) {
            int position = (int) Math.pow(2, i);
            int parity = 0;
            int start = position;
            while (start <= totalBits) {
                for (int j = start; j <= totalBits && j < start + position; ++j) {
                    if (encodedData[j] == 1) {
                        parity++;
                    }
                }
                start += 2 * position;
            }
            if ("even".equals(parityChoice)) {
                encodedData[position] = (parity % 2 == 0) ? 0 : 1;
            } else if ("odd".equals(parityChoice)) {
                encodedData[position] = (parity % 2 == 0) ? 1 : 0;


            } else {
                System.out.println("Invalid parity choice. Defaulting to even.");
                encodedData[position] = (parity % 2 == 0) ? 0 : 1;
            }
        }

        System.out.print("Hamming codeword bits for " + parityChoice + " parity are: ");
        for (int i = 1; i <= totalBits; ++i) {
            System.out.print(encodedData[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
