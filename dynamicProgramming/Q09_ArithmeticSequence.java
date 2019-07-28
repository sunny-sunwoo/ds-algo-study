package ds_algo_study.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * Q1027. Longest Arithmetic Sequence
 * 
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] 
 * with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, 
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 * 
 * e.g.
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation: 
 * The longest arithmetic subsequence is [20,15,10,5].
 * 
 * [Approach]
 * 1. build a hashmap with diff -> list of pairs (takes n^2 time)
 * 2. analyze max length -> checks all pairs. (takes n^2 time)
 * => t.c. O(n^2)
 * 
 * e.g. [1,3,5,11,14,7,9,13]
 * diff 2 -> [(1,3), (3,5), (5,7), (7,9)]
 *        -> [(0,1), (1,2), (2,5), (5,6)] index view
 *           => 0 -> 1 -> 2 -> 5 -> 6 (length: 5)
 *   
 * @author Sunny Park
 *
 */
public class Q09_ArithmeticSequence {
    public static int longestSequence(int[] arr) {
        Map<Integer, List<Pair<Integer, Integer>>> cache = buildMap(arr);
        return getMaxLen(cache, arr.length);
    }
    
    private static Map<Integer, List<Pair<Integer, Integer>>> buildMap(int[] arr) {
        Map<Integer, List<Pair<Integer, Integer>>> cache = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int diff = arr[j] - arr[i];
                List<Pair<Integer, Integer>> currList = cache.getOrDefault(diff, new ArrayList<>());
                currList.add(Pair.of(i,j));
                cache.put(diff, currList);
            }
        }
        return cache;
    }
    
    private static int getMaxLen(Map<Integer, List<Pair<Integer, Integer>>> cache, int len) {
        int maxLen = 0;
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : cache.entrySet()) {
            int[] lengths = new int[len];
            Arrays.fill(lengths, 1);
            
            for (Pair<Integer, Integer> gap : entry.getValue()) {
                lengths[gap.getRight()] = lengths[gap.getLeft()] + 1;
            }
            maxLen = Math.max(maxLen, getMax(lengths));
        }
        return maxLen;
    }
    
    private static int getMax(int[] arr) {
        int max = 0;
        for (int n : arr) {
            max = Math.max(max, n);
        }
        return max;
    }
    
    public static void main(String[] args) {
//        int[] arr = {20,1,15,3,10,5,8};
//        int[] arr = {3,6,9,12};
        int[] arr = {83,20,17,43,52,78,68,45};
        System.out.println(longestSequence(arr));
        
    }
}
