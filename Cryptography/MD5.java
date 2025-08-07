import java.util.Arrays;
import java.util.*;

public class MD5 {

    public static byte[] stringToBytes(String input) {
        try {
            return input.getBytes("US-ASCII");
        } catch (Exception e) {
            return null;
        }
    }
    
    public static byte[] padMessage(byte[] message) {
        int originalLength = message.length;
        long bitLength = (long) originalLength * 8;
        int newLength = originalLength + 1;
        while (newLength % 64 != 56) {
            newLength++;
        }
        byte[] padded = new byte[newLength + 8];
        System.arraycopy(message, 0, padded, 0, originalLength);
        padded[originalLength] = (byte) 0x80;
        for (int i = 0; i < 8; i++) {
            padded[newLength + i] = (byte) ((bitLength >>> (8 * i)) & 0xFF);
        }
        return padded;
    }
    
    public static int[] initializeBuffers() {
        return new int[]{
            0x67452301, 
            0xefcdab89, 
            0x98badcfe, 
            0x10325476 
        };
    }
    
    private static int F(int x, int y, int z) {
        return (x & y) | (~x & z);
    }
    private static int G(int x, int y, int z) {
        return (x & z) | (y & ~z);
    }
    private static int H(int x, int y, int z) {
        return x ^ y ^ z;
    }
    private static int I(int x, int y, int z) {
        return y ^ (x | ~z);
    }
    
    private static int leftRotate(int x, int s) {
        return (x << s) | (x >>> (32 - s));
    }
    
    public static int[] processBlock(byte[] block, int A, int B, int C, int D) {
        int[] M = new int[16];
        for (int i = 0; i < 16; i++) {
            M[i] = ((block[i * 4] & 0xff)) |
                   ((block[i * 4 + 1] & 0xff) << 8) |
                   ((block[i * 4 + 2] & 0xff) << 16) |
                   ((block[i * 4 + 3] & 0xff) << 24);
        }
        
        int a = A, b = B, c = C, d = D;
        
        int[] s = {
            7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21
        };
        
        int[] K = new int[64];
        for (int i = 0; i < 64; i++) {
            K[i] = (int) ((long) (Math.abs(Math.sin(i + 1)) * (1L << 32)));
        }
        
        for (int i = 0; i < 64; i++) {
            int f = 0, g = 0;
            if (i < 16) {
                f = F(b, c, d);
                g = i;
            } else if (i < 32) {
                f = G(b, c, d);
                g = (5 * i + 1) % 16;
            } else if (i < 48) {
                f = H(b, c, d);
                g = (3 * i + 5) % 16;
            } else {
                f = I(b, c, d);
                g = (7 * i) % 16;
            }
            int temp = d;
            d = c;
            c = b;
            b = b + leftRotate(a + f + K[i] + M[g], s[i]);
            a = temp;
        }
        
        A += a;
        B += b;
        C += c;
        D += d;
        return new int[]{A, B, C, D};
    }
    
    public static String intToLittleEndianHex(int value) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int byteVal = value & 0xff;
            String hexByte = Integer.toHexString(byteVal);
            if (hexByte.length() == 1) {
                hex.append("0");
            }
            hex.append(hexByte);
            value >>>= 8;
        }
        return hex.toString();
    }
    
    public static String toHexString(int A, int B, int C, int D) {
        return intToLittleEndianHex(A) + intToLittleEndianHex(B) +
               intToLittleEndianHex(C) + intToLittleEndianHex(D);
    }
    
    public static void main(String[] args) {
        System.out.print("Enter the PlainText: ");
        Scanner input = new Scanner(System.in);
        String plaintext = input.next();

        System.out.println("Plaintext: " + plaintext);
        
        byte[] message = stringToBytes(plaintext);
        System.out.println("Message Bytes: " + Arrays.toString(message));
        
        byte[] paddedMessage = padMessage(message);
        System.out.println("Padded Message Length: " + paddedMessage.length + " bytes");
        
        int[] buffers = initializeBuffers();
        int A = buffers[0], B = buffers[1], C = buffers[2], D = buffers[3];
        System.out.printf("Initial Buffers: A=%08x, B=%08x, C=%08x, D=%08x%n", A, B, C, D);
        
        for (int i = 0; i < paddedMessage.length / 64; i++) {
            byte[] block = Arrays.copyOfRange(paddedMessage, i * 64, (i + 1) * 64);
            int[] result = processBlock(block, A, B, C, D);
            A = result[0];
            B = result[1];
            C = result[2];
            D = result[3];
        }
        System.out.printf("Buffers after processing: A=%08x, B=%08x, C=%08x, D=%08x%n", A, B, C, D);
        
        String md5Hash = toHexString(A, B, C, D);
        System.out.println("Final MD5 Hash: " + md5Hash);
        input.close();
    }
}
