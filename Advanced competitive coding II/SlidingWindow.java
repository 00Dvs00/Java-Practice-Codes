import java.util.*;

public class SlidingWindow {

    public static int[] maxSW(int[] nums, int k){
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n-k+1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            if(i >= k){
                maxHeap.remove(nums[i-k]);
            }
            maxHeap.offer(nums[i]);
            if(i >= k-1){
                result[i-k+1] = maxHeap.peek();
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = maxSW(nums,k);
        for (int i : result) {
            System.out.print(i+" ");
        }
    }
}
