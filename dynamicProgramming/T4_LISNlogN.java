package ds_algo_study.dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Q. Length of Longest Increasing Sequence.
 * [10, 9, 2, 5, 3, 7, 101, 18]
 *         ^     ^  ^        ^  => 4
 *         
 * 1. linear scan 
 * 2. put the number to the insertion point.
 *    - 9 will overide 10.
 *    - 2 will override 9.
 *  -> if insertion point == sia size? add
 *  -> else, override! by siz.set(pos, num)
 *  
 * 3. rt sia.size()
 * 
 * @author Sunny Park
 *
 */
public class T4_LISNlogN {
    public static int findLIS(int[] arr) {
        List<Integer> sia = new ArrayList<>();
        for (int num : arr) {
            int pos = Collections.binarySearch(sia, num);
            pos = pos < 0 ? Math.abs(pos + 1) : pos;
            if (pos == sia.size()) {
                sia.add(num);
                continue;
            }
            sia.set(pos, num);
        }
        System.out.println(sia);
        return sia.size();
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(findLIS(arr));
    }
}
