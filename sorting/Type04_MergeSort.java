package ds_algo_study.sorting;

import java.util.Arrays;

/**
 * Merge Sort: great example of divide & conquer!
 * 
 * 3 steps - using recursion!
 * 1. sort the first half.
 * 2. sort the second half.
 * 3. merge the sorted 2 halves to create the final sorted result. 
 * 
 * 1) sort method: use recursion
 * - base case: lo >= hi. 
 * - logic: 1.sort first half -> 2.sort second half -> 3.merge both.
 * 
 * 2) merge method: use 2 tmp arrays, 2 pointers to compare & merge.
 * 
 * [Notes] 
 * Like QuickSort, Merge Sort is a Divide and Conquer algorithm. 
 * It divides input array in two halves, calls itself for the two halves 
 * and then merges the two sorted halves. The merge() function is used for merging two halves. 
 * The merge(arr, l, m, r) is key process that assumes that arr[l..m] and arr[m+1..r] are sorted 
 * and merges the two sorted sub-arrays into one. 
 * 
 * Time: O(NlogN)
 *  -> merge sort always divides the array into two halves and take linear time to merge two halves.
 *  
 * Space: O(N)
 *  -> Auxiliary Space
 *  
 * Stable: YES
 * 
 * @see <a href="https://www.geeksforgeeks.org/merge-sort/">gfg</a>
 * @author Sunny Park
 *
 */
public class Type04_MergeSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    
    private static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
        // System.out.println(left + ", " + right + " : " + Arrays.toString(arr)); // trace
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int leftLen = mid - left + 1;
        int rightLen = right - mid; 
        
        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];
        
        System.arraycopy(arr, left, leftArr, 0, leftLen);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightLen);
        
        int i = 0, j = 0;
        int ptr = left; // *note* where to start ptr!
        
        while (i < leftLen && j < rightLen) {
            if (leftArr[i] <= rightArr[j]) {
                arr[ptr++] = leftArr[i++];
            } else {
                arr[ptr++] = rightArr[j++];
            }
        }
        
        while (i < leftLen) {
            arr[ptr++] = leftArr[i++];
        }
        
        while (j < rightLen) {
            arr[ptr++] = rightArr[j++];
        }
        System.out.println("merge: " + Arrays.toString(arr));
    }
    
    // practice
//    public static void sort(int[] arr) {
//        sort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//    }
//    
//    private static void sort(int[] arr, int lo, int hi) {
//        if (lo >= hi) return;
//        int mid = lo + (hi - lo) / 2;
//        
//        sort(arr, lo, mid);
//        sort(arr, mid + 1, hi);
//        merge(arr, lo, mid, hi);
//    }
//    
//    private static void merge(int[] arr, int lo, int mid, int hi) {
//        int leftLen = mid - lo + 1;
//        int rightLen = hi - mid;
//        
//        int[] leftArr = new int[leftLen];
//        int[] rightArr = new int[rightLen];
//        
//        System.arraycopy(arr, lo, leftArr, 0, leftLen);
//        System.arraycopy(arr, mid + 1, rightArr, 0, rightLen);
//        
//        int i = 0, j = 0, ptr = lo;
//        while (i < leftLen && j < rightLen) {
//            if (leftArr[i] <= rightArr[j]) {
//                arr[ptr++] = leftArr[i++];
//            } else {
//                arr[ptr++] = rightArr[j++];
//            }
//        }
//        
//        while (i < leftLen) {
//            arr[ptr++] = leftArr[i++];
//        }
//        
//        while (j < rightLen) {
//            arr[ptr++] = rightArr[j++];
//        }
//    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7, 1, 2}; 
        sort(arr);
    }
}
