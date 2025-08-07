 import java.util.*;
public class Celebrity{
    public static int celebsol(int[][] mat){
        Stack<Integer> people = new Stack<>();
        for(int i=0; i<mat.length; i++){
            people.push(i);
        }

        while(people.size() > 1){
            int a = people.pop();
            int b = people.pop();

            if(mat[a][b] == 1){
                people.push(b);
            } else { people.push(a); }
        }

        int x = people.pop();
        int zeroCount = 0;
        for(int i=0; i<mat.length; i++){
            if(mat[x][i] == 0){
                zeroCount++;
            }
        }
        if(zeroCount != mat.length) return -1;

        int oneCount = 0;
        for(int i=0; i<mat.length; i++){
            if(mat[i][x] == 1){
                oneCount++;
            }
        }
        if(oneCount != mat.length-1) return -1;

        return x;
    }
    
    public static void main(String args[]){
        Scanner input = new Scanner (System.in);
        int people = input.nextInt();
        
        int matrix[][] = new int[people][people];
        
        for(int i =0;i<people;i++){
            for(int j=0;j<people;j++){
                matrix[i][j] = input.nextInt();
            }
        }
        
        int result = celebsol(matrix);
        
        if(result == -1) 
		    System.out.println("There is no celebrity in the party");
	    else
		    System.out.println(result + " is the celebrity in the party");
        input.close();
    }
}