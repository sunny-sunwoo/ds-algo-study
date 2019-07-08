package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;


/**
 * HW01. Implement the 0-1 Knapsack problem by building up the result 
 * rather than using a passed variable.
 * 
 * 1. base case: i reaches the end of the Item[] -> return empty result.
 * 2. 2 possible options: exclude or include.
 *      -> compare the value and return the greater value result.
 *      
 * [Time/Space Analysis]
 * Time: O(2^n)
 *       branching factor: 2, height: n(item number)
 * Space: O(n ^ 2)
 *        height: n, space per stack frame: n(Result.result)
 * 
 * 
 * @author Sunny Park
 *
 */
public class P3_HW1KnapsackBuildup {
    public static List<Item> knapsack(Item[] items, int totalWeight) {
        Result result = knapsack_buildup(items, totalWeight, 0);
        return result.result;
    }
    
    private static Result knapsack_buildup(Item[] items, int totalWeight, int i) {
        if (i == items.length) {
            return new Result();
        }
        
        Result exclude = knapsack_buildup(items, totalWeight, i + 1);
        if (totalWeight - items[i].weight >= 0) {
            Result include = knapsack_buildup(items, totalWeight - items[i].weight, i + 1);
            if (include.value + items[i].value > exclude.value) {
                include.result.add(items[i]);
                include.value += items[i].value;
                return include;
            }
        }
        return exclude;
    }
    
    private static class Result {
        List<Item> result;
        int value;
        public Result() {
            this.result = new ArrayList<Item>();
            this.value = 0;
        }
        
    }
    private static class Item {
        private final int weight;
        private final int value;
        private final int id;
        private static int number;
        
        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.id = number;
            number++;
        }
        
        @Override
        public String toString() {
            return "Item" + String.valueOf(id) + "(w" + weight + "-v" + value + ")";
        }
    }
    
    public static void main(String[] args) {
//        Item[] input = new Item[] {new Item(5, 2), new Item(3, 7), new Item(2, 5), new Item(6, 3)};
        Item[] input = new Item[] {new Item(10, 7), new Item(3, 9), new Item(6, 5), new Item(8, 2)};
        
        System.out.println(knapsack(input, 10));
    }
}
