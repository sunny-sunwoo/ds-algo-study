package ds_algo_study.sorting.practice;

/**
 * Q1. Majority number finder
 * validate a given array if there is a number which is majority in the array.
 * 
 * e.g. 
 * [1,1,1,2,3] -> true (1 is majority)
 * [1,2,3,4,5] -> false. 
 * 
 * [Approach] Quick Select
 * if there is any majority number, 
 * it should be placed in the center!
 * 
 * 1. quick select @ position mid
 * 2. linear scan - check if count >= arr.length / 2 + arr.length % 2
 * 
 * @author Sunny Park
 *
 */
public class Q1_MajorityNumberFinder {
    public static boolean hasMajorityNumber(int[] arr) {
        int k = arr.length / 2 + arr.length % 2;
        int candid = quickSelect(arr, k);
        
        int count = 0;
        for (int num : arr) {
            if (num == candid) count++;
        }
        
        return count >= k;
    }
    
    private static int quickSelect(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }
    
    private static int quickSelect(int[] arr, int lo, int hi, final int k) {
        int pivot = doPartition(arr, lo, hi);
        if (pivot == k) {
            return arr[pivot];
        }
        
        if (pivot < k) {
            return quickSelect(arr, pivot + 1, hi, k);
        }
        
        return quickSelect(arr, lo, pivot - 1, k);
    }
    
    private static int doPartition(int[] arr, int lo, int hi) {
        int left = 0;
        int pivot = arr[hi];
        for (int i = lo; i < hi; i++) {
            if (arr[i] <= pivot) {
                swap(arr, left++, i);
            }
        }
        swap(arr, left, hi);
        return left;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
     
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,3};
        System.out.println(hasMajorityNumber(arr));
        System.out.println(quickSelect(arr, 4));
    }
}
