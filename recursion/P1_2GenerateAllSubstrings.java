package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Q. Generate all substrings.
 * 
 * [Approach] Nested for loop -> Recursion
 * 1. move right pointer from the given left pointer.
 * 2. increment the left pointer.
 * 
 * Time Complexity: O(n^3) |  cubic time: outer loop, inner loop, substring
 * Space Complexity: O(n)  |  absolute maximum depth.
 * 
 * 
 * @author Sunny Park
 *
 */
public class P1_2GenerateAllSubstrings {
    public static List<String> generateAllSubstrings(String s) {
        List<String> result = new ArrayList<>();
        iterateFirstChar(result, s, 0);
        return result;
    }
    
    private static void iterateFirstChar(List<String> result, String s, int i) {
        if (i >= s.length()) {
            return;
        }
        iterateSecondChar(result, s, i, i + 1);
        iterateFirstChar(result, s, i + 1);
    }
    
    private static void iterateSecondChar(List<String> result, String s, int i, int j) {
        if (j > s.length()) {
            return;
        }
        result.add(s.substring(i, j));
        iterateSecondChar(result, s, i, j + 1);
    }
    
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(generateAllSubstrings(s));
    }
}
