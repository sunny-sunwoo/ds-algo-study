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
    
}
