import java.util.*;

public class LongestPalindrome {
    public static int lps(int i, int j, String s1, String s2, int[][] dp){
        if(i<0 || j<0) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = 1 + lps(i-1,j-1,s1,s2,dp);
        }

        return dp[i][j] = Math.max(lps(i-1,j,s1,s2,dp) , lps(i,j-1,s1,s2,dp));
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        String s2 = new StringBuilder(s1).reverse().toString();
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        int ans = lps(n-1,m-1,s1,s2,dp);
        System.out.println(ans);
        input.close();
    }
}