package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Q. Generate Pair
 * [1,4,3,2] 
 * -> (1,4) (3,2) // (1,3) (4,2) // (1,2) (4,3)
 * 
 * arr length    = 2   4   6   8    10
 * combination # = 1   3   15  105  945
 * 
 * combination number = (n - 1) * combination[n-1]
 * 
 * @see <a href="https://stackoverflow.com/questions/21761749/all-combinations-of-pairs-within-one-set"> </a>
 * @author Sunny Park
 *
 */
public class GeneratePairs {
    /**
     * 
     * @param set
     * @param currentResults
     * @param results
     */
    private static void compute(Set<Integer> set, List<List<Integer>> currentResults,
                                List<List<List<Integer>>> results)
    {
        if (set.size() < 2)
        {
            results.add(new ArrayList<List<Integer>>(currentResults));
            return;
        }
        
        List<Integer> list = new ArrayList<Integer>(set);
        Integer first = list.remove(0);
        for (int i=0; i<list.size(); i++) {
            Integer second = list.get(i);
            Set<Integer> nextSet = new HashSet<Integer>(list);
            nextSet.remove(second);

            List<Integer> pair = Arrays.asList(first, second);
            currentResults.add(pair);
            compute(nextSet, currentResults, results);
            currentResults.remove(pair);
        }
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8));

        ArrayList<List<List<Integer>>> results = 
                new ArrayList<List<List<Integer>>>();
        compute(set, new ArrayList<List<Integer>>(), results);
        for (List<List<Integer>> result : results)
        {
            System.out.println(result);
            
        }
        System.out.println(results.size());
    }

}
