package ds_algo_study.string.T6_Palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * Q1) Length of longest palindrome out of "substrings". 
 * 
 * [Approach]
 * 1. check palindromic substrings by expanding from 2n+1 points.
 * 2. keep if it has the max length.
 * 
 * 
 * Q2) Length of longest palindrome out of "any combinations".
 * Given a string which consists of lowercase or uppercase letters, 
 * find the length of the longest palindromes that can be built with those letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * [Approach]
 * 1. build int freq arr. with 128 long. (ascii)
 * 2. elem / 2 * 2 (to make 3 -> 2)
 * 3. can add 1 once with odd num.
 * 
 * e.g.
 * Input: "abccccdd"
 * Output: 7
 * 
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 * 
 * 
 * @author Sunny Park
 *
 */
public class LongestPalindrome {
    
    /**
     * Q1. checking all palindromic substrings
     * @param s
     * @return
     */
    public static int longestPalindromicSubsequence(String s) {
        List<Integer> result = new ArrayList<>();
        result.add(Integer.MIN_VALUE);
        for (int i = 0; i < s.length(); i++) {
            expand(result, s, i, i);
            expand(result, s, i, i + 1);
        }
        return result.get(0);
    }
    
    private static void expand(List<Integer> result, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > result.get(0)) {
                result.set(0, right - left + 1);
            }
            left--;
            right++;
        }
    }
    
    public static int longestPalindromicCombination(String s) {
        int[] freqArr = build(s);
        int result = 0;
        for (int num : freqArr) {
            result += (num / 2) * 2;
            if (result % 2 == 0 && num % 2 == 1) {
                result++;
            }
        }
        return result;
    }
    
    private static int[] build(String s) {
        int[] freqArr = new int[128];
        for (char c : s.toCharArray()) {
            freqArr[(int)c]++;
        }
        return freqArr;
    }
    
    public static void main(String[] args) {
        String s = "abccba";
        System.out.println(longestPalindromicSubsequence(s)); // expected: 6
        
        String c = "abccccdd";
        System.out.println(longestPalindromicCombination(c)); // expected: 7
        
        /* char <-> int */
//        char a = 'a';
//        System.out.println(a - '0');
//        System.out.println((int)a);
    }
}
