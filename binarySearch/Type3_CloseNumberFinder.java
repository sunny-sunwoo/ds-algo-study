package ds_algo_study.binarySearch;

import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * Type3. Close Number Finder via Binary Search.
 * By searching k/2 -> k/4 -> ... for both side from the target,
 * achieve O(logn) time to find close number in 1 sorted array.
 * 
 * [Approach]
 * 1. find the target using binary search (logN)
 * 2. find the k closest elements from the target using binary search(logK)
 * 
 * Helper Method
 * 1. check prevResult size matches k -> if found.
 * 2. check l/r bounds -> if it's not valid, find from the other side.
 * 3. binary search -> set l/r bounds, compare l/r diff.
 * 
 * Note. nums[target - k/2] < nums[target + k/2] 
 *  => it means, elems at idx (target - k/2) ~ (target) are included in the k closest elems.
 *  => next comparison: target - k/2 - k/4 vs target + k/4
 *  => therefore, finding k neighbors takes logK time.
 *  
 *  the target can be - the TARGET OR result from the binary search
 *  depending on what I pass to the helper method.
 *  in this case, I'm passing the result from binary search.
 *  
 * 
 * @see <a href= "https://leetcode.com/problems/find-k-closest-elements/"> 
 * Leetcode#658 find k closest elements</a>
 * @author Sunny Park
 *
 */
public class Type3_CloseNumberFinder {
    public static Pair<Integer, Integer> findKClosest(int[] nums, int t, int k) {
        int idx = Arrays.binarySearch(nums, t);
        idx = idx < 0 ? Math.abs(idx + 1) : idx;
        return findKClosest(nums, nums[idx], k, Pair.of(idx, idx));
    }
    
    private static Pair<Integer, Integer> findKClosest
    (int[] nums, int target, int k, Pair<Integer, Integer> prevResult) {
        int size = prevResult.getRight() - prevResult.getLeft() + 1;
        if (k == size) return prevResult;
        if (k == size + 1) return addOneElem(prevResult, target, nums);
        
        int remaining = k - size;
        if (prevResult.getLeft() <= 0) {
            return Pair.of(prevResult.getLeft(), prevResult.getRight() + remaining);
        }
        
        if (prevResult.getRight() >= nums.length - 1) {
            return Pair.of(prevResult.getLeft() - remaining, prevResult.getRight());
        }
        
        int leftBound = prevResult.getLeft() - remaining/2 >= 0 ? prevResult.getLeft() - remaining/2 : 0;
        int rightBound = prevResult.getRight() + remaining/2 < nums.length ? prevResult.getRight() + remaining/2 : nums.length - 1;
        
        int leftDiff = Math.abs(target - nums[leftBound]);
        int rightDiff = Math.abs(target - nums[rightBound]);
        
        return leftDiff <= rightDiff ? 
                findKClosest(nums, target, k, Pair.of(leftBound, prevResult.getRight()))
                : findKClosest(nums, target, k, Pair.of(prevResult.getLeft(), rightBound));
    }
    
    
    private static Pair<Integer, Integer> addOneElem(Pair<Integer, Integer> prevResult, int target, int[] nums) {
        int left = prevResult.getLeft() - 1;
        int right = prevResult.getRight() + 1;
        
        if (left < 0) {
            return Pair.of(prevResult.getLeft(), right);
        }
        
        if (right >= nums.length) {
            return Pair.of(left, prevResult.getRight());
        }
        
        int leftDiff = Math.abs(target - nums[left]);
        int rightDiff = Math.abs(target - nums[right]);
        
        return leftDiff <= rightDiff ? Pair.of(left, prevResult.getRight()) 
                : Pair.of(prevResult.getLeft(), right);
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 6, 8, 9, 10};
        System.out.println(findKClosest(nums, 7, 5));
    }

}
