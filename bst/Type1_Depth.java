package ds_algo_study.bst;

/**
 * Type1. Dealing with the depth.
 * @author Sunny Park
 */
public class Type1_Depth {
    // 1. get depth - Tree
    public static int getDepth(TreeNode n) {
        if (n == null) return 0;
        return Integer.max(getDepth(n.left), getDepth(n.right)) + 1;
    }
    
    // 2. get depth - Trie
    private static class Trie {
        int value;
        Trie[] children;
    }
    
    public static int getDepth_trie(Trie trie) {
        if (trie == null) return 0;
        int maxDepth = 0;
        for (Trie t : trie.children) {
            maxDepth = Math.max(maxDepth, getDepth_trie(t));
        }
        return maxDepth + 1;
    }
    
    // 3. getTotal of all nodes.
    public static int getTotal(TreeNode n) {
        if (n == null) return 0;
        return n.val + getTotal(n.left) + getTotal(n.right);
    }
    
    // 4. max path sum out of left or right path.
    public static int getMaxTotal(TreeNode n) {
        if (n == null) return 0;
        
        int left = getMaxTotal(n.left);
        int right = getMaxTotal(n.right);
        
        return Integer.max(left, right) + n.val;
    }
    
    // 5. validateBST
    public static boolean validateBST(TreeNode root) {
        return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static boolean validateBST(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.val < min || node.val > max) return false;
        return validateBST(node.left, min, node.val) && validateBST(node.right, node.val, max);
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(getDepth(TreeNode.generate()));
    }
}
