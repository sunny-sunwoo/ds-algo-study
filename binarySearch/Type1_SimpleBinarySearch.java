package ds_algo_study.binarySearch;

import java.util.Arrays;

/**
 * 
 * Type1. Simple Binary Search
 * 
 * Q. Given a sorted float array and int key, 
 * find the index where the value is closest to the key. 
 * 
 * Java's Binary Search
 * - returns negative value if it can't find the key in the array.
 * - Math.abs(idx + 1) -> the closest number on the right.
 * 
 * - Return: based on JavaDoc
 * index of the search key, if it is contained in the array; 
 * otherwise, (-(insertion point) - 1). 
 * 
 * The "insertion point" is defined as the point at which 
 * the key would be inserted into the array: 
 * the index of the first element "greater" than the key, 
 * or a.length if all elements in the array are less than the specified key. 
 * 
 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.
 * 
 * @author Sunny Park
 */
public class Type1_SimpleBinarySearch {
    public static int getIndexHavingMinDiff(float[] arr, int key) {
        int idx = Arrays.binarySearch(arr, key);
        System.out.println("idx: " + idx);
        idx = idx < 0 ? Math.abs(idx + 1) : idx;
        System.out.println("idx converted: " + idx);
        
        if (idx == 0) return idx;
        if (idx == arr.length) return arr.length - 1;
        
        return Math.abs(key - arr[idx - 1]) < Math.abs(key - arr[idx]) 
                ? idx - 1 : idx;
    }
    
    /**
     * e.g.
     * key = 4, idx = 2(insertion point)
     * key = 6, idx = -5 -> 4(insertion point)
     * key = 10, idx = -7 -> 6(insertion point) => 5(to return last idx of arr)
     * @param args
     */
    public static void main(String[] args) {
        float[] arr = new float[] {1.2f, 4.0f, 4.0f, 5.1f, 7.0f, 9.0f};
        System.out.println(getIndexHavingMinDiff(arr, 6));
    }
}
