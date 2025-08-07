import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

class MITM {
    private static final SecureRandom random = new SecureRandom();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Public Parameters (Prime p and Base g)
        System.out.println("Enter a prime number (p): ");
        BigInteger p = scanner.nextBigInteger();
        
        System.out.println("Enter a base (g): ");
        BigInteger g = scanner.nextBigInteger();

        // Step 2: User Inputs Private Keys
        System.out.println("Enter Alice's private key: ");
        BigInteger privateAlice = scanner.nextBigInteger();

        System.out.println("Enter Bob's private key: ");
        BigInteger privateBob = scanner.nextBigInteger();

        // Step 3: Compute Public Keys
        BigInteger publicAlice = g.modPow(privateAlice, p);
        BigInteger publicBob = g.modPow(privateBob, p);

        System.out.println("\nAlice's Public Key: " + publicAlice);
        System.out.println("Bob's Public Key: " + publicBob);

        // Man-in-the-Middle Attack (Eve Intercepts)
        System.out.println("\n--- Man-in-the-Middle Attack ---");
        System.out.println("Enter Eve's private key: ");
        BigInteger privateEve = scanner.nextBigInteger();
        
        BigInteger publicEve = g.modPow(privateEve, p);
        System.out.println("Eve's Public Key: " + publicEve);

        // Eve intercepts Alice's public key and replaces it
        BigInteger fakeAlicePublic = publicEve;
        BigInteger fakeBobPublic = publicEve;

        // Alice calculates shared secret with fake Bob (actually Eve)
        BigInteger sharedAlice = fakeBobPublic.modPow(privateAlice, p);
        System.out.println("Alice's computed shared secret (incorrect): " + sharedAlice);

        // Bob calculates shared secret with fake Alice (actually Eve)
        BigInteger sharedBob = fakeAlicePublic.modPow(privateBob, p);
        System.out.println("Bob's computed shared secret (incorrect): " + sharedBob);

        // Eve computes both shared secrets
        BigInteger sharedEveAlice = publicAlice.modPow(privateEve, p);
        BigInteger sharedEveBob = publicBob.modPow(privateEve, p);
        System.out.println("\nEve's shared secret with Alice: " + sharedEveAlice);
        System.out.println("Eve's shared secret with Bob: " + sharedEveBob);

        // Step 5: Compare the shared secrets
        if (sharedEveAlice.equals(sharedAlice) && sharedEveBob.equals(sharedBob)) {
            System.out.println("\nMITM Attack Successful! Eve can decrypt messages.");
        } else {
            System.out.println("\nMITM Attack Failed.");
        }

        scanner.close();
    }
}
