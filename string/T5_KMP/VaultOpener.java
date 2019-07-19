package ds_algo_study.string.T5_KMP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Q. how many times did we shift from the original number?
 * 
 * e.g. 
 * original = "abcdef"
 * shifted = "cdefab"
 *  -> if counterclockwise: 2
 *        clockwise: length - 2 = 4.
 *        
 * [Approach] use KMP!!
 * 1. concat origin + origin (haystack)
 * 2. find the needle(shifted)
 * 
 * abcdefabcdef
 *   |    |
 *   cdefab 
 *   
 * [Solution]
 * 1. build LPS(Longest Prefix Suffix) array
 * 2. match -> i++, j++
 * 3. no match 
 *    => i != 0 ? i = lps[i-1]
 *       else, j++
 * 
 * @author JungwooP
 *
 */
public class VaultOpener {
    public static List<Integer> findShiftingNumber(String original, String... shifts) {
        return Arrays.stream(shifts)
                .map(shift -> find(original + original, shift))
                .collect(Collectors.toList());
    }
    
    private static int find(String original, String shift) {
        int[] lps = buildLps(shift);
        int m = original.length(), n = shift.length();
        int i = 0, j = 0;
        
        while (i < m) {
            if (original.charAt(i) == shift.charAt(j)) {
                i++;
                j++;
                if (j == n) {
                    return i - j;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else { // i == 0.
                    i++;
                }
            }
        }
        return -1;
    }
    
    private static int[] buildLps(String s) {
        int[] lps = new int[s.length()];
        int i = 0;
        for (int j = 1; j < s.length(); ) {
            if (s.charAt(i) ==  s.charAt(j)) {
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
        String origin = "abcdef";
        String[] shifts = {"cdefab", "abcdef", "fabcde"};
        System.out.println(Arrays.toString(buildLps(origin))); // expected: 2, 0, 5
        System.out.println(findShiftingNumber(origin, shifts));
    }
}
