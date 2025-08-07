import java.math.BigInteger;
import java.util.Scanner;

public class Diffie {
    public static BigInteger computePublicKey(BigInteger g, BigInteger privateKey, BigInteger n) {
        return g.modPow(privateKey, n);
    }
    
    public static BigInteger computeSharedKey(BigInteger otherPublicKey, BigInteger ownPrivateKey, BigInteger n) {
        return otherPublicKey.modPow(ownPrivateKey, n);
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Diffie-Hellman Key Exchange");
        System.out.println("Select mode:");
        System.out.println("1. Standard Diffie-Hellman");
        System.out.println("2. Diffie-Hellman with Man-in-the-Middle Attack Simulation");
        System.out.print("Your choice: ");
        int mode = input.nextInt();
        System.out.print("Enter prime number n: ");
        BigInteger n = input.nextBigInteger();
        System.out.print("Enter base g: ");
        BigInteger g = input.nextBigInteger();
        
        if (mode == 1) {
            System.out.println("\n--- Standard Diffie-Hellman Exchange ---");
            System.out.print("Enter sender's private key: ");
            BigInteger senderPrivate = input.nextBigInteger();
            System.out.print("Enter receiver's private key: ");
            BigInteger receiverPrivate = input.nextBigInteger();
            BigInteger senderPublic = computePublicKey(g, senderPrivate, n);
            BigInteger receiverPublic = computePublicKey(g, receiverPrivate, n);
            System.out.println("\nSender computes public key: " + senderPublic);
            System.out.println("Receiver computes public key: " + receiverPublic);
            System.out.println("\nPublic key exchange occurs...");
            BigInteger senderSharedKey = computeSharedKey(receiverPublic, senderPrivate, n);
            BigInteger receiverSharedKey = computeSharedKey(senderPublic, receiverPrivate, n);
            System.out.println("\nSender's computed shared key: " + senderSharedKey);
            System.out.println("Receiver's computed shared key: " + receiverSharedKey);
            if (senderSharedKey.equals(receiverSharedKey)) {
                System.out.println("\nKey exchange successful. Shared symmetric key: " + senderSharedKey);
            } else {
                System.out.println("\nKey exchange failed.");
            }
        } else if (mode == 2) {
            System.out.println("\n--- Man-in-the-Middle Attack Simulation ---");
            System.out.println("Enter private keys for sender and receiver, and for attacker's two connections.");
            System.out.print("Enter sender's private key: ");
            BigInteger senderPrivate = input.nextBigInteger();
            System.out.print("Enter receiver's private key: ");
            BigInteger receiverPrivate = input.nextBigInteger();
            System.out.print("Enter attacker's private key for connection with sender: ");
            BigInteger attackerPrivateForSender = input.nextBigInteger();
            System.out.print("Enter attacker's private key for connection with receiver: ");
            BigInteger attackerPrivateForReceiver = input.nextBigInteger();
            BigInteger senderPublic = computePublicKey(g, senderPrivate, n);
            BigInteger receiverPublic = computePublicKey(g, receiverPrivate, n);
            System.out.println("\n[Sender] Public key: " + senderPublic);
            System.out.println("[Receiver] Public key: " + receiverPublic);
            System.out.println("\n[Sender] sends public key to Receiver (intercepted by Attacker).");
            System.out.println("[Receiver] sends public key to Sender (intercepted by Attacker).");
            BigInteger attackerPublicForReceiver = computePublicKey(g, attackerPrivateForReceiver, n);
            BigInteger attackerPublicForSender = computePublicKey(g, attackerPrivateForSender, n);
            System.out.println("\n[Attacker] intercepts the public keys and sends own:");
            System.out.println("Attacker sends public key to Receiver (pretending to be Sender): " + attackerPublicForReceiver);
            System.out.println("Attacker sends public key to Sender (pretending to be Receiver): " + attackerPublicForSender);
            BigInteger senderSharedKey = computeSharedKey(attackerPublicForSender, senderPrivate, n);
            BigInteger receiverSharedKey = computeSharedKey(attackerPublicForReceiver, receiverPrivate, n);
            System.out.println("\n[Sender] computes shared key (using received key): " + senderSharedKey);
            System.out.println("[Receiver] computes shared key (using received key): " + receiverSharedKey);
            BigInteger attackerSharedWithSender = computeSharedKey(senderPublic, attackerPrivateForSender, n);
            BigInteger attackerSharedWithReceiver = computeSharedKey(receiverPublic, attackerPrivateForReceiver, n);
            System.out.println("\n[Attacker] computes shared key with Sender (using sender's public key): " + attackerSharedWithSender);
            System.out.println("[Attacker] computes shared key with Receiver (using receiver's public key): " + attackerSharedWithReceiver);
            System.out.println("\nAlthough Sender and Receiver believe they share a common key, they actually share separate keys with Attacker.");
            System.out.println("Attacker can decrypt, read, and modify messages between them.");
        } else {
            System.out.println("Invalid selection. Exiting.");
        }
        input.close();
    }
}
