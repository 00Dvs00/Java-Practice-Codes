import java.util.*;
public class CSCAN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), head = sc.nextInt(), dir = sc.nextInt(), total = 0, curr = head;
        int[] req = new int[n];
        for(int i = 0; i < n; i++) req[i] = sc.nextInt();
        Arrays.sort(req);
        
        int pos = 0;
        while(pos < n && req[pos] <= head) pos++;
        
        if(dir == 1) { 
            for(int i = pos; i < n; i++) total += Math.abs(curr - (curr = req[i]));
            total += 199 - curr;
            total += 199;
            curr = 0;
            for(int i = 0; i < pos; i++) total += Math.abs(curr - (curr = req[i]));
        } else {        
            for(int i = pos-1; i >= 0; i--) total += Math.abs(curr - (curr = req[i]));
            total += curr;
            total += 199;
            curr = 199;
            for(int i = n-1; i >= pos; i--) total += Math.abs(curr - (curr = req[i]));
        }
        
        System.out.println(total);
        sc.close();
    }
}