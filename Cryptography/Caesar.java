import java.util.Scanner;

public class Caesar {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                char c = (char) (((ch - 'A' + shift) % 26) + 'A');
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch - 'a' + shift) % 26) + 'a');
                result.append(c);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a message: ");
        String message = sc.nextLine();

        System.out.println("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(message, shift);
        System.out.println("Encrypted Message: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}