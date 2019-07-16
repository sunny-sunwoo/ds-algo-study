package ds_algo_study.sorting;

import java.util.Arrays;

/**
 * Bubble sort: Very slow but simple.
 * 
 * [Logic] nested for loop.
 * 1. outer: rightmost -> leftmost
 * 2. inner: leftmost -> out
 * 3. swap if inner > outer => to sort the rightmost part.
 * 
 * => O(n^2) time, O(1) space
 * 
 */
public class Type01_BubbleSort {
    public static int[] bubblesort(int[] arr) {
        for (int out = arr.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (arr[in] > arr[out]) swap(arr, in, out);
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
        System.out.println(Arrays.toString(bubblesort(arr)));
    }
}

