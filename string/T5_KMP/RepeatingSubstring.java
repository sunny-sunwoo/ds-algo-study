package ds_algo_study.string.T5_KMP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Strings;

/**
 * 3 Examples using LPS array.
 * Q1) validate if string is formed with repeating substring(length > 2)
 * 
 * Q2) find the longest candidate(length) for prefix/suffix
 * 
 * Q3) find all candidates(set of string) for prefix/suffix
 * 
 * @author Sunny Park
 *
 */
public class RepeatingSubstring {
    /**
     * Q1) validate if string is formed with repeating substring(length > 2)
     * 1. build a lps array.
     * 2. (filter) if last elem of lps == 0 OR last + 1 == length -> invalid. 
     * 3. find the candidate substring
     * 4. check the given string by repeating candidate.
     * @param str
     * @return
     */
    public static boolean validatePattern(String str) {
        // validate string.
        int[] lps = getLps(str);
        int subLen = lps[lps.length - 1];
        // no match OR "aaa"
        if (subLen == 0 || subLen + 1 == str.length()) {
            return false;
        }
        
        while (str.length() % subLen != 0) {
            subLen = lps[subLen - 1];
            if (subLen < 2) {
                return false;
            }
        }
        
        return Strings.repeat(str.substring(0, subLen), str.length() / subLen).equals(str);
    }
    
    private static int[] getLps(String str) {
        int[] lps = new int[str.length()];
        int i = 0;
        for (int j = 1; j < str.length();) {
            // 1. match
            // 2. no match - j > 0
            // 3. no match - j == 0
            
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                lps[j] = i;
                j++;
            } else {
                if (i != 0) {
                   i = lps[i - 1];  // * NOTE * index!
                } else { // i == 0
                    lps[j] = 0;
                    j++;
                }
            }
        }
        return lps;
    }
    
    /**
     * Q2) find the longest candidate(length) for prefix/suffix
     * 1. build lps array
     * 2. find the max elem (which means the max length of the candidate.)
     * @param s
     * @return
     */
    public static int findLongestCandidate(String s) {
        int[] lps = buildLps(s);
        return getMax(lps);
    }
    
    private static int[] buildLps(String s) {
        int[] lps = new int[s.length()];
        int i = 0;
        for (int j = 1; j < s.length(); ) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                lps[j] = i;
                j++;
            } else {
                if(i != 0) {
                    i = lps[i - 1];
                } else { // i == 0
                    lps[j] = 0;
                    j++;
                }
            }
        }
        return lps;
    }
    
    private static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }
    
    /**
     * abcdabcdabcd
     * 000012345678
     *    ^   ^    
     * @param s
     * @return
     */
    public static Set<String> findAllCandidates(String s) {
        Set<String> result = new HashSet<>();
        int[] lps = buildLps(s);
        int len = lps[s.length() - 1];
        
        while (len > 0) {
            result.add(s.substring(0, len));
            len = lps[len - 1];
        }
        return result;
    }
    
    public static void main(String[] args) {
        String s = "abababab";
        System.out.println(Arrays.toString(getLps(s)));
        System.out.println(validatePattern(s));
        System.out.println(findLongestCandidate("abcdabcddefg"));
        System.out.println(findAllCandidates("abcdabcdabcd"));
    }

}
