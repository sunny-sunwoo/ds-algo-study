package ds_algo_study.dynamicProgramming;

/**
 * Stock selling profit maximizer
 * 
 * Q1) one-time
 *     profit = curr - minSoFar
 *     => keep min from the beginning
 * 
 * Q2) two-time
 *     get second transaction by checking the profit backward.
 *     1. build an array with one-time profit for each day
 *     2. keep max from the end.
 *     3. profit = Math.max(profit, max - curr + profit[until prev day])
 *        => which means, max profit at curr point + max profit up to the prev day.
 *     
 * @author Sunny Park
 *
 */
public class Q17_StockSellingProfitMaximizer {
    public static int getMaxProfit_onetime(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
    
    public static int getMaxProfit_twotime(int[] prices) {
        int[] profits = new int[prices.length];
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
            profits[i] = profit;
        }
        
        int max = prices[prices.length - 1];
        for (int j = prices.length - 1; j > 0; j--) {
            max = Math.max(max, prices[j]);
            profit = Math.max(profits[j], max - prices[j] + profits[j - 1]);
        }
        
        return profit;
    }
    
}
