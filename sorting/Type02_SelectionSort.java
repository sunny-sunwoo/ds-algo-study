package ds_algo_study.sorting;

import java.util.Arrays;

/**
 * Selection sort.
 * less swap than bubble sort
 * it selects min only.
 * 
 * [Logic] nested for loop
 * 1. set curr min.
 * 2. find min.
 * 3. swap 
 *
 * => O(n^2) time, O(1) space
 * 
 * @author Sunny Park
 *
 */
public class Type02_SelectionSort {
    public static int[] selectionSort(int[] arr) {
        for (int out = 0; out < arr.length - 1; out++) {
            int min = out;
            for (int in = out + 1; in < arr.length; in++) {
                if (arr[in] < arr[min]) {
                    min = in;
                }
            }
            if (min != out) {
                swap(arr, min, out);
            }
        }
        return arr;
    }
    
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,3,5,2,4};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }
}
