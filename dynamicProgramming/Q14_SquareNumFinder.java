package ds_algo_study.dynamicProgramming;

import com.google.common.primitives.Ints;

/**
 * Given a 2D binary matrix filled with 0's and 1's, 
 * find the largest square containing only 1's and return its area.
 * 
 * [Approach] dp. 
 * 1. init a new dp table. copy the 0th row and col.
 * 2. for the rest, check if the cell is 1, 
 * then update with the min of 3 points ([i-1][j], [i-1][j-1], [i][j-1]) + 1.
 * 
 * @see <a href="https://leetcode.com/problems/maximal-square/"> 
 * LC221. Maximal Square </a>
 * @author Sunny Park
 *
 */
public class Q14_SquareNumFinder {
    public static int findSquareNumber(int[][] nums) {
        int[][] memo = init(nums);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < nums[0].length; j++) {
                if (nums[i][j] == 0) continue;
                memo[i][j] = getMin(nums, i, j) + 1;
            }
        }
        return getSum(memo);
    }
    
    private static int[][] init(int[][] nums) {
        int[][] memo = new int[nums.length][nums[0].length];
        System.arraycopy(nums[0], 0, memo[0], 0, nums[0].length);
        for (int i = 0; i < nums.length; i++) {
            memo[i][0] = nums[i][0];
        }
        return memo;
    }
    
    private static int getMin(int[][] nums, int i, int j) {
        return Ints.min(nums[i - 1][j], nums[i - 1][j - 1], nums[i][j - 1]);
    }
    
    private static int getSum(int[][] memo) {
        int sum = 0;
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                sum += memo[i][j];
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        int[][] nums = {{1,0,1}, {1,1,0}, {1,1,0}};
        System.out.print(findSquareNumber(nums));
    }
}
