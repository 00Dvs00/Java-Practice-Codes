import java.util.*;

class Playfair {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String plaintext = input.nextLine();
        String keyword = input.nextLine();
        char[][] matrix = new char[5][5];
        int count = 0; String alpha = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; 
        int count1 = 0;
        Map<Character, int[]> charPos = new HashMap<>();
        for(int i = 0; i<matrix[0].length; i++){
            for(int j = 0; j<matrix.length; j++){
                if(count<keyword.length()){
                    char temp = keyword.charAt(count);
                    matrix[i][j] = temp;
                    charPos.put(temp, new int[]{i,j});
                    alpha = alpha.replace(Character.toString(temp),"");
                    count++;
                }else{
                    char temp = alpha.charAt(count1);
                    matrix[i][j] = temp;
                    charPos.put(temp, new int[]{i,j});
                    count1++;
                }
            }
        }

        ArrayList<String> pairs = new ArrayList<>();
        for(int i=0; i<plaintext.length()-1; i++){
            String temp = "";
            if(plaintext.charAt(i) == plaintext.charAt(i+1)){
                temp = temp + plaintext.charAt(i)+"X";
            }else if(plaintext.charAt(i+1) == plaintext.length()){
                temp = temp + plaintext.charAt(i+1) + "Z";
            } else{
                temp = temp + plaintext.charAt(i) + plaintext.charAt(i+1);
                i++;
            }
            pairs.add(temp);
        }

        ArrayList<String> cipher = new ArrayList<>();
        for (String pair : pairs) {
            char x =pair.charAt(0);
            char y =pair.charAt(1);
            int[] posX = charPos.get(x);
            int[] posY = charPos.get(y);
            String temp = "";
            if(posX[0] != posY[0] && posX[1] != posY[1]){
                temp = temp + Character.toString(matrix[posX[0]][posY[1]]);
                temp = temp + Character.toString(matrix[posY[0]][posX[1]]);
            } else if(posX[0] == posY[0]){
                temp = temp + Character.toString(matrix[posX[0]][(posX[1]+1)%5]);
                temp = temp + Character.toString(matrix[posY[0]][(posY[1]+1)%5]);
            } else{
                temp = temp + Character.toString(matrix[(posX[0]+1)%5][posX[1]]);
                temp = temp + Character.toString(matrix[(posY[0]+1)%5][posY[1]]);
            }
            cipher.add(temp);
        }
        
        System.out.println("\nMATRIX: ");
        for(int i = 0; i<matrix[0].length; i++){
            for(int j = 0; j<matrix.length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
 
        System.out.println("\nCIPHERTEXT: ");
        for(String pair : cipher){
            System.out.print(pair);
        }
    }
}