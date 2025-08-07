import java.io.*;
import java.net.*;

public class HammingCodeServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234);
             Socket socket = serverSocket.accept();
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            int dataBits = Integer.parseInt(input.readLine());
            int[] data = new int[dataBits];
            for (int i = 0; i < dataBits; i++) data[i] = Integer.parseInt(input.readLine());
            String parityChoice = input.readLine();

            int parityBits = 0;
            while (dataBits + parityBits + 1 > Math.pow(2, parityBits)) parityBits++;
            int totalBits = dataBits + parityBits;

            output.println(parityBits); output.println(totalBits);

            int[] encodedData = new int[totalBits + 1];
            for (int i = 1, dataIndex = 0, power = 0; i <= totalBits; i++) {
                if (i == Math.pow(2, power)) power++;
                else encodedData[i] = data[dataIndex++];
            }

            output.println("Data bits entered:");
            for (int i = 0; i < dataBits; i++) output.println(data[i]);

            output.println("Data bits are encoded with parity bits (0):");
            for (int i = 1; i <= totalBits; i++) output.println(encodedData[i]);

            for (int i = 0; i < parityBits; i++) {
                int pos = (int) Math.pow(2, i), parity = 0;
                for (int j = pos; j <= totalBits; j += 2 * pos)
                    for (int k = j; k < j + pos && k <= totalBits; k++) parity += encodedData[k];
                encodedData[pos] = ("even".equals(parityChoice)) ? (parity % 2 == 0 ? 0 : 1) : (parity % 2 == 0 ? 1 : 0);
            }

            output.println("Hamming codeword bits for " + parityChoice + " parity (right to left):");
            for (int i = totalBits; i >= 1; i--) output.println(encodedData[i]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
