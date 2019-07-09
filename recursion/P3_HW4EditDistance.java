package ds_algo_study.recursion;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

/**
 * Q. Edit Distance. 
 * Given 2 strings, s1 and s2, determine the minimum number of steps
 * needed to transform s1 into s2.
 * You may insert, delete, or change characters in the string,
 * each of which is 1 step.
 * 
 * e.g. 
 * editDistance("abc", "cab") = {"abc" -> "cabc", "cabc" -> "cab"}
 * 
 * [Approach1] Recursion for selection.
 *      -> 4 choices: insert, delete, swap. OR skip
 *      -> build up the List<Step>
 *      
 * 1. Base case
 *     1) s1 equals s2
 *     2) s1.length >= s2.length  // delete
 *     3) s1.length <= s2.length  // insert
 *     
 * 2. Selection: 4 choices
 *     1) insert
 *     2) delete
 *     3) swap
 *     
 * 3. pick the best candidate.
 * 4. last choice: skip
 * 
 * 5. return the best answer.
 * 
 * [Time/Space] 
 * Time: O(4^n * n)
 *      height: n (length of longer string)
 *      branching factor: 4(insert, delete, swap, skip)
 * 
 * Space: O(n ^ 2)
 *      height: n, space per stack frame: n for new string.
 *      
 *      
 * [Approach2] DP
 * 
 * {@link leetcode_study.}
 * @see <a href = "https://leetcode.com/problems/edit-distance/"> LC#72 </a>
 * @author Sunny Park
 *
 */
public class P3_HW4EditDistance {
    private static class Step {
        String before;
        String after;
        
        Step(String before, String after) {
            this.before = before;
            this.after = after;
        }
        
        @Override
        public String toString() {
            return Joiner.on("->").join(before, after);
        }
        
    }
    public static List<Step> editDistance(String s1, String s2) {
        return editDistance(s1, s2, 0);
    }
    
    private static List<Step> editDistance(String s1, String s2, int i) {
        if (s1.equals(s2)) {
            return new ArrayList<Step>();
        }
        if (i >= s2.length()) return delete(s1, s2, i);
        if (i >= s1.length()) return insert(s1, s2, i);
        
        List<Step> inserted = insert(s1, s2, i);
        List<Step> deleted = delete(s1, s2, i);
        List<Step> swapped = swap(s1, s2, i);
        
        List<Step> toReturn = inserted;
        if (toReturn.size() > deleted.size()) toReturn = deleted;
        if (toReturn.size() > swapped.size()) toReturn = swapped;
        
        if (s1.charAt(i) == s2.charAt(i)) {
            List<Step> skipped = editDistance(s1, s2, i + 1);
            if (skipped.size() < toReturn.size()) {
                toReturn = skipped;
            }
        }
        return toReturn;
    }
    
    private static List<Step> insert(String s1, String s2, int i) {
        String inserted = s1.substring(0, i) + s2.charAt(i) + s1.substring(i);
        List<Step> toReturn = editDistance(inserted, s2, i + 1);
        toReturn.add(0, new Step(s1, inserted));
        return toReturn;
    }
    
    private static List<Step> delete(String s1, String s2, int i) {
        String deleted = s1.substring(0, i) + s1.substring(i + 1);
        List<Step> toReturn = editDistance(deleted, s2, i);
        toReturn.add(0, new Step(s1, deleted));
        return toReturn;
    }
    private static List<Step> swap(String s1, String s2, int i) {
        String swapped = s1.substring(0, i) + s2.charAt(i) + s1.substring(i + 1);
        List<Step> toReturn = editDistance(swapped, s2, i + 1);
        toReturn.add(0, new Step(s1, swapped));
        return toReturn;
    }
    
    public static void main(String[] args) {
        String s1 = "aba";
        String s2 = "cab";
        
        System.out.println(editDistance(s1, s2));
    }
}
