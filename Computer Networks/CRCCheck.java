import java.util.Scanner;

public class CRCCheck {

    private static String performXOR(String dataWithZeros, String generator) {
        int generatorLength = generator.length();
        char[] dataArr = dataWithZeros.toCharArray();
        for (int i = 0; i <= (dataArr.length - generatorLength); ) {
            if (dataArr[i] == '1') {
                for (int j = 0; j < generatorLength; j++) {
                    dataArr[i + j] = (dataArr[i + j] == generator.charAt(j)) ? '0' : '1';
                }
            }
            i = i < dataArr.length && dataArr[i] == '0' ? i + 1 : i;
        }
        return new String(dataArr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("22BCE3689");
        System.out.println("For sender side:");
        System.out.print("Enter Data bits: ");
        String data = scanner.nextLine();
        System.out.print("Enter Generator: ");
        String generator = scanner.nextLine();

        String dataWithZeros = data + "0".repeat(generator.length() - 1);
        
        String checksum = performXOR(dataWithZeros, generator).substring(dataWithZeros.length() - generator.length() + 1);

        System.out.println("Checksum generated is: " + checksum);
        System.out.println("Message to be Transmitted over network: " + data + checksum);

        System.out.println("For receiver side:");
        System.out.print("Enter the received message: ");
        String receivedMessage = scanner.nextLine();

        String result = performXOR(receivedMessage, generator);
        if (result.substring(result.length() - generator.length() + 1).contains("1")) {
            System.out.println("Error in communication");
        } else {
            System.out.println("No Error!");
        }

        scanner.close();
    }
}
