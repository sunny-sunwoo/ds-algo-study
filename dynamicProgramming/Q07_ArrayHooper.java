package ds_algo_study.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q. Given an array of non negative integers where each element represents the max 
 * number of steps that can be made forward from that element. Write a function to 
 * return the minimum number of jumps to reach the end of the array from first element
 * 
 * Solution1. 
 * Have 2 for loop. j trails i. If arr[j] + j >= i then you calculate new jump
 * and result.
 * 
 * Space complexity O(n) to maintain result and min jumps
 * Time complexity O(n^2)
 * 
 * Reference
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 * 
 * Solution2. linear time
 * track curr, last separately.
 * when i passes last, save the visited index.
 * 
 * @author Sunny Park
 *
 */
public class Q07_ArrayHooper {
    public static int minJumpToEnd(int[] input) {
       int[] jumpNumber = new int[input.length];
       Arrays.fill(jumpNumber, Integer.MAX_VALUE);
       
       int[] jumpPoint = new int[input.length];
       jumpNumber[0] = 0;
       
       for (int i = 1; i < input.length; i++) {
           for (int j = 0; j <= i - 1; j++) {
               if (i <= j + input[j]) {
                   if (jumpNumber[i] > jumpNumber[j] + 1) {
                       jumpNumber[i] = jumpNumber[j] + 1;
                       jumpPoint[i] = j;
                   }
               }
           }
       }
       List<Integer> path = new ArrayList<>();
       buildPath(path, jumpPoint);
       System.out.println(path);
       return jumpNumber[input.length - 1];
    }
    
    private static void buildPath(List<Integer> path, int[] points) {
        path.add(0, points.length - 1);
        int idx = points.length - 1;
        while (points[idx] != 0) {
            path.add(0, points[idx]);
            idx = points[idx];
        }
        path.add(0, 0);
    }
    
    public static List<Integer> findMinJumpPath_linearTime(int[] arr) {
        List<Integer> path = new ArrayList<>();
        int last = 0, curr = 0, visited = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > last) {
                path.add(visited);
                last = curr;
            } 
            
            if (i + arr[i] > curr) {
                curr = i + arr[i];
                visited = i;
            }
        }
        System.out.println("check - " + last);
        path.add(arr.length - 1);
        return path;
    }
    
    public static void main(String[] args) {
        int[] input = {2,3,1,1,2,4,2,0,1,1};
        System.out.println(minJumpToEnd(input));
        System.out.println(findMinJumpPath_linearTime(input));
    }
}
