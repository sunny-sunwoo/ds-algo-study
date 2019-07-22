package ds_algo_study.dynamicProgramming;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * Q. Longest Continuous Increasing Sequence?
 * find the length of longest continuous LIS in the given arr.
 * 
 * e.g. [100, 4, 5, 3, 2, 87, 66, 1] -> [1, 2, 3, 4, 5]
 * 
 * [Approach] Use Set! 
 * 1. copy all elem to the set. (no need to consider the duplicates.)
 * 2. linear scan the given arr.
 *    -> count = 1 // count from the curr elem. 
 *    -> search for (curr elem - 1) and (curr elem + 1) while "REMOVING"
 *    -> keep the max length
 *    
 * [Time/Space]
 *  Time: O(n) bc/ checking count while removing from the set!
 *  Space: O(n)
 *  
 * @author Sunny Park
 *
 */
public class Q1_LongestContinuousIncreasingSequence {
    public static int lcis(int[] arr) {
        Set<Integer> set = IntStream.of(arr).boxed().collect(Collectors.toSet());
        int maxCount = 1;
        for (int num : arr) {
            int count = 1;
            set.remove(num);
            
            int left = num - 1;
            while (set.contains(left)) {
                set.remove(left--);
                count++;
            }
            
            int right = num + 1;
            while (set.contains(right)) {
                set.remove(right++);
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
    
    public static void main(String[] args) {
        int[] arr = {100, 4, 5, 3, 2, 87, 66, 1};
        System.out.println(lcis(arr));
    }
}
