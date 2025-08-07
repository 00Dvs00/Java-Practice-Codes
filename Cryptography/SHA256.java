import java.util.Scanner;

public class SHA256 {
    
    static int[] K;
    static {
        K = generateKTable();
    }

    public static int[] generateKTable() {
        int[] primes = new int[64];
        int count = 0, candidate = 2;
        while (count < 64) {
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(candidate); i++) {
                if (candidate % i == 0) { 
                    isPrime = false; 
                    break; 
                }
            }
            if (isPrime) { 
                primes[count++] = candidate; 
            }
            candidate++;
        }
        int[] K = new int[64];
        for (int i = 0; i < 64; i++) {
            double cubeRoot = Math.cbrt(primes[i]);
            double frac = cubeRoot - Math.floor(cubeRoot);
            long value = (long)Math.floor(frac * (1L << 32));
            K[i] = (int)(value & 0xffffffffL);
        }
        return K;
    }
    
    public static byte[] padMessage(byte[] message) {
        int originalLength = message.length;
        long bitLength = originalLength * 8L;  

        int newLength = originalLength + 1;
        int mod = newLength % 64;
        int padBytes = (mod <= 56) ? (56 - mod) : (64 + 56 - mod);
        int totalLength = originalLength + 1 + padBytes + 8;
        byte[] padded = new byte[totalLength];

        for (int i = 0; i < originalLength; i++) {
            padded[i] = message[i];
        }
        padded[originalLength] = (byte) 0x80;
        for (int i = 0; i < 8; i++) {
            padded[totalLength - 1 - i] = (byte)(bitLength >>> (8 * i));
        }
        return padded;
    }

    public static int[][] createBlocks(byte[] paddedMessage) {
        int numBlocks = paddedMessage.length / 64;
        int[][] blocks = new int[numBlocks][16];
        
        for (int i = 0; i < numBlocks; i++) {
            for (int j = 0; j < 16; j++) {
                int index = i * 64 + j * 4;
                blocks[i][j] = ((paddedMessage[index] & 0xff) << 24) |
                               ((paddedMessage[index + 1] & 0xff) << 16) |
                               ((paddedMessage[index + 2] & 0xff) << 8) |
                               (paddedMessage[index + 3] & 0xff);
            }
        }
        return blocks;
    }

    public static int rightRotate(int x, int n) {
        return (x >>> n) | (x << (32 - n));
    }

    public static int[] generateMessageSchedule(int[] block) {
        int[] w = new int[64];
        for (int i = 0; i < 16; i++) {
            w[i] = block[i];
        }
        for (int i = 16; i < 64; i++) {
            int s0 = rightRotate(w[i - 15], 7) ^ rightRotate(w[i - 15], 18) ^ (w[i - 15] >>> 3);
            int s1 = rightRotate(w[i - 2], 17) ^ rightRotate(w[i - 2], 19) ^ (w[i - 2] >>> 10);
            w[i] = w[i - 16] + s0 + w[i - 7] + s1;
        }
        return w;
    }

    public static int ch(int x, int y, int z) {
        return (x & y) ^ ((~x) & z);
    }
    
    public static int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }
    
    public static int bigSigma0(int x) {
        return rightRotate(x, 2) ^ rightRotate(x, 13) ^ rightRotate(x, 22);
    }
    
    public static int bigSigma1(int x) {
        return rightRotate(x, 6) ^ rightRotate(x, 11) ^ rightRotate(x, 25);
    }

    public static int[] compressBlock(int[] w, int[] hash) {
        int a = hash[0], b = hash[1], c = hash[2], d = hash[3];
        int e = hash[4], f = hash[5], g = hash[6], h = hash[7];
        
        for (int i = 0; i < 64; i++) {
            int t1 = h + bigSigma1(e) + ch(e, f, g) + K[i] + w[i];
            int t2 = bigSigma0(a) + maj(a, b, c);
            h = g;
            g = f;
            f = e;
            e = d + t1;
            d = c;
            c = b;
            b = a;
            a = t1 + t2;
        }
        
        hash[0] += a;
        hash[1] += b;
        hash[2] += c;
        hash[3] += d;
        hash[4] += e;
        hash[5] += f;
        hash[6] += g;
        hash[7] += h;
        
        return hash;
    }

    public static String toHexString(int[] hash) {
        StringBuilder sb = new StringBuilder();
        for (int value : hash) {
            sb.append(String.format("%08x", value));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.print("Enter the PlainText: ");
        Scanner input = new Scanner(System.in);
        String message = input.next();
        byte[] messageBytes = message.getBytes();

        byte[] padded = padMessage(messageBytes);
        
        int[][] blocks = createBlocks(padded);
        
        int[] hash = {
            0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
            0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
        };
        
        for (int i = 0; i < blocks.length; i++) {
            int[] w = generateMessageSchedule(blocks[i]);
            hash = compressBlock(w, hash);
        }
        
        System.out.println("SHA-256: " + toHexString(hash));
        input.close();
    }
}
