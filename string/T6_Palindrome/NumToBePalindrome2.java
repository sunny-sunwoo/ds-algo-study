package ds_algo_study.string.T6_Palindrome;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Q. Given a string,
 * chars can be added "ANYWHERE" in the string.
 * how many chars do we need for the str to be a palindrome?
 * 
 * [Approach] length - LCS
 * find LCS between original string & reversed string
 * bc/ no need to add LCS char. need to add chars other than the LCS char.
 * 
 *  e.g. 
 *  "abc" -> "abc + cba" => lcs = 1 (3-1 = 2) -> need to add 2 more chars. (ba)
 *  "abb" -> "abb + bba" => lcs = 2 (3-2 = 1) -> need to add 1 more char. (a)
 *  "aba" -> "aba + aba" => lcs = 3 (3-3 = 0) -> palindrome itself.
 * 
 * @author Sunny Park
 *
 */
public class NumToBePalindrome2 {
    public static int findNumToBePalindrome(String s) {
        checkArgument(checkNotNull(s).length() > 0);
        return s.length() - getLCS(s, new StringBuilder(s).reverse().toString());
    }
    
    private static int getLCS(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) continue;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                
            }
        }
        
        return dp[s1.length()][s2.length()]; 
    }
    
    public static void main(String[] args) {
        System.out.println(findNumToBePalindrome("abc")); // 2
        System.out.println(findNumToBePalindrome("abb")); // 1
        System.out.println(findNumToBePalindrome("aba")); // 0
    }
}
