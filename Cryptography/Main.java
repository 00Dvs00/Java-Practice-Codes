import java.math.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String plaintext = input.nextLine();
        String key = input.nextLine();
        int n = 0;
        for(int i =1; i<key.length(); i++){
            int d = key.length() / i;
            if(i == d){
                n = i;
                break;
            }
        }
        int[][] matrix = new int[n][n];
        for(int i = 0; i<n; i++){
            for(int j=0; j<n; j++){
                matrix[i][j] = key.charAt(i) - 65;
            }
        }

        ArrayList<int[]> vector = new ArrayList<>();
        for(int i = 0; i<plaintext.length(); i++){
            vector.add(new int[]{plaintext.charAt(i)-65,plaintext.charAt(i+1)-65,plaintext.charAt(i+2)-65});
            i+=2;
        }

        for (int[] is : vector) {
            for (int i = 0; i < is.length; i++) {
                System.out.print(is[i]+" ");
            }
            System.out.println(" ");
        }
        int count = 0;

        ArrayList<int[]> result = new ArrayList<>();
        while (count<vector.size()) {
            for (int i = 0; i < n; i++) {
                int[] temp = new int[n];
                for (int j = 0; j < n; j++) {
                    int[] v = vector.get(count);
                    temp[j] = matrix[i][j] * v[j];
                    result.add(temp);
                }
                count++;
            }
        }

        for (int[] is : result) {
            for (int i = 0; i < is.length; i++) {
                System.out.print(is[i]+" ");
            }
            System.out.println(" ");
        }
    }
}