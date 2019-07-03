package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinations. 
 * Q1. Count combinations.
 * 
 * Q2. Generate combinations.
 *     a) with return (without passed var)
 *     b) without return (with passed var)
 *     
 *     
 * [Approach] Pattern3 - Selection.
 *  2 choices: include or exclude
 *     
 * @author Sunny Park
 *
 */
public class P3_1Combinations {
    /**
     * Q1. Count combinations
     * base case: when the pointer passed the right bound.
     * 
     * Time complexity: O(2^n)
     * Space complexity: O(n), n depth of search tree.
     * 
     * @param nums
     * @return num of combinations.
     */
    public static int countCombinations(int[] nums) {
        return count(nums, 0);
    }
    
    private static int count(int[] nums, int ptr) {
        if (ptr == nums.length) {
            return 1;
        }
        
        int include = count(nums, ptr + 1);
        int exclude = count(nums, ptr + 1);
        
        return include + exclude;
    }
    
    /**
     * Q2 - 1. Generate combinations without passed var, with returned result.
     * 
     * TC: O(n * 2^n), n depth * copy array size of (2^n)
     * SC: O(n * 2^n)
     * 
     * @param nums
     * @return
     */
    public static List<List<Integer>> generateCombinations(int[] nums) {
        return generate(nums, 0);
    }
    
    /**
     * base case: ptr out of bounds -> return empty L<L<I>>
     * @param nums
     * @param ptr
     * @return
     */
    private static List<List<Integer>> generate(int[] nums, int ptr) {
        if (ptr == nums.length) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> tmp : generate(nums, ptr + 1)) { // recursive call.
            result.add(new ArrayList<>(tmp));
            tmp.add(0, nums[ptr]); // prepend curr elem.
            result.add(new ArrayList<>(tmp));
        }
        return result;
    }
    
    /**
     * Q2 - 2. Generate combinations with passed var, without returned result.
     * 
     * T.C: O(2^n * n), 2 branches, n heights, copy an array of size n 
     * S.C: O(n), we don't count O(2^n * n) space bc/ it's allocated for the client. 
     * 
     * @param nums
     * @return
     */
    public static List<List<Integer>> generateCombinations2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generate2(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    /**
     * base case: ptr reaches to the end.
     * recursive calls: 2 calls for include/exclude decision
     * @param result
     * @param tmp
     * @param nums
     * @param ptr
     */
    private static void generate2(List<List<Integer>> result, List<Integer> tmp, int[] nums, int ptr) {
        if (ptr == nums.length) {
            result.add(tmp);
            return;
        }
        
        List<Integer> tmpWithCurr = new ArrayList<>(tmp);
        tmpWithCurr.add(nums[ptr]);
        
        generate2(result, tmp, nums, ptr + 1); // exclude
        generate2(result, tmpWithCurr, nums, ptr + 1); // include
        
    }
    
    
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(countCombinations(nums));
        System.out.println(generateCombinations(nums));
        System.out.println(generateCombinations2(nums));
    }
}
