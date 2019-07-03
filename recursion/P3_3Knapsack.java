package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Q. Given a list of items with values and weights, as well as a max weight,
 * find the max value you can generate from items,
 * where the sum of the weights is less than or equal to the max.
 * 
 * [Approach1] Recursion (4 iterations)
 * 
 * Iter-1) Brute force
 * 1. generate all combinations.
 * 2. filter out by checking the weight constraint. weight should be within the max weight.
 * 3. find the max value set among the candidates.
 * 
 * Iter-2) Optimize - based on constraint.
 * check the total weight exceeds the constraint. 
 * -> return to keep the valid weight lists only.
 * 
 * Iter-3) Optimize - keep only one list.
 * Since the result is 1 set anyways, 
 * keep the max value list only.
 * 
 * Iter-4) Optimize - Result object to hold the value.
 * To avoid recomputing the values every time, hold the value.
 * 
 * [Approach2] Dynamic Programming
 * 
 * @author Sunny Park
 *
 */

public class P3_3Knapsack {
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
    
    /**
     * Solution1) Brute force - find all combinations! 
     * @param input
     * @param maxWeight
     * @return
     */
    public static List<Item> knapsack_bruteforce(List<Item> input, int maxWeight) {
        List<List<Item>> result = new ArrayList<>();
        allCombination(result, new ArrayList<>(), input, 0);
        List<List<Item>> filtered = filterWeight(result, maxWeight);
        return findMaxValue(filtered);
    }
    
    private static void allCombination(List<List<Item>> result, List<Item> tmp, List<Item> input, int ptr) {
        if (ptr == input.size()) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        
        tmp.add(input.get(ptr));
        allCombination(result, tmp, input, ptr + 1);
        tmp.remove(tmp.size() - 1);
        allCombination(result, tmp, input, ptr + 1);
    }
    
    private static List<List<Item>> filterWeight(List<List<Item>> result, int maxWeight) {
        List<List<Item>> filtered = new ArrayList<>();
        
        for (List<Item> list : result) {
            int weight = 0;
            for (Item item : list) {
                weight += item.weight;
            }
            
            if (weight <= maxWeight) {
                filtered.add(list);
            }
        }
        return filtered;
    }
    
    private static List<Item> findMaxValue(List<List<Item>> filtered) {
        List<Item> toReturn = new ArrayList<>();
        int maxValue = Integer.MIN_VALUE;
        for (List<Item> list : filtered) {
            int currValue = 0;
            for (Item item : list) {
                currValue += item.value;
            }
            
            if (currValue > maxValue) {
                toReturn = list;
                maxValue = currValue;
            }
        }
        return toReturn;
    }
    
    /**
     * Solution2) 1st Optimization - no need to keep all combinations over the weight limit. 
     * check weight while building up the combination.
     * @param args
     */
    
    public static List<Item> knapsack_optimize1(List<Item> input, int maxWeight) {
        List<List<Item>> result = new ArrayList<>();
        validCombination(result, new ArrayList<>(), input, 0, maxWeight);
        return findMaxValue(result);
    }
    
    private static void validCombination(List<List<Item>> result, List<Item> tmp, List<Item> input, int ptr, int maxWeight) {
        if (!isValidWeight(tmp, maxWeight)) return;
        if (ptr == input.size()) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        
        tmp.add(input.get(ptr));
        validCombination(result, tmp, input, ptr + 1, maxWeight);
        tmp.remove(tmp.size() - 1);
        validCombination(result, tmp, input, ptr + 1, maxWeight);
    }
    
    private static boolean isValidWeight(List<Item> tmp, int maxWeight) {
        return tmp.stream().mapToInt(i -> i.weight).sum() < 10;
    }
    
    /**
     * Solution3) 2nd Optimization - no need to save all combinations.
     * bc/ only 1 set with the max value is required for the final return.
     * @param input
     * @param maxWeight
     * @return
     */
    public static List<Item> knapsack_optimize2(List<Item> input, int maxWeight) {
        List<List<Item>> result= new ArrayList<>();
        result.add(new ArrayList<>());
        findOneCombination(result, new ArrayList<>(), input, 0, maxWeight);
        return result.get(0);
    }
    
    private static void findOneCombination(List<List<Item>> result, List<Item> tmp, List<Item> input, 
            int ptr, int maxWeight) {
        
        if (!isValidWeight(tmp, maxWeight)) return;
        if (ptr == input.size()) {
            // compare currValue with the max value.
            if (getTotalValue(tmp) > getTotalValue(result.get(0))) {
                result.set(0, new ArrayList<>(tmp));
            } 
            return;
        }
        
        tmp.add(input.get(ptr));
        findOneCombination(result, tmp, input, ptr + 1, maxWeight);
        tmp.remove(tmp.size() - 1);
        findOneCombination(result, tmp, input, ptr + 1, maxWeight);
    }
    
    private static int getTotalValue(List<Item> tmp) {
        return tmp.stream().mapToInt(i -> i.value).sum();
    }
    
    /**
     * Solution4) 3rd Optimization - with Result object.
     * Use a separate result ds to hold the value all the time
     * rather than recomputing the value all the time.
     * 
     * Important Notes
     * 1. New "object" is always required for the return!! 
     * 2. use "parameter" to keep current status!! 
     * 
     * @param input
     * @param maxWeight
     * @return
     */
    public static List<Item> knapsack_optimize3(List<Item> input, int maxWeight) {
        Result ret = new Result(new ArrayList<>(), 0);
        findWithResult(ret, new ArrayList<>(), input, 0, 0, maxWeight, 0);
        return ret.result;
    }
    
    private static void findWithResult(Result ret, List<Item> tmp, 
            List<Item> input, int ptr, int currWeight, int maxWeight, int currValue) {
        if (currWeight > maxWeight) return;
        if (ptr == input.size()) {
            if (currValue > ret.value) {
                ret.result = new ArrayList<>(tmp);
                ret.value = currValue;
            } 
            return;
        }
        
        tmp.add(input.get(ptr));
        findWithResult(ret, tmp, input, ptr + 1, currWeight + input.get(ptr).weight, maxWeight, currValue + input.get(ptr).value);
        tmp.remove(tmp.size() - 1);
        findWithResult(ret, tmp, input, ptr + 1, currWeight, maxWeight, currValue);
    }
    
    private static class Result {
        List<Item> result;
        int value;
        
        Result(List<Item> result, int value) {
            this.result = result;
            this.value = value;
        }
    }
    
    
    public static void main(String[] args) {
        List<Item> input = new ArrayList<>();
        input.addAll(Arrays.asList(new Item(10, 7), new Item(3, 9), new Item(6, 5), new Item(8, 2)));
        System.out.println(knapsack_bruteforce(input, 10));
        System.out.println(knapsack_optimize1(input, 10));
        System.out.println(knapsack_optimize2(input, 10));
        System.out.println(knapsack_optimize3(input, 10));
    }
}
