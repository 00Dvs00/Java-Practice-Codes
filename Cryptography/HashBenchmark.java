import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashBenchmark {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        int[] sizes = {1024, 10 * 1024, 100 * 1024, 1024 * 1024, 10 * 1024 * 1024};
        int iterations = 100;

        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

        Random random = new Random();

        System.out.printf("%-12s %-15s %-15s%n", "Size (Bytes)", "MD5 (ms)", "SHA-256 (ms)");

        for (int size : sizes) {
            byte[] message = new byte[size];
            random.nextBytes(message);

            for (int i = 0; i < 10; i++) {
                md5Digest.update(message);
                md5Digest.digest();
                sha256Digest.update(message);
                sha256Digest.digest();
            }

            long md5Start = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                md5Digest.update(message);
                md5Digest.digest();
            }
            long md5Time = (System.nanoTime() - md5Start) / 1_000_000; 

            long sha256Start = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                sha256Digest.update(message);
                sha256Digest.digest();
            }
            long sha256Time = (System.nanoTime() - sha256Start) / 1_000_000; 

            System.out.printf("%-12d %-15d %-15d%n", size, md5Time, sha256Time);
        }
    }
}
