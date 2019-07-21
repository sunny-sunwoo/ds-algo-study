package ds_algo_study.sorting;

import java.util.Arrays;

/**
 * Quick Sort: Remember Pivot & Partition!
 *  - by Tony Hoare(who invented "null")
 *  
 * 1) Concept
 *  - 3 steps: partition -> quicksort(before pivot) -> quicksort(after pivot)
 *      Partition array(sub arrays) into left & right group. (curr elem compared to the pivot.)
 *      Call itself to sort the left group.
 *      Call itself to sort the right group.
 *             
 *  - pivot: used to determine which group a value should be placed. 
 *           left    | pv |   right  
 *                      ^ at its final position after each round. 
 *                       
 *  2) worst case? {7, 6, 5, 4, 3, 2, 1} 
 *    - (n - 1) recursive calls needed. & k(sub arr length) comparisons per every call.
 *      => O(n^2) time.
 *  
 *  - solution
 *    1. median.
 *       by picking the median as a pivot, sorting can guarantee O(NLogN) time.(LogN level.)
 *       but, there is a overhead for calculation of median.
 *       
 *    2. random value OR pseudo median(median of 3 elems: first, mid, last)
 *       with this improvement, Quicksort can achieve O(NlogN) time.
 *       => *NOTE* Worst case is still O(n^2) 
 *  
 * @author Sunny Park
 *
 */
public class Type05_QuickSort {
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    
    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo > hi) return;
        int pivot = doPartition(arr, lo, hi);
        quickSort(arr, lo, pivot - 1);
        quickSort(arr, pivot + 1, hi);
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
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7, 1, 2}; 
        quickSort(arr);
    }
    
}
