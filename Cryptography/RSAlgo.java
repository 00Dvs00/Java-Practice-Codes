import java.math.BigInteger;
import java.util.Scanner;

public class RSAlgo {

    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        return a.modInverse(m);
    }

    public static KeyPair generateKeys(BigInteger p, BigInteger q, BigInteger e) throws Exception {
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        if (!e.gcd(phi).equals(BigInteger.ONE)) {
            throw new Exception("The chosen e is not coprime with φ(n). Please choose a different e.");
        }
        BigInteger d = modInverse(e, phi);
        
        return new KeyPair(new PublicKey(n, e), new PrivateKey(n, d));
    }

    public static BigInteger encrypt(BigInteger m, PublicKey publicKey) throws Exception {
        if (m.compareTo(publicKey.n) >= 0) {
            throw new Exception("The message is too long for the chosen key size.");
        }
        return m.modPow(publicKey.e, publicKey.n);
    }

    public static BigInteger decrypt(BigInteger ciphertext, PrivateKey privateKey) {
        return ciphertext.modPow(privateKey.d, privateKey.n);
    }

    public static class PublicKey {
        public final BigInteger n;
        public final BigInteger e;
        public PublicKey(BigInteger n, BigInteger e) {
            this.n = n;
            this.e = e;
        }
        @Override
        public String toString() {
            return "(n = " + n + ", e = " + e + ")";
        }
    }

    public static class PrivateKey {
        public final BigInteger n;
        public final BigInteger d;
        public PrivateKey(BigInteger n, BigInteger d) {
            this.n = n;
            this.d = d;
        }
        @Override
        public String toString() {
            return "(n = " + n + ", d = " + d + ")";
        }
    }

    public static class KeyPair {
        public final PublicKey publicKey;
        public final PrivateKey privateKey;
        public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a prime number p: ");
            BigInteger p = new BigInteger(scanner.nextLine().trim());
            
            System.out.print("Enter a different prime number q: ");
            BigInteger q = new BigInteger(scanner.nextLine().trim());
            
            System.out.print("Enter a public exponent e : ");
            BigInteger e = new BigInteger(scanner.nextLine().trim());
            
            KeyPair keyPair = generateKeys(p, q, e);
            System.out.println("\nGenerated Keys:");
            System.out.println("Public key: " + keyPair.publicKey);
            System.out.println("Private key: " + keyPair.privateKey);

            System.out.print("\nEnter the message to encrypt: ");
            String message = scanner.nextLine();
            
            BigInteger m = new BigInteger(message.getBytes("UTF-8"));
            
            BigInteger ciphertext = encrypt(m, keyPair.publicKey);
            System.out.println("\nEncrypted ciphertext (as a BigInteger):");
            System.out.println(ciphertext);
            
            BigInteger decryptedBigInt = decrypt(ciphertext, keyPair.privateKey);
            String decryptedMessage = new String(decryptedBigInt.toByteArray(), "UTF-8");
            System.out.println("\nDecrypted message:");
            System.out.println(decryptedMessage);
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}
