package ds_algo_study.string.T5_KMP;

import java.util.Arrays;

/**
 * 
 * Q. how to find needle in the haystack.
 * 
 * [Approach] KMP algorithm.
 * "How many right shifting is possible in max?"
 * Think about "Pattern"! 
 * 
 * - by keeping overlapped length of prefix-suffix,
 *   after mismatch, jump by max steps, instead of 1.
 *   
 *   Java has indexOf but it moves by 1. 
 *   e.g. hay = "aaaaaaaaaaaaaaaa", needle = "aaaaaaab" // N * M time.
 *   
 *   => hay = "abcdabcf......" needle = "abcdabck"
 *                    ^                         ^
 *   in KMP, when mismatch happened, 
 *   jump step = matched number(7) - overlap(3) 
 *             = 4 (max num of right shifting)
 *  
 * @author Sunny Park
 *
 */
public class PartialMatch {
    public static int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        
        int i = 0; // haystack pointer
        int j = 0; // needle pointer
        
        if (n == 0) return 0;
        int[] lps = getLPS(needle);
        
        while (i < m) {
//            System.out.println(i + " , " + j);
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == n) {
                    return i - n; // starting point of the pattern.
                }
            } else if (j > 0) { // need to know where to jump.
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }
    
    // Longest Prefix Suffix
    private static int[] getLPS(String s) {
        int[] lps = new int[s.length()];
        int i = 0;
        for (int j = 1; j < lps.length;) {
            // 1. matched
            // 2. not matched -> i != 0
            // 3. not matched -> i == 0
            
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                lps[j] = i;
                j++;
            } else if (i != 0) {
                i = lps[i - 1];
            } else { // i == 0
                lps[j] = 0;
                j++;
            }
        }
        return lps;
    }
    
//    //prac
//    public static int strStr(String hay, String needle) {
//        int m = hay.length(), n = needle.length();
//        int i = 0, j = 0;
//        int[] lps = getLPS(needle);
//        
//        while (i < m) {
//            if (hay.charAt(i) == needle.charAt(j)) {
//                i++;
//                j++;
//                if (j == n) {
//                    return i - j;
//                }
//            } else {
//                if (j != 0) {
//                    j = lps[j - 1];
//                } else { // j == 0
//                    i++;
//                }
//            }
//        }
//        return -1;
//    }
//    
//    private static int[] getLPS(String str) {
//        int[] lps = new int[str.length()];
//        int i = 0;
//        for (int j = 1; j < str.length(); ) {
//            if (str.charAt(i) == str.charAt(j)) {
//                i++;
//                lps[j] = i;
//                j++;
//            } else {
//                if (i != 0) {
//                    i = lps[i - 1];
//                } else {
//                    lps[j] = 0;
//                    j++;
//                }
//            }
//        }
//        return lps;
//    }
    
    public static void check(String s) {
        int[] lps = new int[s.length()];
        computeLPSArray(s, s.length(), lps);
        System.out.println(Arrays.toString(lps));
    }
    
    static void computeLPSArray(String pat, int M, int lps[]) 
    { 
        // length of the previous longest prefix suffix 
        int len = 0; 
        int i = 1; 
        lps[0] = 0; // lps[0] is always 0 
  
        // the loop calculates lps[i] for i = 1 to M-1 
        while (i < M) { 
            if (pat.charAt(i) == pat.charAt(len)) { 
                len++; 
                lps[i] = len; 
                i++; 
            } 
            else // (pat[i] != pat[len]) 
            { 
                // This is tricky. Consider the example. 
                // AAACAAAA and i = 7. The idea is similar 
                // to search step. 
                if (len != 0) { 
                    len = lps[len - 1]; 
  
                    // Also, note that we do not increment 
                    // i here 
                } 
                else // if (len == 0) 
                { 
                    lps[i] = len; 
                    i++; 
                } 
            } 
        } 
    } 
     public static void main(String[] args) {
         String haystack = "abcdabcrabcdabcf";
         String needle = "abcdabcf";
         System.out.println("check  " + Arrays.toString(getLPS("aaabaabb")));    
         System.out.println(strStr(haystack, needle)); // expected: 8
     }
     
}
