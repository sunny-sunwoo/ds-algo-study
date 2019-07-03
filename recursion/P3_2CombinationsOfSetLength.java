package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Q. Generate all the combinations with a specific number of items.
 * 
 * [Approach1] Brute force
 *  Generate all combinations -> filter them based on the set length.
 * 
 * [Approach2] Backtracking with constraints
 *  if the curr length exceeds the set length OR doesn't the set length,
 *  just return! (End early rather than generating all.)
 *  
 * @author Sunny Park
 *
 */

public class P3_2CombinationsOfSetLength {
    /**
     * Brute force approach
     * @param nums
     * @param n
     * @return
     */
    public static List<List<Integer>> combination_bruteforce(int[] nums, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combination(result, new ArrayList<>(), nums, 0);
        return filter(result, n);
    }
    
    private static void combination(List<List<Integer>> result, List<Integer> tmp, int[] nums, int ptr) {
        if (ptr == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        
        tmp.add(nums[ptr]);
        combination(result, tmp, nums, ptr + 1);
        tmp.remove(tmp.size() - 1);
        combination(result, tmp, nums, ptr + 1);
    }
    
    private static List<List<Integer>> filter(List<List<Integer>> result, int n) {
        List<List<Integer>> filtered = new ArrayList<>();
        for (List<Integer> list : result) {
            if (list.size() == n) {
                filtered.add(list);
            }
        }
        return filtered;
    }
    
    /**
     * Better solution with backtracking.
     * by returning a "placeholder" when the condition is satisfied,
     * decide to include or exclude for each time.
     * 
     * T.C: same with combinations in worst case, which is O(n * 2^n)
     * but average case is improved.
     * 
     * @param nums
     * @param n
     * @return
     */
    public static List<List<Integer>> combination_backtrack(int[] nums, int n) {
        return backtrack(nums, n, 0, 0);
    }
    
    
    private static List<List<Integer>> backtrack(int[] nums, int setLength, int currLength, int idx) {
        if (setLength < currLength) return new ArrayList<>();
        if (idx == nums.length && setLength > currLength) return new ArrayList<>();
        if (setLength == currLength) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        
        List<List<Integer>> include = backtrack(nums, setLength, currLength + 1, idx + 1);
        List<List<Integer>> exclude = backtrack(nums, setLength, currLength, idx + 1);
        
        List<List<Integer>> result = new ArrayList<>(); 
        for (List<Integer> tmp : include) {
            tmp.add(0, nums[idx]);
            result.add(new ArrayList<>(tmp));
        }
        
        result.addAll(exclude);
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(combination_bruteforce(nums, 2));
        System.out.println(combination_backtrack(nums, 2));
    }
}
