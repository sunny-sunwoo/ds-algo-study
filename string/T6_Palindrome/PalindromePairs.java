package ds_algo_study.string.T6_Palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * LC#336 Palindrome Pairs
 * Q. Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * 
 * e.g.
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]] 
 * Explanation: The palindromes are ["battab","tabbat"]
 * 
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]] 
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * [Approach]
 * 1. build a hashmap: String - Index
 * 2. iterate through each word:
 *    1) make 2 substrings of prefix, suffix
 *       e.g. "bat" -> "", "bat" // "b", "at" // "ba", "t" // "bat" ""
 *       
 *    2) check if
 *       (case 1) prefix is palindrome                   e.g.  "b"
 *                 -> find the reverse of suffix               find "ta" which is rev("at")
 *                 -> pair (rev suffix idx, curr idx)          -> "tabat" = pair of (rev, curr) 
 *                 
 *       (case 2) suffix is palindrome
 *                 -> find the reverse of prefix.
 *                 -> pair (curr, rev)
 *                
 * 
 * @see <a href="https://leetcode.com/problems/palindrome-pairs/"> LC#336 </a>
 * @author Sunny Park
 *
 */
public class PalindromePairs {
    public static List<Pair<Integer, Integer>> findPalindromePairs(String... strs) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        Map<String, Integer> cache = populate(strs);
        
        for (int i = 0; i < strs.length; i++) {
            String curr = strs[i];
            for (int j = 0; j < curr.length(); j++) {
                String prefix = curr.substring(0, j); // 0, curr.length() - j 
                String suffix = curr.substring(j); // curr.length() - j, for the same order w/LC
                
                if (isPalindrome(prefix)) {
                    Integer revSuffixIdx = cache.get(new StringBuilder(suffix).reverse().toString());
                    if (revSuffixIdx != null && revSuffixIdx != i) {
                        result.add(Pair.of(revSuffixIdx.intValue(), i));
                    }
                }
                
                if (isPalindrome(suffix)) {
                    Integer revPrefixIdx = cache.get(new StringBuilder(prefix).reverse().toString());
                    if (revPrefixIdx != null && revPrefixIdx != i) {
                        result.add(Pair.of(i, revPrefixIdx.intValue()));
                    }
                }
            }
        }
        return result;
    }
    
    private static Map<String, Integer> populate(String... strs) {
        Map<String, Integer> cache = new HashMap<>();
        int idx = 0;
        for (String s : strs) {
            cache.put(s, idx++);
        }
        return cache;
    }
    
    private static boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
    
    public static void main(String[] args) {
        String[] strs = {"bat","tab","cat"};
//        String[] strs = {"abcd","dcba","lls","s","sssll"};
        System.out.println(findPalindromePairs(strs));
    }
}
