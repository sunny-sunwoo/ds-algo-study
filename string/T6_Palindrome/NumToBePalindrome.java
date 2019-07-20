package ds_algo_study.string.T6_Palindrome;

/**
 * Q. Given a string,
 * chars can be added "in front of" the string only.
 * how many chars do we need for the str to be a palindrome?
 * 
 * [Approach] use lps arr!
 *  1. build lps array of given str + reversed str
 *  2. answer = total str len - last elem.
 *  
 *  e.g. 
 *  "abc" -> "abc + cba" = [0,0,0,0,0,1] -> 3 - 1 = 2 (to be added: cbabc)
 *  "abb" -> "abb + bba" = [0,0,0,0,0,1] -> 3 - 1 = 2
 *  "aba" -> "aba + aba" = [0,0,1,1,2,3] -> 3 - 3 = 0 (palindrome itself)
 * 
 * @author Sunny Park
 *
 */
public class NumToBePalindrome {
    public static int numToBePalindrome(String str) {
        StringBuilder sb = new StringBuilder(str).append(new StringBuilder(str).reverse());
        int[] lps = buildLps(sb.toString());
        return str.length() - lps[lps.length - 1];
    }
    
    private static int[] buildLps(String s) {
        int[] lps = new int[s.length()];
        int i = 0;
        for (int j = 1; j < s.length();) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                lps[j] = i;
                j++;
            } else {
                if (i != 0) {
                    i = lps[i - 1];
                } else { // i == 0
                    lps[j] = 0;
                    j++;
                }
            }
        }
        return lps;
    }
    
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abb";
        String s3 = "aba";
        System.out.println(numToBePalindrome(s1)); // 2 -> cbabc
        System.out.println(numToBePalindrome(s2)); // 2 -> bbabb
        System.out.println(numToBePalindrome(s3)); // 0 -> itself
    }
}
