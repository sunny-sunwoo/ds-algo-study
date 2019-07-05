package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Q. Given 2 Strings, find all different ways that the 2 strings can be interleaved.
 * e.g. interleave("ab", "cd") = {"abcd", "acbd", "acdb", "cabd", "cadb", "cdab"}
 * 
 * [Approach1] make a combination using the index.
 * Choose s1.length() positions in result string (s1.length() + s2.length())
 * - For those positions, fill in values from s1 in order.
 * - then fill remaining positions with s2
 * 
 * [Approach2] reframe the perspective.
 * Rather than "include or not include" a specific character, 
 * Focus on including a character from "either s1 or s2."
 * -> each pos can be filled with s1 or s2. 
 * 
 * [Time/Space]
 * time complexity: O(2^n * n)
 *   height: n (s1 + s2),
 *   branching factor: 2,
 *   work per call: n (generate string for every base case)
 *   
 * space complexity: O(n), n = s1 + s2. (height)
 * 
 * @author Sunny Park
 *
 */
public class P3_4InterleaveStrings {
    /**
     * [Approach1] using set-length combination method.
     * @param s1
     * @param s2
     * @return
     */
    public static List<String> interleave_combination(String s1, String s2) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> idxSet= new ArrayList<>();
        for (int i = 0; i < s1.length() + s2.length(); i++) {
            idxSet.add(i);
        }
        findCombination(result, new ArrayList<>(), idxSet, s1.length(), 0);
        System.out.println(result);
        return interleaveString(result, s1, s2);
    }
    
    // find combination of set length.
    private static void findCombination(List<List<Integer>> result, List<Integer> tmp, List<Integer> input, int len, int ptr) {
        if (tmp.size() == len) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        int spaceLeft = len - tmp.size();
        for (int i = ptr; i < input.size() && spaceLeft <= input.size() - i; i++) {
            tmp.add(input.get(i));
            findCombination(result, tmp, input, len, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
    
    private static List<String> interleaveString(List<List<Integer>> result, String s1, String s2) {
        List<String> ret = new ArrayList<>();
        for (List<Integer> list : result) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int j = 0;
            for (int ptr = 0; ptr < s1.length() + s2.length(); ptr++) {
                if (list.contains(ptr)) {
                    sb.append(s1.charAt(i++));
                } else {
                    sb.append(s2.charAt(j++));
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }
    
    /**
     * [Approach2] include from s1 or s2.
     * @param s1
     * @param s2
     * @return
     */
    public static List<String> interleave_backtracking(String s1, String s2) {
        List<String> result = new ArrayList<>();
        interleaveString2(result, new StringBuilder(), s1, 0, s2, 0);
        return result;
    }
    
    private static void interleaveString2(List<String> result, StringBuilder sb, String s1, int i, String s2, int j) {
        if (sb.length() == s1.length() + s2.length()) {
            result.add(sb.toString());
            return;
        }
        
        if (i < s1.length()) {
            sb.append(s1.charAt(i));
            interleaveString2(result, sb, s1, i + 1, s2, j);
            sb.setLength(sb.length() - 1);
        }
        
        if (j < s2.length()) {
            sb.append(s2.charAt(j));
            interleaveString2(result, sb, s1, i, s2, j + 1);
            sb.setLength(sb.length() - 1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(interleave_combination("ab", "cd"));
        System.out.println(interleave_backtracking("ab", "cd"));
    }
    
}
