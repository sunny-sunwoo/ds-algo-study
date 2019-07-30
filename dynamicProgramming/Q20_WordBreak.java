package ds_algo_study.dynamicProgramming;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 
 * LC 139. Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * e.g.
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * [Approach] dp. backward!
 * 1. create a boolean array with len + 1. 
 * 2. assign true to the last elem.
 * 3. iterate through the string
 *    - u = starting point. (from right to the left)
 *    - v = ending point. 
 *    1) v = u + minLen
 *    2) while v is within the string length AND u + maxLen
 *       - if (1)valid[v] is true AND (2)substring is in the dictionary
 *         -> mark the starting point as true. 
 *         (to avoid repeating after this point)
 *       - else, v++. (just move ending point to the right.)
 * 
 * @author Sunny Park
 *
 */
public class Q20_WordBreak {
    Set<String> dictionary;
    int minLength;
    int maxLength;
    
    Q20_WordBreak(Set<String> dictionary) {
        this.dictionary = dictionary;
        
        minLength = dictionary.stream()
                              .mapToInt(String::length)
                              .min()
                              .getAsInt();
        
        maxLength = dictionary.stream()
                              .mapToInt(String::length)
                              .max()
                              .getAsInt();
    }
    
    public boolean isValidString(String str) {
        int len = checkNotNull(str).length();
        checkArgument(len > 0);
        
        boolean[] valids = new boolean[len + 1];
        valids[len] = true;
        
        for (int u = len - 1; u >= 0; u--) {
            int v = u + minLength;
            while (v <= len && v <= u + maxLength) {
                if (valids[v] && dictionary.contains(str.substring(u, v))) {
                    valids[u] = true;
                    break;
                }
                v++;
            }
        }
        // System.out.println(Arrays.toString(valids));
        return valids[0];
    }
    
    public static void main(String[] args) {
        Q20_WordBreak wb = new Q20_WordBreak(Sets.newHashSet("cats", "dog", "sand", "and", "cat"));
        System.out.println(wb.isValidString("catsanddog"));
    }
}
