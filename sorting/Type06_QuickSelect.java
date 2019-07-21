package ds_algo_study.sorting;

/**
 * Quick Select: what's Nth number?
 * - no need to sort all.
 * 
 * [Approach] 
 * 1. do partition.
 * 2. if found, rt arr[pivot]
 * 3. if pivot > pos ? recursive call to the left. 
 * 4.    else, recursive call to the right.
 * 
 * @author Sunny Park
 *
 */
public class Type06_QuickSelect {
    public static int quickSelect(int[] arr, int pos) { 
        return quickSelect(arr, 0, arr.length - 1, pos - 1);
    }

    private static int quickSelect(int[] arr, int lo, int hi, final int pos) {
        int pivot = doPartition(arr, lo, hi);
        if (pivot == pos) {
            return arr[pivot];
        }
        
        if (pivot > pos) {
            return quickSelect(arr, lo, pivot - 1, pos);
        }
        return quickSelect(arr, pivot + 1, hi, pos);
    }
    
    
    private static int doPartition(int[] arr, int lo, int hi) {
        int left = lo;
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
        int[] arr = {12, 11, 13, 5, 6, 7, 1, 2}; 
        System.out.println(quickSelect(arr, 5));
    }
}
