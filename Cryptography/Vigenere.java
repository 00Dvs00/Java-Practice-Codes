import java.util.Scanner;

public class Vigenere {

    public static String encrypt(String message, String key) {
        StringBuilder encryptedMessage = new StringBuilder();
        message = message.toUpperCase();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                int encryptedChar = ((currentChar - 'A') + (key.charAt(keyIndex) - 'A')) % 26 + 'A';
                encryptedMessage.append((char) encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encryptedMessage.append(currentChar);
            }
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        encryptedMessage = encryptedMessage.toUpperCase();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char currentChar = encryptedMessage.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                int decryptedChar = ((currentChar - 'A') - (key.charAt(keyIndex) - 'A') + 26) % 26 + 'A';
                decryptedMessage.append((char) decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedMessage.append(currentChar);
            }
        }
        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key (alphabetic characters only): ");
        String key = scanner.nextLine();

        System.out.print("Enter the message to be encrypted: ");
        String message = scanner.nextLine();

        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
