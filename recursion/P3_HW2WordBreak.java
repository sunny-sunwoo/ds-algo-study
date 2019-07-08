package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;

/**
 * Given a string s, where all spaces have been removed 
 * and a dictionary dict that includes a list of valid words,
 * find all different ways that you can break s into a string of valid words.
 * 
 * [Approach1] Brute force
 * 1. find all combinations of splits
 * 2. validate
 * 
 * [Approach2] Backtracking
 * 1. base case: when remainder length == 0 -> add to result
 * 2. logic: apple | pine
 *    1) try splitting from the start
 *    2) if the substring is contained in the dict
 *       -> add to tmp list
 *       -> recurse on the next call (with updated remainder)
 *       -> remove from the tmp list(revert to see different combination including the curr)
 * 
 * @author Sunny Park
 *
 */
public class P3_HW2WordBreak {
    public static List<String> wordBreak(String s, Set<String> dict) {
        List<String> result = new ArrayList<>();
        wordBreak(result, new ArrayList<>(), s, dict);
        return result;
    }
    
    private static void wordBreak(List<String> result, List<String> tmp, String remainder, Set<String> dict) {
        if (remainder.length() == 0) {
            result.add(buildResult(tmp));
            return;
        }
        for (int i = 1; i <= remainder.length(); i++) {
            String sub = remainder.substring(0,  i);
            if (dict.contains(sub)) {
                tmp.add(sub);
                wordBreak(result, tmp, remainder.substring(i, remainder.length()), dict);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
    
    private static String buildResult(List<String> tmp) {
//        StringBuilder sb = new StringBuilder();
//        for (String s : tmp) {
//            sb.append(s).append(" ");
//        }
//        return sb.deleteCharAt(sb.length() - 1).toString();
        return Joiner.on(" ").join(tmp);
    }
    
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.addAll(Arrays.asList("pine", "apple", "pineapple"));
        String s = "applepineapple";
        System.out.println(wordBreak(s, dict));
    }
}
