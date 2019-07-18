package ds_algo_study.string.T1_LHM;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;

/**
 * Q. Given a string and characters,
 * find the length of shortest substring with all characters.
 * 
 * e.g. "aaabctrrak" chars=[c,t,a]
 *         ^ ^^
 * [Approach] LHM with hashset
 *  - build hashset with given chars for faster lookup
 *  - if curr char is contained in the set -> push to the cache.
 *  - compare cache size vs hashset size
 *     => same? keep the shorter length.
 *  - return length. * NOTE * check one more time.
 *  
 * @author Sunny Park
 *
 */
public class Q2_ShortestSubstringWithChars {
    public static OptionalInt findShortestSubstring(String s, char... chars) {
        checkArgument(checkNotNull(chars).length > 0);
        checkArgument(checkNotNull(s).length() >= chars.length);
        
        int targetLen = chars.length;
        LinkedHashMap<Character, Integer> cache = new LinkedHashMap<Character, Integer>(targetLen + 1, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<Character, Integer> entry) {
                return size() > targetLen + 1;
            }
        };
        Set<Character> charSet = Sets.newHashSet(Chars.asList(chars));
        int length = Integer.MAX_VALUE;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (!charSet.contains(c)) {
                continue;
            }
            cache.put(c, i);
            if (cache.size() == charSet.size()) {
                Map.Entry<Character, Integer> entry = Iterables.getFirst(cache.entrySet(), null);
                length = Math.min(length, i - entry.getValue() + 1);
                // cache.remove(entry.getKey());
            }
        }
        return cache.size() == charSet.size() ? OptionalInt.of(length) : OptionalInt.empty();
    }
    
    public static void main(String[] args) {
        char[] chars = {'c', 't', 'a', 'k'};
        String s = "aaabctrrrrrak";
//        String s = "ctttttakc";
        System.out.println(findShortestSubstring(s, chars));
    }
}
