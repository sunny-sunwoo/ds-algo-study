package ds_algo_study.string;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

/**
 * [Note]
 * when dealing with String,
 * clarify if it's lower/upper case, or Ascii(7-bit, 128), or Unicode(16-bit, 2^16=65,536)
 * 
 * @author Sunny Park
 *
 */
public class T3Anagram {
    public static boolean isAnagram(String s1, String s2) {
        checkNotNull(s1);
        checkNotNull(s2);
        
        if (s1.length() != s2.length()) {
            return false;
        }
        return Objects.equals(getUnicodeMap(s1), getUnicodeMap(s2));
    }
    
    public static ImmutableMap<Integer, Integer> getUnicodeMap(String s) {
        return  ImmutableMap.copyOf(
                s.codePoints()
                .boxed()
                .collect(Collectors.groupingBy((t -> t), Collectors.summingInt(t -> 1))));
    }
    
    public static boolean isEnglishAnagram(String s1, String s2) {
        checkArgument(checkNotNull(s1).length() != 0);
        checkArgument(checkNotNull(s2).length() != 0);
        
        int[] chars = new int[26];
        for (char c : s1.toCharArray()) {
            chars[c - 'a']++;
        }
        
        for (char c : s2.toCharArray()) {
            chars[c - 'a']--;
        }
        
        return Arrays.stream(chars).allMatch(num -> (num == 0));
    }
    
    public static boolean isAsciiAnagram(String s1, String s2) {
        checkArgument(checkNotNull(s1).length() != 0);
        checkArgument(checkNotNull(s2).length() != 0);
        
        int[] chars = new int[128];
        
        for (char c : s1.toCharArray()) {
            chars[c - 'a']++;
        }
        
        for (char c : s2.toCharArray()) {
            chars[c - 'a']--;
            if (chars[c - 'a'] < 0) return false;
        }
        
        return Arrays.stream(chars).allMatch((num) -> (num == 0));
    }
    
    public static void main(String[] args) {
        System.out.println(isAnagram("ana", "aan"));
        System.out.println(isEnglishAnagram("anaaaaaa", "aanaaaaa"));
        System.out.println(isAsciiAnagram("anaaaaaa", "aanaaaaa"));
    }
}
