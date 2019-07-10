package ds_algo_study.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Type2_Traverse {
    // get List from the node.
    public static List<Integer> toList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        populate(result, root);
        return result;
        
    }
    // inorder traversal -> generate ascending-order result.
    public static void populate(List<Integer> result, TreeNode node) {
        if (node == null) return;
        populate(result, node.left);
        result.add(node.val);
        populate(result, node.right);
    }
    
    // preorder: curr - left - right
    public static void populate_pre(List<Integer> result, TreeNode node) {
        if (node == null) return;
        result.add(node.val);
        populate_pre(result, node.left);
        populate_pre(result, node.right);
    }
    
    // postorder: left - right - curr
    public static void populate_post(List<Integer> result, TreeNode node) {
        if (node == null) return;
        populate_post(result, node.left);
        populate_post(result, node.right);
        result.add(node.val);
    }
    
    /**
     * Q. Find k numbers which is close to t.
     * 
     * [approach1] inorder
     * 1. inorder traverse to create ascending-order list.
     * 2. binary search.
     * 
     * [approach2] don't keep the elems more than k.
     * 1) use arraylist: O(nk), remove takes O(k) for shifting. 
     * 2) use linkedlist: O(n)
     * 
     * @param root
     * @param t
     * @param k
     * @return
     */
    public static List<Integer> findNumbers(TreeNode root, int t, int k) {
        List<Integer> result = new ArrayList<>();
        findNumbers(root, t, k, result);
        
        LinkedList<Integer> result2 = new LinkedList<>();
        find(root, t, k, result2);
        return result2;
    }
    
    private static void findNumbers(TreeNode node, int t, int k, List<Integer> result) {
        if (node == null) return;
        findNumbers(node.left, t, k, result);
        
        result.add(node.val);
        if (result.size() > k) {
            int currDiff = Math.abs(t - result.get(0));
            int candidDiff = Math.abs(t - node.val);
            if (currDiff > candidDiff) {
                result.remove(0);
            } else {
                result.remove(result.size() - 1);
                return;
            }
        }
        findNumbers(node.right, t, k, result);
    }
    
    // Optimize with LL
    private static void find(TreeNode node, int t, int k, LinkedList<Integer> result) {
        if (node == null) return;
        find(node.left, t, k, result);
        
        // deal with the curr node.
        result.add(node.val);
        if (result.size() > k) {
            int currDiff = Math.abs(t - result.getFirst());
            int candidDiff = Math.abs(t - result.getLast());
            if (currDiff > candidDiff) {
                result.removeFirst();
            } else {
                result.removeLast();
                return; // recursive PRUNNING, no more calling is necessary.
            }
        }
        find(node.right, t, k, result);
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(toList(TreeNode.generate()));
        System.out.println(findNumbers(TreeNode.generate(), 4, 5));
    }
}
