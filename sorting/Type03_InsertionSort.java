package ds_algo_study.sorting;

import java.util.Arrays;

/**
 * Insertion sort: Dividing line!
 * - Based on the dividing line,
 *   left side (sorted) /  DL / right side(unsorted).
 *   => left side is sorted within themselves!
 *
 * - no swap.
 * - worst case: still O(n^2) time, but best case can be O(n)
 * @author Sunny Park
 *
 */
public class Type03_InsertionSort {
    public static int[] insertionSort(int[] arr) {
        for (int out = 1; out < arr.length; out++) {
            int in = out;
            int temp = arr[out];
            while (in > 0 && arr[in - 1] > temp) {
                arr[in] = arr[in - 1];
                in--;
            }
            if (in != out) {
                arr[in] = temp;
            }
        }
        return arr;
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 7, 2, 5, 3};
        System.out.println(Arrays.toString(insertionSort(arr)));
    }
}
