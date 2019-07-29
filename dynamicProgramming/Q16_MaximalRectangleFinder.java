package ds_algo_study.dynamicProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * LC 85. Maximal Rectangle
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
 * containing only 1's and return its area.
 * 
 * [Approach] DP similar to {@code Q15_MaxRecyclingArea}
 * 1. iterating through each cell. using nested loop
 * 2. for each row, 
 *    - populate a height list with "1" point. 
 *      -> if 1, accumulate. else, override 0!
 *    - get max rect area. keep max only
 * 
 * => to get max rect area, given a height list
 * 1. add 0 to the end
 * 2. iterate through the height list
 *    - while (stack is not empty && height at stack.peek value >= height at curr) 
 *       -> highest? pop
 *       -> width? i - 1 - peek OR i 
 *       -> get max area.
 *    - push curr index to the stack.
 * 
 * @author Sunny Park
 *
 */
public class Q16_MaximalRectangleFinder {
    public static int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int maxRectArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            int curr = getMaxArea(heights);
            maxRectArea = Math.max(maxRectArea, curr);
        }
        return maxRectArea;
    }
    
    private static int getMaxArea(int[] input) {
        List<Integer> heights = Arrays.stream(input).boxed().collect(Collectors.toList());
        heights.add(0);
        Stack<Integer> stack = new Stack<>();
        
        int maxArea = 0;
        for (int i = 0; i < heights.size(); i++) {
            while (!stack.isEmpty() && heights.get(stack.peek()) >= heights.get(i)) {
                int highest = stack.pop();
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, width * heights.get(highest));
            }
            stack.push(i);
        }
        return maxArea;
    }
    
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        System.out.println(maximalRectangle(matrix));
    }
}
