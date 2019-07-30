package ds_algo_study.dynamicProgramming;

import java.util.Arrays;

/**
 * Q. find nth fibo number
 *    0, 1, 1, 2, 3, 5, 8, 13
 * [Approach1] Top down - using recursion.
 *             O(2^n) time, O(n) space
 *  1. base  case : n == 0 or 1
 *  2. otherwise, f(n) = f(n - 1) + f(n - 2)
 *  
 *  
 * [Approach2] Top down - with cache
 *             O(n) time, O(n) space
 * in the approach1, there are overlapping subproblems. 
 * => should cache to avoid repeating same operation.
 * 
 *  1. create an array of n+1 length.
 *  2. fill -1
 *  3. call helper method
 *     => if curr > 0 -> just return. (which means it finished operation)
 *     => return f(n - 1) + f(n - 2)
 *     
 *     
 * [Approach3] Bottom up - using array
 *             O(n) time, O(n) space
 * 1. seed: when n = 0 or 1. 
 * 2. iterate over the nums and fill out the array.
 *    => if we know arr[0], arr[1] -> can solve arr[2]
 *    
 *    
 * [Approach4] Bottom up - constant space
 *             O(n) time, O(1) space
 * => no need to keep all elem in array
 * 1. by proceeding n1, n2, 
 *    and updating n0.
 * 2. return n1 + n2
 * 
 * @author Sunny Park
 *
 */
public class Q11_FibonacciSequence {
    public static int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        return fibo(n - 1) + fibo(n - 2);
    }
    
    public static int fibo_topDown(int n) {
        if (n < 2) return n;
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return fibo_topDown(n, cache);
    }
    
    private static int fibo_topDown(int n, int[] cache) {
        if (cache[n] >= 0) return cache[n]; 
        cache[n] = fibo_topDown(n - 1, cache) + fibo_topDown(n - 2, cache);
        return cache[n];
    }
    
    public static int fibo_bottomUp(int n) {
        if (n < 2) return n;
        int[] cache = new int[n + 1];
        cache[0] = 0;
        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }
    
    public static int fibo_constantSpace(int n) {
        if (n < 2) return n;
        int n1 = 0;
        int n2 = 1;
        for (int i = 2; i < n; i++) {
            int n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n1 + n2;
    }
    
    public static void main(String[] args) {
        System.out.println(fibo(6));
        System.out.println(fibo_topDown(6));
        System.out.println(fibo_bottomUp(6));
        System.out.println(fibo_constantSpace(6));
    }
}
