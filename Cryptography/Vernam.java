import java.util.Scanner;

public class Vernam {

    public static String stringEncryption(String text, String key) {
        String cipherText = "";
        int[] cipher = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            cipher[i] = text.charAt(i) - 'A' + key.charAt(i) - 'A';
        }

        for (int i = 0; i < key.length(); i++) {
            if (cipher[i] > 25) {
                cipher[i] -= 26;
            }
        }

        for (int i = 0; i < key.length(); i++) {
            cipherText += (char) (cipher[i] + 'A');
        }

        return cipherText;
    }

    public static String stringDecryption(String s, String key) {
        String plainText = "";
        int[] plain = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            plain[i] = s.charAt(i) - 'A' - (key.charAt(i) - 'A');
        }

        for (int i = 0; i < key.length(); i++) {
            if (plain[i] < 0) {
                plain[i] += 26;
            }
        }

        for (int i = 0; i < key.length(); i++) {
            plainText += (char) (plain[i] + 'A');
        }

        return plainText;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plain text: ");
        String plainText = scanner.nextLine().toUpperCase();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        if (plainText.length() != key.length()) {
            System.out.println("Error: Plain text and key must have the same length.");
            return;
        }

        String encryptedText = stringEncryption(plainText, key);
        System.out.println("Cipher Text - " + encryptedText);
        System.out.println("Message - " + stringDecryption(encryptedText, key));
    }
}
