package ds_algo_study.recursion;


/**
 * Q. Making Change. Given an integer representing a given amount of change,
 * write a function to compute the minimum number of coins
 * required to make that amount of change. You can assume that
 * there is always a 1cent coin.
 * 
 * e.g.
 * coins: 1, 5, 10, 25 cents
 * 
 * makeChange(1) = 1
 * makeChange(6) = 2 (5, 1)
 * makeChange(49) = 7 (25, 10, 10, 1, 1, 1, 1)
 * 
 * [Approach] Recursion
 * 1. Base case
 *    - remainder == 0: found!
 *    - remainder < 0 or index out of bounds: not found.
 *    
 * 2. Logic
 *    - recursive call for include OR exclude
 *    - if (include < 0) => rt exclude.
 *    - if (exclude < 0) => rt include + 1.
 *    - rt min(exclude, include + 1).
 *    
 * 
 * [Space/Time]
 * - Time: O(2 ^ n)
 *   branching factor: 2, height: n(amt)
 *   
 * - Space: O(n) // depth of the recursion
 * 
 * @author Sunny Park
 *
 */
public class P3_HW3MakeChange {
    public static int makeChange(int[] coins, int amt) {
        return makeChange(coins, amt, 0);
    }
    
    private static int makeChange(int[] coins, int remainder, int ptr) {
        if (remainder == 0) return 0;
        if (remainder < 0 || ptr == coins.length) return -1;
        
        int include = makeChange(coins, remainder - coins[ptr], ptr);
        int exclude = makeChange(coins, remainder, ptr + 1);
        
        if (include < 0) return exclude; // include should be checked first!!
        if (exclude < 0) return include + 1; // should add up 1 later! to add 'self'
        
        return Math.min(include + 1, exclude);
    }
    
    public static void main(String[] args) {
        int[] coins = {1, 5, 10, 25};
        int amt = 49;
        System.out.println(makeChange(coins, amt));
    }
    
    
}
