package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Q. Given an array of integers and a target,
 * find all combinations of integers that sum up to the target.
 * Values may be duplicated.
 * 
 * e.g. arr = [1, 2, 3], target = 4
 * => [[1,1,1,1], [1,1,2], [1,3], [2,2]]
 * 
 * [Approach] Backtracking.
 * base case: 
 *      remaining target < 0 -> rt
 *      remaining target == 0 -> path should be added to the result.
 *      ptr is out of bound. -> rt
 *      
 * logic:
 *      1. add curr elem to the path
 *      2. recurse: (target - curr) and same ptr
 *      3. remove the last
 *      4. recurse: same target and next ptr.
 *      
 * [Time/Space]
 * time complexity: O(2^n * n)
 *   height: n (target),
 *   branching factor: 2,
 *   work per call: n (copy path to the result)
 *   
 * space complexity: O(n)
 *   
 *      
 * @author Sunny Park
 *
 */
public class P3_5TargetSum {
    public static List<List<Integer>> targetSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        targetSum(result, new ArrayList<>(), arr, target, 0);
        return result;
    }
    
    private static void targetSum(List<List<Integer>> result, List<Integer> path, int[] arr, int target, int ptr) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (ptr == arr.length) return;
        
        path.add(arr[ptr]);
        targetSum(result, path, arr, target - arr[ptr], ptr);
        path.remove(path.size() - 1);
        targetSum(result, path, arr, target, ptr + 1);
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(targetSum(arr, 4));
    }
}
