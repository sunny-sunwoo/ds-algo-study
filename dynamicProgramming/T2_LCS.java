package ds_algo_study.dynamicProgramming;

/**
 * Longest Common Sequence
 * Given 2 strings, find lcs length.
 * 
 * e.g. acbrtkt vs absogrt
 *      ^ ^^  ^    ^^   ^^    => 4
 *      
 * [Approach]
 * think about subproblems. check if last letter matches!
 * 
 * acb vs ab  =>  'b' matches? 
 *                then lcs length is lcs("ac", "a") + 1.
 *             
 * acbr vs abso => r vs o: no match
 *                 then the length is max(lcs("acb"), lcs("abs")). 
 * 
 * @author Sunny Park
 *
 */
public class T2_LCS {
    public static int findLCS(String s1, String s2) {
        int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) continue;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        
        return lcs[s1.length()][s2.length()];
    }
    
    public static void main(String[] args) {
        System.out.println(findLCS("acbrtkt", "absogrt")); // expected: 4
    }
}
