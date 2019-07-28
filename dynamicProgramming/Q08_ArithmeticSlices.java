package ds_algo_study.dynamicProgramming;

/**
 * A sequence of number is called arithmetic if it consists of at least three elements 
 * and if the difference between any two consecutive elements is the same.
 * 
 * e.g. 
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 
 * Input:
 * A = [1, 2, 3, 4]
 * 
 * Output:
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 * 
 * @author Sunny Park
 *
 */
public class Q08_ArithmeticSlices {
    public static int arithmeticSlices(int[] input) {
        int sum = 0;
        int[] memo = new int[input.length];
        
        for (int i = 2; i < input.length; i++) {
            if (input[i - 1] - input[i - 2] == input[i] - input[i - 1]) {
                memo[i] = 1 + memo[i - 1];
                sum += memo[i];
            }
        }
        
        return sum;
    }
}
