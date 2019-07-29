package ds_algo_study.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 
 * Q. Given n non-negative integers representing the histogram's bar height 
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * 
 * e.g. 
 * heights = [1,2,3,4,5,3,3,2]
 * height 5 -> 5*1 = 5
 * height 4 -> 4*2 = 8
 * height 3 -> 3*5 = 15
 * height 2 -> 2*7 = 14
 * height 1 -> 1*8 = 8 
 *  => output : 15 (max area) 
 * 
 * [Approach1] expand to the both side.
 * 1. iterate through the heights arr
 *  - find left & right bound.
 *  - keep max area
 *   => will take O(n^2). I should traverse back and forth.
 *   
 * [Approach2] better solution with Stack & right anchor point!
 * 1. add 0 to the end. (for the last height.)
 * 2. iterate through the heights arr
 *    - while stack is NOT empty && curr height is smaller than stack's peek
 *       -> get currMax(height) with popping, width, max area.
 *    - add curr idx to the stack.
 * 
 *    
 * [Note]
 *   stack keeps index! so heights.get(stack.peek()) is the actual height to compare!!! 
 * 
 * @see <a href="https://leetcode.com/problems/largest-rectangle-in-histogram/">
 * LC#84 Largest Rectangle in Histogram</a>
 * @author Sunny Park
 *
 */
public class Q15_MaxRecyclingArea {
    public static int largestRectangleArea(int[] h) {
        List<Integer> heights = new ArrayList<>(Arrays.stream(h).boxed().collect(Collectors.toList()));
        heights.add(0);
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.size(); i++) {
            while (!stack.isEmpty() && heights.get(i) <= heights.get(stack.peek())) { // be careful!! 
                int currHighest = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, width * heights.get(currHighest));
            }
            stack.push(i);
            //System.out.println(stack);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
//        int[] h = {1,2,3,4,5,3,3,2};
        int[] h = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(h));
    }
}
