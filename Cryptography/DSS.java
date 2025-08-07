import java.util.Scanner;
import java.math.BigInteger;

public class DSS {
    private BigInteger p;  
    private BigInteger q;  
    private BigInteger g;  
    private BigInteger a;  
    private BigInteger y;  
    
    public DSS(BigInteger p, BigInteger q, BigInteger g, BigInteger a) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.a = a;
        this.y = g.modPow(a, p); 
    }
    
    private BigInteger simpleHash(String message) {
        BigInteger hash = BigInteger.ZERO;
        for (char c : message.toCharArray()) {
            hash = hash.multiply(BigInteger.valueOf(25))
                      .add(BigInteger.valueOf(c))
                      .mod(q);
        }
        return hash;
    }
    
    public BigInteger[] signMessage(String message, BigInteger k) {
        BigInteger r = g.modPow(k, p).mod(q);
        BigInteger h = simpleHash(message);
        BigInteger s = h.add(a.multiply(r))
                       .multiply(k.modInverse(q))
                       .mod(q);
        
        System.out.println("Generated hash value: " + h);
        return new BigInteger[] {r, s};
    }
    
    public boolean verifySignature(String message, BigInteger[] signature) {
        BigInteger r = signature[0];
        BigInteger s = signature[1];
        
        if (r.compareTo(BigInteger.ZERO) <= 0 || r.compareTo(q) >= 0 ||
            s.compareTo(BigInteger.ZERO) <= 0 || s.compareTo(q) >= 0) {
            return false;
        }
        
        BigInteger w = s.modInverse(q);
        System.out.println("Compute w = "+w);
        BigInteger h = simpleHash(message);
        BigInteger u1 = h.multiply(w).mod(q);
        System.out.println("Compute u1 = "+u1);
        BigInteger u2 = r.multiply(w).mod(q);
        System.out.println("Compute u2 = "+u2);
        BigInteger X = g.modPow(u1, p)
                        .multiply(y.modPow(u2, p))
                        .mod(p);
        System.out.println("Compute X = "+X);        
        BigInteger v = X.mod(q);
        System.out.println("Compute v = "+v);
        return v.equals(r);
    }
    
    public BigInteger[] getPublicKey() {
        return new BigInteger[] {p, q, g, y};
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter prime modulus p: ");
        BigInteger p = new BigInteger(scanner.nextLine());
        
        System.out.print("Enter prime divisor q: ");
        BigInteger q = new BigInteger(scanner.nextLine());
        
        System.out.print("Enter generator g: ");
        BigInteger g = new BigInteger(scanner.nextLine());
        
        System.out.print("Enter private key a: ");
        BigInteger a = new BigInteger(scanner.nextLine());
        
        DSS dss = new DSS(p, q, g, a);
        
        System.out.print("Enter message to sign: ");
        String message = scanner.nextLine();
        
        System.out.print("Enter random number k: ");
        BigInteger k = new BigInteger(scanner.nextLine());

        System.out.println("\nSENDERS SIDE");
        
        BigInteger[] signature = dss.signMessage(message, k);
        System.out.println("Signature: r=" + signature[0] + ", s=" + signature[1]);
        
        System.out.println("\nRECEIVER's SIDE");

        BigInteger[] publicKey = dss.getPublicKey();
        System.out.println("Public key components: p=" + publicKey[0] + 
                          ", q=" + publicKey[1] + 
                          ", g=" + publicKey[2] + 
                          ", A(Public Key)=" + publicKey[3]);

        boolean isValid = dss.verifySignature(message, signature);
        System.out.println("Signature valid: " + isValid);
        
        System.out.println("\nCHECK TAMPERING");
        System.out.print("Enter a different message to test tampering: ");
        String tampered = scanner.nextLine();
        boolean isTamperedValid = dss.verifySignature(tampered, signature);
        System.out.println("signature valid? : " + isTamperedValid);
        scanner.close();
    }
}
