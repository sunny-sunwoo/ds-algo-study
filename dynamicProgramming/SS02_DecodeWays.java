package ds_algo_study.dynamicProgramming;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * 
 * e.g.
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * 
 * [Approach] dp.
 * 1. create an int array of (len+1)
 * 2. cache[0] = 1.
 *    cache[1] = 0 or 1 (depending on the first str is 0 or not)
 * 3. iterate through i = 2 ~ len
 *    1) one digit. 
 *       if 1 <= oneDigit <= 9, cache[i] += cache[i - 1]  
 *    2) two digit.
 *      if 10 <= twoDigit <= 26, cache[i] += cache[i - 2]
 * 
 * 
 * @author Sunny Park
 *
 */
public class SS02_DecodeWays {
    public static int numDecodings(String s) {
        int len = checkNotNull(s).length();
        int[] cache = new int[len + 1];
        cache[0] = 1;
        cache[1] = Character.getNumericValue(s.charAt(0)) == 0 ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int oneDigit = Character.getNumericValue(s.charAt(i - 1));
            if (1 <= oneDigit && oneDigit <= 9) {
                cache[i] = cache[i - 1];
            }
            
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (10 <= twoDigit && twoDigit <= 26) {
                cache[i] += cache[i - 2];
            }
        }
        return cache[len];
    }
    
    public static void main(String[] args) {
        System.out.println(numDecodings("11"));
    }
}
