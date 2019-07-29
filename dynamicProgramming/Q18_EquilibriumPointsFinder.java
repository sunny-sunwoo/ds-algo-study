package ds_algo_study.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Equilibrium points = specific point whose left sum equals to the right sum.
 * Given an int arr, get a list of equilibrium points.
 * 
 * e.g. [1,3,4,2,2] => [3]
 * 
 * [Approach]
 * 1. get a total sum.
 * 2. iterate over the arr
 *    - accumulate the left sum.
 *    - right = total - left - curr
 *    - if (left == right) keep the index.
 * 
 * @author Sunny Park
 *
 */
public class Q18_EquilibriumPointsFinder {
    public static List<Integer> findPoints(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int left = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            left += nums[i - 1];
            int right = total - left - nums[i];
            if (left == right) {
                result.add(i);
            } 
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        System.out.print(findPoints(nums));
    }
}
