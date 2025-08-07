import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static long inverse(long n, long m) {
        BigInteger x = BigInteger.valueOf(n);
        BigInteger y = BigInteger.valueOf(m);
        BigInteger z = x.modInverse(y);
        return z.longValue();
    }

    public static long gcd(long a, long b) {
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }

    public static long coprimeNumber(long n) {
        for(long i = 2; i < n; i++){
            if(gcd(i, n) == 1){
                return i;
            }
        }
        return -1;
    }

    public static long encrypt(String message, long product, long e, long multiplier) {
        long crypted = 0;
        for(int i = 0; i < message.length(); i++){
            long temp = (long) message.charAt(i) - 64;
            temp = BigInteger.valueOf(temp).modPow(BigInteger.valueOf(e), BigInteger.valueOf(product)).longValue();
            crypted = crypted * multiplier + temp;
        }
        return crypted;
    }

    public static String decrypt(long encrypted, long product, long d, long multiplier) {
        String message = "";
        long num = encrypted;
        while (num > 0) {
            long block = num % multiplier;
            long temp = BigInteger.valueOf(block).modPow(BigInteger.valueOf(d), BigInteger.valueOf(product)).longValue();
            message = ((char)(temp + 64)) + message;
            num /= multiplier;
        }
        return message;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter prime numbers p and q: ");
        long num1 = input.nextLong();
        long num2 = input.nextLong();
        long product = num1 * num2;
        long phi = (num1-1)*(num2-1);
        long e = coprimeNumber(phi);
        long d = inverse(e, phi);
        
        int blockSize = String.valueOf(product).length();
        long multiplier = (long) Math.pow(10, blockSize);
        
        System.out.print("Enter message: ");
        input.nextLine(); 
        String message = input.nextLine();
        
        System.out.println("Public key [" + product + "," + e + "]");
        long encrypted = encrypt(message, product, e, multiplier);
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Private key [" + product + "," + d + "]");
        String decrypted = decrypt(encrypted, product, d, multiplier);
        System.out.println("Decrypted message: " + decrypted);
        input.close();
    }
}
