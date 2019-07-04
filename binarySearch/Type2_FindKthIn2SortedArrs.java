package ds_algo_study.binarySearch;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Type2. Binary search for 2 arrays.
 * 
 * When we find the kth item in sorted array, brute-force would take O(k).
 * Using binary search, we can optimize the search into O(logk)
 * by checking k/2th items only.
 * 
 * k -> k/2 -> k/4 -> ... 
 * 
 * [Approach] Find kth item in 2 sorted arrays.
 * 
 * 1. check if the arr is exhausted.
 *      - if so, find the answer from the other arr.
 *      
 * 2. k == 1 ? answer is the min value.
 * 
 * 3. set mid1, mid2 while checking the bounds.
 *      if the length is not enough to find start + k/2 - 1, 
 *      set mid as Integer.MAX_VALUE
 *      to avoid proceeding with the start pointers.
 * 
 * @author Sunny Park
 *
 */
public class Type2_FindKthIn2SortedArrs {
    public static int findKth(int[] arr1, int[] arr2, int k) {
        checkArgument(checkNotNull(arr1).length > 0, "Array1 is empty");
        checkArgument(checkNotNull(arr2).length > 0,  "Array2 is empty");
        checkArgument(arr1.length + arr2.length >= k, "k is invalid");
        return findKth(arr1, 0, arr2, 0, k);
    }
    
    private static int findKth(int[] arr1, int start1, int[] arr2, int start2, int k) {
        // 1. check exhausted
        if (start1 >= arr1.length) {
            return arr2[start2 + k - 1];
        }
        
        if (start2 >= arr2.length) {
            return arr1[start1 + k - 1];
        }
        
        // 2. k == 1
        if (k == 1) {
            return Math.min(arr1[start1], arr2[start2]);
        }
        
        // 3. use mid1, mid2
        int mid1 = Integer.MAX_VALUE;
        int mid2 = Integer.MAX_VALUE;
        
        mid1 = arr1.length > start1 + k/2 - 1 ? arr1[start1 + k/2 - 1] : mid1;
        mid2 = arr2.length > start2 + k/2 - 1 ? arr2[start2 + k/2 - 1] : mid2;
        
        return mid1 < mid2 ? findKth(arr1, start1 + k/2, arr2, start2, k - k/2)
                : findKth(arr1, start1, arr2, start2 + k/2, k - k/2);
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 4, 5};
        int[] arr2 = {2, 6, 7};
        
        System.out.println(findKth(arr1, arr2, 7));
    }
}
