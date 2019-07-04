package ds_algo_study.binarySearch;

/**
 * Binary Search
 * 
 * Q. Given a sorted array of n elements, 
 * write a function to search a given element x in the arr.
 * 
 * Simple approach is to do linear search. O(n)
 * Another approach to perform the same task is using binary search. O(logN)
 *
 * Search a sorted arr by repeatedly dividing the search interval in half.
 * Begin with an interval covering the whole array.
 * If the value of the search key is less than the item in the mid of the interval,
 * narrow the interval to the lower half. Otherwise to the upper half.
 * Repeatedly check until the value is found or the interval is empty!
 * 
 * [Approach] Recursive or Iterative
 * We basically "ignore" half of the elements just after one comparison.
 * 1. Compare x with the middle element.
 * 2. If x matches with middle element, we return the mid index.
 * 3. Else If x is greater than the mid element, 
 *    then x can only lie in right half subarray after the mid element. So we recur for right half.
 * 4. Else (x is smaller) recur for the left half.
 * 
 * @see <a href="https://www.geeksforgeeks.org/binary-search/">Geeks For Geeks</a>
 * @author Sunny Park
 *
 */
public class BinarySearchConcept {
    public static int binarySearch(int[] arr, int key) {
        return iterative(arr, key, 0, arr.length - 1);
    }
    
    private static int recursive(int[] arr, int key, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        
        if (lo > hi) return -1;
        
        if (arr[mid] == key) {
            return mid;
        }
        
        if (arr[mid] < key) {
            return recursive(arr, key, mid + 1, hi);
        }
        return recursive(arr, key, lo, mid - 1);
    }
    
    private static int iterative(int[] arr, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 5, 6, 7};
        System.out.println(binarySearch(arr, 6));
    }
}
