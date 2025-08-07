import java.util.*;

public class check{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        int start_count = 0;
        int end_count = 0;
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                start_count++;
            }
            if(s.charAt(i) == ')' && start_count!=0){
                end_count++;
            }
        }
        System.out.println(Math.min(start_count, end_count)*2);
    }
}