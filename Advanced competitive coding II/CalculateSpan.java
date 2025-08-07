import java.util.*;

class Span{
    public int[] calculate(int[] stockPrice){
        int n = stockPrice.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();
        span[0] = 1;
        stack.push(0);  
        for(int i=0; i<n; i++){
            span[i] = 1;
            int j = i-1;
            while(j>=0 && stockPrice[i] >= stockPrice[j]){
                span[i]++;
                j--;
            }
        }
        return span;
    }
}
public class CalculateSpan {
    public static void main(String[] args) {
        Span calc = new Span();
        int[] stockPrices = {100, 80, 60, 70 , 60, 75, 85};
        int[] spans = calc.calculate(stockPrices);
        System.out.println("Stock Prices: "+ Arrays.toString(stockPrices));
        System.out.println("Stock Span: "+ Arrays.toString(spans));
    }
}
