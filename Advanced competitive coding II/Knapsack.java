import java.util.*;

public class Knapsack {

    public static int knapsack(int i, int W, int[] wt, int[] val){
        if(i == 0){
            if(wt[i] <= W) return val[0];
            else return 0;
        }
        int notpick = 0 + knapsack(i-1, W, wt, val);
        int pick = 0;
        if(wt[i] < W){
            pick = val[i] + knapsack(i-1, W - wt[i], wt, val);
        }

        return Math.max(pick,notpick);
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int W = input.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];

        for (int i = 0; i < n; i++) {
            val[i] = input.nextInt();
            wt[i] = input.nextInt();
        }

        int ans = knapsack(n-1,W,wt,val);
        System.out.println(ans);
        input.close();
    }
}
