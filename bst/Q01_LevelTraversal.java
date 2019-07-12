package ds_algo_study.bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;

/**
 * 
 * [Approach1] using Queue
 * - by checking the size of queue, bst can be divided by lv.
 * 
 * [Approach2] using HashMap
 * - using recursion, traverse to the down while building the hashmap<Integer, List<Integer>>
 * 
 * @author Sunny Park
 *
 */
public class Q01_LevelTraversal {
    public static List<List<Integer>> levelBST(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = q.poll();
                if (i == 0) {
                    result.add(new ArrayList<>());
                }
                result.get(result.size() - 1).add(top.val);
                if (top.left != null) q.offer(top.left);
                if (top.right != null) q.offer(top.right);
            }
        }
        return result;
    }
    
    public static List<List<Integer>> levelBST_cache(TreeNode root) {
        Map<Integer, List<Integer>> cache = new HashMap<>();
        levelBST(cache, root, 0);
        return cache.values().stream().collect(Collectors.toList());
    }
    
    private static void levelBST(Map<Integer, List<Integer>> cache, TreeNode node, int lv) {
        if (node == null) return;
        List<Integer> currList = cache.getOrDefault(lv, new ArrayList<>());
        currList.add(node.val);
        cache.put(lv, currList);
        levelBST(cache, node.left, lv + 1);
        levelBST(cache, node.right, lv + 1);
    }
    
    public static void main(String[] args) {
        TreeNode root = TreeNode.generate();
        System.out.println(Joiner.on(" -> ").join(levelBST(root)));
        System.out.println(Joiner.on(" -> ").join(levelBST_cache(root)));
    }
}
