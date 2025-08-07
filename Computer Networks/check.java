import java.util.Scanner;

public class check {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("22BCE3689");

        // Sender side
        System.out.println("For sender side:");
        System.out.print("Enter Data bits: ");
        String data = scanner.nextLine();
        System.out.print("Enter Generator: ");
        String generator = scanner.nextLine();

        // Append zeros to data based on generator length
        String dataWithZeros = data + "0".repeat(generator.length() - 1);

        // Convert strings to integers for division
        int dataSend = Integer.parseInt(dataWithZeros, 2);
        int generatorSend = Integer.parseInt(generator, 2);

        // Calculate remainder
        int remainder = dataSend % generatorSend;
        String binaryRemainder = Integer.toBinaryString(remainder);

        // Pad remainder with leading zeros if necessary
        binaryRemainder = "0".repeat(generator.length() - 1 - binaryRemainder.length()) + binaryRemainder;

        System.out.println("Checksum generated is: " + binaryRemainder);
        System.out.println("Message to be transmitted over network: " + (data + binaryRemainder));

        // Receiver side
        System.out.println("For receiver side:");
        System.out.print("Enter the received message: ");
        String receivedMessage = scanner.nextLine();

        // Convert received message to integer
        int dataRecv = Integer.parseInt(receivedMessage, 2);

        // Calculate the remainder on the receiver side
        int recvRemainder = dataRecv % generatorSend;
        String result = Integer.toBinaryString(recvRemainder);

        System.out.println("Remainder on receiver side: " + result);

        // Check if remainder is zero
        if (recvRemainder == 0) {
            System.out.println("No error detected in transmission.");
        } else {
            System.out.println("Error detected in transmission.");
        }

        scanner.close();
    }
}
