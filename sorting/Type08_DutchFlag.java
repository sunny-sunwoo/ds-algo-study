package ds_algo_study.sorting;

/**
 * There is a determinant interface which decides the nums's destination where to be placed. (low/mid/high)
 * How to sort with 3 divisions?
 * 
 * @author Sunny Park
 *
 */
public class Type08_DutchFlag {
    public static void doBucketSort(BucketDeterminant bd, int[] arr) {
        int low = 0;
        for (int i = 0; i < arr.length; i++) {
            if (bd.isLow(arr[i])) {
                swap(arr, low++, i);
            }
        }
        int mid = low;
        for (int j = mid; j < arr.length; j++) {
            if (bd.isMid(arr[j])) {
                swap(arr, mid++, j);
            }
        }
    }
    
    public static void doBucketSort_DualPivot(int[] arr, int lo, int hi) {
        int left = 0, right = arr.length - 1;
        int idx = 0;
        
        while (idx <= right) {
            int curr = arr[idx];
            if (curr <= lo) {
                swap(arr, left++, idx++);
                continue;
            }
            
            if (curr >= hi) {
                swap(arr, idx, right--);
                continue;
            }
            idx++;
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        // swap 2 nums.
    }
}

interface BucketDeterminant {
    boolean isLow(int n);
    boolean isMid(int n);
    boolean isHigh(int n);
}
