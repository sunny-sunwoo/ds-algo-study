package ds_algo_study.string.T1_LHM;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.Iterables;

/**
 * Q.Given a String,
 * find the index from where 26 letters(a-z) are found in a consecutive sequence.
 * 
 * [Approach] LHM size == 26?
 * - keep Character-Position mapping in LHM to keep insertion order.
 * - when size is 26, check the dist btw last - first (to see if consecutive.)
 *  -> rt (curr pos - 25). 
 * 
 * @author Sunny Park
 *
 */
public class Q3_ContinuousAlphabet {
    public static int findAlphabetSequence(String s) {
        checkArgument(checkNotNull(s).length() >= 26);
        s = s.toLowerCase();
        LinkedHashMap<Character, Integer> cache = new LinkedHashMap<Character, Integer>(27, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<Character, Integer> entry) {
                return size() > 27;
            }
        };
        
        for (int i = 0; i < s.length(); i++) {
            cache.put(s.charAt(i), i);
            if (cache.size() == 26) {
                if (Iterables.getLast(cache.entrySet()).getValue() 
                        - Iterables.getFirst(cache.entrySet(), null).getValue() == 25) {
                    return i - 25;
                }
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        String s = "AAAAACCCHIJKLMNOPQRSTUVWXYZABCDEFG";
        System.out.println(findAlphabetSequence(s)); // expected: 8
    }
}
