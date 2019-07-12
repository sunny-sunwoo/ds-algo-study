package ds_algo_study.bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;

/**
 * Q. Reversed-depth traversal
 * [Approach] Recursion
 * 1. base case: reaches to the end. -> rt 0
 * 2. calculate the depth using 2 recursive calls to the left, right children.
 * 3. fill out the hashmap using getOrDefault.
 * 
 * @author Sunny Park
 *
 */
public class Q02_ReverseDepthTraversal {
    public static List<List<Integer>> reversedTraversal(TreeNode root) {
        Map<Integer, List<Integer>> cache = new HashMap<>();
        traverse(cache, root);
        return cache.values().stream().collect(Collectors.toList());
    }
    
    private static int traverse(Map<Integer, List<Integer>> cache, TreeNode node) {
        if (node == null) return 0;
        
        int left = traverse(cache, node.left);
        int right = traverse(cache, node.right);
        int depth = Math.max(left, right) + 1;
        
        List<Integer> currList = cache.getOrDefault(depth, new ArrayList<>());
        currList.add(node.val);
        cache.put(depth, currList);
        return depth;
    }
    
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode();
        TreeNode n2 = new TreeNode();
        TreeNode n3 = new TreeNode();
        TreeNode n4 = new TreeNode();
        TreeNode n5 = new TreeNode();
        TreeNode n6 = new TreeNode();
        
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.left = n6;
        
        // expected: [6, 5, 3] -> [4] -> [2] -> [1]
        System.out.print(Joiner.on(" -> ").join(reversedTraversal(n1)));
    }
}
