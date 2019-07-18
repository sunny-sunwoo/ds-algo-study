package ds_algo_study.string.T1_LHM;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.OptionalInt;

import com.google.common.collect.Iterables;

/**
 * Given an unique char number and a string,
 * find the length of longest substring.
 * 
 * e.g. aabbccd, n = 3(num of unique chars)
 *      ^    ^   => length = 6.
 *      
 * [Approach] LinkedHashMap
 * - LHM guarantees the insertion order. 
 * - character(each letter) - integer(index) mapping
 *   => by using linear scan, 
 *      keep the longest length when the cache size passes n.
 * 
 * @author Sunny Park
 *
 */
public class Q1_LongestSubstringWithUniqChar {
    public static OptionalInt longestSubstring(String s, int num) {
        checkArgument(checkNotNull(s).length() > 0);
        LinkedHashMap<Character, Integer> cache = new LinkedHashMap<Character, Integer>(num + 1, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<Character, Integer> entry) {
                return size() > num + 1;
            }
        };
        
        int length = 0;
        int startPos = 0;
        
        for (int i = 0; i < s.length(); i++) {
            cache.put(s.charAt(i), i);
            if (cache.size() > num) {
                length = Integer.max(length, i - startPos);
                Character removal = Iterables.get(cache.entrySet(), 0).getKey();
                startPos = cache.remove(removal) + 1;
            }
        }
        
        // check to cover edge case(e.g. "aabbccaa")
        return cache.size() >= num 
                ? OptionalInt.of(Integer.max(length, s.length() - startPos)) : OptionalInt.empty();
    }
    
    public static void main(String[] args) {
        System.out.println(longestSubstring("aabbccd", 3));
    }
}
