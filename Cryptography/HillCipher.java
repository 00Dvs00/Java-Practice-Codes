import java.util.*;

public class HillCipher {

    private static final int MOD = 26;

    public static int[][] multiplyMatrix(int[][] key, int[] block) {
        int n = key.length;
        int[][] result = new int[n][1];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                result[i][0] += key[i][k] * block[k];
            }
            result[i][0] %= MOD;
        }
        return result;
    }

    public static int determinant(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        int det = 0;
        int sign = 1;
        for (int i = 0; i < matrix.length; i++) {
            int[][] minor = new int[matrix.length - 1][matrix.length - 1];
            for (int j = 1; j < matrix.length; j++) {
                int col = 0;
                for (int k = 0; k < matrix.length; k++) {
                    if (k == i) continue;
                    minor[j - 1][col++] = matrix[j][k];
                }
            }
            det += sign * matrix[0][i] * determinant(minor);
            sign *= -1;
        }
        return det;
    }

    public static int[][] adjoint(int[][] matrix) {
        int n = matrix.length;
        int[][] adjoint = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] minor = new int[n - 1][n - 1];
                int row = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    int col = 0;
                    for (int l = 0; l < n; l++) {
                        if (l == j) continue;
                        minor[row][col++] = matrix[k][l];
                    }
                    row++;
                }
                adjoint[i][j] = determinant(minor) * ((i + j) % 2 == 0 ? 1 : -1);
            }
        }

        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = adjoint[i][j];
            }
        }
        return result;
    }

    public static String encrypt(String plaintext, int[][] key) {
        StringBuilder modifiedPlaintext = new StringBuilder(plaintext);
        int n = key.length;
        int padding = n - (modifiedPlaintext.length() % n);
        if (padding != n) {
            for (int i = 0; i < padding; i++) {
                modifiedPlaintext.append('X');
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < modifiedPlaintext.length(); i += n) {
            int[] block = new int[n];
            for (int j = 0; j < n; j++) {
                block[j] = modifiedPlaintext.charAt(i + j) - 'A';
            }
            int[][] encryptedBlock = multiplyMatrix(key, block);
            for (int[] row : encryptedBlock) {
                for (int value : row) {
                    ciphertext.append((char) (value + 'A'));
                }
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int[][] key) {
        int det = determinant(key);
        int[][] adj = adjoint(key);
        int[][] inverseKey = new int[key.length][key.length];
        int n = key.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseKey[i][j] = (adj[i][j] * det) % MOD;
                if (inverseKey[i][j] < 0) {
                    inverseKey[i][j] += MOD;
                }
            }
        }

        return encrypt(ciphertext, inverseKey);
    }

    public static void main(String[] args) {
        int[][] key = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();
        String ciphertext = encrypt(plaintext, key);
        System.out.println("The Encrypted Text: " + ciphertext);
        String decrypted = decrypt(ciphertext, key);
        System.out.println("The Decrypted Text: " + decrypted);
        scanner.close();
    }
}
