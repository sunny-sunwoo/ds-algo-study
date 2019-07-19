package ds_algo_study.string.T6_Palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string,
 * Q1. how many palindromic substrings can be found?
 *  [Approach] 
 *  sol1) generate all substrings and compare? n^3
 *  sol2) expand from the possible starting points? n^2 
 *        possible starting point num = 2n + 1.
 *        
 *        | | | |
 *        a b b a
 *         ^ ^ ^ 
 *  sol3) dp     
 *       
 * Q2. longest palindrome's length?
 *     - find all palindromic substrings & keep max length.
 *     
 * @author Sunny Park
 *
 */
public class PalindromicSubstring {
    public static List<String> findPalindromicSubstring(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            expand(result, s, i, i);
            expand(result, s, i, i + 1);
        }
        return result;
    }
    
    private static void expand(List<String> result, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }
    
    /**
     * Q. Using dp, find the NUMBER of palindrome.
     * (instead of list<String>, I just need to count here.)
     * possible cases:
     * 1, 2 letter -> if start letter == end letter, then palindrome.
     * 3+ letter -> if (1)start letter == end letter && (2) start + 1 ~ end -1 is palindrome,
     *              then palindrome.
     *              => which means, we should compute the bottom left cell first! 
     *                 \ <- this line first!
     *                 
     *              =>  when computing dp[i][j], we should first know dp[i + 1][j - 1]
     *              
     * @param s
     * @return
     */
    public static int findPalindromicSubstring_dp(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for (int d = 0 ; d < s.length(); d++) {
            for (int i = 0; i + d < s.length(); i++) {
                int j = i + d;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (j - i < 2) ? true : dp[i + 1][j - 1];
                    if(dp[i][j]) count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Generate all substrings?
     */
    public static List<String> generate(String s) {
        List<String> result = new ArrayList<>();
        generateAll(result, s, 0);
        return result;
    }
    
    private static void generateAll(List<String> result, String s, int left) {
        if (left >= s.length()) {
            return;
        }
        generateAll(result, s, left, left + 1);
        generateAll(result, s, left + 1);
        return;
    }
    
    private static void generateAll(List<String> result, String s, int left, int right) {
        if (right > s.length()) {
            return;
        }
        result.add(s.substring(left, right));
        generateAll(result, s, left, right + 1);
        
    }
    
    public static List<String> generate_iterative(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                result.add(s.substring(i, j));
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        // [a, b, c, bcb, abcba, b, a], use set to remove repeated substring.
        System.out.println(findPalindromicSubstring("abcba"));
        System.out.println(findPalindromicSubstring_dp("abcba"));
        System.out.println(generate("abc"));
        System.out.println(generate_iterative("abc"));
    }
}
