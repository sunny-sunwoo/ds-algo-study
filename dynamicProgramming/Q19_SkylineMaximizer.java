package ds_algo_study.dynamicProgramming;

/**
 * LC 807. Max Increase to Keep City Skyline
 * 
 * In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there.
 * We are allowed to increase the height of any number of buildings, by any amount 
 * (the amounts can be different for different buildings). 
 * At the end, the "skyline" when viewed from all four directions of the grid, 
 * i.e. top, bottom, left, and right, must be the same as the skyline of the original grid.
 * 
 * What is the maximum total sum that the height of the buildings can be increased?
 * (input grid is square.)
 * 
 * e.g. 
 * 
 * 12, 9, 5  <= 12
 * 4 , 7, 7  <= 7
 * 11, 3, 7  <= 11
 *  ^  ^  ^
 * 12  9  7
 * 
 * amount that can be increased?
 *   = min(rowMax, colMax) - curr cell.
 *   
 *   => (0,2) (1,0) (2,1) points can be increased.
 *   
 * [Approach] 
 * 1. create a rowMax, colMax array and populate max values.
 * 2. accumulate the difference between min of rowMax, colMax AND curr cell.
 *   
 *   
 * @author Sunny Park
 *
 */
public class Q19_SkylineMaximizer {
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int len = grid.length;
        int[] rowMax = new int[len];
        int[] colMax = new int[len];
        
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                rowMax[r] = Math.max(rowMax[r], grid[r][c]);
                colMax[c] = Math.max(colMax[c], grid[r][c]);
            }
        }
        
        int result = 0;
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                result += Math.min(rowMax[r], colMax[c]) - grid[r][c];
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        System.out.println(maxIncreaseKeepingSkyline(grid));
        
        /**
         * original grid = 
                        [ [3, 0, 8, 4], 
                          [2, 4, 5, 7],
                          [9, 2, 6, 3],
                          [0, 3, 1, 0] ]
                          
         * gridNew = [  [8, 4, 8, 7],
                        [7, 4, 7, 7],
                        [9, 4, 8, 7],
                        [3, 3, 3, 3] ]
                        
                        => Output: 35
         */
        
    }
}
